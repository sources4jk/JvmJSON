package org.s4jk.jvm.json

import kotlin.reflect.KClass

class IllegalJsonValueTypeException(value: Any?): Exception(
    "Invalid value type: ${value?.javaClass?.simpleName}"
)

class JsonValueTypeCastException(value: Any?, cast: KClass<*>): RuntimeException(
    "${value?.javaClass?.name} cannot be cast to $cast"
)

class JsonValueNullException(value: Any?): RuntimeException(
    "${value?.javaClass?.name} cannot be null"
)

class IllegalJsonStringParsingException(message: String): RuntimeException(message)