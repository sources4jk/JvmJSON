package source4jk.json.array

/**
 * Default implementation of the IJA interface representing a JSON array.
 * Provides methods for accessing, adding, setting, and removing elements,
 * as well as converting the JSON array to a string with optional indentation.
 */
class JsonArray private constructor(private val list: MutableList<Any?>): IJA<Any?> {
    override val values get() = this.list

    /**
     * Retrieves a value at the specified index.
     *
     * @param index The index to look up.
     * @return The value at the specified index, or null if the index is out of bounds.
     */
    inline fun <reified T> get(index: Int): T? {
        return this.values[index] as? T
    }

    /**
     * Adds a value to the JSON array.
     *
     * @param value The value to add.
     * @return True if the value was added successfully, false otherwise.
     */
    fun add(value: Any?): Boolean {
        return this.list.add(value)
    }

    /**
     * Sets a value at the specified index in the JSON array.
     *
     * @param value The value to set.
     * @param index The index at which to set the value.
     * @return The previous value at the specified index, or null if there was no previous value.
     */
    fun set(value: Any?, index: Int): Any? {
        return this.list.set(index, value)
    }

    /**
     * Removes the first occurrence of a value from the JSON array.
     *
     * @param value The value to remove.
     * @return True if the value was removed successfully, false otherwise.
     */
    fun remove(value: Any?): Boolean {
        return this.list.remove(value)
    }

    /**
     * Removes a value at the specified index from the JSON array.
     *
     * @param index The index of the value to remove.
     * @return The value that was removed, or null if the index is out of bounds.
     */
    fun removeAt(index: Int): Any? {
        return this.list.removeAt(index)
    }

    /**
     * Converts the JSON array to a compact string without indentation.
     *
     * @return A compact string representation of the JSON array.
     */
    override fun toString(): String {
        return JsonArrayStringer.jsonArrayToString(this, 0, 1)
    }

    /**
     * Converts the JSON array to a formatted string with a specified indentation level.
     *
     * @param indent The number of spaces to use for indentation.
     * @return A string representation of the JSON array.
     */
    override fun toString(indent: Int): String {
        return JsonArrayStringer.jsonArrayToString(this, indent, 1)
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
         * @param values The values to include in the JsonArray.
         * @return A new JsonArray instance with the specified values.
         */
        @JvmStatic
        fun create(vararg values: Any?): JsonArray {
            return JsonArray(values.toMutableList())
        }

        /**
         * Creates a JsonArray from an existing array.
         *
         * @param source The source array to convert.
         * @return A new JsonArray instance containing the elements from the source array.
         */
        @JvmStatic
        fun from(source: Array<*>): JsonArray {
            return JsonArray(source.toMutableList())
        }

        /**
         * Creates a JsonArray from an existing list.
         *
         * @param source The source list to convert.
         * @return A new JsonArray instance containing the elements from the source list.
         */
        @JvmStatic
        fun from(source: MutableList<*>): JsonArray {
            return JsonArray(source.toMutableList())
        }

        /**
         * Creates an empty JsonArray.
         *
         * @return An empty JsonArray instance.
         */
        @JvmStatic
        fun empty(): JsonArray {
            return JsonArray(mutableListOf())
        }
    }
}