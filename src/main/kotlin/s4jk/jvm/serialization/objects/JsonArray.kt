package s4jk.jvm.serialization.objects

import s4jk.jvm.serialization.JsonStringManager


/**
 * Creates a [JsonArray] from the provided elements.
 *
 * This function allows for a concise and flexible way to build JSON arrays programmatically by specifying
 * a variable number of elements. The elements are converted into a list and then used to create a [JsonArray].
 *
 * @param elements The elements to include in the JSON array.
 * @return Returns an [IJA] instance representing the created [JsonArray].
 */
fun jsonArrayOf(vararg elements: Any?): IJA {
    return JsonArray.from(elements.toList())
}

/**
 * Concrete implementation of a [IJA], extending the [AbstractJsonArray] class.
 * This class provides methods for creating and manipulating JSON arrays in various ways, including
 * initializing from different data sources such as arrays, lists, and JSON strings.
 */
class JsonArray private constructor(list: MutableList<Any?>): AbstractJsonArray(list) {
    companion object Static {

        /**
         * Creates an empty JSON array.
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
         * @param source The JSON string to parse.
         * @return A new [JsonArray] instance representing the parsed JSON data.
         */
        @JvmStatic
        fun from(source: String): IJA {
            return JsonStringManager.stringToJsonArray(source)
        }
    }
}
