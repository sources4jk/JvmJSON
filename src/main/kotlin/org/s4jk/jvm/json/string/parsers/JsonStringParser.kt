package org.s4jk.jvm.json.string.parsers

import org.s4jk.jvm.json.IllegalJsonStringParsingException
import org.s4jk.jvm.json.core.JsonObject

class JsonStringParser(private val parser: Parser) {
    fun parse(): JsonObject {
        val json = JsonObject()
        this.parser.advanceIndex()
        this.parser.skipWhitespaces()

        if (this.parser.currentChar() == '}') {
            this.parser.advanceIndex()
            return json
        }

        while (true) {
            this.parser.skipWhitespaces()
            val key = ValueStringParser.StringParser(parser).parseString()

            this.parser.skipWhitespaces()
            this.parser.requireChar(':')

            val value = ValueStringParser(parser).parseValue()
            json[key] = value

            this.parser.skipWhitespaces()
            when (this.parser.currentChar()) {
                '}' -> {
                    this.parser.advanceIndex()
                    return json
                }
                ',' -> this.parser.advanceIndex()
                else -> throw IllegalJsonStringParsingException("Expected '}' or ',' at position ${this.parser.currentIndex()}")
            }
        }
    }
}