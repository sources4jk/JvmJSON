package s4jk.jvm.serialization

import kotlin.random.Random

object JSUtils {

    fun generateName(name: String?): String {
        if (name.isNullOrEmpty()) {
            return Random.nextLong().hashCode().toString(8)
        }
        return name
    }
}