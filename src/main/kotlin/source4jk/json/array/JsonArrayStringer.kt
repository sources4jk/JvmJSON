package source4jk.json.array

import source4jk.json.IllegalValueTypeException
import source4jk.json.obj.JsonObject
import source4jk.json.obj.JsonObjectStringer

object JsonArrayStringer {

    fun jsonArrayToString(array: JsonArray, indent: Int, depth: Int): String {
        val spaces = " ".repeat(indent * depth)

        if (array.values.isEmpty()) {
            return "[]"
        }

        return buildString {
            append("[")

            if (indent > 0) {
                append("\n")
            } else {
                append(" ")
            }

            array.values.forEachIndexed { index, value ->
                if (indent > 0) {
                    append(spaces)
                }

                append(JsonArrayStringer.valueToString(value, indent, depth + 1))


                if (index != array.values.size - 1) {
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
            append("]")
        }
    }

    private fun valueToString(value: Any?, indent: Int, depth: Int): String {
        return when (value) {
            is Number, is Boolean -> value.toString()
            is String -> "\"$value\""
            is JsonObject -> JsonObjectStringer.jsonObjectToString(value, indent, depth)
            is JsonArray -> value.toString(indent)
            null -> "null"
            else -> throw IllegalValueTypeException()
        }
    }
}