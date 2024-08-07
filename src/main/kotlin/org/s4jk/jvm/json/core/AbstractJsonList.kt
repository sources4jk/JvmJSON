package org.s4jk.jvm.json.core

import org.s4jk.jvm.json.JsonStringManager
import java.util.Spliterator
import java.util.function.Consumer

/**z
 * Abstract base class for [IJL], providing default implementations for common operations.
 *
 * @property list A mutable list holding the value for the JSON list.
 */
abstract class AbstractJsonList protected constructor(
    private val list: MutableList<JsonValue>
): IJL {

    override val size get() = this.list.size

    override fun isEmpty(): Boolean {
        return this.list.isEmpty()
    }

    override fun contains(element: Any?): Boolean {
        return this.list.contains(JsonValue.handle(element))
    }

    override fun containsAll(elements: Collection<Any?>): Boolean {
        return this.list.containsAll(elements.map { JsonValue.handle(it) })
    }

    override operator fun get(index: Int): JsonValue {
        return this.list[index]
    }

    override fun indexOf(element: Any?): Int {
        return this.list.indexOf(JsonValue.handle(element))
    }

    override fun lastIndexOf(element: Any?): Int {
        return this.list.lastIndexOf(JsonValue.handle(element))
    }

    override fun listIterator(): ListIterator<JsonValue> {
        return this.list.listIterator()
    }

    override fun listIterator(index: Int): ListIterator<JsonValue> {
        return this.list.listIterator(index)
    }

    override fun sub(from: Int, to: Int): IJL {
        return JsonList.from(this.list.subList(from, to))
    }

    override fun add(element: Any?): Boolean {
        return this.list.add(JsonValue.handle(element))
    }

    override fun add(index: Int, element: Any?) {
        return this.list.add(index, JsonValue.handle(element))
    }

    override fun addAll(elements: Collection<Any?>): Boolean {
        return this.list.addAll(elements.map { JsonValue.handle(it) })
    }

    override fun addAll(index: Int, elements: Collection<Any?>): Boolean {
        return this.list.addAll(index, elements.map { JsonValue.handle(it) })
    }

    override fun remove(element: Any?): Boolean {
        return this.list.remove(JsonValue.handle(element))
    }

    override fun removeAt(index: Int): JsonValue {
        return this.list.removeAt(index)
    }

    override fun removeAll(elements: Collection<Any?>): Boolean {
        return this.list.removeAll(elements.map { JsonValue.handle(it) })
    }

    override fun retainAll(elements: Collection<Any?>): Boolean {
        return this.list.retainAll(elements.map { JsonValue.handle(it) })
    }

    override fun clear() {
        return this.list.clear()
    }

    override fun toString(): String {
        return JsonStringManager.jsonListToString(this, 0, 1)
    }

    override fun toString(indent: Int): String {
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
}