package org.s4jk.jvm.json

import org.s4jk.jvm.json.objects.IJA
import org.s4jk.jvm.json.objects.IJO
import org.s4jk.jvm.json.objects.ValueContainer
import kotlin.random.Random

object JsonUtils {

    @JvmStatic
    fun generateName(name: String?): String {
        if (name.isNullOrEmpty()) {
            return Random.nextLong().hashCode().toString(8)
        }
        return name
    }

    @JvmStatic
    fun resolveJsonValue(value: Any?): Any? {
        return when(value) {
            is String -> value
            is Number -> value
            is Boolean -> value
            is IJO -> value
            is IJA -> value
            is ValueContainer<*> -> resolveJsonValue(value.asAny())
            null -> null
            else -> throw IllegalValueTypeException(value)
        }
    }
}
