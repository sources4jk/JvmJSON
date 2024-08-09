package org.s4jk.jvm.json.string.parsers

import org.s4jk.jvm.json.IllegalJsonStringParsingException
import org.s4jk.jvm.json.core.JsonList

class ListStringParser(private val parser: JsonStringParser) {
    fun parse(): JsonList {
        val list = JsonList.create()
        parser.advanceIndex()
        parser.skipWhitespaces()

        if (parser.currentChar() == ']') {
            parser.advanceIndex()
            return list
        }

        while (true) {
            parser.skipWhitespaces()
            val value = ValueStringParser(parser).parseValue()
            list.add(value)

            parser.skipWhitespaces()
            when (parser.currentChar()) {
                ']' -> {
                    parser.advanceIndex()
                    return list
                }
                ',' -> parser.advanceIndex()
                else -> throw IllegalJsonStringParsingException("Expected ']' or ',' at position ${parser.currentIndex()}")
            }
        }
    }
}