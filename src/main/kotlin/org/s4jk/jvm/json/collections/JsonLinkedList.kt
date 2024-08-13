package org.s4jk.jvm.json.collections

import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable
import org.s4jk.jvm.json.core.JsonList
import org.s4jk.jvm.json.core.JsonValue

/**
 * The [JsonLinkedList] interface represents a mutable list of JSON values.
 * It provides various methods to manipulate the list, such as adding,
 * removing, and searching for elements. The interface extends the
 * [MutableIterable]<[JsonValue]> interface.
 *
 * @property size The number of elements in the list.
 */
interface JsonLinkedList: MutableIterable<JsonValue> {

    @get:NotNull
    val size: Int

    /**
     * Checks if the list is empty.
     *
     * @return true if the list is empty.
     */
    @NotNull
    fun isEmpty(): Boolean

    /**
     * Checks if the list contains the specified element.
     *
     * @param element The element to check for.
     * @return true if the [element] is found in the list.
     */
    @NotNull
    fun contains(@Nullable element: Any?): Boolean

    /**
     * Checks if the list contains all elements in the specified collection.
     *
     * @param elements The collection of elements to check for.
     * @return true if all elements are found in the list.
     */
    @NotNull
    fun containsAll(@NotNull elements: Collection<Any?>): Boolean

    /**
     * Retrieves the element at the specified index.
     *
     * @param index The index of the element to retrieve.
     * @return The [JsonValue] at the specified index.
     * @throws [IndexOutOfBoundsException] if the index is out of range.
     */
    @NotNull
    operator fun get(@NotNull index: Int): JsonValue

    /**
     * Adds an element to the list.
     *
     * @param element The element to be added.
     * @return true if the element was added successfully.
     */
    @NotNull
    fun add(@Nullable element: Any?): Boolean

    /**
     * Adds an element at the specified index in the list.
     *
     * @param index The index at which the element should be added.
     * @param element The element to be added.
     * @throws [IndexOutOfBoundsException] if the index is out of range.
     */
    @NotNull
    fun add(@NotNull index: Int, @Nullable element: Any?)

    /**
     * Returns the index of the first occurrence of the specified element.
     *
     * @param element The element to search for.
     * @return The index of the first occurrence of the element, or -1 if not found.
     */
    @NotNull
    fun indexOf(@Nullable element: Any?): Int

    /**
     * Returns the index of the last occurrence of the specified element.
     *
     * @param element The element to search for.
     * @return The index of the last occurrence of the element, or -1 if not found.
     */
    @NotNull
    fun lastIndexOf(@Nullable element: Any?): Int

    /**
     * Returns a list iterator over the elements in this list.
     *
     * @return A [ListIterator]<[JsonValue]> over the elements in this list.
     */
    @NotNull
    fun listIterator(): ListIterator<JsonValue>

    /**
     * Returns a list iterator over the elements in this list, starting at the specified index.
     *
     * @param index The index to start the iterator from.
     * @return A [ListIterator]<[JsonValue]> starting at the specified index.
     */
    @NotNull
    fun listIterator(@NotNull index: Int): ListIterator<JsonValue>

    /**
     * Returns a sublist of elements between the specified indices.
     *
     * @param from The start index of the sublist, inclusive.
     * @param to The end index of the sublist, exclusive.
     * @return A [JsonList] representing the sublist.
     * @throws [IndexOutOfBoundsException] if the indices are out of range.
     */
    @NotNull
    @Throws(IndexOutOfBoundsException::class)
    fun sub(@NotNull from: Int, @NotNull to: Int): JsonList

    /**
     * Adds all elements from the specified collection to the list.
     *
     * @param elements The collection of elements to add.
     * @return true if all elements were added successfully.
     */
    @NotNull
    fun addAll(@NotNull elements: Collection<Any?>): Boolean

    /**
     * Adds all elements from the specified [JsonList] to the list.
     *
     * @param elements The [JsonList] of elements to add.
     * @return true if all elements were added successfully.
     */
    @NotNull
    fun addAll(@NotNull elements: JsonList): Boolean

    /**
     * Adds all elements from the specified collection to the list, starting at the specified index.
     *
     * @param index The index at which to start adding elements.
     * @param elements The collection of elements to add.
     * @return true if all elements were added successfully.
     * @throws [IndexOutOfBoundsException] if the index is out of range.
     */
    @NotNull
    @Throws(IndexOutOfBoundsException::class)
    fun addAll(@NotNull index: Int, @NotNull elements: Collection<Any?>): Boolean

    /**
     * Adds all elements from the specified [JsonList] to the list, starting at the specified index.
     *
     * @param index The index at which to start adding elements.
     * @param elements The [JsonList] of elements to add.
     * @return true if all elements were added successfully.
     * @throws [IndexOutOfBoundsException] if the index is out of range.
     */
    @NotNull
    @Throws(IndexOutOfBoundsException::class)
    fun addAll(@NotNull index: Int, @NotNull elements: JsonList): Boolean

    /**
     * Removes the first occurrence of the specified element from the list.
     *
     * @param element The element to remove.
     * @return true if the element was removed successfully.
     */
    @NotNull
    fun remove(@Nullable element: Any?): Boolean

    /**
     * Removes the element at the specified index in the list.
     *
     * @param index The index of the element to remove.
     * @return The [JsonValue] that was removed.
     * @throws [IndexOutOfBoundsException] if the index is out of range.
     */
    @NotNull
    @Throws(IndexOutOfBoundsException::class)
    fun removeAt(@NotNull index: Int): JsonValue

    /**
     * Removes all elements in the specified collection from the list.
     *
     * @param elements The collection of elements to remove.
     * @return true if all specified elements were removed successfully.
     */
    @NotNull
    fun removeAll(@NotNull elements: Collection<Any?>): Boolean

    /**
     * Retains only the elements in this list that are contained in the specified collection.
     *
     * @param elements The collection of elements to retain.
     * @return true if any elements were removed from the list.
     */
    @NotNull
    fun retainAll(@NotNull elements: Collection<Any?>): Boolean

    /**
     * Clears all elements from the list.
     */
    fun clear()

    /**
     * Returns a JSON-formatted string representation of the list.
     *
     * @return A JSON string representing the list.
     */
    @NotNull
    override fun toString(): String

    /**
     * Returns a JSON-formatted string representation of the list with the specified indentation.
     *
     * @param indent The number of spaces to use for indentation.
     * @return A formatted JSON string representing the list.
     */
    @NotNull
    fun toString(@NotNull indent: Int): String
}