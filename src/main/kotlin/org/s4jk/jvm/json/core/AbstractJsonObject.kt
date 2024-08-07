package org.s4jk.jvm.json.core

import org.jetbrains.annotations.NotNull
import org.s4jk.jvm.json.JsonStringManager
import java.util.Spliterator
import java.util.function.Consumer

/**z
 * Abstract base class for [IJO], providing default implementations for common operations.
 *
 * @property jsonName The name of the file used for serializing the object.
 * @property map A mutable map holding the key-value pairs for the JSON object.
 */
abstract class AbstractJsonObject protected constructor(
    private val jsonName: String,
    private val map: MutableMap<String, JsonValue>
): IJO {
    override val size get() = this.map.size
    override val name get() = this.jsonName
    override val entries get() = this.map.entries
    override val keys get() = this.map.keys
    override val values get() = this.map.values

    override fun isEmpty(): Boolean {
        return this.map.isEmpty()
    }

    override fun containsKey(key: String): Boolean {
        return this.map.containsKey(key)
    }

    override fun containsValue(value: Any?): Boolean {
        return this.map.containsValue(JsonValue.handle(value))
    }

    override operator fun get(key: String): JsonValue {
        return this.map[key] ?: JsonValue.Null
    }

    override fun getOrDefault(key: String, defaultValue: Any?): JsonValue {
        return this.map.getOrDefault(key, JsonValue.handle(defaultValue))
    }

    override operator fun set(key: String, value: Any?): JsonValue? {
        return this.map.put(key, JsonValue.handle(value))
    }

    override fun setAll(from: IJO) {
        from.entries.map { this.map[it.key] = it.value }
    }

    override fun remove(key: String): JsonValue? {
        return this.map.remove(key)
    }

    override fun clear() {
        return this.map.clear()
    }

    override fun toString(): String {
        return JsonStringManager.jsonObjectToString(this, 0, 1)
    }

    override fun toString(@NotNull indent: Int): String {
        return JsonStringManager.jsonObjectToString(this, indent, 1)
    }

    override fun forEach(action: Consumer<in MutableMap.MutableEntry<String, JsonValue>>?) {
        return this.entries.forEach(action)
    }

    override fun spliterator(): Spliterator<MutableMap.MutableEntry<String, JsonValue>> {
        return this.entries.spliterator()
    }

    override fun iterator(): MutableIterator<MutableMap.MutableEntry<String, JsonValue>> {
        return this.entries.iterator()
    }
}
