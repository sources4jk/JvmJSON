package source4jk.json.array

import java.io.Serializable

/**
 * An interface to offload the main class and provide the possibility to create its own functionality through implementation.
 * The interface is necessary to set a pattern for the creation and management of similar objects.
 *
 * @property elements A list of values in the array.
 */
interface IJA: MutableIterable<Any?>, Serializable {
    val elements: List<Any?>

    /**
     * Retrieves the element at the specified index.
     *
     * @param index The index of the element to retrieve.
     * @return The element at the specified index, or null if the index is out of bounds or the element is not of type T.
     */
    fun <T> get(index: Int): T?

    /**
     * Adds an element to the end of the array.
     *
     * @param element The element to add to the array.
     * @return The added element.
     */
    fun add(element: Any?): Any?

    /**
     * Inserts an element at the specified index in the array.
     *
     * @param index The index at which to insert the element.
     * @param element The element to insert.
     * @return The added element.
     */
    fun add(index: Int, element: Any?): Any?

    /**
     * Removes the first occurrence of the specified element from the array.
     *
     * @param element The element to remove.
     * @return The removed element, or null if the element was not found.
     */
    fun remove(element: Any?): Any?

    /**
     * Removes the element at the specified index.
     *
     * @param index The index of the element to remove.
     * @return The removed element.
     */
    fun removeAt(index: Int): Any?

    /**
     * Converts the JSON array to a formatted string with a specified indentation level.
     *
     * @param indent The number of spaces to use for indentation.
     * @return A string representation of the JSON array.
     */
    fun toString(indent: Int): String

    companion object Static {
        private const val serialVersionUID: Long = 2L
    }
}