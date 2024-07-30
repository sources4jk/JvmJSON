package s4jk.jvm.serialization.objects

import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable
import s4jk.jvm.serialization.JSUtils
import s4jk.jvm.serialization.JsonStringManager

/**
 * Creates a [JsonObject] with an optional name and a custom build action.
 *
 * This function provides a way to create a [JsonObject] by specifying an optional name and a lambda function
 * ([buildAction]) that configures the [JsonObject.Constructor]. The [buildAction] lambda is applied to a new
 * instance of [JsonObject.Constructor] to populate its internal map. The resulting map is then used to create
 * a [JsonObject], which is returned with the given name.
 *
 * This function allows for a concise and flexible way to build JSON objects programmatically.
 *
 * @param name An optional name to associate with the JsonObject. Can be null.
 * @param buildAction A lambda function that configures the [JsonObject.Constructor]. The lambda is applied
 * to a new [JsonObject.Constructor] instance and should configure its map.
 * @return Returns an [IJO] instance representing the created [JsonObject].
 */
fun jsonObjectOf(
    name: String = JSUtils.generateName(null),
    buildAction: JsonObject.Constructor.() -> JsonObject.Constructor
): IJO {
    val constructor = JsonObject.Constructor().apply { buildAction() }
    return JsonObject.from(name, constructor.map)
}

/**
 * Concrete implementation of a [IJO], extending the [AbstractJsonObject] class.
 * This class provides methods for creating and manipulating JSON objects in various ways, including
 * initializing from different data sources such as maps and JSON strings.
 */
class JsonObject private constructor(
    @Nullable objectName: String?,
    @NotNull map: MutableMap<String, Any?>
): AbstractJsonObject(JSUtils.generateName(objectName), map) {

    class Constructor {
        internal val map: MutableMap<String, Any?> = mutableMapOf()

        infix fun String.to(value: Any?): Constructor {
            this@Constructor.map[this] = value
            return this@Constructor
        }
    }


    companion object Static {

        /**
         * Creates a [JsonObject] manually by specifying key-value pairs.
         *
         * @param name The name to associate with the [JsonObject]. Defaults to null.
         * @return A new [JsonObject] instance with the specified name and an empty map.
         */
        @JvmStatic
        fun create(@Nullable name: String?): IJO {
            return JsonObject(name, mutableMapOf())
        }

        @JvmStatic
        fun create(): IJO {
            return create(null)
        }

        /**
         * Creates a [JsonObject] from an existing map.
         *
         * @param name The name to associate with the [JsonObject]. Defaults to null.
         * @param source The source map to convert into a [JsonObject]. The map keys are converted to strings.
         * @return A new [JsonObject] instance containing the entries from the source map.
         */
        @JvmStatic
        fun from(@Nullable name: String?, @NotNull source: Map<*, *>): IJO {
            val json = create(name)

            source.forEach { (key, value) ->
                json.set(key.toString(), value)
            }

            return json
        }

        @JvmStatic
        fun from(@NotNull source: Map<*, *>): IJO {
            return from(null, source)
        }

        /**
         * Creates a [JsonObject] from another [IJO] instance.
         *
         * @param name The name to associate with the new [JsonObject]. Defaults to null.
         * @param source The source [IJO] instance to convert.
         * @return A new [JsonObject] instance containing the entries from the source [IJO].
         */
        @JvmStatic
        fun from(@Nullable name: String?, @NotNull source: IJO): IJO {
            val json = create(name)

            source.entries.forEach { (key, value) ->
                json.set(key, value)
            }

            return json
        }

        @JvmStatic
        fun from(@NotNull source: IJO): IJO {
            return from(null, source)
        }

        /**
         * Parses a JSON string into a [JsonObject].
         *
         * @param name The name to associate with the new [JsonObject]. Defaults to null.
         * @param source The JSON string to parse.
         * @return A new [JsonObject] instance representing the parsed JSON data.
         */
        @JvmStatic
        fun from(@Nullable name: String?, @NotNull source: String): IJO {
            return JsonStringManager.stringToJsonObject(name, source)
        }

        @JvmStatic
        fun from(@NotNull source: String): IJO {
            return from(null, source)
        }
    }
}
