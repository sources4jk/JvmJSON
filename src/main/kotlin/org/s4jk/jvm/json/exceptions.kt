package org.s4jk.jvm.json

class IllegalValueTypeException(value: Any?): Exception("Invalid value type: ${value?.javaClass?.simpleName}")

class IllegalJsonStringParserException(message: String): Exception(message)
