package source4jk.json

fun main() {
    val json1 = JsonObject.create {
        "key1" set 123456789
        "key2" set "string value"
        "key3" set true
        "key4" set false
        "key5" set JsonObject.create {  }
    }

    val testMap = mutableMapOf<String, Any?>()
    val json2 = JsonObject.from(testMap)
}