package s4jk.jvm.serialization.io

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