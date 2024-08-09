package org.s4jk.jvm.json.string.parsers

import org.s4jk.jvm.json.IllegalJsonStringParsingException
import org.s4jk.jvm.json.core.JsonObject

class ObjectStringParser(private val parser: JsonStringParser) {
    fun parse(): JsonObject {
        val json = JsonObject.create()
        parser.advanceIndex()
        parser.skipWhitespaces()

        if (parser.currentChar() == '}') {
            parser.advanceIndex()
            return json
        }

        while (true) {
            parser.skipWhitespaces()
            val key = ValueStringParser.StringParser(parser).parseString()

            parser.skipWhitespaces()
            parser.requireChar(':')

            val value = ValueStringParser(parser).parseValue()
            json[key] = value

            parser.skipWhitespaces()
            when (parser.currentChar()) {
                '}' -> {
                    parser.advanceIndex()
                    return json
                }
                ',' -> parser.advanceIndex()
                else -> throw IllegalJsonStringParsingException("Expected '}' or ',' at position ${parser.currentIndex()}")
            }
        }
    }
}