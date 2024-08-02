package s4jk.jvm.json.io

import java.nio.charset.Charset

/**
 * Interface defining the file metadata and character encoding required for file operations.
 * @property path The directory path where the file will be saved.
 * @property name The name of the file (without extension).
 * @property extension The file extension (e.g., "json").
 * @property charset The character encoding used for writing to the file.
 */
interface IFM {
    val path: String
    val name: String
    val extension: String
    val charset: Charset
}