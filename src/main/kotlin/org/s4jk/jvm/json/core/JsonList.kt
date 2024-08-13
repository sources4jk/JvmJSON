package org.s4jk.jvm.json.core

import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable
import org.s4jk.jvm.json.string.JsonStringManager
import org.s4jk.jvm.json.string.parsers.Parser
import java.util.Spliterator
import java.util.function.Consumer

/**
 * Represents a mutable JSON array. Provides methods to manipulate the list of values within the JSON structure.
 * Implements [MutableIterable] to allow iteration over the elements in the list.
 *
 * @property size The number of elements in this [JsonList].
 */
class JsonList(): MutableIterable<JsonValue> {
    private val _values: ArrayList<JsonValue> = ArrayList()

    /**
     * Creates a [JsonList] by recognizing and adding elements from an array.
     *
     * @param from An array of elements to be recognized as [JsonValue] and added to the list.
     */
    constructor(@NotNull from: Array<*>): this() {
        this._values.addAll(from.map { JsonValue.recognize(it) })
    }

    /**
     * Creates a [JsonList] by recognizing and adding elements from a collection.
     *
     * @param from A collection of elements to be recognized as [JsonValue] and added to the list.
     */
    constructor(@NotNull from: Collection<*>): this() {
        this._values.addAll(from.map { JsonValue.recognize(it) })
    }

    /**
     * Creates a [JsonList] by copying elements from another [JsonList].
     *
     * @param from A [JsonList] from which to copy elements.
     */
    constructor(@NotNull from: JsonList): this() {
        this._values.addAll(from._values)
    }

    /**
     * Creates a [JsonList] by parsing a JSON-formatted string.
     *
     * @param from A JSON string to be parsed into a [JsonList].
     */
    constructor(@NotNull from: String): this() {
        JsonList(Parser(from).parseList())
    }

    /**
     * Returns the number of elements in this [JsonList].
     */
    @get:NotNull
    val size: Int get() = this._values.size

    /**
     * Checks if this [JsonList] is empty.
     *
     * @return true if the [JsonList] contains no elements, false otherwise.
     */
    @NotNull
    fun isEmpty(): Boolean {
        return this._values.isEmpty()
    }

    /**
     * Checks if the specified element exists in this [JsonList].
     *
     * @param element The element to check for.
     * @return true if the element is present, false otherwise.
     */
    @NotNull
    fun contains(@Nullable element: Any?): Boolean {
        return this._values.contains(JsonValue.recognize(element))
    }

    /**
     * Checks if all the specified elements exist in this [JsonList].
     *
     * @param elements A collection of elements to check for.
     * @return true if all elements are present, false otherwise.
     */
    @NotNull
    fun containsAll(@NotNull elements: Collection<Any?>): Boolean {
        return this._values.containsAll(elements.map { JsonValue.recognize(it) })
    }

    /**
     * Retrieves the element at the specified index.
     *
     * @param index The index of the element to retrieve.
     * @return The element at the specified index.
     */
    @NotNull
    fun get(@NotNull index: Int): JsonValue {
        return this._values[index]
    }

    /**
     * Adds the specified element to the end of this [JsonList].
     *
     * @param element The element to add.
     * @return true if the element was added successfully, false otherwise.
     */
    @NotNull
    fun add(@Nullable element: Any?): Boolean {
        return this._values.add(JsonValue.recognize(element))
    }

    /**
     * Inserts the specified element at the specified position in this [JsonList].
     *
     * @param index The index at which to insert the element.
     * @param element The element to insert.
     */
    fun add(@NotNull index: Int, @Nullable element: Any?) {
        return this._values.add(index, JsonValue.recognize(element))
    }

    /**
     * Returns the index of the first occurrence of the specified element in this [JsonList].
     *
     * @param element The element to search for.
     * @return The index of the first occurrence of the element, or -1 if not found.
     */
    @NotNull
    fun indexOf(@Nullable element: Any?): Int {
        return this._values.indexOf(JsonValue.recognize(element))
    }

    /**
     * Returns the index of the last occurrence of the specified element in this [JsonList].
     *
     * @param element The element to search for.
     * @return The index of the last occurrence of the element, or -1 if not found.
     */
    @NotNull
    fun lastIndexOf(@Nullable element: Any?): Int {
        return this._values.lastIndexOf(JsonValue.recognize(element))
    }

    /**
     * Returns a list iterator over the elements in this [JsonList].
     *
     * @return A list iterator over the elements in this list.
     */
    @NotNull
    fun listIterator(): ListIterator<JsonValue> {
        return this._values.listIterator()
    }

    /**
     * Returns a list iterator over the elements in this [JsonList], starting at the specified position.
     *
     * @param index The index to start the iterator from.
     * @return A list iterator over the elements in this list, starting at the specified position.
     */
    @NotNull
    fun listIterator(@NotNull index: Int): ListIterator<JsonValue> {
        return this._values.listIterator(index)
    }

