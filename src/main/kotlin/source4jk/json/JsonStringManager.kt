package source4jk.json

/*
Example:
    fun main() {
    val json = { ... }
    println(json.toString(intent = 4))
}
Output:
{
    "key1": 123456789,
    "key2": "string value",
    "key3": true,
    "key4": false,
    "key5": null,
    "key6": {
        "key7": 987654321
    },
    "key8": {
        "key9": {}
    }
}
*/


object JsonStringManager {

    fun jsonObjectToString(json: JsonObject, indent: Int, depth: Int): String {
        val spaces = " ".repeat(indent * depth)

        if (json.entries.isEmpty()) {
            return "{}"
        }

        return buildString {
            append("{")
            if (indent > 0) {
                append("\n")
            } else {
                append(" ")
            }

            json.entries.forEachIndexed { index, entry ->
                val value = entry.value

                append("$spaces\"${entry.key}\": ")
                append(jsonValueToString(value, indent, depth + 1))

                if (index != json.entries.size - 1) {
                    append(", ")
                }

                if (indent > 0) {
                    append("\n")
                }
            }
            if (indent > 0) {
                append(" ".repeat(indent * (depth - 1)))
            } else {
                append(" ")
            }
            append("}")
        }
    }

    private fun jsonValueToString(value: Any?, indent: Int, depth: Int): String {
        return when (value) {
            is Number, is Boolean -> value.toString()
            is String -> "\"$value\""
            is JsonObject -> jsonObjectToString(value, indent, depth)
            null -> "null"
            else -> throw InvalidJsonValueTypeException()
        }
    }
}