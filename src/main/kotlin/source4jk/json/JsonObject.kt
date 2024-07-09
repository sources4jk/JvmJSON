package source4jk.json

class JsonObject private constructor(
    private val map: MutableMap<String, Any?>
): MutableIterable<MutableMap.MutableEntry<String, Any?>> {

    val entries: MutableSet<MutableMap.MutableEntry<String, Any?>>
        get() = this.map.entries

    val keys: MutableSet<String>
        get() = this.map.keys

    val values: MutableCollection<Any?>
        get() = this.map.values


    @Suppress("UNCHECKED_CAST")
    fun <T> get(key: String): T? {
        return this.entries.find { entry ->
            entry.key == key
        }?.value as? T
    }

    fun set(key: String, value: Any?): Any? {
        return this.map.put(key, value)
    }

    fun remove(key: String): Any? {
        return this.entries.find { entry ->
            entry.key == key
        }?.also { entry ->
            this.map.remove(key)
            entry.value
        }
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

        fun from(map: MutableMap<String, Any?>): JsonObject {
            return JsonObject(map)
        }

        fun from(string: String): JsonObject {
            TODO()
        }
    }
}