package org.s4jk.jvm.json

import org.jetbrains.annotations.NotNull
import org.s4jk.jvm.json.core.JsonList
import org.s4jk.jvm.json.core.JsonObject
import org.s4jk.jvm.json.core.JsonValue

/**
 * Creates a [JsonObject] using a DSL-style syntax.
 *
 * @param buildAction The action to build the [JsonObject].
 * @return A new [JsonObject] containing the constructed entries.
 */
@NotNull
@JvmSynthetic
fun jsonObjectOf(@NotNull buildAction: JsonObject.Constructor.() -> Unit): JsonObject {
    val builder = JsonObject.Constructor().apply { this.buildAction() }
    return JsonObject.from(builder.map)
}

/**
 * Creates a [JsonObject] from the given key-value pairs.
 *
 * @param entries The key-value pairs to include in the [JsonObject].
 * @return A new [JsonObject] containing the provided entries.
 */
@NotNull
@JvmSynthetic
fun jsonObjectOf(@NotNull vararg entries: Pair<String, Any?>): JsonObject {
    return JsonObject.from(entries.associate { (key, value) -> key to JsonValue.handle(value) }.toMutableMap())
}

/**
 * Converts this [Map] to a [JsonObject].
 *
 * @return A new [JsonObject] containing the entries of the map.
 */
@NotNull
@JvmSynthetic
fun Map<*, *>.toJsonObject(): JsonObject {
    return JsonObject.from(this)
}

/**
 * Parses the given JSON string and returns a [JsonObject].
 *
 * @return A [JsonObject] representing the parsed JSON.
 */
@NotNull
@JvmSynthetic
fun String.toJsonObject(): JsonObject {
    return JsonObject.from(this)
}

/**
 * Creates a [JsonList] from the given elements.
 *
 * @param elements The elements to include in the [JsonList].
 * @return A new [JsonList] containing the provided elements.
 */
@NotNull
@JvmSynthetic
fun jsonListOf(vararg elements: Any?): JsonList {
    return JsonList.from(elements.toList())
}

/**
 * Converts this [Array] to a [JsonList].
 *
 * @return A new [JsonList] containing the elements of the array.
 */
@NotNull
@JvmSynthetic
fun Array<*>.toJsonList(): JsonList {
    return JsonList.from(this@toJsonList)
}

/**
 * Converts this [List] to a [JsonList].
 *
 * @return A new [JsonList] containing the elements of the list.
 */
@NotNull
@JvmSynthetic
fun List<*>.toJsonList(): JsonList {
    return JsonList.from(this@toJsonList)
}

/**
 * Converts this [Set] to a [JsonList].
 *
 * @return A new [JsonList] containing the elements of the set.
 */
@NotNull
@JvmSynthetic
fun Set<*>.toJsonList(): JsonList {
    return JsonList.from(this@toJsonList)
}

/**
 * Parses the given JSON string and returns a [JsonList].
 *
 * @return A [JsonList] representing the parsed JSON.
 */
@NotNull
@JvmSynthetic
fun String.toJsonList(): JsonList {
    return JsonList.from(this@toJsonList)
}