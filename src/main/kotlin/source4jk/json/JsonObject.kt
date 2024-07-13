package source4jk.json

class JsonObject private constructor(
    private val map: MutableMap<String, Any?>,
    private val modes: Set<JsonAccessMode>
): AbstractJsonObject(map) {

    fun toString(indent: Int): String {
        return JsonStringManager.ObjectToString.jsonToString(this, indent, 1)
    }

    override fun toString(): String {
        return JsonStringManager.ObjectToString.jsonToString(this, 0, 1)
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
            return JsonObject(constructor.map, JsonAccessMode.ALL)
        }

        fun from(map: Map<String, *>, modes: Set<JsonAccessMode>): JsonObject {
            return JsonObject(map.toMutableMap(), modes)
        }

        fun from(string: String, modes: Set<JsonAccessMode>): JsonObject {
            return JsonStringManager.StringToObject.stringToJsonObject(string, modes)
        }

        fun empty(modes: Set<JsonAccessMode>): JsonObject {
            return JsonObject(mutableMapOf(), modes)
        }
    }

}