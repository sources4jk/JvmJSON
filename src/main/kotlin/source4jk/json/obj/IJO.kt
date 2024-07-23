package source4jk.json.obj

import java.io.Serializable

/**
 * An interface to offload the main class and provide the possibility to create custom functionality through implementation.
 * This interface sets a pattern for the creation and management of similar objects, specifically JSON-like objects.
 *
 * @property entries A set of key-value pairs contained in the map.
 * @property keys A set of keys contained in the map.
 * @property values A collection of values contained in the map.
 */
interface IJO<K, V>: MutableIterable<MutableMap.MutableEntry<K, V>>, Serializable {
    val entries: MutableSet<MutableMap.MutableEntry<K, V>>
    val keys: MutableSet<K>
    val values: MutableCollection<V>

    /**
     * Converts the JSON object to a formatted string with a specified indentation level.
     *
     * @param indent The number of spaces to use for indentation.
     * @return A string representation of the JSON object.
     */
    fun toString(indent: Int): String

    companion object Static {
        private const val serialVersionUID: Long = 1L
    }
}
