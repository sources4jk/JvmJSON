package org.s4jk.jvm.json

import org.jetbrains.annotations.NotNull
import org.s4jk.jvm.json.core.JsonList
import org.s4jk.jvm.json.core.JsonObject

@NotNull
@JvmSynthetic
fun jsonObjectOf(@NotNull buildAction: JsonObject.() -> Unit): JsonObject {
    return JsonObject().apply(buildAction)
}

@NotNull
@JvmSynthetic
fun Map<*, *>.toJsonObject(): JsonObject {
    return JsonObject(this@toJsonObject)
}

@NotNull
@JvmSynthetic
fun String.toJsonObject(): JsonObject {
    return JsonObject(this@toJsonObject)
}

@NotNull
@JvmSynthetic
fun jsonListOf(vararg elements: Any?): JsonList {
    return JsonList.from(elements.toList())
}

@NotNull
@JvmSynthetic
fun Collection<*>.toJsonList(): JsonList {
    return JsonList.from(this@toJsonList)
}

@NotNull
@JvmSynthetic
fun Array<*>.toJsonList(): JsonList {
    return JsonList.from(this@toJsonList.toList())
}

@NotNull
@JvmSynthetic
fun CharArray.toJsonList(): JsonList {
    return JsonList.from(this@toJsonList.map { it.toString() }.toList())
}

@NotNull
@JvmSynthetic
fun ByteArray.toJsonList(): JsonList {
    return JsonList.from(this@toJsonList.toList())
}

@NotNull
@JvmSynthetic
fun ShortArray.toJsonList(): JsonList {
    return JsonList.from(this@toJsonList.toList())
}

@NotNull
@JvmSynthetic
fun IntArray.toJsonList(): JsonList {
    return JsonList.from(this@toJsonList.toList())
}

@NotNull
@JvmSynthetic
fun DoubleArray.toJsonList(): JsonList {
    return JsonList.from(this@toJsonList.toList())
}


@NotNull
@JvmSynthetic
fun FloatArray.toJsonList(): JsonList {
    return JsonList.from(this@toJsonList.map { it.toDouble() }.toList())
}

@NotNull
@JvmSynthetic
fun LongArray.toJsonList(): JsonList {
    return JsonList.from(this@toJsonList.toList())
}

@NotNull
@JvmSynthetic
fun BooleanArray.toJsonList(): JsonList {
    return JsonList.from(this@toJsonList.toList())
}

@NotNull
@JvmSynthetic
fun String.toJsonList(): JsonList {
    return JsonList.from(this@toJsonList)
}