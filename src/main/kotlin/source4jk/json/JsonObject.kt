package source4jk.json

import java.io.Serializable

class JsonObject private constructor(
    private val map: MutableMap<String, Any?>
) : MutableIterable<MutableMap.MutableEntry<String, Any?>>, Serializable {
    val entries get() = this.map.entries
    val keys get() = this.map.keys
    val values get() = this.map.values


    fun toString(indent: Int = 0): String {
        return JsonStringManager.jsonObjectToString(this, indent, 0)
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

        fun from(source: Map<String, *>): JsonObject {
            return JsonObject(source.toMutableMap())
        }

        fun from(source: String): JsonObject {
            return JsonStringManager.stringToJsonObject(source)
        }

        fun empty(): JsonObject {
            return JsonObject(mutableMapOf())
        }
    }

}