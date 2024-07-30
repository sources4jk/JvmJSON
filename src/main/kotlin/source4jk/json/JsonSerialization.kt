package source4jk.json

import source4jk.json.obj.IJO
import source4jk.json.obj.JsonObject
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

/**
 * Extension function to create a `JsonSerializer` for the given `IJO` instance.
 *
 * This function initializes a `JsonSerializer` with the receiver `IJO` instance, providing
 * methods to serialize and deserialize the JSON object to and from files.
 *
 * @return A `JsonSerializer` instance initialized with the receiver `IJO`.
 */
fun IJO.serializer(): JsonSerializer {
    return JsonSerializer(this@serializer)
}

/**
 * Class responsible for serializing and deserializing JSON objects to and from files.
 *
 * @property json The JSON object to be serialized or deserialized.
 * @property defaultFilePath The default file path where the JSON object will be saved or read from.
 * @property defaultSaveDirectory The default directory for saving JSON files.
 *
 * @param json The `IJO` instance representing the JSON object to be handled.
 */
class JsonSerializer(private val json: IJO) {
    private val defaultFilePath: String = "src/main/resources/${this.json.name}.json"
    private val defaultSaveDirectory: File = File("src/main/resources")

    /**
     * Writes the JSON object to a file.
     *
     * This method serializes the JSON object to a file, using the provided path if specified,
     * or the default path otherwise. The JSON data is written with optional indentation.
     *
     * @param path The directory path where the file will be saved. If null or blank, the default path is used.
     * @param indent The number of spaces to use for indentation in the JSON file. Defaults to 0.
     * @return The `IJO` instance representing the JSON object that was written to the file.
     */
    fun write(path: String? = null, indent: Int = 0): IJO {
        val saveTo = this.validatePath(path)
        val saveFile = File(saveTo)

        FileOutputStream(saveFile).use { stream ->
            stream.bufferedWriter().use { writer ->
                writer.write(json.toString(indent))
                writer.flush()
                return this.json
            }
        }
    }

    /**
     * Reads a JSON object from a file.
     *
     * This method deserializes a JSON object from a file. It uses the provided path if specified;
     * otherwise, it uses the default path. If the file does not exist, it creates a new file with
     * an empty JSON object ("{}"). The JSON object read from the file is then used to update the
     * current `IJO` instance.
     *
     * @param path The directory path where the file is located. If null, the default path is used.
     * @return The `IJO` instance, with its contents updated from the JSON data read from the file.
     */
    fun open(path: String? = null): IJO {
        val openFrom = this.validatePath(path)
        val openFile = File(openFrom)

        if (!openFile.exists()) {
            openFile.createNewFile()
            openFile.writeText("{}")
        }

        FileInputStream(openFile).use { stream ->
            stream.bufferedReader().use { reader ->
                return this.json.apply {
                    JsonObject.from("", reader.readText()).forEach { (key, value) ->
                        this.set(key, value)
                    }
                }
            }
        }
    }

    /**
     * Validates and constructs the file path for saving or reading the JSON object.
     *
     * This method determines the correct file path based on the provided path or defaults
     * to a predefined path if no path is specified.
     *
     * @param path The directory path to validate. If null or blank, the default path is used.
     * @return The validated and constructed file path.
     */
    private fun validatePath(path: String? = null): String {
        return if (path.isNullOrBlank()) {
            if (!defaultSaveDirectory.exists()) {
                defaultSaveDirectory.mkdir()
            }
            defaultFilePath
        } else {
            "$path/${this.json.name}.json"
        }
    }
}
