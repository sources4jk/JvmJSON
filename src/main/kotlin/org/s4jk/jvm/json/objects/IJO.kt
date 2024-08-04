package org.s4jk.jvm.json.objects

import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable
import org.s4jk.jvm.json.JsonInputOutput
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
interface IJO: MutableIterable<MutableMap.MutableEntry<String, ValueContainer<Any?>>> {

    @get:NotNull
    val name: String

    @get:NotNull
    val entries: Set<Map.Entry<String, ValueContainer<Any?>>>

    @get:NotNull
    val keys: Set<String>

    @get:NotNull
    val values: Collection<ValueContainer<Any?>>

    /**
     * Creates a JsonInputOutput object for reading from and writing to the JSON object.
     *
     * @param charset The character set to use for encoding. Defaults to UTF-8.
     * @return A JsonInputOutput instance for handling input and output operations.
     */
    @NotNull
    fun io(charset: Charset = Charsets.UTF_8): JsonInputOutput

    /**
     * Retrieves a value associated with the specified key.
     *
     * @param key The key to look up in the JSON object.
     * @return The value associated with the key.
     */
    @NotNull
    fun get(key: String): ValueContainer<Any?>

    /**
     * Retrieves a value associated with the specified key, or returns a default value if the key is not found.
     *
     * @param key The key to look up in the JSON object.
     * @param defaultValue The value to return if the key is not found. Can be null.
     * @return The value associated with the key, or the default value if the key is not found.
     */
    @NotNull
    fun getOrDefault(@NotNull key: String, @Nullable defaultValue: Any?): ValueContainer<Any?>

    /**
     * Sets a key-value pair in the JSON object.
     *
     * @param key The key to set in the JSON object.
     * @param value The value to associate with the key. Can be null.
     */
    fun set(@NotNull key: String, @Nullable value: Any?)

    /**
     * Removes a key-value pair from the JSON object.
     *
     * @param key The key to remove from the JSON object.
     * @return The value that was associated with the key, or null if the key was not found.
     */
    @Nullable
    fun remove(@NotNull key: String): ValueContainer<Any?>

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
}
