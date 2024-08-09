package org.s4jk.jvm.json.core

import org.s4jk.jvm.json.IllegalJsonStringParsingException
import org.s4jk.jvm.json.string.JsonStringManager
import org.s4jk.jvm.json.string.parsers.JsonStringParser
import java.util.Spliterator
import java.util.function.Consumer
import kotlin.jvm.Throws

/**
 * The [JsonList] class represents a mutable list of JSON values.
 * It provides various methods to manipulate the list, such as adding,
 * removing, and searching for elements. The class implements the
 * [MutableIterable]<[JsonValue]> interface.
 *
 * @property list The underlying [ArrayList] that stores [JsonValue] objects.
 * @property size The number of elements in the list.
 */
class JsonList private constructor(private val list: ArrayList<JsonValue>): MutableIterable<JsonValue> {

    val size get() = this.list.size

    /**
     * Checks if the list is empty.
     *
     * @return true if the list is empty, false otherwise.
     */
    fun isEmpty(): Boolean {
        return this.list.isEmpty()
    }

    /**
     * Checks if the list contains the specified element.
     *
     * @param element The element to check for.
     * @return true if the element is found.
     */
    fun contains(element: Any?): Boolean {
        return this.list.contains(JsonValue.handle(element))
    }

    /**
     * Checks if the list contains all elements in the specified collection.
     *
     * @param elements The collection of elements to check for.
     * @return true if all elements are found.
     */
    fun containsAll(elements: Collection<Any?>): Boolean {
        return this.list.containsAll(elements.map { JsonValue.handle(it) })
    }

    /**
     * Retrieves the element at the specified index.
     *
     * @param index The index of the element to retrieve.
     * @return The [JsonValue] at the specified index.
     * @throws [IndexOutOfBoundsException] If the index is out of range.
     */
    operator fun get(index: Int): JsonValue {
        return this.list[index]
    }

    /**
     * Returns the index of the first occurrence of the specified element, or -1 if not found.
     *
     * @param element The element to search for.
     * @return The index of the element, or -1 if not found.
     */
    fun indexOf(element: Any?): Int {
        return this.list.indexOf(JsonValue.handle(element))
    }

    /**
     * Returns the index of the last occurrence of the specified element, or -1 if not found.
     *
     * @param element The element to search for.
     * @return The index of the element, or -1 if not found.
     */
    fun lastIndexOf(element: Any?): Int {
        return this.list.lastIndexOf(JsonValue.handle(element))
    }

    /**
     * Returns a list iterator over the elements in the list.
     *
     * @return A [ListIterator] of [JsonValue].
     */
    fun listIterator(): ListIterator<JsonValue> {
        return this.list.listIterator()
    }

    /**
     * Returns a list iterator over the elements in the list, starting at the specified index.
     *
     * @param index The index to start the iterator at.
     * @return A [ListIterator] of [JsonValue].
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    fun listIterator(index: Int): ListIterator<JsonValue> {
        return this.list.listIterator(index)
    }

    /**
     * Returns a sublist of the list between the specified indices.
     *
     * @param from The start index, inclusive.
     * @param to The end index, exclusive.
     * @return A new [JsonList] containing the specified range.
     * @throws IndexOutOfBoundsException If the indices are out of range.
     */
    fun sub(from: Int, to: Int): JsonList {
        return from(this.list.subList(from, to))
    }

    /**
     * Adds the specified element to the end of the list.
     *
     * @param element The element to add.
     * @return true if the list changed as a result.
     */
    fun add(element: Any?): Boolean {
        return this.list.add(JsonValue.handle(element))
    }

    /**
     * Inserts the specified element at the specified position in the list.
     *
     * @param index The index at which to insert the element.
     * @param element The element to insert.
     * @throws [IndexOutOfBoundsException] If the index is out of range.
     */
    fun add(index: Int, element: Any?) {
        return this.list.add(index, JsonValue.handle(element))
    }

    /**
     * Adds all elements from the specified collection to the end of the list.
     *
     * @param elements The collection of elements to add.
     * @return true if the list changed as a result.
     */
    fun addAll(elements: Collection<Any?>): Boolean {
        return this.list.addAll(elements.map { JsonValue.handle(it) })
    }

