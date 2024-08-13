package org.s4jk.jvm.json

import org.jetbrains.annotations.NotNull
import org.s4jk.jvm.json.core.JsonList
import org.s4jk.jvm.json.core.JsonObject
import org.s4jk.jvm.json.core.JsonValue

/**
 * Constructs a [JsonObject] from a vararg of key-value pairs.
 * Each value is recognized as a [JsonValue] by the [JsonValue.recognize] method.
 *
 * @param entries A vararg of key-value pairs where each key is a [String] and each value can be of [Any] type.
 * @return A [JsonObject] containing the given entries.
 */
@NotNull
@JvmSynthetic
fun jsonObjectOf(@NotNull vararg entries: Pair<String, Any?>): JsonObject {
    return JsonObject(entries.associate { it.first to JsonValue.recognize(it.second) })
}

/**
 * Constructs a [JsonObject] using a DSL-style [JsonObject] initialization.
 *
 * @param buildAction The initialization block where entries can be added to the [JsonObject].
 * @return A [JsonObject] built from the provided [buildAction].
 */
@NotNull
@JvmSynthetic
fun jsonObjectOf(@NotNull buildAction: JsonObject.() -> Unit): JsonObject {
    return JsonObject().apply(buildAction)
}

/**
 * Converts a [Map] to a [JsonObject].
 *
 * @receiver The [Map] to be converted.
 * @return A [JsonObject] representing the map's key-value pairs.
 */
@NotNull
@JvmSynthetic
fun Map<*, *>.toJsonObject(): JsonObject {
    return JsonObject(this@toJsonObject)
}

/**
 * Parses a JSON string into a [JsonObject].
 *
 * @receiver The JSON string to parse.
 * @return A [JsonObject] parsed from the string.
 */
@NotNull
@JvmSynthetic
fun String.toJsonObject(): JsonObject {
    return JsonObject(this@toJsonObject)
}

/**
 * Constructs a [JsonList] from a vararg of elements.
 * Each element is recognized as a [JsonValue] by the [JsonValue.recognize] method.
 *
 * @param elements A vararg of elements to be added to the list.
 * @return A [JsonList] containing the given elements.
 */
@NotNull
@JvmSynthetic
fun jsonListOf(vararg elements: Any?): JsonList {
    return JsonList(elements.map { JsonValue.recognize(it) })
}

/**
 * Constructs a [JsonList] using a DSL-style [JsonList] initialization.
 *
 * @param buildAction The initialization block where elements can be added to the [JsonList].
 * @return A [JsonList] built from the provided [buildAction].
 */
@NotNull
@JvmSynthetic
fun jsonListOf(buildAction: JsonList.() -> Unit): JsonList {
    return JsonList().apply(buildAction)
}

/**
 * Converts a [Collection] to a [JsonList].
 *
 * @receiver The [Collection] to be converted.
 * @return A [JsonList] representing the collection's elements.
 */
@NotNull
@JvmSynthetic
fun Collection<*>.toJsonList(): JsonList {
    return JsonList(this@toJsonList)
}

/**
 * Converts an [Array] to a [JsonList].
 *
 * @receiver The [Array] to be converted.
 * @return A [JsonList] representing the array's elements.
 */
@NotNull
@JvmSynthetic
fun Array<*>.toJsonList(): JsonList {
    return JsonList(this@toJsonList)
}

/**
 * Converts a [CharArray] to a [JsonList].
 * Each character in the array is converted to a [String] before being added to the list.
 *
 * @receiver The [CharArray] to be converted.
 * @return A [JsonList] representing the character array's elements.
 */
@NotNull
@JvmSynthetic
fun CharArray.toJsonList(): JsonList {
    return JsonList(this@toJsonList.map { it.toString() }.toTypedArray())
}

/**
 * Converts a [ByteArray] to a [JsonList].
 *
 * @receiver The [ByteArray] to be converted.
 * @return A [JsonList] representing the byte array's elements.
 */
@NotNull
@JvmSynthetic
fun ByteArray.toJsonList(): JsonList {
    return JsonList(this@toJsonList.toTypedArray())
}

/**
 * Converts a [ShortArray] to a [JsonList].
 *
 * @receiver The [ShortArray] to be converted.
 * @return A [JsonList] representing the short array's elements.
 */
@NotNull
@JvmSynthetic
fun ShortArray.toJsonList(): JsonList {
    return JsonList(this@toJsonList.toTypedArray())
}

/**
 * Converts an [IntArray] to a [JsonList].
 *
 * @receiver The [IntArray] to be converted.
 * @return A [JsonList] representing the int array's elements.
 */
@NotNull
@JvmSynthetic
fun IntArray.toJsonList(): JsonList {
    return JsonList(this@toJsonList.toTypedArray())
}

/**
 * Converts a [DoubleArray] to a [JsonList].
 *
 * @receiver The [DoubleArray] to be converted.
 * @return A [JsonList] representing the double array's elements.
 */
@NotNull
@JvmSynthetic
fun DoubleArray.toJsonList(): JsonList {
    return JsonList(this@toJsonList.toTypedArray())
}

/**
 * Converts a [FloatArray] to a [JsonList].
 * Each float value in the array is converted to a double before being added to the list.
 *
 * @receiver The [FloatArray] to be converted.
 * @return A [JsonList] representing the float array's elements as doubles.
 */
@NotNull
@JvmSynthetic
fun FloatArray.toJsonList(): JsonList {
    return JsonList(this@toJsonList.map { it.toDouble() }.toTypedArray())
}

/**
 * Converts a [LongArray] to a [JsonList].
 *
 * @receiver The [LongArray] to be converted.
 * @return A [JsonList] representing the long array's elements.
 */
@NotNull
@JvmSynthetic
fun LongArray.toJsonList(): JsonList {
    return JsonList(this@toJsonList.toTypedArray())
}

/**
 * Converts a [BooleanArray] to a [JsonList].
 *
 * @receiver The [BooleanArray] to be converted.
 * @return A [JsonList] representing the boolean array's elements.
 */
@NotNull
@JvmSynthetic
fun BooleanArray.toJsonList(): JsonList {
    return JsonList(this@toJsonList.toTypedArray())
}

/**
 * Parses a JSON string into a [JsonList].
 *
 * @receiver The JSON string to parse.
 * @return A [JsonList] parsed from the string.
 */
@NotNull
@JvmSynthetic
fun String.toJsonList(): JsonList {
    return JsonList(this@toJsonList)
}