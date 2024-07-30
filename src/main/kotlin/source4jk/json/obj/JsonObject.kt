package source4jk.json.obj

import source4jk.json.StringManager

/**
 * Extension function to convert a string into a JSON object.
 *
 * This function creates an `IJO` instance from the string it is called on.
 * The string is treated as the JSON content, and an optional `name` can be provided
 * to associate with the resulting JSON object. If `name` is not provided, the JSON object
 * will use a default name based on the string itself.
 *
 * @param name An optional name to associate with the resulting JSON object. If null,
 *             the JSON object will use a default name derived from the string.
 * @return An `IJO` instance representing the JSON object created from the string.
 */
fun String.toJsonObject(name: String? = null): IJO {
    return JsonObject.from(name, this@toJsonObject)
}

/**
 * Default implementation of the IJO interface representing a JSON object.
 *
 * @property name The name associated with this JSON object.
 * @property map A mutable map holding the key-value pairs for the JSON object.
 */
class JsonObject private constructor(
    override val name: String?,
    private val map: MutableMap<String, Any?>
): IJO {
    override val entries get() = this.map.entries
    override val keys get() = this.map.keys
    override val values get() = this.map.values

    /**
     * Retrieves a value associated with the specified key.
     *
     * @param key The key to look up.
     * @return The value associated with the key, or null if the key is not found.
     */
    @Suppress("UNCHECKED_CAST")
    override fun <T> get(key: String): T? {
        return this.entries.find { it.key == key }?.value as? T
    }

    /**
     * Sets a key-value pair in the JSON object.
     *
     * @param key The key to set.
     * @param value The value to associate with the key.
     * @return The previous value associated with the key, or null if there was no previous value.
     */
    override fun set(key: String, value: Any?): Any? {
        return this.map.put(key, value)
    }

    /**
     * Removes a key-value pair from the JSON object.
     *
     * @param key The key to remove.
     * @return The value that was associated with the key, or null if the key was not found.
     */
    override fun remove(key: String): Any? {
        return this.map.remove(key)
    }

    /**
     * Converts the JSON object to a compact string without indentation.
     *
     * @return A compact string representation of the JSON object.
     */
    override fun toString(): String {
        return StringManager.jsonObjectToString(this, 0, 1)
    }

    /**
     * Converts the JSON object to a formatted string with a specified indentation level.
     *
     * @param indent The number of spaces to use for indentation.
     * @return A string representation of the JSON object with the specified indentation.
     */
    override fun toString(indent: Int): String {
        return StringManager.jsonObjectToString(this, indent, 1)
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
        infix fun String.to(value: Any?): Constructor {
            this@Constructor.map[this@to] = value
            return this@Constructor
        }
    }

    companion object Static {

        /**
         * Creates a JsonObject manually by specifying key-value pairs.
         *
         * @param name The name to associate with the JsonObject.
         * @param buildAction A lambda with receiver to construct the JsonObject.
         * @return A new JsonObject instance with the specified data.
         */
        @JvmStatic
        fun create(name: String? = null, buildAction: Constructor.() -> Constructor): IJO {
            val constructor = Constructor().apply { buildAction() }
            return JsonObject(name, constructor.map)
        }

        /**
         * Creates a JsonObject from an existing map.
         *
         * @param name The name to associate with the JsonObject.
         * @param source The source map to convert.
         * @return A new JsonObject instance containing the entries from the source map.
         */
        @JvmStatic
        fun from(name: String? = null, source: Map<*, *>): IJO {
            val json = this.empty(name)

            source.forEach { (key, value) ->
                json.set(key.toString(), value)
            }

            return json
        }

        /**
         * Creates a JsonObject from another IJO instance.
         *
         * @param name The name to associate with the new JsonObject.
         * @param source The source IJO instance to convert.
         * @return A new JsonObject instance containing the entries from the source IJO.
         */
        @JvmStatic
        fun from(name: String? = null, source: IJO): IJO {
            val json = this.empty(name)

            source.entries.forEach { (key, value) ->
                json.set(key, value)
            }
            return json
        }

        /**
         * Parses a JSON string into a JsonObject.
         *
         * @param name The name to associate with the new JsonObject.
         * @param source The JSON string to parse.
         * @return A new JsonObject instance representing the JSON data.
         */
        @JvmStatic
        fun from(name: String? = null, source: String): IJO {
            return StringManager.stringToJsonObject(name, source)
        }

        /**
         * Creates an empty JsonObject.
         *
         * @param name The name to associate with the empty JsonObject.
         * @return An empty JsonObject instance.
         */
        @JvmStatic
        fun empty(name: String? = null): IJO {
            return JsonObject(name, mutableMapOf())
        }
    }
}
