package s4jk.jvm.json

import kotlin.random.Random

object JSUtils {

    @JvmStatic
    fun generateName(name: String?): String {
        if (name.isNullOrEmpty()) {
            return Random.nextLong().hashCode().toString(8)
        }
        return name
    }
}