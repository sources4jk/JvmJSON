package org.s4jk.jvm.json.core

import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable
import org.s4jk.jvm.json.string.JsonStringManager
import org.s4jk.jvm.json.string.parsers.Parser
import java.util.Spliterator
import java.util.function.Consumer

/**
 * Represents a mutable JSON object. Provides methods to manipulate entries within the JSON structure.
 * Implements [MutableIterable]<[JsonValue]> to allow iteration over the entries in the object.
 *
 * @property size The number of entries in this [JsonObject].
 * @property entries A set view of the key-value pairs contained in this [JsonObject].
 * @property keys A set of the keys contained in this [JsonObject].
 * @property values A collection of the values contained in this [JsonObject].
 */
class JsonObject(): MutableIterable<MutableMap.MutableEntry<String, JsonValue>> {
    private val _map: HashMap<String, JsonValue> = HashMap()

    /**
     * Creates an empty [JsonObject].
     */
    constructor(@NotNull from: JsonObject): this() {
        this._map.putAll(from._map)
    }

    /**
     * Creates a [JsonObject] by copying entries from a given [JsonObject].
     *
     * @param from A [Map] from which to copy entries.
     */
    constructor(@NotNull from: Map<*, *>): this() {
        this._map.putAll(from.map { it.key.toString() to JsonValue.recognize(it.value) })
    }

    /**
     * Creates a [JsonObject] by parsing a JSON-formatted string.
     *
     * @param from A JSON string to be parsed into a [JsonObject].
     */
    constructor(@NotNull from: String): this() {
        JsonObject(Parser(from).parseObject())
    }

    @get:NotNull
    val size: Int get() = this._map.size

    @get:NotNull
    val entries: Set<MutableMap.MutableEntry<String, JsonValue>> get() = this._map.entries

    @get:NotNull
    val keys: Set<String> get() = this._map.map { it.key }.toSet()

    @get:NotNull
    val values: Collection<JsonValue> get() = this._map.map { it.value }

    /**
     * Checks if this [JsonObject] is empty.
     *
     * @return true if the [JsonObject] contains no entries.
     */
    @NotNull
    fun isEmpty(): Boolean {
        return this._map.isEmpty()
    }

    /**
     * Checks if the specified key exists in this [JsonObject].
     *
     * @param key The key to check for.
     * @return true if the key is present.
     */
    @NotNull
    fun containsKey(@NotNull key: String): Boolean {
        return this._map.filter { it.key == key }.isNotEmpty()
    }

    /**
     * Checks if the specified value exists in this [JsonObject].
     *
     * @param value The value to check for.
     * @return true if the value is present.
     */
    @NotNull
    fun containsValue(@Nullable value: Any?): Boolean {
        return this._map.filter { it.value == JsonValue.recognize(value) }.isNotEmpty()
    }

    /**
     * Retrieves the value associated with the specified key.
     *
     * @param key The key whose associated value is to be returned.
     * @return The value associated with the key, or [JsonValue.NULL] if the key is not found.
     */
    @NotNull
    operator fun get(@NotNull key: String): JsonValue {
        return this._map[key] ?: JsonValue.NULL
    }

    /**
     * Retrieves the value associated with the specified key, or returns a default value if the key is not found.
     *
     * @param key The key whose associated value is to be returned.
     * @param defaultValue The value to return if the key is not found.
     * @return The value associated with the key, or the default value if the key is not found.
     */
    @NotNull
    fun getOrDefault(@NotNull key: String, @Nullable defaultValue: Any?): JsonValue {
        return this._map.getOrDefault(key, JsonValue.recognize(defaultValue))
    }

    /**
     * Associates the specified value with the specified key in this [JsonObject].
     *
     * @param key The key with which the specified value is to be associated.
     * @param value The value to be associated with the specified key.
     * @return The previous value associated with the key, or null if there was no mapping for the key.
     */
    @NotNull
    operator fun set(@NotNull key: String, @NotNull value: Any?): JsonValue? {
        return this._map.put(key, JsonValue.recognize(value))
    }

    /**
     * Removes the entry associated with the specified key.
     *
     * @param key The key whose associated entry is to be removed.
     * @return The value that was removed, or null if there was no mapping for the key.
     */
    @Nullable
    fun remove(@NotNull key: String): JsonValue? {
        return this._map.remove(key)
    }

    /**
     * Removes the entry associated with the specified key only if it is currently mapped to the specified value.
     *
     * @param key The key whose associated entry is to be removed.
     * @param value The value expected to be associated with the specified key.
     * @return true if the entry was removed.
     */
    @NotNull
    fun remove(@NotNull key: String, @Nullable value: Any?): Boolean {
        return this._map.remove(key, value)
    }

    /**
     * Removes all entries from this [JsonObject].
     */
    fun clear() {
        return this._map.clear()
    }

    /**
     * Returns a string representation of the [JsonObject].
     *
     * @return A string representation of the [JsonObject].
     */
    @NotNull
    override fun toString(): String {
        return JsonStringManager.jsonObjectToString(this, 0, 1)
    }

    /**
     * Returns a string representation of the [JsonObject] with the specified indentation.
     *
     * @param indent The indentation level to be used.
     * @return A string representation of the [JsonObject].
     */
    @NotNull
    fun toString(@NotNull indent: Int): String {
        return JsonStringManager.jsonObjectToString(this, indent, 1)
    }

    /**
     * Performs the given action for each entry in the [JsonObject].
     *
     * @param action The action to be performed for each entry.
     */
    @NotNull
    override fun forEach(@NotNull action: Consumer<in MutableMap.MutableEntry<String, JsonValue>>?) {
        return this._map.entries.forEach(action)
    }

    /**
     * Creates a [Spliterator] over the entries in the [JsonObject].
     *
     * @return A [Spliterator] over the entries in the [JsonObject].
     */
    @NotNull
    override fun spliterator(): Spliterator<MutableMap.MutableEntry<String, JsonValue>> {
        return this._map.entries.spliterator()
    }

    /**
     * Returns an iterator over the entries in the [JsonObject].
     *
     * @return An iterator over the entries in the [JsonObject].
     */
    @NotNull
    override fun iterator(): MutableIterator<MutableMap.MutableEntry<String, JsonValue>> {
        return this._map.entries.iterator()
    }
}