package org.s4jk.jvm.json.core

import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable
import org.s4jk.jvm.json.JsonStringManager
import org.s4jk.jvm.json.JsonUtils
import java.util.Spliterator
import java.util.function.Consumer

class JsonObject private constructor(
    @Nullable private val name: String? = JsonUtils.generateName(null),
    @NotNull private val map: MutableMap<String, JsonValue>
): MutableIterable<MutableMap.MutableEntry<String, JsonValue>> {

    val size: Int get() = this.map.size
    val entries: MutableSet<MutableMap.MutableEntry<String, JsonValue>> get() = this.map.entries
    val keys: MutableSet<String> get() = this.map.keys
    val values: MutableCollection<JsonValue> get() = this.map.values

    fun isEmpty(): Boolean {
        return this.map.isEmpty()
    }

    fun containsKey(key: String): Boolean {
        return this.map.containsKey(key)
    }

    fun containsValue(value: Any?): Boolean {
        return this.map.containsValue(JsonValue.handle(value))
    }

    operator fun get(key: String): JsonValue {
        return this.map[key] ?: JsonValue.Null
    }

    fun getOrDefault(key: String, defaultValue: Any?): JsonValue {
        return this.map.getOrDefault(key, JsonValue.handle(defaultValue))
    }

    operator fun set(key: String, value: Any?): JsonValue? {
        return this.map.put(key, JsonValue.handle(value))
    }

    fun setAll(from: JsonObject) {
        from.entries.map { this.map[it.key] = it.value }
    }

    fun remove(key: String): JsonValue? {
        return this.map.remove(key)
    }

    fun clear() {
        return this.map.clear()
    }

    fun toString(@NotNull indent: Int): String {
        return JsonStringManager.jsonObjectToString(this, indent, 1)
    }

    override fun toString(): String {
        return JsonStringManager.jsonObjectToString(this, 0, 1)
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

    class Constructor {
        internal val map: MutableMap<String, JsonValue> = mutableMapOf()

        infix fun String.to(value: Any?) {
            this@Constructor.map[this] = JsonValue.handle(value)
        }
    }

    companion object Static {

        @JvmStatic
        fun create(@Nullable name: String?): JsonObject {
            return JsonObject(name, mutableMapOf())
        }

        @JvmStatic
        fun create(): JsonObject {
            return this.create(null)
        }


        @JvmStatic
        fun from(@Nullable name: String?, @NotNull source: Map<*, *>): JsonObject {
            return JsonObject(name, source.map { (key, value) ->
                key.toString() to JsonValue.handle(value)
            }.toMap().toMutableMap())
        }

        @JvmStatic
        fun from(@NotNull source: Map<*, *>): JsonObject {
            return this.from(null, source)
        }


        @JvmStatic
        fun from(@Nullable name: String?, @NotNull source: JsonObject): JsonObject {
            return this.create(name).apply {
                source.entries.forEach { (key, value) ->
                    this[key] = JsonValue.handle(value)
                }
            }
        }

        @JvmStatic
        fun from(@NotNull source: JsonObject): JsonObject {
            return this.from(null, source)
        }


        @JvmStatic
        fun from(@Nullable name: String?, @NotNull source: String): JsonObject {
            return JsonStringManager.stringToJsonObject(name, source)
        }

        @JvmStatic
        fun from(@NotNull source: String): JsonObject {
            return this.from(null, source)
        }
    }
}