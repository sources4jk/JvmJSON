package source4jk.json

class IllegalValueTypeException : Exception("JSON document cannot support this value type")

class IllegalJsonStringException(message: String) : Exception(message)