package org.s4jk.jvm.json.core

import org.jetbrains.annotations.NotNull
import org.s4jk.jvm.json.collections.JsonMap
import org.s4jk.jvm.json.string.JsonStringManager
import org.s4jk.jvm.json.string.parsers.Parser
import java.util.LinkedList
import java.util.Spliterator
import java.util.function.Consumer

/**
 * The [JsonObject] class represents a mutable JSON object.
 * It implements the [JsonMap] interface and provides various methods
 * for manipulating JSON objects, such as adding, removing, and retrieving
 * entries. It also supports JSON serialization and deserialization.
 */
class JsonObject(): JsonMap {

    /**
     * Constructs a new [JsonObject] by copying entries from another [JsonObject].
     *
     * @param from The [JsonObject] to copy.
     */
    constructor(from: JsonObject): this() {
        this._entries.addAll(from.entries)
    }

    /**
     * Constructs a new [JsonObject] from a [Map].
     *
     * @param from The map to convert to a [JsonObject].
     */
    constructor(from: Map<*, *>): this() {
        this._entries.addAll(from.map { Node(it.key.toString(), JsonValue.recognize(it.value)) })
    }

    /**
     * Constructs a new [JsonObject] from a JSON-formatted string.
     *
     * @param from The JSON string to parse.
     */
    constructor(from: String): this() {
        JsonObject(Parser(from).parseObject())
    }


    private val _entries: LinkedHashSet<JsonMap.Node> = LinkedHashSet()

    override val size: Int
        get() = this._entries.size

    override val entries: LinkedHashSet<JsonMap.Node>
        get() = _entries

    override val keys: LinkedHashSet<String>
        get() = this._entries.map { it.key }.toCollection(LinkedHashSet())

    override val values: LinkedList<JsonValue>
        get() = this._entries.map { it.value }.toCollection(LinkedList())


    override fun isEmpty(): Boolean {
        return this._entries.isEmpty()
    }

    override fun containsKey(key: String): Boolean {
        return this._entries.find { it.key == key } != null
    }

    override fun containsValue(value: Any?): Boolean {
        return this._entries.find { it.value == JsonValue.recognize(value) } != null
    }

    override operator fun get(key: String): JsonValue {
        return this._entries.find { it.key == key }?.value ?: JsonValue.NULL
    }

    override fun getOrDefault(key: String, defaultValue: Any?): JsonValue {
        return this._entries.find { it.key == key }?.value ?: JsonValue.recognize(defaultValue)
    }

    override operator fun set(key: String, value: Any?): JsonValue {
        this._entries.removeIf { it.key == key }.also { this._entries += Node(key, JsonValue.recognize(value)) }
        return JsonValue.recognize(value)
    }

    override fun remove(key: String): JsonValue {
        return this._entries.find { it.key == key }?.let { node ->
            this._entries.remove(node)
            node.value
        } ?: JsonValue.NULL
    }

    override fun remove(key: String, value: Any?): Boolean {
        return this.entries.removeIf { it.key == key && it.value == value }
    }

    override fun clear() {
        return this.entries.clear()
    }

    override fun toString(): String {
        return this.toString(0)
    }

    override fun toString(indent: Int): String {
        return JsonStringManager.jsonObjectToString(this, indent, 1)
    }

    override fun forEach(@NotNull action: Consumer<in JsonMap.Node?>) {
        return this.entries.forEach(action)
    }

    override fun spliterator(): Spliterator<JsonMap.Node> {
        return this.entries.spliterator()
    }

    override fun iterator(): MutableIterator<JsonMap.Node> {
        return this.entries.iterator()
    }

    /**
     * Represents a key-value pair in the JSON object.
     * This class is used to store and retrieve individual entries in a [JsonObject].
     * It holds a [key] of type [String] and a [value] of type [JsonValue].
     *
     * @property key The key of the [Node].
     * @property value The value of the [Node].
     */
    class Node(override val key: String, override val value: JsonValue): JsonMap.Node {
        override fun component1(): String = this.key
        override fun component2(): JsonValue = this.value
    }
}