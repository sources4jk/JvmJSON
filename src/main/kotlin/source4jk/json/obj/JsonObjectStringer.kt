package source4jk.json.obj

import source4jk.json.IllegalJsonStringException
import source4jk.json.IllegalValueTypeException
import source4jk.json.array.JsonArray
import source4jk.json.array.JsonArrayStringer

object JsonObjectStringer {

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
            is JsonObject -> jsonObjectToString(value, indent, depth)
            is JsonArray -> JsonArrayStringer.jsonArrayToString(value, indent, depth)
            null -> "null"
            else -> throw IllegalValueTypeException()
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

            while (index < length) {
                skipWhitespace()

                val key = parseString()

                skipWhitespace()
                requireChar(':')
                val value = parseValue()
                json.set(key, value)

                skipWhitespace()
                when {
                    index < length && source[index] == '}' -> {
                        index++
                        return json
                    }

                    index < length && source[index] == ',' -> index++
                    else -> throw IllegalJsonStringException("Expected '}' or ',' at position $index")
                }
            }
            throw IllegalJsonStringException("Unexpected end of input")
        }

        private fun parseString(): String {
            requireChar('"')
            val start = index

            while (index < length && source[index] != '"') {
                index++
            }

            if (index >= length) {
                throw IllegalJsonStringException("Unterminated string at position $index")
            }

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
                index < length && (source[index] == '-' || source[index].isDigit()) -> parseNumber()
                else -> throw IllegalJsonStringException("Unexpected character at position $index")
            }
        }

        private fun parseBoolean(): Boolean {
            return when {
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
                when (source[index]) {
                    '.' -> {
                        if (hasDecimalPoint) throw IllegalJsonStringException("Multiple decimal points in number at position $index")
                        hasDecimalPoint = true
                    }

                    'e', 'E' -> {
                        if (hasExponent) throw IllegalJsonStringException("Multiple exponents in number at position $index")
                        hasExponent = true
                    }
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

        private fun requireChar(expected: Char) {
            if (index >= length || source[index] != expected) {
                throw IllegalJsonStringException("Expected '$expected' at position $index")
            }
            index++
        }
    }

}