package s4jk.jvm.serialization.objects

import org.jetbrains.annotations.NotNull
import s4jk.jvm.serialization.JsonStringManager
import s4jk.jvm.serialization.JsonSerializer
import java.nio.charset.Charset
import java.util.*
import java.util.function.Consumer

/**
 * Abstract base class for [IJO], providing default implementations for common operations.
 *
 * @property objectName The name associated with this JSON object.
 * @property map A mutable map holding the key-value pairs for the JSON object.
 */
abstract class AbstractJsonObject protected constructor(
    private val objectName: String,
    private val map: MutableMap<String, Any?>
): IJO {
    override val name get() = this.objectName
    override val entries get() = this.map.entries
    override val keys get() = this.map.keys
    override val values get() = this.map.values

    override fun serializer(charset: Charset) = JsonSerializer(this, charset)
    override fun serializer() = JsonSerializer(this, Charsets.UTF_8)

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

    override fun toString(): String {
        return JsonStringManager.jsonObjectToString(this, 0, 1)
    }

    override fun toString(@NotNull indent: Int): String {
        return JsonStringManager.jsonObjectToString(this, indent, 1)
    }

    override fun forEach(action: Consumer<in MutableMap.MutableEntry<String, Any?>>?) {
        return this.map.entries.forEach(action)
    }

    override fun spliterator(): Spliterator<MutableMap.MutableEntry<String, Any?>> {
        return this.map.entries.spliterator()
    }

    override fun iterator(): MutableIterator<MutableMap.MutableEntry<String, Any?>> {
        return this.map.entries.iterator()
    }
}