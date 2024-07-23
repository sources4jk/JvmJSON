package source4jk.json.array

import java.io.Serializable

/**
 * An interface to offload the main class and provide the possibility to create its own functionality through implementation.
 * The interface is necessary to set a pattern for the creation and management of similar objects.
 *
 * @property values A list of values in the array.
 */
interface IJA<V>: MutableIterable<V>, Serializable {
    val values: MutableList<V>

    /**
     * Converts the JSON array to a formatted string with a specified indentation level.
     *
     * @param indent The number of spaces to use for indentation.
     * @return A string representation of the JSON array.
     */
    fun toString(indent: Int): String

    companion object Static {
        private const val serialVersionUID: Long = 2L
    }
}
