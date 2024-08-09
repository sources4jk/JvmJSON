package org.s4jk.jvm.json.core

import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable
import org.s4jk.jvm.json.string.JsonStringManager
import org.s4jk.jvm.json.string.parsers.JsonStringParser
import java.util.Spliterator
import java.util.function.Consumer

/**
 * The [JsonObject] class represents a mutable map of JSON key-value pairs.
 * It provides methods for manipulating the map, including adding, removing,
 * and searching for entries. The class implements the [MutableIterable] interface
 * for iterating over the map's entries.
 *
 * @property map The underlying [HashMap] that stores the key-value pairs.
 * @property size The number of entries in the map.
 * @property entries A set of all entries in the map.
 * @property keys A set of all keys in the map.
 * @property values A collection of all values in the map.
 */
class JsonObject private constructor(
    @NotNull private val map: HashMap<String, JsonValue>
): MutableIterable<MutableMap.MutableEntry<String, JsonValue>> {

    @get:NotNull
    val size: Int get() = this.map.size

    @get:NotNull
    val entries: Set<Map.Entry<String, JsonValue>> get() = this.map.entries

    @get:NotNull
    val keys: Set<String> get() = this.map.keys

    @get:NotNull
    val values: Collection<JsonValue> get() = this.map.values

    /**
     * Checks if the map is empty.
     *
     * @return true if the map is empty.
     */
    @NotNull
    fun isEmpty(): Boolean {
        return this.map.isEmpty()
    }

    /**
     * Checks if the map contains the specified key.
     *
     * @param key The key to check for.
     * @return true if the key is found.
     */
    @NotNull
    fun containsKey(key: String): Boolean {
        return this.map.containsKey(key)
    }

    /**
     * Checks if the map contains the specified value.
     *
     * @param value The value to check for.
     * @return true if the value is found.
     */
    @NotNull
    fun containsValue(value: Any?): Boolean {
        return this.map.containsValue(JsonValue.handle(value))
    }

    /**
     * Retrieves the value associated with the specified key, or [JsonValue.Null] if not found.
     *
     * @param key The key of the value to retrieve.
     * @return The [JsonValue] associated with the key, or [JsonValue.Null].
     */
    @NotNull
    operator fun get(key: String): JsonValue {
        return this.map[key] ?: JsonValue.Null
    }

    /**
     * Retrieves the value associated with the specified key, or a default value if not found.
     *
     * @param key The key of the value to retrieve.
     * @param defaultValue The default value to return if the key is not found.
     * @return The [JsonValue] associated with the key, or the default value.
     */
    @NotNull
    fun getOrDefault(key: String, defaultValue: Any?): JsonValue {
        return this.map.getOrDefault(key, JsonValue.handle(defaultValue))
    }

    /**
     * Associates the specified value with the specified key in the map.
     *
     * @param key The key with which the specified value is to be associated.
     * @param value The value to be associated with the key.
     * @return The previous value associated with the key, or [JsonValue.Null] if there was no mapping.
     */
    @Nullable
    operator fun set(key: String, value: Any?): JsonValue {
        return this.map.put(key, JsonValue.handle(value)) ?: JsonValue.Null
    }

    /**
     * Copies all key-value pairs from the specified [JsonObject] to this map.
     *
     * @param from The [JsonObject] to copy entries from.
     */
    fun setAll(from: JsonObject) {
        this.map.putAll(from.map)
    }

    /**
     * Removes the entry for the specified key from the map.
     *
     * @param key The key whose entry is to be removed.
     * @return The previous value associated with the key, or null if there was no mapping.
     */
    @Nullable
    fun remove(key: String): JsonValue? {
        return this.map.remove(key)
    }

    /**
     * Removes all entries from the map.
     */
    fun clear() {
        return this.map.clear()
    }

    /**
     * Returns a JSON-formatted string representation of the map with the specified indentation.
     *
     * @param indent The number of spaces to use for indentation.
     * @return A formatted JSON string representing the map.
     */
    @NotNull
    fun toString(@NotNull indent: Int): String {
        return JsonStringManager.jsonObjectToString(this, indent, 1)
    }

    /**
     * Returns a JSON-formatted string representation of the map.
     *
     * @return A JSON string representing the map.
     */
    @NotNull
    override fun toString(): String {
        return JsonStringManager.jsonObjectToString(this, 0, 1)
    }

    /**
     * Performs the given action for each entry in the map.
     *
     * @param action The action to perform on each entry.
     */
    override fun forEach(action: Consumer<in MutableMap.MutableEntry<String, JsonValue>>?) {
        return this.map.entries.forEach(action)
    }

    /**
     * Creates a [Spliterator] over the entries in the map.
     *
     * @return A [Spliterator] of [MutableMap.MutableEntry]<[String], [JsonValue]>.
     */
    @NotNull
    override fun spliterator(): Spliterator<MutableMap.MutableEntry<String, JsonValue>> {
        return this.map.entries.spliterator()
    }

    /**
     * Returns an iterator over the entries in the map.
     *
     * @return A [MutableIterator] of [MutableMap.MutableEntry]<[String], [JsonValue]>.
     */
    @NotNull
    override fun iterator(): MutableIterator<MutableMap.MutableEntry<String, JsonValue>> {
        return this.map.entries.iterator()
    }

    /**
     * The [Constructor] class is used to build a [JsonObject] using a DSL-style syntax.
     */
    class Constructor {
        internal val map: HashMap<String, JsonValue> = hashMapOf()

        /**
         * Adds a key-value pair to the [JsonObject].
         *
         * @param value The value to associate with the key.
         */
        infix fun String.to(value: Any?) {
            this@Constructor.map[this] = JsonValue.handle(value)
        }
    }

    companion object Static {

        /**
         * Creates a new, empty [JsonObject].
         *
         * @return A new instance of [JsonObject].
         */
        @NotNull
        @JvmStatic
        fun create(): JsonObject {
            return JsonObject(hashMapOf())
        }

        /**
         * Creates a [JsonObject] from the specified map.
         *
         * @param from The map to convert.
         * @return A new [JsonObject] containing the entries of the map.
         */
        @NotNull
        @JvmStatic
        fun from(@NotNull from: Map<*, *>): JsonObject {
            return JsonObject(HashMap(from.entries.associate { (key, value) ->
                key.toString() to JsonValue.handle(value)
            }))
        }

        /**
         * Creates a [JsonObject] from the specified [JsonObject].
         *
         * @param from The [JsonObject] to copy.
         * @return A new [JsonObject] containing the same entries as the original.
         */
        @NotNull
        @JvmStatic
        fun from(@NotNull from: JsonObject): JsonObject {
            return JsonObject(HashMap(from.map))
        }

        /**
         * Creates a [JsonObject] from the specified JSON string.
         *
         * @param from The JSON string to parse.
         * @return A new [JsonObject] representing the parsed JSON.
         */
        @NotNull
        @JvmStatic
        fun from(@NotNull from: String): JsonObject {
            return JsonStringParser(from).parseObject()
        }
    }
}