package source4jk.json

import java.io.Serializable

abstract class AbstractJsonObject(
    private val map: MutableMap<String, Any?>
): MutableIterable<MutableMap.MutableEntry<String, Any?>>, Serializable {
    val entries: MutableSet<MutableMap.MutableEntry<String, Any?>>
        get() = this.map.entries
    val keys: MutableSet<String>
        get() = this.map.keys
    val values: MutableCollection<Any?>
        get() = this.map.values

    fun reader() = JsonReader(this)
    fun modifier() = JsonModifier(this)
    fun writer() = JsonWriter(this)


    companion object Static {
        private const val serialVersionUID: Long = 1L
    }
}