package source4jk.json

class IllegalJsonValueTypeException: Exception("JSON document cannot support this value type")

class IllegalJsonStringException(message: String): Exception(message)