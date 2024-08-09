package org.s4jk.jvm.json.core

import org.s4jk.jvm.json.JsonStringManager
import java.util.Spliterator
import java.util.function.Consumer

class JsonList private constructor(private val list: MutableList<JsonValue>): MutableIterable<JsonValue> {

    val size get() = this.list.size

    fun isEmpty(): Boolean {
        return this.list.isEmpty()
    }

    fun contains(element: Any?): Boolean {
        return this.list.contains(JsonValue.handle(element))
    }

    fun containsAll(elements: Collection<Any?>): Boolean {
        return this.list.containsAll(elements.map { JsonValue.handle(it) })
    }

    operator fun get(index: Int): JsonValue {
        return this.list[index]
    }

    fun indexOf(element: Any?): Int {
        return this.list.indexOf(JsonValue.handle(element))
    }

    fun lastIndexOf(element: Any?): Int {
        return this.list.lastIndexOf(JsonValue.handle(element))
    }

    fun listIterator(): ListIterator<JsonValue> {
        return this.list.listIterator()
    }

    fun listIterator(index: Int): ListIterator<JsonValue> {
        return this.list.listIterator(index)
    }

    fun sub(from: Int, to: Int): JsonList {
        return from(this.list.subList(from, to))
    }

    fun add(element: Any?): Boolean {
        return this.list.add(JsonValue.handle(element))
    }

    fun add(index: Int, element: Any?) {
        return this.list.add(index, JsonValue.handle(element))
    }

    fun addAll(elements: Collection<Any?>): Boolean {
        return this.list.addAll(elements.map { JsonValue.handle(it) })
    }

    fun addAll(index: Int, elements: Collection<Any?>): Boolean {
        return this.list.addAll(index, elements.map { JsonValue.handle(it) })
    }

    fun remove(element: Any?): Boolean {
        return this.list.remove(JsonValue.handle(element))
    }

    fun removeAt(index: Int): JsonValue {
        return this.list.removeAt(index)
    }

    fun removeAll(elements: Collection<Any?>): Boolean {
        return this.list.removeAll(elements.map { JsonValue.handle(it) })
    }

    fun retainAll(elements: Collection<Any?>): Boolean {
        return this.list.retainAll(elements.map { JsonValue.handle(it) })
    }

    fun clear() {
        return this.list.clear()
    }

    override fun toString(): String {
        return JsonStringManager.jsonListToString(this, 0, 1)
    }

    fun toString(indent: Int): String {
        return JsonStringManager.jsonListToString(this, indent, 1)
    }

    override fun forEach(action: Consumer<in JsonValue>?) {
        return this.list.forEach(action)
    }

    override fun spliterator(): Spliterator<JsonValue> {
        return this.list.spliterator()
    }

    override fun iterator(): MutableIterator<JsonValue> {
        return this.list.iterator()
    }

    companion object Static {

        @JvmStatic
        fun create(): JsonList {
            return JsonList(mutableListOf())
        }

        @JvmStatic
        fun from(source: Array<*>): JsonList {
            return JsonList(source.map { JsonValue.handle(it) }.toMutableList())
        }

        @JvmStatic
        fun from(source: List<*>): JsonList {
            return JsonList(source.map { JsonValue.handle(it) }.toMutableList())
        }

        @JvmStatic
        fun from(source: Set<*>): JsonList {
            return JsonList(source.map { JsonValue.handle(it) }.toMutableList())
        }

        @JvmStatic
        fun from(source: JsonList): JsonList {
            return JsonList(source.map { JsonValue.handle(it) }.toMutableList())
        }

        @JvmStatic
        fun from(source: String): JsonList {
            return JsonStringManager.stringToJsonList(source)
        }
    }
}