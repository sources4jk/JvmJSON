package s4jk.jvm.serialization.io

import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.nio.charset.Charset

/**
 * Class responsible for reading the contents of a file.
 *
 * @property path The directory path where the file is located.
 * @property name The name of the file (without extension).
 * @property extension The file extension (e.g., "json").
 * @property charset The character encoding used for reading the file.
 */
class FileReader(
    override val path: String,
    override val name: String,
    override val extension: String,
    override val charset: Charset
) : IFM {
    private val file: File = File("$path/$name.$extension")

    /**
     * Reads the contents of the file and returns it as a string.
     *
     * @return The content of the file.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    @Throws(IOException::class)
    fun readFile(): String {
        FileInputStream(this.file).buffered().reader(this.charset).use { reader ->
            return reader.readText()
        }
    }
}
