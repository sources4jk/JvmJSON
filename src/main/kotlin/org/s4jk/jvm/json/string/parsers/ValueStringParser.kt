package org.s4jk.jvm.json.string.parsers

import org.s4jk.jvm.json.IllegalJsonStringParsingException

class ValueStringParser(private val parser: JsonStringParser) {
    fun parseValue(): Any? {
        this.parser.skipWhitespaces()
        return when {
            this.parser.currentChar() in "-0123456789" -> {
                NumberParser(parser).parseNumber()
            }
            this.parser.currentChar() == '"' -> {
                StringParser(parser).parseString()
            }
            this.parser.source.startsWith("true", this.parser.currentIndex()) || this.parser.source.startsWith("false", this.parser.currentIndex()) -> {
                BooleanParser(parser).parseBoolean()
            }
            this.parser.source.startsWith("null", this.parser.currentIndex()) -> {
                NullParser(parser).parseNull()
            }
            this.parser.currentChar() == '{' -> {
                ObjectStringParser(parser).parse()
            }
            this.parser.currentChar() == '[' -> {
                ListStringParser(parser).parse()
            }
            else -> {
                throw IllegalJsonStringParsingException("Unexpected character at position ${this.parser.currentIndex()}")
            }
        }
    }

    class StringParser(private val parser: JsonStringParser) {
        fun parseString(): String {
            this.parser.requireChar('"')
            val start = this.parser.currentIndex()

            while (this.parser.currentChar() != '"') {
                this.parser.advanceIndex()
            }

            if (this.parser.currentIndex() >= this.parser.length) {
                throw IllegalJsonStringParsingException("Unterminated string at position ${this.parser.currentIndex()}")
            }

            val result = this.parser.source.substring(start, this.parser.currentIndex())
            this.parser.advanceIndex()
            return result
        }
    }

    class NumberParser(private val parser: JsonStringParser) {
        fun parseNumber(): Number {
            val start = this.parser.currentIndex()
            var hasDecimalPoint = false
            var hasExponent = false

            while (this.parser.currentChar() in "-0123456789.eE") {
                when (this.parser.currentChar()) {
                    '.' -> {
                        if (hasDecimalPoint) {
                            throw IllegalJsonStringParsingException("Multiple decimal points in number at position ${this.parser.currentIndex()}")
                        }
                        hasDecimalPoint = true
                    }
                    'e', 'E' -> {
                        if (hasExponent) {
                            throw IllegalJsonStringParsingException("Multiple exponents in number at position ${this.parser.currentIndex()}")
                        }
                        hasExponent = true
                    }
                }
                this.parser.advanceIndex()
            }

            val numberStr = this.parser.source.substring(start, this.parser.currentIndex())

            if (hasDecimalPoint || hasExponent) {
                return numberStr.toDouble()
            }

            return when (val longValue = numberStr.toLong()) {
                in Byte.MIN_VALUE..Byte.MAX_VALUE -> longValue.toByte()
                in Short.MIN_VALUE .. Short.MAX_VALUE -> longValue.toShort()
                in Int.MIN_VALUE..Int.MAX_VALUE -> longValue.toInt()
                else -> longValue
            }
        }
    }

    class BooleanParser(private val parser: JsonStringParser) {
        fun parseBoolean(): Boolean {
            return when {
                this.parser.source.startsWith("true", this.parser.currentIndex()) -> {
                    this.parser.advanceIndex(4)
                    true
                }
                this.parser.source.startsWith("false", this.parser.currentIndex()) -> {
                    this.parser.advanceIndex(5)
                    false
                }
                else -> throw IllegalJsonStringParsingException("Unexpected value at position ${this.parser.currentIndex()}")
            }
        }
    }


    class NullParser(private val parser: JsonStringParser) {
        fun parseNull(): Any? {
            return when {
                this.parser.source.startsWith("null", this.parser.currentIndex()) -> {
                    this.parser.advanceIndex(4)
                    null
                }
                else -> throw IllegalJsonStringParsingException("Unexpected value at position ${this.parser.currentIndex()}")
            }
        }
    }
}