    /**
     * Inserts all elements from the specified collection at the specified position in the list.
     *
     * @param index The index at which to insert the first element.
     * @param elements The collection of elements to insert.
     * @return true if the list changed as a result.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    fun addAll(index: Int, elements: Collection<Any?>): Boolean {
        return this.list.addAll(index, elements.map { JsonValue.handle(it) })
    }

    /**
     * Removes the first occurrence of the specified element from the list, if present.
     *
     * @param element The element to remove.
     * @return true if the list contained the specified element.
     */
    fun remove(element: Any?): Boolean {
        return this.list.remove(JsonValue.handle(element))
    }

    /**
     * Removes the element at the specified position in the list.
     *
     * @param index The index of the element to remove.
     * @return The element that was removed.
     * @throws [IndexOutOfBoundsException] If the index is out of range.
     */
    fun removeAt(index: Int): JsonValue {
        return this.list.removeAt(index)
    }

    /**
     * Removes all elements in the list that are also contained in the specified collection.
     *
     * @param elements The collection of elements to remove.
     * @return true if the list changed as a result.
     */
    fun removeAll(elements: Collection<Any?>): Boolean {
        return this.list.removeAll(elements.map { JsonValue.handle(it) }.toSet())
    }

    /**
     * Retains only the elements in the list that are contained in the specified collection.
     *
     * @param elements The collection of elements to retain.
     * @return true if the list changed as a result.
     */
    fun retainAll(elements: Collection<Any?>): Boolean {
        return this.list.retainAll(elements.map { JsonValue.handle(it) }.toSet())
    }

    /**
     * Removes all elements from the list.
     */
    fun clear() {
        return this.list.clear()
    }

    /**
     * Returns a JSON-formatted string representation of the list.
     *
     * @return A JSON string representing the list.
     */
    override fun toString(): String {
        return JsonStringManager.jsonListToString(this, 0, 1)
    }

    /**
     * Returns a JSON-formatted string representation of the list with the specified indentation.
     *
     * @param indent The number of spaces to use for indentation.
     * @return A formatted JSON string representing the list.
     */
    fun toString(indent: Int): String {
        return JsonStringManager.jsonListToString(this, indent, 1)
    }

    /**
     * Performs the given action for each element of the [Iterable].
     *
     * @param action The action to perform on each element.
     */
    override fun forEach(action: Consumer<in JsonValue>?) {
        return this.list.forEach(action)
    }

    /**
     * Creates a [Spliterator] over the elements in the list.
     *
     * @return A [Spliterator] of [JsonValue].
     */
    override fun spliterator(): Spliterator<JsonValue> {
        return this.list.spliterator()
    }

    /**
     * Returns an iterator over the elements in the list.
     *
     * @return A [MutableIterator] of [JsonValue].
     */
    override fun iterator(): MutableIterator<JsonValue> {
        return this.list.iterator()
    }

    companion object Static {

        /**
         * Creates a new, empty [JsonList].
         *
         * @return A new instance of [JsonList].
         */
        @JvmStatic
        fun create(): JsonList {
            return JsonList(arrayListOf())
        }

        /**
         * Creates a [JsonList] from the specified array.
         *
         * @param from The array to convert.
         * @return A new [JsonList] containing the elements of the array.
         */
        @JvmStatic
        fun from(from: Array<*>): JsonList {
            return JsonList(from.map { JsonValue.handle(it) }.toCollection(ArrayList()))
        }

        /**
         * Creates a [JsonList] from the specified list.
         *
         * @param from The list to convert.
         * @return A new [JsonList] containing the elements of the list.
         */
        @JvmStatic
        fun from(from: List<*>): JsonList {
            return JsonList(from.map { JsonValue.handle(it) }.toCollection(ArrayList()))
        }

        /**
         * Creates a [JsonList] from the specified set.
         *
         * @param from The set to convert.
         * @return A new [JsonList] containing the elements of the set.
         */
        @JvmStatic
        fun from(from: Set<*>): JsonList {
            return JsonList(from.map { JsonValue.handle(it) }.toCollection(ArrayList()))
        }

        /**
         * Creates a new [JsonList] by copying the specified [JsonList].
         *
         * @param from The [JsonList] to copy.
         * @return A new [JsonList] containing the same elements.
         */
        @JvmStatic
        fun from(from: JsonList): JsonList {
            return JsonList(from.list)
        }

        /**
         * Parses a JSON-formatted string and creates a [JsonList] from it.
         *
         * @param source The JSON string to parse.
         * @return A new [JsonList] representing the JSON array.
         * @throws IllegalJsonStringParsingException If the string cannot be parsed as a JSON list.
         */
        @JvmStatic
        @Throws(IllegalJsonStringParsingException::class)
        fun from(source: String): JsonList {
            return JsonStringParser(source).parseList()
        }
    }
}