package s4jk.jvm.serialization.objects

import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable
import s4jk.jvm.serialization.serializer.JsonSerializer
import java.io.Serializable
import java.nio.charset.Charset

/**
 * Interface for representing JSON-like objects, enabling custom functionality through implementation.
 * Defines methods and properties for managing and interacting with objects consisting of key-value pairs.
 *
 * @property name A unique identifier or label for the object, used for distinguishing between instances or for descriptive purposes.
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
     * Creates a JSON serializer for the object with the specified charset.
     *
     * @param charset The charset for serialization. Defaults to UTF-8 if not provided.
     * @return The JSON serializer configured with the given charset.
     */
    @NotNull
    fun serializer(@NotNull charset: Charset): JsonSerializer

    /**
     * Creates a JSON serializer for the object with the default charset (UTF-8).
     *
     * @return The JSON serializer configured with the default charset.
     */
    @NotNull
    fun serializer(): JsonSerializer

    /**
     * Retrieves a value associated with the specified key.
     *
     * @param key The key to look up in the JSON object.
     * @return The value associated with the key, or null if the key is not found.
     * @throws ClassCastException If the value associated with the key cannot be cast to the expected type.
     */
    @Nullable
    @Throws(ClassCastException::class)
    fun <T> get(@NotNull key: String): T?

    /**
     * Retrieves a value associated with the specified key, or returns a default value if the key is not found.
     *
     * @param key The key to look up in the JSON object.
     * @param defaultValue The value to return if the key is not found. Can be null.
     * @return The value associated with the key, or the default value if the key is not found.
     * @throws ClassCastException If the value associated with the key cannot be cast to the expected type.
     */
    @Nullable
    @Throws(ClassCastException::class)
    fun <T> getOrDefault(@NotNull key: String, @Nullable defaultValue: T?): T?

    /**
     * Sets a key-value pair in the JSON object.
     *
     * @param key The key to set in the JSON object.
     * @param value The value to associate with the key. Can be null.
     */
    @NotNull
    fun set(@NotNull key: String, @Nullable value: Any?)

    /**
     * Removes a key-value pair from the JSON object.
     *
     * @param key The key to remove from the JSON object.
     * @return The value that was associated with the key, or null if the key was not found.
     */
    @NotNull
    fun remove(@NotNull key: String): Any?

    /**
     * Converts the JSON object to a compact string representation without indentation.
     *
     * @return A compact string representation of the JSON object.
     */
    @NotNull
    override fun toString(): String

    /**
     * Converts the JSON object to a formatted string representation with the specified indentation level.
     *
     * @param indent The number of spaces to use for indentation in the formatted string.
     * @return A string representation of the JSON object with the specified indentation.
     */
    @NotNull
    fun toString(@NotNull indent: Int): String

    companion object Static {
        @NotNull
        private const val serialVersionUID: Long = 621009875L
    }
}