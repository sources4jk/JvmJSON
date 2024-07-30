package source4jk.json.serialization

import java.nio.charset.Charset

/**
 * Interface defining the file metadata and character encoding required for file operations.
 */
interface IFM {
     val path: String
     val name: String
     val extension: String
     val charset: Charset
}