package org.s4jk.jvm.json.core

import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable
import org.s4jk.jvm.json.IllegalJsonValueTypeException
import org.s4jk.jvm.json.JsonValueNullException
import org.s4jk.jvm.json.JsonValueTypeCastException

class JsonValue(private val value: Any?) {

    @NotNull
    @Throws(JsonValueTypeCastException::class)
    fun asString(): String {
        return this.castToType(this.value)
    }

    @NotNull
    @Throws(JsonValueTypeCastException::class)
    fun asNumber(): Number {
        return this.castToType(this.value)
    }

    @NotNull
    @Throws(JsonValueTypeCastException::class)
    fun asBoolean(): Boolean {
        return this.castToType(this.value)
    }

    @NotNull
    @Throws(JsonValueTypeCastException::class)
    fun asJsonObject(): JsonObject {
        return this.castToType(this.value)
    }

    @NotNull
    @Throws(JsonValueTypeCastException::class)
    fun asJsonList(): JsonList {
        return this.castToType(this.value)
    }

    @Nullable
    @Throws(JsonValueTypeCastException::class)
    fun asNull(): Any? {
        if (this.value != null) {
            throw JsonValueTypeCastException(value, Nothing::class)
        }
        return null
    }

    @Nullable
    fun asAny(): Any? {
        return this.value
    }

    @NotNull
    @Throws(JsonValueTypeCastException::class)
    private inline fun <reified T> castToType(value: Any?): T {
        if (value == null) {
            throw JsonValueNullException()
        }

        if (value !is T) {
            throw JsonValueTypeCastException(value, T::class)
        }

        return value
    }

    companion object {
        val NULL = JsonValue(null)

        @NotNull
        @JvmStatic
        @Throws(IllegalJsonValueTypeException::class)
        fun recognize(value: Any?): JsonValue {
            return when (value) {
                is String, is Number, is Boolean, is JsonObject, is JsonList -> {
                    JsonValue(value)
                }

                is JsonValue -> {
                    this.recognize(value.asAny())
                }

                null -> {
                    this.NULL
                }

                else -> {
                    throw IllegalJsonValueTypeException(value)
                }
            }
        }

    }
}