package org.s4jk.jvm.json.objects

import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable

/**
 * Interface representing a JSON-like array with customizable functionality.
 * Provides methods for interacting with arrays that consist of a list of elements.
 *
 * @property size The number of elements currently in the array.
 */
interface IJA : MutableIterable<JsonValue> {

    @get:NotNull
    val size: Int

    /**
     * Retrieves the element at the specified index in the array.
     *
     * @param index The index of the element to retrieve.
     * @return The element at the specified index, or null if the index is out of bounds.
     */
    @NotNull
    fun get(@NotNull index: Int): JsonValue

    /**
     * Adds an element to the end of the array.
     *
     * @param element The element to add.
     * @return The added element.
     */
    fun add(@Nullable element: Any?): Boolean

    /**
     * Adds an element at the specified index, shifting subsequent elements.
     *
     * @param index The index at which the element should be inserted.
     * @param element The element to add.
     */
    fun add(@NotNull index: Int, @Nullable element: Any?)

    /**
     * Removes the first occurrence of the specified element from the array.
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
     * Finds the index of the first occurrence of the specified element.
     *
     * @param element The element to find.
     * @return The index of the element, or -1 if the element is not found.
     */
    @NotNull
    fun indexOf(@Nullable element: Any?): Int

    /**
     * Checks if the array is empty.
     *
     * @return `true` if the array contains no elements; `false` otherwise.
     */
    @NotNull
    fun isEmpty(): Boolean

    /**
     * Converts the array to a compact string representation without indentation.
     *
     * @return A string representation of the array, with elements separated by commas and enclosed in brackets.
     */
    @NotNull
    override fun toString(): String

    /**
     * Converts the array to a string with a specified level of indentation.
     *
     * @param indent The number of spaces to use for indentation.
     * @return A string representation of the array with elements formatted according to the specified indentation level.
     */
    @NotNull
    fun toString(@NotNull indent: Int): String
}
