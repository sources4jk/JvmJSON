package org.s4jk.jvm.json.io

import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable
import org.s4jk.jvm.json.objects.IJO
import org.s4jk.jvm.json.objects.JsonObject
import java.io.File
import java.io.IOException
import java.nio.charset.Charset

class JsonInputOutput(private val json: IJO, private val charset: Charset) {
    private val defaultSaveDirectory: File = File("src/main/resources")

    /**
     * Returns the JSON object associated with this instance.
     *
     * @return The IJO instance representing the JSON object.
     */
    fun asJsonObject(): IJO = this.json

    /**
     * Saves the JSON object to a file with the specified path and indentation.
     *
     * @param path The path to save the file. If null, uses the default save directory.
     * @param indent The number of spaces to use for indentation in the JSON file.
     * @return This JsonInputOutput instance.
     * @throws IOException If an I/O error occurs during writing.
     */
    fun toFile(@Nullable path: String?, @Nullable indent: Int = 0): JsonInputOutput {
        FileWriter(resolveFilePath(path), this.json.name, "json", this.charset)
            .write(this.json.toString(indent).toByteArray(this.charset))
        return this
    }

    /**
     * Saves the JSON object to a file with the specified indentation, using the default path.
     *
     * @param indent The number of spaces to use for indentation in the JSON file.
     * @return This JsonInputOutput instance.
     * @throws IOException If an I/O error occurs during writing.
     */
    fun toFile(@NotNull indent: Int): JsonInputOutput {
        return this.toFile(null, indent)
    }

    /**
     * Saves the JSON object to a file with no indentation, using the default path.
     *
     * @return This JsonInputOutput instance.
     * @throws IOException If an I/O error occurs during writing.
     */
    fun toFile(): JsonInputOutput {
        return this.toFile(null, 0)
    }

    /**
     * Reads a JSON object from a file with the specified path and name.
     *
     * @param path The path to the file. If null, uses the default save directory.
     * @param name The name of the JSON file to read.
     * @return This JsonInputOutput instance.
     * @throws IOException If an I/O error occurs during reading.
     */
    @NotNull
    fun fromFile(@Nullable path: String?, @Nullable name: String): JsonInputOutput {
        JsonObject.from(FileReader(resolveFilePath(path), name, "json", this.charset).read())
            .forEach { (key, value) ->
                this.json.set(key, value)
            }

        return this
    }

    /**
     * Reads a JSON object from a file with the specified name, using the default path.
     *
     * @param name The name of the JSON file to read.
     * @return This JsonInputOutput instance.
     * @throws IOException If an I/O error occurs during reading.
     */
    @NotNull
    fun fromFile(@NotNull name: String): JsonInputOutput {
        return this.fromFile(null, name)
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