    /**
     * Returns a sublist of this [JsonList] from the specified start index (inclusive) to the specified end index (exclusive).
     *
     * @param from The starting index, inclusive.
     * @param to The ending index, exclusive.
     * @return A new [JsonList] representing the specified range of the list.
     */
    @NotNull
    fun sub(@NotNull from: Int, @NotNull to: Int): JsonList {
        return JsonList(_values.subList(from, to))
    }

    /**
     * Adds all the elements in the specified collection to this [JsonList].
     *
     * @param elements A collection of elements to be added.
     * @return true if the list changed as a result of the operation, false otherwise.
     */
    @NotNull
    fun addAll(@NotNull elements: Collection<Any?>): Boolean {
        return this._values.addAll(elements.map { JsonValue.recognize(it) })
    }

    /**
     * Adds all the elements in the specified [JsonList] to this list.
     *
     * @param elements A [JsonList] of elements to be added.
     * @return true if the list changed as a result of the operation, false otherwise.
     */
    @NotNull
    fun addAll(@NotNull elements: JsonList): Boolean {
        return this._values.addAll(elements._values)
    }

    /**
     * Inserts all the elements in the specified collection into this [JsonList], starting at the specified position.
     *
     * @param index The index at which to start inserting the elements.
     * @param elements A collection of elements to be inserted.
     * @return true if the list changed as a result of the operation, false otherwise.
     */
    @NotNull
    fun addAll(@NotNull index: Int, @NotNull elements: Collection<Any?>): Boolean {
        return this._values.addAll(index, elements.map { JsonValue.recognize(it) })
    }

    /**
     * Inserts all the elements in the specified [JsonList] into this list, starting at the specified position.
     *
     * @param index The index at which to start inserting the elements.
     * @param elements A [JsonList] of elements to be inserted.
     * @return true if the list changed as a result of the operation, false otherwise.
     */
    @NotNull
    fun addAll(@NotNull index: Int, @NotNull elements: JsonList): Boolean {
        return this._values.addAll(index, elements._values)
    }

    /**
     * Removes the first occurrence of the specified element from this list, if it is present.
     *
     * @param element The element to remove.
     * @return true if the list contained the specified element, false otherwise.
     */
    @NotNull
    fun remove(@Nullable element: Any?): Boolean {
        return this._values.remove(JsonValue.recognize(element))
    }

    /**
     * Removes the element at the specified position in this list.
     *
     * @param index The index of the element to remove.
     * @return The element that was removed from the list.
     */
    @NotNull
    fun removeAt(@NotNull index: Int): JsonValue {
        return this._values.removeAt(index)
    }

    /**
     * Removes from this list all the elements that are contained in the specified collection.
     *
     * @param elements A collection of elements to be removed.
     * @return true if the list changed as a result of the operation, false otherwise.
     */
    @NotNull
    fun removeAll(@NotNull elements: Collection<Any?>): Boolean {
        return this._values.removeAll(elements.map { JsonValue.recognize(it) }.toSet())
    }

    /**
     * Retains only the elements in this list that are contained in the specified collection.
     *
     * @param elements A collection of elements to retain.
     * @return true if the list changed as a result of the operation, false otherwise.
     */
    @NotNull
    fun retainAll(@NotNull elements: Collection<Any?>): Boolean {
        return this._values.retainAll(elements.map { JsonValue.recognize(it) }.toSet())
    }

    /**
     * Removes all elements from this [JsonList].
     */
    fun clear() {
        return this._values.clear()
    }

    /**
     * Returns a string representation of this [JsonList].
     *
     * @return A string representation of this [JsonList].
     */
    @NotNull
    override fun toString(): String {
        return JsonStringManager.jsonListToString(this, 0, 1)
    }

    /**
     * Returns a string representation of this [JsonList] with the specified indentation.
     *
     * @param indent The number of spaces to use for indentation.
     * @return A string representation of this [JsonList] with the specified indentation.
     */
    @NotNull
    fun toString(@NotNull indent: Int): String {
        return JsonStringManager.jsonListToString(this, indent, 1)
    }

    /**
     * Performs the given action for each element of the [JsonList] until all elements have been processed.
     *
     * @param action The action to be performed for each element.
     */
    override fun forEach(@NotNull action: Consumer<in JsonValue>?) {
        return this._values.forEach(action)
    }

    /**
     * Creates a [Spliterator] over the elements in this list.
     *
     * @return A [Spliterator] over the elements in this list.
     */
    @NotNull
    override fun spliterator(): Spliterator<JsonValue> {
        return this._values.spliterator()
    }

    /**
     * Returns an iterator over the elements in this list.
     *
     * @return An iterator over the elements in this list.
     */
    @NotNull
    override fun iterator(): MutableIterator<JsonValue> {
        return this._values.iterator()
    }
}
