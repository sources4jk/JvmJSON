package source4jk.json

import java.io.Serializable

/**
 * An interface to offload the main class and provide the possibility to create its own functionality through implementation.
 * The interface is necessary to set a pattern for the creation and management of similar objects.
 * @property entries A set of key-value pairs in the map.
 * @property keys A set of keys in the map.
 * @property values A collection of values in the map.
 */
interface IJO<K, V>: MutableIterable<MutableMap.MutableEntry<K, V>>, Serializable {
    val entries: MutableSet<MutableMap.MutableEntry<K, V>>
    val keys: MutableSet<K>
    val values: MutableCollection<V>

}