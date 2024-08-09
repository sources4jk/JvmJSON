package org.s4jk.jvm.json.string.parsers

import org.s4jk.jvm.json.IllegalJsonStringParsingException
import org.s4jk.jvm.json.core.JsonList
import org.s4jk.jvm.json.core.JsonObject

class JsonStringParser(val source: String) {
    private var index = 0
    val length = source.length

    fun parseObject(): JsonObject = ObjectStringParser(this).parse()
    fun parseList(): JsonList = ListStringParser(this).parse()

    fun skipWhitespaces() {
        while (this.index < length && source[this.index].isWhitespace()) {
            this.index++
        }
    }

    fun requireChar(expected: Char) {
        if (this.index >= length || source[this.index] != expected) {
            throw IllegalJsonStringParsingException("Expected '$expected' at position $this.index")
        }
        this.index++
    }

    fun currentChar(): Char = source[this.index]
    fun currentIndex(): Int = this.index
    fun advanceIndex(advance: Int = 1) {
        this.index += advance
    }
}