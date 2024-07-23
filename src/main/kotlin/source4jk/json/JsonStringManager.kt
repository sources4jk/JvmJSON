package source4jk.json

object JsonStringManager {

    fun <K, V> jsonObjectToString(json: IJO<K, V>, indent: Int, depth: Int): String {
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

    private fun <V> valueToString(value: V, indent: Int, depth: Int): String {
        return when (value) {
            is Number, is Boolean -> value.toString()
            is String -> "\"$value\""
            is JsonObject -> jsonObjectToString(value, indent, depth)
            null -> "null"
            else -> throw IllegalJsonValueTypeException()
        }
    }


    fun stringToJsonObject(source: String): JsonObject {
        return StringParser(source).parseObject()
    }

    private class StringParser(private val source: String) {
        private var index = 0
        private val length = source.length

        fun parseObject(): JsonObject {
            val json = JsonObject.empty()
            index++
            skipWhitespace()

            if (index < length && source[index] == '}') {
                index++
                return json
            }

            while (true) {
                skipWhitespace()

                val key = parseString()

                skipWhitespace()

                if (index < length && source[index] == ':') {
                    index++
                    val value = parseValue()

                    json.set(key, value)

                    skipWhitespace()

                    if (index < length && source[index] == '}') {
                        index++
                        return json

                    } else if (index < length && source[index] == ',') {
                        index++
                    } else {
                        throw IllegalJsonStringException("Expected '}' or ',' at position $index")
                    }
                } else {
                    throw IllegalJsonStringException("Expected ':' at position $index")
                }
            }
        }

        private fun parseString(): String {
            index++
            val start = index

            while (index < length && source[index] != '"') {
                index++
            }

            if (index >= length) throw IllegalJsonStringException("Unterminated string at position $index")

            val result = source.substring(start, index)
            index++

            return result
        }

        private fun parseValue(): Any? {
            skipWhitespace()
            return when {
                index < length && source[index] == '{' -> parseObject()
                index < length && source[index] == '"' -> parseString()
                index < length && (source[index] == 't' || source[index] == 'f') -> parseBoolean()
                index < length && source[index] == 'n' -> parseNull()
                source.substring(index).startsWith("-") || source[index].isDigit() -> parseNumber()
                else -> throw IllegalJsonStringException("Unexpected character at position $index")
            }
        }

        private fun parseBoolean(): Boolean {
            val value = when {
                source.startsWith("true", index) -> {
                    index += 4
                    true
                }
                source.startsWith("false", index) -> {
                    index += 5
                    false
                }
                else -> throw IllegalJsonStringException("Unexpected value at position $index")
            }
            return value
        }

        private fun parseNull(): Any? {
            if (source.startsWith("null", index)) {
                index += 4
                return null
            } else {
                throw IllegalJsonStringException("Unexpected value at position $index")
            }
        }

        private fun parseNumber(): Number {
            val start = index
            var hasDecimalPoint = false
            var hasExponent = false

            while (index < length && (source[index].isDigit() || source[index] == '.' || source[index] == '-' || source[index] == 'e' || source[index] == 'E')) {
                if (source[index] == '.') {
                    if (hasDecimalPoint) throw IllegalJsonStringException("Multiple decimal points in number at position $index")
                    hasDecimalPoint = true
                }
                if (source[index] == 'e' || source[index] == 'E') {
                    if (hasExponent) throw IllegalJsonStringException("Multiple exponents in number at position $index")
                    hasExponent = true
                }
                index++
            }

            val numberStr = source.substring(start, index)
            return if (hasDecimalPoint || hasExponent) {
                numberStr.toDouble()
            } else {
                numberStr.toLong()
            }
        }

        private fun skipWhitespace() {
            while (index < length && source[index].isWhitespace()) {
                index++
            }
        }
    }

}