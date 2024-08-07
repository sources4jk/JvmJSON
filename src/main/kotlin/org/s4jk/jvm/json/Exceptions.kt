package org.s4jk.jvm.json

import org.s4jk.jvm.json.core.jsonListOf
import org.s4jk.jvm.json.core.jsonObjectOf

class IllegalJsonValueTypeException(value: Any?):
    RuntimeException("Invalid value type: ${value?.javaClass?.simpleName}")

class IllegalJsonStringParsingException(message: String): RuntimeException(message)


fun main() {
    val json = jsonObjectOf {
        "key1" to 123456789
        "key2" to "str pizdec"
        "key3" to true
        "key4" to false
        "key5" to null
        "key6" to jsonListOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
        "key7" to jsonObjectOf {
            "key1" to true
            "key2" to "huinya"
            "key3" to jsonListOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
        }
    }

    println(json.toString(4))

}