package org.s4jk.jvm.json

class IllegalJsonValueTypeException(value: Any?):
    RuntimeException("Invalid value type: ${value?.javaClass?.simpleName}")

class IllegalJsonStringParsingException(message: String): RuntimeException(message)
