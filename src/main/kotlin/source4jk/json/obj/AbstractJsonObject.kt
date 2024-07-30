package source4jk.json.obj

import org.jetbrains.annotations.NotNull
import source4jk.json.JsonStringManager
import source4jk.json.serialization.JsonSerializer
import java.nio.charset.Charset

/**
 * Abstract base class for [IJO], providing default implementations for common operations.
 *
 * @property objectName The name associated with this JSON object.
 * @property map A mutable map holding the key-value pairs for the JSON object.
 */
abstract class AbstractJsonObject(
    private val objectName: String? = null,
    private val map: MutableMap<String, Any?>
): IJO {
    override val name get() = this.objectName
    override val entries get() = this.map.entries
    override val keys get() = this.map.keys
    override val values get() = this.map.values

    override fun serializer(charset: Charset) = JsonSerializer(this, charset)

    @Suppress("UNCHECKED_CAST")
    override fun <T> get(key: String): T? {
        return this.entries.find { it.key == key }?.value as? T
    }

    override fun set(key: String, value: Any?): IJO {
        this.map[key] = value
        return this
    }

    override fun remove(key: String): IJO {
        this.map.remove(key)
        return this
    }

    override fun iterator(): MutableIterator<MutableMap.MutableEntry<String, Any?>> {
        return this.map.iterator()
    }

    override fun toString(): String {
        return JsonStringManager.jsonObjectToString(this, 0, 1)
    }

    override fun toString(@NotNull indent: Int): String {
        return JsonStringManager.jsonObjectToString(this, indent, 1)
    }
}