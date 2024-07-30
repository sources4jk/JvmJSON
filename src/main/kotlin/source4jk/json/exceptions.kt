package source4jk.json

import java.io.FileNotFoundException

class IllegalValueTypeException: Exception("JSON document cannot support this value type")

class IllegalJsonStringParserException(message: String): Exception(message)

class JsonSerializationException(message: String): Exception(message)
