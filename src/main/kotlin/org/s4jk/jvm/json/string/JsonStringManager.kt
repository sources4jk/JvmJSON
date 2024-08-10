package org.s4jk.jvm.json.string

import org.s4jk.jvm.json.IllegalJsonValueTypeException
import org.s4jk.jvm.json.core.JsonList
import org.s4jk.jvm.json.core.JsonObject
import org.s4jk.jvm.json.core.JsonValue
import org.s4jk.jvm.json.string.parsers.JsonStringParser


object JsonStringManager {

    private val spaces: (Int, Int) -> String = { indent, depth -> if (indent > 0) " ".repeat(indent * depth) else " " }
    private val tabs: (Int) -> String = { indent -> if (indent > 0) "\n" else "" }


    @JvmStatic
    fun jsonObjectToString(json: JsonObject, indent: Int, depth: Int): String {
        if (json.isEmpty()) {
            return "{}"
        }

        return buildString {
            append("{").append(tabs(indent))

            json.entries.forEachIndexed { index, (key, value) ->

                append(spaces(indent, depth))
                append("\"${key}\": ").append(valueToString(value, indent, depth + 1))

                if (index != json.entries.size - 1) {
                    append(",")
                }

                append(tabs(indent))
            }
            append(spaces(indent, depth - 1)).append("}")
        }
    }

    @JvmStatic
    fun jsonListToString(list: JsonList, indent: Int, depth: Int): String {
        if (list.isEmpty()) {
            return "[]"
        }

        return buildString {
            append("[").append(tabs(indent))

            list.forEachIndexed { index, value ->

                append(spaces(indent, depth)).append(
                    valueToString(
                        value,
                        indent,
                        depth + 1
                    )
                )

                if (index != list.size - 1) {
                    append(",")
                }

                append(tabs(indent))

            }
            append(spaces(indent, depth - 1)).append("]")
        }
    }

    private fun valueToString(value: JsonValue, indent: Int, depth: Int): String {
        return when (value.asAny()) {
            is Byte, is Int, is Double, is Long -> {
                value.asNumber().toString()
            }

            is Boolean -> {
                value.asBoolean().toString()
            }

            is String -> {
                "\"${value.asString()}\""
            }

            is JsonObject -> {
                jsonObjectToString(value.asObject(), indent, depth)
            }

            is JsonList -> {
                jsonListToString(value.asList(), indent, depth)
            }

            null -> {
                "null"
            }

            else -> {
                throw IllegalJsonValueTypeException(value)
            }
        }
    }
}