package org.s4jk.jvm.json.core

import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable
import org.s4jk.jvm.json.IllegalJsonValueTypeException

class JsonValue(private val value: Any?) {

    @NotNull
    @Throws(ClassCastException::class)
    fun asString(): String {
        return this.value as String
    }

    @NotNull
    @Throws(ClassCastException::class)
    fun asByte(): Byte {
        return this.value as Byte
    }

    @NotNull
    @Throws(ClassCastException::class)
    fun asShort(): Short {
        return this.value as Short
    }

    @NotNull
    @Throws(ClassCastException::class)
    fun asInt(): Int {
        return this.value as Int
    }

    @NotNull
    @Throws(ClassCastException::class)
    fun asDouble(): Double {
        return this.value as Double
    }

    @NotNull
    @Throws(ClassCastException::class)
    fun asLong(): Long {
        return this.value as Long
    }

    @NotNull
    @Throws(ClassCastException::class)
    fun asNumber(): Number {
        return this.value as Number
    }

    @NotNull
    @Throws(ClassCastException::class)
    fun asBoolean(): Boolean {
        return this.value as Boolean
    }

    @NotNull
    @Throws(ClassCastException::class)
    fun asObject(): JsonObject {
        return this.value as JsonObject
    }

    @NotNull
    @Throws(ClassCastException::class)
    fun asList(): JsonList {
        return this.value as JsonList
    }

    @NotNull
    @Throws(IllegalArgumentException::class)
    fun asNull(): Any? {
        if (this.value != null) {
            throw IllegalArgumentException("${value::class.java.simpleName} is not Null!")
        }
        return value
    }

    @Nullable
    fun asAny(): Any? {
        return this.value
    }

    @NotNull
    override fun toString(): String {
        return "JsonValue { name: ${this.value?.javaClass?.simpleName}, value: ${this.value} }"
    }

    companion object Static {

        @get:Nullable
        @JvmStatic
        val Null = JsonValue(null)

        @NotNull
        @JvmStatic
        fun handle(value: Any?): JsonValue {
            return when (value) {
                is String -> {
                    JsonValue(value)
                }

                is Byte -> {
                    JsonValue(value)
                }

                is Short -> {
                    JsonValue(value)
                }

                is Int -> {
                    JsonValue(value)
                }

                is Double -> {
                    JsonValue(value)
                }

                is Long -> {
                    JsonValue(value)
                }

                is Boolean -> {
                    JsonValue(value)
                }

                is JsonObject -> {
                    JsonValue(value)
                }

                is JsonList -> {
                    JsonValue(value)
                }

                is JsonValue -> {
                    this.handle(value.asAny())
                }

                null -> {
                    this.Null
                }

                else -> {
                    throw IllegalJsonValueTypeException(value)
                }
            }
        }
    }
}