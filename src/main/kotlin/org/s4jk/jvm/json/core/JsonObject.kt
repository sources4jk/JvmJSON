package org.s4jk.jvm.json.core

import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable
import org.s4jk.jvm.json.JsonStringManager
import org.s4jk.jvm.json.JsonUtils

/**
 * Creates a [JsonObject] with an optional name and a custom build action.
 *
 * This function allows for the creation of a [JsonObject] by providing an optional name and a lambda function
 * ([buildAction]) that configures the [JsonObject.Constructor]. The [buildAction] lambda is applied to a new
 * instance of [JsonObject.Constructor] to set up its internal map. The resulting map is then used to create
 * a [JsonObject], which is returned with the specified name.
 *
 * This approach provides a concise and flexible way to construct JSON objects programmatically.
 *
 * @param name An optional name to associate with the [JsonObject]. Can be null.
 * @param [buildAction] A lambda function that configures the [JsonObject.Constructor]. The lambda is applied
 * to a new [JsonObject.Constructor] instance and should populate its map.
 * @return An [IJO] instance representing the created [JsonObject].
 */
@NotNull
@JvmSynthetic
fun jsonObjectOf(@Nullable name: String? = null, @NotNull buildAction: JsonObject.Constructor.() -> Unit): IJO {
    val constructor = JsonObject.Constructor().apply { this.buildAction() }
    return JsonObject.from(name, constructor.map)
}

/**τ♥
 * Converts a map into a [JsonObject].
 *
 * This function creates a [JsonObject] from a given map. The map's keys are used as the keys in the resulting
 * [JsonObject], and the map's values are used as the corresponding values.
 *
 * @param name An optional name to associate with the [JsonObject]. Can be null.
 * @return An [IJO] instance representing the created [JsonObject].
 */
@NotNull
@JvmSynthetic
fun Map<*, *>.toJsonObject(@Nullable name: String? = null): IJO {
    return JsonObject.from(name, this)
}

/**
 * Converts a JSON string into a [JsonObject].
 *
 * This function creates a [JsonObject] from a JSON-formatted string. The string is parsed, and the resulting
 * [JsonObject] contains the data represented by the JSON string.
 *
 * @param name An optional name to associate with the [JsonObject]. Can be null.
 * @return An [IJO] instance representing the parsed JSON data.
 */
@NotNull
@JvmSynthetic
fun String.toJsonObject(@Nullable name: String? = null): IJO {
    return JsonObject.from(name, this)
}

/**
 * Concrete implementation of [IJO], extending [AbstractJsonObject].
 * This class provides methods for creating and manipulating JSON objects from various data sources
 * such as maps and JSON strings.
 */
class JsonObject private constructor(
    @Nullable name: String?,
    @NotNull map: MutableMap<String, JsonValue>
): AbstractJsonObject(JsonUtils.generateName(name), map) {

    class Constructor {
        internal val map: MutableMap<String, JsonValue> = mutableMapOf()

        infix fun String.to(value: Any?) {
            this@Constructor.map[this] = JsonValue.handle(value)
        }

    }

    companion object Static {

        /**
         * Creates a new [JsonObject] with an optional name and an empty map.
         *
         * @param name An optional name to associate with the [JsonObject]. Defaults to null.
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
         * Creates a [JsonObject] from a given map.
         *
         * @param name An optional name to associate with the [JsonObject]. Can be null.
         * @param source The source map to convert into a [JsonObject]. Map keys are converted to strings.
         * @return A new [JsonObject] instance with the entries from the source map.
         */
        @JvmStatic
        fun from(@Nullable name: String?, @NotNull source: Map<*, *>): IJO {
            return JsonObject(name, source.map { (key, value) ->
                key.toString() to JsonValue.handle(value)
            }.toMap().toMutableMap())
        }

        @JvmStatic
        fun from(@NotNull source: Map<*, *>): IJO {
            return this.from(null, source)
        }

        /**
         * Creates a [JsonObject] from another [IJO] instance.
         *
         * @param name An optional name to associate with the new [JsonObject]. Can be null.
         * @param source The source [IJO] instance to convert.
         * @return A new [JsonObject] instance containing the entries from the source [IJO].
         */
        @JvmStatic
        fun from(@Nullable name: String?, @NotNull source: IJO): IJO {
            return this.create(name).apply {
                source.entries.forEach { (key, value) ->
                    this[key] = JsonValue.handle(value)
                }
            }
        }

        @JvmStatic
        fun from(@NotNull source: IJO): IJO {
            return this.from(null, source)
        }

        /**
         * Creates a [JsonObject] from a JSON string.
         *
         * @param name An optional name to associate with the new [JsonObject]. Can be null.
         * @param source The JSON string to parse into a [JsonObject].
         * @return A new [JsonObject] instance representing the parsed JSON data.
         */
        @JvmStatic
        fun from(@Nullable name: String?, @NotNull source: String): IJO {
            return JsonStringManager.stringToJsonObject(name, source)
        }

        @JvmStatic
        fun from(@NotNull source: String): IJO {
            return this.from(null, source)
        }
    }
}