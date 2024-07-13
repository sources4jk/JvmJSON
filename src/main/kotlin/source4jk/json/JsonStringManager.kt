package source4jk.json

object JsonStringManager {
    object ObjectToString {
        fun jsonToString(json: JsonObject, indent: Int, depth: Int): String {
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

                    if (indent > 0) {
                        append(spaces)
                    }

                    append("\"${entry.key}\": ")
                    append(valueToString(value, indent, depth + 1))

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

        private fun valueToString(value: Any?, indent: Int, depth: Int): String {
            return when (value) {
                is Number, is Boolean -> value.toString()
                is String -> "\"$value\""
                is JsonObject -> jsonToString(value, indent, depth)
                null -> "null"
                else -> throw InvalidJsonValueTypeException()
            }
        }
    }

    object StringToObject {

        fun stringToJsonObject(string: String, modes: Set<JsonAccessMode>): JsonObject {
            val json = JsonObject.empty(modes)

            return json
        }
    }
}