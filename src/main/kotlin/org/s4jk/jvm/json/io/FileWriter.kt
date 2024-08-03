package org.s4jk.jvm.json.io

import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.nio.charset.Charset

class FileWriter(
    override val path: String,
    override val name: String,
    override val extension: String,
    override val charset: Charset
): IFM {
    private val file: File = File("$path/$name.$extension")

    /**
     * Writes the given content to the file.
     *
     * @param data The content to write to the file.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    @Throws(IOException::class)
    fun write(data: ByteArray) {
        FileOutputStream(this.file).use { stream ->
            stream.write(data)
            stream.flush()
        }
    }
}
