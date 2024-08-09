package org.s4jk.jvm.json.string.parsers

import org.s4jk.jvm.json.IllegalJsonStringParsingException

class ValueStringParser(private val parser: JsonStringParser) {
    fun parseValue(): Any? {
        parser.skipWhitespaces()
        return when {
            parser.currentChar() in "-0123456789" -> {
                NumberParser(parser).parseNumber()
            }
            parser.currentChar() == '"' -> {
                StringParser(parser).parseString()
            }
            parser.source.startsWith("true", parser.currentIndex()) || parser.source.startsWith("false", parser.currentIndex()) -> {
                BooleanParser(parser).parseBoolean()
            }
            parser.source.startsWith("null", parser.currentIndex()) -> {
                NullParser(parser).parseNull()
            }
            parser.currentChar() == '{' -> {
                ObjectStringParser(parser).parse()
            }
            parser.currentChar() == '[' -> {
                ListStringParser(parser).parse()
            }
            else -> {
                throw IllegalJsonStringParsingException("Unexpected character at position ${parser.currentIndex()}")
            }
        }
    }

    class StringParser(private val parser: JsonStringParser) {
        fun parseString(): String {
            parser.requireChar('"')
            val start = parser.currentIndex()

            while (parser.currentChar() != '"') {
                parser.advanceIndex()
            }

            if (parser.currentIndex() >= parser.length) {
                throw IllegalJsonStringParsingException("Unterminated string at position ${parser.currentIndex()}")
            }

            val result = parser.source.substring(start, parser.currentIndex())
            parser.advanceIndex()
            return result
        }
    }

    class NumberParser(private val parser: JsonStringParser) {
        fun parseNumber(): Number {
            val start = parser.currentIndex()
            var hasDecimalPoint = false
            var hasExponent = false

            while (parser.currentChar() in "-0123456789.eE") {
                when (parser.currentChar()) {
                    '.' -> {
                        if (hasDecimalPoint) {
                            throw IllegalJsonStringParsingException("Multiple decimal points in number at position ${parser.currentIndex()}")
                        }
                        hasDecimalPoint = true
                    }
                    'e', 'E' -> {
                        if (hasExponent) {
                            throw IllegalJsonStringParsingException("Multiple exponents in number at position ${parser.currentIndex()}")
                        }
                        hasExponent = true
                    }
                }
                parser.advanceIndex()
            }

            val numberStr = parser.source.substring(start, parser.currentIndex())

            if (hasDecimalPoint || hasExponent) {
                return numberStr.toDouble()
            }

            return when (val longValue = numberStr.toLong()) {
                in Byte.MIN_VALUE..Byte.MAX_VALUE -> longValue.toByte()
                in Int.MIN_VALUE..Int.MAX_VALUE -> longValue.toInt()
                else -> longValue
            }
        }
    }

    class BooleanParser(private val parser: JsonStringParser) {
        fun parseBoolean(): Boolean {
            return when {
                parser.source.startsWith("true", parser.currentIndex()) -> {
                    parser.advanceIndex(4)
                    true
                }
                parser.source.startsWith("false", parser.currentIndex()) -> {
                    parser.advanceIndex(5)
                    false
                }
                else -> throw IllegalJsonStringParsingException("Unexpected value at position ${parser.currentIndex()}")
            }
        }
    }


    class NullParser(private val parser: JsonStringParser) {
        fun parseNull(): Any? {
            return when {
                parser.source.startsWith("null", parser.currentIndex()) -> {
                    parser.advanceIndex(4)
                    null
                }
                else -> throw IllegalJsonStringParsingException("Unexpected value at position ${parser.currentIndex()}")
            }
        }
    }
}