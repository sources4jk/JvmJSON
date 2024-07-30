package source4jk.json.array

import source4jk.json.StringManager

/**
 * Default implementation of the IJA interface representing a JSON array.
 * Provides methods for accessing, adding, setting, and removing elements,
 * as well as converting the JSON array to a string with optional indentation.
 */
class JsonArray private constructor(private val list: MutableList<Any?>): IJA {
    override val elements get() = this.list

    /**
     * Retrieves the element at the specified index.
     *
     * @param index The index of the element to retrieve.
     * @return The element at the specified index, cast to type T, or null if the index is out of bounds or the element is not of type T.
     */
    @Suppress("UNCHECKED_CAST")
    override fun <T> get(index: Int): T? {
        return this.elements[index] as? T
    }

    /**
     * Adds an element to the end of the array.
     *
     * @param element The element to add to the array.
     * @return The added element.
     */
    override fun add(element: Any?): Any? {
        this.list.add(element)
        return element
    }

    /**
     * Inserts an element at the specified index in the array.
     *
     * @param index The index at which to insert the element.
     * @param element The element to insert.
     * @return The added element.
     */
    override fun add(index: Int, element: Any?): Any? {
        this.list.add(index, element)
        return element
    }

    /**
     * Removes the first occurrence of the specified element from the array.
     *
     * @param element The element to remove.
     * @return The removed element, or null if the element was not found.
     */
    override fun remove(element: Any?): Any? {
        this.list.remove(element)
        return element
    }

    /**
     * Removes the element at the specified index.
     *
     * @param index The index of the element to remove.
     * @return The removed element.
     */
    override fun removeAt(index: Int): Any? {
        return this.list.removeAt(index)
    }

    /**
     * Converts the JSON array to a compact string without indentation.
     *
     * @return A compact string representation of the JSON array.
     */
    override fun toString(): String {
        return StringManager.jsonArrayToString(this, 0, 1)
    }

    /**
     * Converts the JSON array to a formatted string with a specified indentation level.
     *
     * @param indent The number of spaces to use for indentation.
     * @return A string representation of the JSON array with the specified indentation.
     */
    override fun toString(indent: Int): String {
        return StringManager.jsonArrayToString(this, indent, 1)
    }

    /**
     * Returns an iterator over the values in the JSON array.
     *
     * @return An iterator over the values in the JSON array.
     */
    override fun iterator(): MutableIterator<Any?> {
        return this.list.iterator()
    }

    companion object Static {

        /**
         * Creates a JsonArray with the specified values.
         *
         * @param elements The values to include in the JsonArray.
         * @return A new JsonArray instance with the specified values.
         */
        @JvmStatic
        fun create(vararg elements: Any?): IJA {
            return JsonArray(elements.toMutableList())
        }

        /**
         * Creates a JsonArray from an existing array.
         *
         * @param source The source array to convert.
         * @return A new JsonArray instance containing the elements from the source array.
         */
        @JvmStatic
        fun from(source: Array<*>): IJA {
            return JsonArray(source.toMutableList())
        }

        /**
         * Creates a JsonArray from an existing list.
         *
         * @param source The source list to convert.
         * @return A new JsonArray instance containing the elements from the source list.
         */
        @JvmStatic
        fun from(source: List<*>): IJA {
            return JsonArray(source.toMutableList())
        }

        /**
         * Creates a JsonArray from an existing IJA instance.
         *
         * @param source The source IJA instance to convert.
         * @return A new JsonArray instance containing the elements from the source IJA.
         */
        fun from(source: IJA): IJA {
            val array = this.empty()

            source.forEachIndexed { index, element ->
                array.add(index, element)
            }

            return array
        }

        /**
         * Creates a JsonArray from a JSON string representation.
         *
         * @param source The JSON string to parse into a JsonArray.
         * @return A new JsonArray instance created from the JSON string.
         */
        fun from(source: String): IJA {
            return StringManager.stringToJsonArray(source)
        }

        /**
         * Creates an empty JsonArray.
         *
         * @return An empty JsonArray instance.
         */
        @JvmStatic
        fun empty(): IJA {
            return JsonArray(mutableListOf())
        }
    }
}
