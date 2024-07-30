package s4jk.jvm.serialization

import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.nio.charset.Charset

/**
 * Class responsible for writing contents to a file.
 *
 * @property path The directory path where the file will be saved.
 * @property name The name of the file (without extension).
 * @property extension The file extension (e.g., "json").
 * @property charset The character encoding used for writing to the file.
 */
class FileWritter(
    override val path: String,
    override val name: String,
    override val extension: String,
    override val charset: Charset
) : IFM {
    private val file: File = File("$path/$name.$extension")

    /**
     * Writes the given content to the file.
     *
     * @param source The content to write to the file.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    @Throws(IOException::class)
    fun writeTo(source: String) {
        FileOutputStream(this.file).buffered().writer(this.charset).use { writer ->
            writer.write(source)
            writer.flush()
        }
    }
}
