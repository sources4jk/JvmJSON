package org.s4jk.jvm.json

import org.s4jk.jvm.json.core.IJL
import org.s4jk.jvm.json.core.IJO
import org.s4jk.jvm.json.core.JsonList
import org.s4jk.jvm.json.core.JsonObject
import org.s4jk.jvm.json.core.JsonValue


object JsonStringManager {

    @JvmStatic
    fun jsonObjectToString(json: IJO, indent: Int, depth: Int): String {
        if (json.isEmpty()) {
            return "{}"
        }

        val spaces: (Int) -> String = { if (indent > 0) " ".repeat(indent * it) else " " }
        val tab = if (indent > 0) "\n" else ""

        return buildString {
            append("{").append(tab)

            json.entries.forEachIndexed { index, (key, value) ->

                append(spaces(depth))
                append("\"${key}\": ").append(this@JsonStringManager.valueToString(value, indent, depth + 1))

                if (index != json.entries.size - 1) {
                    append(",")
                }

                append(tab)

            }
            append(spaces(depth - 1)).append("}")
        }
    }

    @JvmStatic
    fun jsonListToString(list: IJL, indent: Int, depth: Int): String {
        if (list.isEmpty()) {
            return "[]"
        }

        val spaces: (Int) -> String = { if (indent > 0) " ".repeat(indent * it) else "" }
        val tab = if (indent > 0) "\n" else ""

        return buildString {
            append("[").append(tab)

            list.forEachIndexed { index, value ->

                append(spaces(depth)).append(this@JsonStringManager.valueToString(value, indent, depth + 1))

                if (index != list.size - 1) {
                    append(", ")
                }

                append(tab)

            }
            append(spaces(depth - 1)).append("]")
        }
    }

    private fun valueToString(value: JsonValue, indent: Int, depth: Int): String {
        return when (value.asAny()) {
            is Number -> {
                value.asNumber().toString()
            }

            is Boolean -> {
                value.asBoolean().toString()
            }

            is String -> {
                "\"${value.asString()}\""
            }

            is JsonObject -> {
                this.jsonObjectToString(value.asObject(), indent, depth)
            }

            is JsonList -> {
                this.jsonListToString(value.asList(), indent, depth)
            }

            null -> {
                "null"
            }

            else -> {
                throw IllegalJsonValueTypeException(value)
            }
        }
    }

    @JvmStatic
    fun stringToJsonObject(name: String?, source: String): IJO {
        return StringParser(name, source).parseObject()
    }

    @JvmStatic
    fun stringToJsonList(source: String): IJL {
        return StringParser("", source).parseList()
    }

    private class StringParser(private val name: String?, private val source: String) {
        private var index = 0
        private val length = source.length

        fun parseObject(): IJO {
            val json = JsonObject.create(name)
            this.index++
            this.skipWhitespace()

            if (this.index < length && source[this.index] == '}') {
                this.index++
                return json
            }

            while (this.index < length) {
                this.skipWhitespace()
                val key = this.parseString()

                this.skipWhitespace()
                this.requireChar(':')

                val value = this.parseValue()
                json[key] = value
                this.skipWhitespace()

                when {
                    this.index < length && source[this.index] == '}' -> {
                        this.index++
                        return json
                    }

                    this.index < length && source[this.index] == ',' -> this.index++
                    else -> throw IllegalJsonStringParsingException("Expected '}' or ',' at position $this.index")
                }
            }
            throw IllegalJsonStringParsingException("Unexpected end of input")
        }

        fun parseList(): IJL {
            val array = JsonList.create()
            this.index++
            this.skipWhitespace()

            if (this.index < length && source[this.index] == ']') {
                this.index++
                return array
            }

            while (this.index < length) {
                this.skipWhitespace()
                val value = this.parseValue()
                array.add(value)

                this.skipWhitespace()
                when {
                    this.index < length && source[this.index] == ']' -> {
                        this.index++
                        return array
                    }

                    this.index < length && source[this.index] == ',' -> this.index++
                    else -> throw IllegalJsonStringParsingException("Expected ']' or ',' at position $this.index")
                }
            }
            throw IllegalJsonStringParsingException("Unexpected end of input")
        }

        private fun parseString(): String {
            this.requireChar('"')
            val start = this.index

            while (this.index < length && source[this.index] != '"') {
                this.index++
            }

            if (this.index >= length) {
                throw IllegalJsonStringParsingException("Unterminated string at position $this.index")
            }

            val result = source.substring(start, this.index)
            this.index++
            return result
        }

        private fun parseValue(): Any? {
            this.skipWhitespace()
            return when {
                source[this.index] in "-0123456789" -> {
                    this.parseNumber()
                }

                source.startsWith("\"", this.index) -> {
                    this.parseString()
                }

                source.startsWith("true", this.index) || source.startsWith("false", this.index) -> {
                    this.parseBoolean()
                }

                source.startsWith("null", this.index) -> {
                    this.parseNull()
                }

                source.startsWith("{", this.index) -> {
                    this.parseObject()
                }

                source.startsWith("[", this.index) -> {
                    this.parseList()
                }

                else -> {
                    throw IllegalJsonStringParsingException("Unexpected character at position $this.index")
                }
            }
        }

        private fun parseBoolean(): Boolean {
            return when {
                source.startsWith("true", this.index) -> {
                    this.index += 4; true
                }

                source.startsWith("false", this.index) -> {
                    this.index += 5
                    false
                }

                else -> {
                    throw IllegalJsonStringParsingException("Unexpected value at position $this.index")
                }

            }
        }

        private fun parseNull(): Any? {
            return when {
                source.startsWith("null", this.index) -> {
                    this.index += 4
                    null
                }

                else -> {
                    throw IllegalJsonStringParsingException("Unexpected value at position $this.index")
                }
            }
        }

        private fun parseNumber(): Number {
            val start = this.index
            var hasDecimalPoint = false
            var hasExponent = false

            while (this.index < length && source[this.index] in "-0123456789.eE") {
                when (source[this.index]) {
                    '.' -> {
                        if (hasDecimalPoint) {
                            throw IllegalJsonStringParsingException("Multiple decimal points in number at position $this.index")
                        }
                        hasDecimalPoint = true
                    }

                    'e', 'E' -> {
                        if (hasExponent) {
                            throw IllegalJsonStringParsingException("Multiple exponents in number at position $this.index")
                        }
                        hasExponent = true
                    }
                }
                this.index++
            }

            val numberStr = source.substring(start, this.index)
            return if (hasDecimalPoint || hasExponent) numberStr.toDouble() else numberStr.toLong()
        }

        private fun skipWhitespace() {
            while (this.index < length && source[this.index].isWhitespace()) {
                this.index++
            }
        }

        private fun requireChar(expected: Char) {
            if (this.index >= length || source[this.index] != expected) {
                throw IllegalJsonStringParsingException("Expected '$expected' at position $this.index")
            }
            this.index++
        }
    }


}