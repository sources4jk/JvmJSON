package source4jk.json

import java.io.Serializable

class JsonObject private constructor(private val map: MutableMap<String, Any?>):
    MutableIterable<MutableMap.MutableEntry<String, Any?>>, Serializable {

    val entries: MutableSet<MutableMap.MutableEntry<String, Any?>>
        get() = this.map.entries
    val keys: MutableSet<String>
        get() = this.map.keys
    val values: MutableCollection<Any?>
        get() = this.map.values


    @Suppress("UNCHECKED_CAST")
    fun <T> get(key: String): T? {
        return this.entries.find { it.key == key }?.value as? T
    }

    fun set(key: String, value: Any?): Any? {
        return this.map.put(key, value)
    }

    fun remove(key: String): Any? {
        return this.map.remove(key)
    }

    override fun toString(): String {
        return JsonStringManager.jsonObjectToString(this, 0, 1)
    }

    fun toString(indent: Int): String {
        return JsonStringManager.jsonObjectToString(this, indent, 1)
    }

    override fun iterator(): MutableIterator<MutableMap.MutableEntry<String, Any?>> {
        return this.map.iterator()
    }


    class Constructor {
        internal val map: MutableMap<String, Any?> = mutableMapOf()

        infix fun String.set(value: Any?) {
            this@Constructor.map[this@set] = value
        }
    }


    companion object Static {
        fun create(buildAction: Constructor.() -> Unit): JsonObject {
            val constructor = Constructor()
            constructor.buildAction()
            return JsonObject(constructor.map)
        }
    }

}