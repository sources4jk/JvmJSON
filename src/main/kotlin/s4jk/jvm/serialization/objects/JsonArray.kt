package s4jk.jvm.serialization.objects

import s4jk.jvm.serialization.JsonStringManager

/**
 * Creates a [JsonArray] from the provided elements.
 *
 * This function enables the creation of a [JsonArray] by specifying a variable number of elements. The elements
 * are converted into a list and then used to construct a [JsonArray].
 *
 * @param elements The elements to include in the JSON array.
 * @return An [IJA] instance representing the created [JsonArray].
 */
fun jsonArrayOf(vararg elements: Any?): IJA {
    return JsonArray.from(elements.toList())
}

/**
 * Converts an array into a [JsonArray].
 *
 * This extension function creates a [JsonArray] from the elements of the array. The array elements are converted
 * into a list and used to construct the [JsonArray].
 *
 * @return An [IJA] instance representing the created [JsonArray].
 */
fun Array<*>.toJsonArray(): IJA {
    return jsonArrayOf(*this)
}

/**
 * Converts a list into a [JsonArray].
 *
 * This extension function creates a [JsonArray] from the elements of the list. The list elements are used directly
 * to construct the [JsonArray].
 *
 * @return An [IJA] instance representing the created [JsonArray].
 */
fun List<*>.toJsonArray(): IJA {
    return jsonArrayOf(*this.toTypedArray())
}

/**
 * Converts a JSON string into a [JsonArray].
 *
 * This extension function parses a JSON-formatted string into a [JsonArray]. The resulting [JsonArray] contains
 * the data represented by the JSON string.
 *
 * @return An [IJA] instance representing the parsed JSON data.
 */
fun String.toJsonArray(): IJA {
    return jsonArrayOf(this)
}

/**
 * Concrete implementation of [IJA], extending [AbstractJsonArray].
 * This class provides methods for creating and manipulating JSON arrays from various data sources
 * such as arrays, lists, and JSON strings.
 */
class JsonArray private constructor(list: MutableList<Any?>): AbstractJsonArray(list) {

    companion object Static {

        /**
         * Creates an empty [JsonArray].
         *
         * @return A new [JsonArray] instance with an empty list.
         */
        @JvmStatic
        fun create(): IJA {
            return JsonArray(mutableListOf())
        }

        /**
         * Creates a [JsonArray] from an existing array.
         *
         * @param source The source array to convert into a [JsonArray].
         * @return A new [JsonArray] instance containing the elements from the source array.
         */
        @JvmStatic
        fun from(source: Array<*>): IJA {
            return JsonArray(source.toMutableList())
        }

        /**
         * Creates a [JsonArray] from an existing list.
         *
         * @param source The source list to convert into a [JsonArray].
         * @return A new [JsonArray] instance containing the elements from the source list.
         */
        @JvmStatic
        fun from(source: List<*>): IJA {
            return JsonArray(source.toMutableList())
        }

        /**
         * Creates a [JsonArray] from another [IJA] instance.
         *
         * @param source The source [IJA] instance to convert.
         * @return A new [JsonArray] instance containing the elements from the source [IJA].
         */
        @JvmStatic
        fun from(source: IJA): IJA {
            val array = create()

            source.forEachIndexed { index, element ->
                array.add(index, element)
            }

            return array
        }

        /**
         * Parses a JSON string into a [JsonArray].
         *
         * @param source The JSON string to parse into a [JsonArray].
         * @return A new [JsonArray] instance representing the parsed JSON data.
         */
        @JvmStatic
        fun from(source: String): IJA {
            return JsonStringManager.stringToJsonArray(source)
        }
    }
}