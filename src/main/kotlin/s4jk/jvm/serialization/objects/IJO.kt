package s4jk.jvm.serialization.objects

import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable
import s4jk.jvm.serialization.JsonSerializer
import java.io.Serializable
import java.nio.charset.Charset

/**
 * Interface for representing JSON-like objects, allowing for custom functionality through implementation.
 * Defines a pattern for managing and interacting with objects that consist of key-value pairs.
 *
 * @property name A unique identifier or label for the object, used for distinguishing between different instances or for descriptive purposes.
 * @property entries A set of key-value pairs contained in the object.
 * @property keys A set of keys contained in the object.
 * @property values A collection of values contained in the object.
 */
interface IJO: MutableIterable<MutableMap.MutableEntry<String, Any?>>, Serializable {

    @get:NotNull
    val name: String

    @get:NotNull
    val entries: Set<Map.Entry<String, Any?>>

    @get:NotNull
    val keys: Set<String>

    @get:NotNull
    val values: Collection<Any?>

    /**
     * Creates a serializer for the object with the specified charset.
     *
     * @param charset The charset for serialization. Defaults to UTF-8.
     * @return The JSON serializer.
     */
    @NotNull
    fun serializer(@NotNull charset: Charset): JsonSerializer

    @NotNull
    fun serializer(): JsonSerializer
    /**
     * Retrieves a value associated with the specified key.
     *
     * @param key The key to look up.
     * @return The value associated with the key, or null if the key is not found.
     */
    @Nullable
    fun <T> get(@NotNull key: String): T?

    /**
     * Sets a key-value pair in the JSON object.
     *
     * @param key The key to set.
     * @param value The value to associate with the key.
     * @return The previous value associated with the key, or null if there was no previous value.
     */
    @NotNull
    fun set(@NotNull key: String, @Nullable value: Any?): IJO

    /**
     * Removes a key-value pair from the JSON object.
     *
     * @param key The key to remove.
     * @return The value that was associated with the key, or null if the key was not found.
     */
    @NotNull
    fun remove(@NotNull key: String): IJO

    /**
     * Converts the JSON object to a compact string without indentation.
     *
     * @return A compact string representation of the JSON object.
     */
    @NotNull
    override fun toString(): String

    /**
     * Converts the JSON object to a formatted string with a specified indentation level.
     *
     * @param indent The number of spaces to use for indentation.
     * @return A string representation of the JSON object with the specified indentation.
     */
    @NotNull
    fun toString(@NotNull indent: Int): String

    companion object Static {
        @NotNull
        private const val serialVersionUID: Long = 621009875L
    }
}