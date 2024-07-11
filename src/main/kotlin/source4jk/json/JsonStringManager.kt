package source4jk.json

object JsonStringManager {

    fun jsonObjectToString(json: JsonObject, indent: Int, depth: Int): String {
        val spaces = " ".repeat(indent * depth)

        if (json.entries.isEmpty()) {
            return "{}"
        }

        return buildString {
            append("{\n")
            json.entries.forEachIndexed { index, entry ->
                val value = entry.value

                append("$spaces\"${entry.key}\": ")
                append(jsonValueToString(value, indent, depth + 1))

                if (index != json.entries.size - 1) {
                    append(",")
                }

                append("\n")
            }
            append(" ".repeat(indent * (depth - 1)))
            append("}")
        }
    }

    private fun jsonValueToString(value: Any?, indent: Int, depth: Int): String {
        return when (value) {
            is Number, is Boolean -> value.toString()
            is String -> "\"$value\""
            is JsonObject -> jsonObjectToString(value, indent, depth)
            null -> "null"
            else -> error("asd")
        }
    }
}