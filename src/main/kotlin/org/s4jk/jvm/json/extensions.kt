package org.s4jk.jvm.json

import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable
import org.s4jk.jvm.json.core.JsonList
import org.s4jk.jvm.json.core.JsonObject
import org.s4jk.jvm.json.core.JsonValue

@NotNull
@JvmSynthetic
fun jsonObjectOf(@Nullable name: String? = null, @NotNull buildAction: JsonObject.Constructor.() -> Unit): JsonObject {
    val builder = JsonObject.Constructor().apply { this.buildAction() }
    return JsonObject.from(name, builder.map)
}

@NotNull
@JvmSynthetic
fun jsonObjectOf(@Nullable name: String? = null, @NotNull vararg entries: Pair<String, Any?>): JsonObject {
    return JsonObject.from(name, entries.associate { (key, value) -> key to JsonValue.handle(value) }.toMutableMap())
}

@NotNull
@JvmSynthetic
fun Map<*, *>.toJsonObject(@Nullable name: String? = null): JsonObject {
    return JsonObject.from(name, this)
}

@NotNull
@JvmSynthetic
fun String.toJsonObject(@Nullable name: String? = null): JsonObject {
    return JsonObject.from(name, this)
}


@NotNull
@JvmSynthetic
fun jsonListOf(vararg elements: Any?): JsonList {
    return JsonList.from(elements.toList())
}

@NotNull
@JvmSynthetic
fun Array<*>.toJsonList(): JsonList {
    return JsonList.from(this@toJsonList)
}

@NotNull
@JvmSynthetic
fun List<*>.toJsonList(): JsonList {
    return JsonList.from(this@toJsonList)
}

@NotNull
@JvmSynthetic
fun Set<*>.toJsonList(): JsonList {
    return JsonList.from(this@toJsonList)
}

@NotNull
@JvmSynthetic
fun String.toJsonList(): JsonList {
    return JsonList.from(this@toJsonList)
}