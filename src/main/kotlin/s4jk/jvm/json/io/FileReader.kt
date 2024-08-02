package s4jk.jvm.json.io

import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.nio.charset.Charset

class FileReader(
    override val path: String,
    override val name: String,
    override val extension: String,
    override val charset: Charset
): IFM {
    private val file: File = File("$path/$name.$extension")

    /**
     * Reads the contents of the file and returns it as a string.
     *
     * @return The content of the file.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    @Throws(IOException::class)
    fun read(): String {
        FileInputStream(this.file).buffered().reader(this.charset).use { reader ->
            return reader.readText()
        }
    }
}
