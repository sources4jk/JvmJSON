package org.s4jk.jvm.json.core

import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable

/**
 * Interface representing a JSON-like list with customizable functionality.
 * Provides methods for interacting with lists that consist of a list of elements.
 *
 * @property size The number of elements currently in the list.
 */
interface IJL: MutableIterable<JsonValue> {

    @get:NotNull
    val size: Int

    /**
     * Checks if the list is empty.
     *
     * @return `true` if the list contains no elements; `false` otherwise.
     */
    @NotNull
    fun isEmpty(): Boolean

    /**
     * Checks if the list contains the specified element.
     *
     * @param element The element to check for.
     * @return `true` if the list contains the element; `false` otherwise.
     */
    @NotNull
    fun contains(@Nullable element: Any?): Boolean

    /**
     * Checks if the list contains all elements in the specified collection.
     *
     * @param elements The collection of elements to check for.
     * @return `true` if the list contains all elements; `false` otherwise.
     */
    @NotNull
    fun containsAll(elements: Collection<Any?>): Boolean

    /**
     * Retrieves the element at the specified index in the list.
     *
     * @param index The index of the element to retrieve.
     * @return The element at the specified index, or null if the index is out of bounds.
     */
    @NotNull
    operator fun get(@NotNull index: Int): JsonValue

    /**
     * Finds the index of the first occurrence of the specified element.
     *
     * @param element The element to find.
     * @return The index of the element, or -1 if the element is not found.
     */
    @NotNull
    fun indexOf(@Nullable element: Any?): Int

    /**
     * Finds the index of the last occurrence of the specified element.
     *
     * @param element The element to find.
     * @return The index of the element, or -1 if the element is not found.
     */
    @NotNull
    fun lastIndexOf(@Nullable element: Any?): Int

    /**
     * Returns a list iterator over the elements in the list.
     *
     * @return A list iterator over the elements in the list.
     */
    @NotNull
    fun listIterator(): ListIterator<JsonValue>

    /**
     * Returns a list iterator over the elements in the list, starting at the specified index.
     *
     * @param index The index to start the iterator at.
     * @return A list iterator over the elements in the list, starting at the specified index.
     */
    @NotNull
    fun listIterator(index: Int): ListIterator<JsonValue>

    /**
     * Returns a view of the portion of the list between the specified `fromIndex`, inclusive, and `toIndex`, exclusive.
     *
     * @param from The starting index, inclusive.
     * @param to The ending index, exclusive.
     * @return A view of the specified range within the list.
     */
    @NotNull
    fun sub(from: Int, to: Int): IJL

    /**
     * Adds an element to the end of the list.
     *
     * @param element The element to add.
     * @return `true` if the element was added successfully.
     */
    @NotNull
    fun add(@Nullable element: Any?): Boolean

    /**
     * Adds an element at the specified index, shifting subsequent elements.
     *
     * @param index The index at which the element should be inserted.
     * @param element The element to add.
     * @return `true` if the element was added successfully.
     */
    @NotNull
    fun add(@NotNull index: Int, @Nullable element: Any?)

    /**
     * Adds all elements from the specified collection to the end of the list.
     *
     * @param elements The collection of elements to add.
     * @return `true` if the elements were added successfully.
     */
    @NotNull
    fun addAll(elements: Collection<Any?>): Boolean

    /**
     * Adds all elements from the specified collection at the specified index, shifting subsequent elements.
     *
     * @param index The index at which to insert the first element from the specified collection.
     * @param elements The collection of elements to add.
     * @return `true` if the elements were added successfully.
     */
    @NotNull
    fun addAll(index: Int, elements: Collection<Any?>): Boolean

    /**
     * Removes the first occurrence of the specified element from the list.
     *
     * @param element The element to remove.
     * @return `true` if the element was removed successfully; `false` otherwise.
     */
    @NotNull
    fun remove(@Nullable element: Any?): Boolean

    /**
     * Removes the element at the specified index.
     *
     * @param index The index of the element to remove.
     * @return The removed element, or null if the index is out of bounds.
     */
    @Nullable
    fun removeAt(@NotNull index: Int): JsonValue

    /**
     * Removes all elements in the specified collection from the list.
     *
     * @param elements The collection of elements to remove.
     * @return `true` if all elements were removed successfully; `false` otherwise.
     */
    @NotNull
    fun removeAll(elements: Collection<Any?>): Boolean

    /**
     * Retains only the elements in the list that are contained in the specified collection.
     *
     * @param elements The collection of elements to retain.
     * @return `true` if the list was modified as a result of this operation.
     */
    @NotNull
    fun retainAll(elements: Collection<Any?>): Boolean

    /**
     * Removes all elements from the list.
     */
    fun clear()

    /**
     * Converts the list to a compact string representation without indentation.
     *
     * @return A string representation of the list, with elements separated by commas and enclosed in brackets.
     */
    @NotNull
    override fun toString(): String

    /**
     * Converts the list to a string with a specified level of indentation.
     *
     * @param indent The number of spaces to use for indentation.
     * @return A string representation of the list with elements formatted according to the specified indentation level.
     */
    @NotNull
    fun toString(@NotNull indent: Int): String
}
