package source4jk.json

/**
 * Default implementation of the IJO interface representing a JSON object.
 * Provides methods for iterating over its entries, retrieving, setting, and removing key-value pairs,
 * as well as converting the JSON object to a string with optional indentation.
 */
class JsonObject private constructor(private val map: MutableMap<String, Any?>): IJO<String, Any?> {
    override val entries get() = this.map.entries
    override val keys get() = this.map.keys
    override val values get() = this.map.values

    /**
     * Retrieves a value associated with the specified key.
     *
     * @param key The key to look up.
     * @return The value associated with the key, or null if the key is not found.
     */
    inline fun <reified T> get(key: String): T? {
        return this.entries.find { it.key == key }?.value as? T
    }

    /**
     * Sets a key-value pair in the JSON object.
     *
     * @param key The key to set.
     * @param value The value to associate with the key.
     * @return The previous value associated with the key, or null if there was no previous value.
     */
    fun set(key: String, value: Any?): Any? {
        return this.map.put(key, value)
    }

    /**
     * Removes a key-value pair from the JSON object.
     *
     * @param key The key to remove.
     * @return The value that was associated with the key, or null if the key was not found.
     */
    fun remove(key: String): Any? {
        return this.map.remove(key)
    }

    /**
     * Converts the JSON object to a compact string without indentation.
     *
     * @return A compact string representation of the JSON object.
     */
    override fun toString(): String {
        return JsonStringManager.jsonObjectToString(this, 0, 1)
    }

    /**
     * Converts the JSON object to a formatted string with a specified indentation level.
     *
     * @param indent The number of spaces to use for indentation.
     * @return A string representation of the JSON object.
     */
    override fun toString(indent: Int): String {
        return JsonStringManager.jsonObjectToString(this, indent, 1)
    }

    /**
     * Returns an iterator over the entries in the JSON object.
     *
     * @return An iterator over the key-value pairs in the JSON object.
     */
    override fun iterator(): MutableIterator<MutableMap.MutableEntry<String, Any?>> {
        return this.map.iterator()
    }

    /**
     * Helper class to construct a JsonObject in a fluent manner by manually creating key-value pairs.
     */
    class Constructor {
        internal val map: MutableMap<String, Any?> = mutableMapOf()

        /**
         * Sets a key-value pair in the JSON object being constructed.
         *
         * @param value The value to associate with the key.
         * @return The current Constructor instance for method chaining.
         */
        infix fun String.set(value: Any?): Constructor {
            this@Constructor.map[this@set] = value
            return this@Constructor
        }
    }

    companion object Static {

        /**
         * Creates a JsonObject manually by specifying key-value pairs.
         *
         * @param buildAction A lambda with receiver to construct the JsonObject.
         * @return A new JsonObject instance with the specified data.
         */
        fun create(buildAction: Constructor.() -> Constructor): JsonObject {
            val constructor = Constructor().apply { buildAction() }
            return JsonObject(constructor.map)
        }

        /**
         * Creates a JsonObject from an existing map.
         *
         * @param source The source map to convert.
         * @return A new JsonObject instance containing the entries from the source map.
         */
        fun from(source: Map<String, *>): JsonObject {
            return JsonObject(source.toMutableMap())
        }

        /**
         * Parses a JSON string into a JsonObject.
         *
         * @param source The JSON string to parse.
         * @return A new JsonObject instance representing the JSON data.
         * @throws IllegalJsonStringException If the JSON string is invalid.
         */
        fun from(source: String): JsonObject {
            return JsonStringManager.stringToJsonObject(source)
        }

        /**
         * Creates an empty JsonObject.
         *
         * @return An empty JsonObject instance.
         */
        fun empty(): JsonObject {
            return JsonObject(mutableMapOf())
        }
    }
}
