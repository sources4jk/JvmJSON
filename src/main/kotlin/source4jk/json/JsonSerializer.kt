package source4jk.json

import java.io.FileInputStream
import java.io.FileOutputStream

/**
 * An object responsible for serializing and deserializing JSON objects to and from files.
 */
object JsonSerializer {

    /**
     * Writes a JSON object to a file.
     *
     * @param json The JSON object to be written.
     * @param name The name of the file (without extension) to write the JSON object to.
     * @param path The directory path where the file will be saved.
     * @param indent The number of spaces to use for indentation (default is 0).
     * @throws IllegalJsonFilePath if the provided path is blank or empty.
     */
    fun write(json: IJO<*, *>, name: String, path: String, indent: Int = 0) {
        if (path.isBlank() || path.isEmpty()) {
            throw IllegalJsonFilePath()
        }

        FileOutputStream("$path/$name.json").use { stream ->
            stream.bufferedWriter().use { writer ->
                writer.write(json.toString(indent))
                writer.flush()
            }
        }
    }

    /**
     * Reads a JSON object from a file.
     *
     * @param name The name of the file (without extension) to read the JSON object from.
     * @param path The directory path where the file is located.
     * @return The JSON object from the file.
     * @throws IllegalJsonFilePath if the provided path is blank or empty.
     */
    fun open(name: String, path: String): IJO<*, *> {
        if (path.isBlank() || path.isEmpty()) {
            throw IllegalJsonFilePath()
        }

        FileInputStream("$path/$name.json").use { stream ->
            stream.bufferedReader().use { reader ->
                return JsonObject.from(reader.readText())
            }
        }
    }
}
