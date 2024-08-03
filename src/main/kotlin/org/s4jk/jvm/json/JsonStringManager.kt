package org.s4jk.jvm.json

import org.s4jk.jvm.json.objects.IJA
import org.s4jk.jvm.json.objects.JsonArray
import org.s4jk.jvm.json.objects.IJO
import org.s4jk.jvm.json.objects.JsonObject

object JsonStringManager {

    @JvmStatic
    fun jsonObjectToString(json: IJO, indent: Int, depth: Int): String {
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

    @JvmStatic
    fun jsonArrayToString(array: IJA, indent: Int, depth: Int): String {
        val spaces = " ".repeat(indent * depth)

        if (array.isEmpty()) {
            return "[]"
        }

        return buildString {
            append("[")

            if (indent > 0) {
                append("\n")
            } else {
                append(" ")
            }

            array.forEachIndexed { index, value ->
                if (indent > 0) {
                    append(spaces)
                }

                append(valueToString(value, indent, depth + 1))


                if (index != array.size - 1) {
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
            is JsonObject -> jsonObjectToString(value, indent, depth)
            is JsonArray -> jsonArrayToString(value, indent, depth)
            null -> "null"
            else -> throw IllegalValueTypeException()
        }
    }

    @JvmStatic
    fun stringToJsonObject(name: String?, source: String): IJO {
        return StringParser(name, source).parseObject()
    }

    @JvmStatic
    fun stringToJsonArray(source: String): IJA {
        return StringParser("", source).parseArray()
    }

    private class StringParser(private val name: String?, private val source: String) {
        private var index = 0
        private val length = source.length

        fun parseObject(): IJO {
            val json = JsonObject.create(name)
            index++
            this.skipWhitespace()

            if (index < length && source[index] == '}') {
                index++
                return json
            }

            while (index < length) {
                this.skipWhitespace()
                val key = this.parseString()

                this.skipWhitespace()
                this.requireChar(':')

                val value = this.parseValue()
                json.set(key, value)
                this.skipWhitespace()

                when {
                    index < length && source[index] == '}' -> {
                        index++
                        return json
                    }

                    index < length && source[index] == ',' -> index++
                    else -> throw IllegalJsonStringParserException("Expected '}' or ',' at position $index")
                }
            }
            throw IllegalJsonStringParserException("Unexpected end of input")
        }

        fun parseArray(): IJA {
            val array = JsonArray.create()
            index++
            this.skipWhitespace()

            if (index < length && source[index] == ']') {
                index++
                return array
            }

            while (index < length) {
                this.skipWhitespace()
                val value = this.parseValue()
                array.add(value)

                this.skipWhitespace()
                when {
                    index < length && source[index] == ']' -> {
                        index++
                        return array
                    }

                    index < length && source[index] == ',' -> index++
                    else -> throw IllegalJsonStringParserException("Expected ']' or ',' at position $index")
                }
            }
            throw IllegalJsonStringParserException("Unexpected end of input")
        }

        private fun parseString(): String {
            this.requireChar('"')
            val start = index

            while (index < length && source[index] != '"') {
                index++
            }

            if (index >= length) {
                throw IllegalJsonStringParserException("Unterminated string at position $index")
            }

            val result = source.substring(start, index)
            index++
            return result
        }

        private fun parseValue(): Any? {
            this.skipWhitespace()
            return when {
                source[index] in "-0123456789" -> this.parseNumber()
                source.startsWith("\"", index) -> this.parseString()
                source.startsWith("true", index) || source.startsWith("false", index) -> this.parseBoolean()
                source.startsWith("null", index) -> this.parseNull()
                source.startsWith("{", index) -> this.parseObject()
                source.startsWith("[", index) -> this.parseArray()
                else -> throw IllegalJsonStringParserException("Unexpected character at position $index")
            }
        }

        private fun parseBoolean(): Boolean {
            return if (source.startsWith("true", index)) {
                index += 4
                true
            } else if (source.startsWith("false", index)) {
                index += 5
                false
            } else {
                throw IllegalJsonStringParserException("Unexpected value at position $index")
            }
        }

        private fun parseNull(): Any? {
            if (source.startsWith("null", index)) {
                index += 4
                return null
            } else {
                throw IllegalJsonStringParserException("Unexpected value at position $index")
            }
        }

        private fun parseNumber(): Number {
            val start = index
            var hasDecimalPoint = false
            var hasExponent = false

            while (index < length && source[index] in "-0123456789.eE") {
                when (source[index]) {
                    '.' -> {
                        if (hasDecimalPoint) throw IllegalJsonStringParserException("Multiple decimal points in number at position $index")
                        hasDecimalPoint = true
                    }

                    'e', 'E' -> {
                        if (hasExponent) throw IllegalJsonStringParserException("Multiple exponents in number at position $index")
                        hasExponent = true
                    }
                }
                index++
            }

            val numberStr = source.substring(start, index)
            return if (hasDecimalPoint || hasExponent) numberStr.toDouble() else numberStr.toLong()
        }

        private fun skipWhitespace() {
            while (index < length && source[index].isWhitespace()) {
                index++
            }
        }

        private fun requireChar(expected: Char) {
            if (index >= length || source[index] != expected) {
                throw IllegalJsonStringParserException("Expected '$expected' at position $index")
            }
            index++
        }
    }


}