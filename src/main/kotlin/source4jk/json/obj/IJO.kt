package source4jk.json.obj

import java.io.Serializable

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
    val name: String?
    val entries: Set<Map.Entry<String, Any?>>
    val keys: Set<String>
    val values: Collection<Any?>

    /**
     * Retrieves the value associated with the specified key.
     *
     * @param key The key to look up.
     * @return The value associated with the key, or null if the key is not found.
     */
    fun <T> get(key: String): T?

    /**
     * Sets a key-value pair in the object.
     *
     * @param key The key to set.
     * @param value The value to associate with the key.
     * @return The previous value associated with the key, or null if there was no previous value.
     */
    fun set(key: String, value: Any?): Any?

    /**
     * Removes a key-value pair from the object.
     *
     * @param key The key to remove.
     * @return The value that was associated with the key, or null if the key was not found.
     */
    fun remove(key: String): Any?

    /**
     * Converts the object to a formatted string with the specified level of indentation.
     *
     * @param indent The number of spaces to use for indentation.
     * @return A string representation of the object with the specified indentation.
     */
    fun toString(indent: Int): String

    companion object Static {
        private const val serialVersionUID: Long = 1468177767L
    }
}
