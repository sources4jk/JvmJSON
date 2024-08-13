package org.s4jk.jvm.json.collections

import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable
import org.s4jk.jvm.json.core.JsonValue
import java.util.LinkedList

/**
 * The [JsonLinkedHashMap] interface represents a mutable map of JSON values.
 * It provides various methods to manipulate the map, such as adding,
 * removing, and searching for entries. The interface extends the
 * [MutableIterable]<[JsonLinkedHashMap.Node]> interface.
 *
 * @property size The number of entries in the map.
 * @property entries The underlying [HashSet] that stores [JsonLinkedHashMap.Node] objects.
 * @property keys The set of keys in the map.
 * @property values The list of values in the map.
 */
interface JsonLinkedHashMap: MutableIterable<JsonLinkedHashMap.Node> {

    @get:NotNull
    val size: Int

    @get:NotNull
    val entries: LinkedHashSet<Node>

    @get:NotNull
    val keys: LinkedHashSet<String>

    @get:NotNull
    val values: LinkedList<JsonValue>

    /**
     * Checks if the map is empty.
     *
     * @return true if the map is empty.
     */
    @NotNull
    fun isEmpty(): Boolean

    /**
     * Checks if the map contains the specified key.
     *
     * @param key The key to check for.
     * @return true if the [key] is found in the map.
     */
    @NotNull
    fun containsKey(@NotNull key: String): Boolean

    /**
     * Checks if the map contains the specified value.
     *
     * @param value The value to check for.
     * @return true if the [value] is found in the map.
     */
    @NotNull
    fun containsValue(@NotNull value: Any?): Boolean

    /**
     * Retrieves the value associated with the specified key.
     *
     * @param key The key whose associated value is to be retrieved.
     * @return The [JsonValue] associated with the specified key.
     */
    @NotNull
    operator fun get(@NotNull key: String): JsonValue

    /**
     * Retrieves the value associated with the specified key, or returns a default value if the key is not found.
     *
     * @param key The key whose associated value is to be retrieved.
     * @param defaultValue The value to return if the key is not found.
     * @return The [JsonValue] associated with the specified [key], or the [defaultValue] if the [key] is not found.
     */
    @NotNull
    fun getOrDefault(@NotNull key: String, @Nullable defaultValue: Any?): JsonValue

    /**
     * Associates the specified value with the specified key in the map.
     *
     * @param key The key with which the value is to be associated.
     * @param value The value to be associated with the key.
     * @return The [JsonValue] that was added or updated.
     */
    @NotNull
    operator fun set(@NotNull key: String, @Nullable value: Any?): JsonValue

    /**
     * Removes the entry with the specified key from the map, if present.
     *
     * @param key The key whose associated entry is to be removed.
     * @return The [JsonValue] that was removed.
     */
    @Nullable
    fun remove(@NotNull key: String): JsonValue

    /**
     * Removes the entry with the specified key and value from the map, if present.
     *
     * @param key The key of the entry to remove.
     * @param value The value of the entry to remove.
     * @return true if the entry was removed.
     */
    @NotNull
    fun remove(@NotNull key: String, @Nullable value: Any?): Boolean

    /**
     * Clears all entries from the JSON object.
     */
    fun clear()

    /**
     * Returns a JSON-formatted string representation of the object.
     *
     * @return A JSON string representing the object.
     */
    @NotNull
    override fun toString(): String

    /**
     * Returns a JSON-formatted string representation of the object with the specified indentation.
     *
     * @param indent The number of spaces to use for indentation.
     * @return A formatted JSON string representing the object.
     */
    @NotNull
    fun toString(@NotNull indent: Int): String

    /**
     * Represents a key-value pair in the map.
     * @property key The key of the [Node]
     * @property value The value of the [Node]
     */
    interface Node {

        @get:NotNull
        val key: String

        @get:NotNull
        val value: JsonValue

        /**
         * @return The [key] of the [Node].
         */
        @NotNull
        operator fun component1(): String

        /**
         * @return The [value] of the [Node].
         */
        @NotNull
        operator fun component2(): JsonValue
    }
}