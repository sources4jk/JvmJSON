package org.s4jk.jvm.json

class IllegalValueTypeException(value: Any?): Exception("Invalid value type: ${value?.javaClass?.simpleName}")

class NullableValueException(message: String): Exception(message)

class IllegalJsonStringParsingException(message: String): Exception(message)
