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
    fun asObject(): IJO {
        return this.value as IJO
    }

    @NotNull
    @Throws(ClassCastException::class)
    fun asList(): IJL {
        return this.value as IJL
    }

    @NotNull
    @Throws(IllegalArgumentException::class)
    fun asNullable(): Any? {
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
        return "Value={ ${this.value} }"
    }

    companion object Static {

        @get:Nullable
        @JvmStatic
        val Null = JsonValue(null)

        @NotNull
        @JvmStatic
        fun handle(value: Any?): JsonValue {
            return when(value) {
                is String -> JsonValue(value)
                is Number -> JsonValue(value)
                is Boolean -> JsonValue(value)
                is IJO -> JsonValue(value)
                is IJL -> JsonValue(value)
                is JsonValue -> handle(value.asAny())
                null -> this.Null
                else -> throw IllegalJsonValueTypeException(value)
            }
        }
    }
}