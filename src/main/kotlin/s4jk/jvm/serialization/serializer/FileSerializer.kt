package s4jk.jvm.serialization.serializer

import org.jetbrains.annotations.NotNull
import s4jk.jvm.serialization.io.FileReader
import s4jk.jvm.serialization.io.FileWriter
import s4jk.jvm.serialization.objects.JsonObject
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.nio.charset.Charset

class FileSerializer(private val serializer: JsonSerializer, private val charset: Charset = Charsets.UTF_8) {
    private val defaultSaveDirectory: File = File("src/main/resources")

    @NotNull
    fun json(): JsonSerializer = this.serializer

    /**
     * Serializes the JSON object to a file.
     *
     * @param path The path to the file where the JSON object will be saved. If null, uses the default directory.
     * @param indent The number of spaces to use for indentation in the JSON file.
     * @return This [JsonSerializer] instance for chaining.
     * @throws IOException If an I/O error occurs.
     */
    @NotNull
    @Throws(IOException::class)
    fun to(@NotNull path: String, @NotNull indent: Int = 0): FileSerializer {
        FileWriter(this.resolveFilePath(path), this.serializer.asJsonObject().name, "json", this.charset)
            .writeTo(this.serializer.asJsonObject().toString(indent))
        return this
    }

    @NotNull
    @Throws(IOException::class)
    fun to(@NotNull path: String): FileSerializer {
        return this.to(path, 0)
    }

    @NotNull
    @Throws(IOException::class)
    fun to(@NotNull indent: Int): FileSerializer {
        return this.to("", indent)
    }

    @NotNull
    @Throws(IOException::class)
    fun to(): FileSerializer {
        return this.to("", 0)
    }

    /**
     * Deserializes a JSON object from a file.
     *
     * @param path The path to the file from which the JSON object will be read. If null, uses the default directory.
     * @return This [JsonSerializer] instance for chaining.
     * @throws IOException If an I/O error occurs.
     * @throws FileNotFoundException If the specified file is not found.
     */
    @NotNull
    @Throws(IOException::class, FileNotFoundException::class)
    fun from(@NotNull path: String): FileSerializer {
        JsonObject.from(
            FileReader(
                this.resolveFilePath(path),
                this.serializer.asJsonObject().name,
                "json",
                this.charset
            ).readFile()
        ).forEach { (key, value) -> this.serializer.asJsonObject().set(key, value) }

        return this
    }

    @NotNull
    @Throws(IOException::class, FileNotFoundException::class)
    fun from(): FileSerializer {
        return this.from("")
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