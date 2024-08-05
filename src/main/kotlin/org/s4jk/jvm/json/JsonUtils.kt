package org.s4jk.jvm.json

import kotlin.random.Random

object JsonUtils {

    @JvmStatic
    fun generateName(name: String?): String {
        if (name.isNullOrEmpty()) {
            return Random.nextLong().hashCode().toString(8)
        }
        return name
    }
}
