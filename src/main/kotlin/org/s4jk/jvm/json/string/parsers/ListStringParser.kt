package org.s4jk.jvm.json.string.parsers

import org.s4jk.jvm.json.IllegalJsonStringParsingException
import org.s4jk.jvm.json.core.JsonList

class ListStringParser(private val parser: JsonStringParser) {
    fun parse(): JsonList {
        val list = JsonList.create()
        this.parser.advanceIndex()
        this.parser.skipWhitespaces()

        if (this.parser.currentChar() == ']') {
            this.parser.advanceIndex()
            return list
        }

        while (true) {
            this.parser.skipWhitespaces()
            val value = ValueStringParser(parser).parseValue()
            list.add(value)

            this.parser.skipWhitespaces()
            when (this.parser.currentChar()) {
                ']' -> {
                    this.parser.advanceIndex()
                    return list
                }
                ',' -> this.parser.advanceIndex()
                else -> throw IllegalJsonStringParsingException("Expected ']' or ',' at position ${this.parser.currentIndex()}")
            }
        }
    }
}