package source4jk.json

class InvalidJsonValueTypeException(): Exception("JSON document cannot support this value type")

class InvalidJsonStringException(message: String): Exception(message)