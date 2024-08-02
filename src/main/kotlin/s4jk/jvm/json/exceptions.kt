package s4jk.jvm.json

class IllegalValueTypeException: Exception("JSON document cannot support this value type")

class IllegalJsonStringParserException(message: String): Exception(message)
