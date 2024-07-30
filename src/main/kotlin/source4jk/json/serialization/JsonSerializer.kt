package source4jk.json.serialization

import source4jk.json.obj.IJO
import source4jk.json.obj.JsonObject
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.nio.charset.Charset

/**
 * Class responsible for serializing and deserializing JSON objects to and from files.
 *
 * @property defaultSaveDirectory The default directory for saving JSON files.
 * @param json The [IJO] instance representing the JSON object to be handled.
 * @param charset The character encoding used for reading and writing files.
 */
class JsonSerializer(private val json: IJO, private val charset: Charset) {
    private val defaultSaveDirectory: File = File("src/main/resources")

    /**
     * Returns the JSON object being handled by this serializer.
     *
     * @return The [IJO] instance representing the JSON object.
     */
    fun asJsonObject(): IJO = this.json

    /**
     * Serializes the JSON object to a file.
     *
     * @param path The path to the file where the JSON object will be saved. If null, uses the default directory.
     * @param indent The number of spaces to use for indentation in the JSON file.
     * @return This [JsonSerializer] instance for chaining.
     * @throws IOException If an I/O error occurs.
     */
    @Throws(IOException::class)
    fun toFile(path: String? = null, indent: Int = 0): JsonSerializer {
        FileWritter(this.resolveFilePath(path), this.json.name ?: "", "json", this.charset).writeTo(
            this.json.toString(
                indent
            )
        )
        return this
    }

    /**
     * Deserializes a JSON object from a file.
     *
     * @param path The path to the file from which the JSON object will be read. If null, uses the default directory.
     * @return This [JsonSerializer] instance for chaining.
     * @throws IOException If an I/O error occurs.
     * @throws FileNotFoundException If the specified file is not found.
     */
    @Throws(IOException::class, FileNotFoundException::class)
    fun fromFile(path: String? = null): JsonSerializer {
        JsonObject.from(
            null,
            FileReader(this.resolveFilePath(path), this.json.name ?: "", "json", this.charset).readFile()
        ).forEach { (key, value) ->
            this.json.set(key, value)
        }
        return this
    }

    /**
     * Resolves the file path to use for saving or reading the JSON object.
     *
     * @param path The path to the file. If null or blank, uses the default save directory.
     * @return The resolved file path.
     * @throws IOException If an I/O error occurs while ensuring the directory exists.
     */
    @Throws(IOException::class)
    private fun resolveFilePath(path: String? = null): String {
        return if (path.isNullOrBlank()) {
            this.ensureDirectoryExists(defaultSaveDirectory)
            this.defaultSaveDirectory.absolutePath
        } else {
            this.ensureDirectoryExists(File(path))
            path
        }
    }

    /**
     * Ensures that the specified directory exists, creating it if necessary.
     *
     * @param directory The directory to check or create.
     * @throws IOException If the directory could not be created.
     */
    @Throws(IOException::class)
    private fun ensureDirectoryExists(directory: File) {
        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                throw IOException("Failed to create directory: ${directory.absolutePath}")
            }
        }
    }
}
