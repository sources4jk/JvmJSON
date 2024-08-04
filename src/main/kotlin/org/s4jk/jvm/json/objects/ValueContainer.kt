package org.s4jk.jvm.json.objects

import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable

class ValueContainer<T: Any?>(private val value: T) {

    @NotNull
    @Throws(ClassCastException::class)
    fun asString(): String {
        return this.value.toString()
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
    fun asArray(): IJA {
        return this.value as IJA
    }

    @NotNull
    @Throws(ClassCastException::class)
    fun asNullable(): Any? {
        return null
    }

    @Nullable
    fun asAny(): Any? {
        return this.value
    }

    @NotNull
    override fun toString(): String {
        return this.value.toString()
    }

    companion object Static {
        val NULL = ValueContainer<Any?>(null)
    }
}