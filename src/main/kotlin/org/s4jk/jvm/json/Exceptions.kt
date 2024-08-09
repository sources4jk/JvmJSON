package org.s4jk.jvm.json

class IllegalJsonValueTypeException(value: Any?):
    Exception("Invalid value type: ${value?.javaClass?.simpleName}")

class IllegalJsonStringParsingException(message: String): Exception(message)
