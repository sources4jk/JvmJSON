package org.s4jk.jvm.json

import org.jetbrains.annotations.NotNull
import org.s4jk.jvm.json.core.JsonList
import org.s4jk.jvm.json.core.JsonObject

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
 * Converts this [Collection] to a [JsonList].
 *
 * @return A new [JsonList] containing the elements of the collection.
 */
@NotNull
@JvmSynthetic
fun Collection<*>.toJsonList(): JsonList {
    return JsonList.from(this@toJsonList)
}

/**
 * Converts this [Array] to a [JsonList].
 *
 * @return A new [JsonList] containing the elements of the array.
 */
@NotNull
@JvmSynthetic
fun Array<*>.toJsonList(): JsonList {
    return JsonList.from(this@toJsonList.toList())
}

/**
 * Converts this [CharArray] to a [JsonList].
 *
 * @return A new [JsonList] containing the characters of the array.
 */
@NotNull
@JvmSynthetic
fun CharArray.toJsonList(): JsonList {
    return JsonList.from(this@toJsonList.map { it.toString() }.toList())
}

/**
 * Converts this [ByteArray] to a [JsonList].
 *
 * @return A new [JsonList] containing the bytes of the array.
 */
@NotNull
@JvmSynthetic
fun ByteArray.toJsonList(): JsonList {
    return JsonList.from(this@toJsonList.toList())
}

/**
 * Converts this [ShortArray] to a [JsonList].
 *
 * @return A new [JsonList] containing the short values of the array.
 */
@NotNull
@JvmSynthetic
fun ShortArray.toJsonList(): JsonList {
    return JsonList.from(this@toJsonList.toList())
}

/**
 * Converts this [IntArray] to a [JsonList].
 *
 * @return A new [JsonList] containing the integer values of the array.
 */
@NotNull
@JvmSynthetic
fun IntArray.toJsonList(): JsonList {
    return JsonList.from(this@toJsonList.toList())
}

/**
 * Converts this [DoubleArray] to a [JsonList].
 *
 * @return A new [JsonList] containing the double values of the array.
 */
@NotNull
@JvmSynthetic
fun DoubleArray.toJsonList(): JsonList {
    return JsonList.from(this@toJsonList.toList())
}

/**
 * Converts this [FloatArray] to a [JsonList].
 *
 * @return A new [JsonList] containing the float values of the array.
 */
@NotNull
@JvmSynthetic
fun FloatArray.toJsonList(): JsonList {
    return JsonList.from(this@toJsonList.toList())
}

/**
 * Converts this [LongArray] to a [JsonList].
 *
 * @return A new [JsonList] containing the long values of the array.
 */
@NotNull
@JvmSynthetic
fun LongArray.toJsonList(): JsonList {
    return JsonList.from(this@toJsonList.toList())
}

/**
 * Converts this [BooleanArray] to a [JsonList].
 *
 * @return A new [JsonList] containing the boolean values of the array.
 */
@NotNull
@JvmSynthetic
fun BooleanArray.toJsonList(): JsonList {
    return JsonList.from(this@toJsonList.toList())
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