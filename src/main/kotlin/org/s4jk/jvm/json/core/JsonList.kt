package org.s4jk.jvm.json.core

import org.s4jk.jvm.json.collections.JsonLinkedList
import org.s4jk.jvm.json.string.JsonStringManager
import org.s4jk.jvm.json.string.parsers.Parser
import java.util.LinkedList
import java.util.Spliterator
import java.util.function.Consumer

/**
 * The [JsonList] class represents a mutable JSON array.
 * It implements the [JsonLinkedList] interface and provides various methods
 * for manipulating JSON arrays, such as adding, removing, and retrieving
 * elements. It also supports JSON serialization and deserialization.
 */
class JsonList(): JsonLinkedList {

    private val _values: LinkedList<JsonValue> = LinkedList()

    /**
     * Constructs a new [JsonList] by copying elements from a [Collection].
     *
     * @param from The collection to copy elements from.
     */
    constructor(from: Collection<*>): this() {
        this._values.addAll(from.map { JsonValue.recognize(it) })
    }

    /**
     * Constructs a new [JsonList] by copying elements from another [JsonList].
     *
     * @param from The [JsonList] to copy.
     */
    constructor(from: JsonList): this() {
        this._values.addAll(from._values)
    }

    /**
     * Constructs a new [JsonList] from a JSON-formatted string.
     *
     * @param from The JSON string to parse.
     */
    constructor(from: String): this() {
        JsonList(Parser(from).parseList())
    }


    override val size: Int
        get() = this._values.size

    override fun isEmpty(): Boolean {
        return this._values.isEmpty()
    }

    override fun contains(element: Any?): Boolean {
        return this._values.contains(JsonValue.recognize(element))
    }

    override fun containsAll(elements: Collection<Any?>): Boolean {
        return this._values.containsAll(elements.map { JsonValue.recognize(it) })
    }

    override fun get(index: Int): JsonValue {
        return this._values[index]
    }

    override fun add(element: Any?): Boolean {
        return this._values.add(JsonValue.recognize(element))
    }

    override fun add(index: Int, element: Any?) {
        return this._values.add(index, JsonValue.recognize(element))
    }

    override fun indexOf(element: Any?): Int {
        return this._values.indexOf(JsonValue.recognize(element))
    }

    override fun lastIndexOf(element: Any?): Int {
        return this._values.lastIndexOf(JsonValue.recognize(element))
    }

    override fun listIterator(): ListIterator<JsonValue> {
        return this._values.listIterator()
    }

    override fun listIterator(index: Int): ListIterator<JsonValue> {
        return this._values.listIterator(index)
    }

    override fun sub(from: Int, to: Int): JsonList {
        return JsonList(_values.subList(from, to))
    }

    override fun addAll(elements: Collection<Any?>): Boolean {
        return this._values.addAll(elements.map { JsonValue.recognize(it) })
    }

    override fun addAll(elements: JsonList): Boolean {
        return this._values.addAll(elements._values)
    }

    override fun addAll(index: Int, elements: Collection<Any?>): Boolean {
        return this._values.addAll(index, elements.map { JsonValue.recognize(it) })
    }

    override fun addAll(index: Int, elements: JsonList): Boolean {
        return this._values.addAll(index, elements._values)
    }

    override fun remove(element: Any?): Boolean {
        return this._values.remove(JsonValue.recognize(element))
    }

    override fun removeAt(index: Int): JsonValue {
        return this._values.removeAt(index)
    }

    override fun removeAll(elements: Collection<Any?>): Boolean {
        return this._values.removeAll(elements.map { JsonValue.recognize(it) }.toSet())
    }

    override fun retainAll(elements: Collection<Any?>): Boolean {
        return this._values.retainAll(elements.map { JsonValue.recognize(it) }.toSet())
    }

    override fun clear() {
        return this._values.clear()
    }

    override fun toString(): String {
        return JsonStringManager.jsonListToString(this, 0, 1)
    }

    override fun toString(indent: Int): String {
        return JsonStringManager.jsonListToString(this, indent, 1)
    }

    override fun forEach(action: Consumer<in JsonValue>?) {
        return this._values.forEach(action)
    }

    override fun spliterator(): Spliterator<JsonValue> {
        return this._values.spliterator()
    }

    override fun iterator(): MutableIterator<JsonValue> {
        return this._values.iterator()
    }

}