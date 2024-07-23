package source4jk.json

import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream

object JsonSerializer {

    fun <K, V> write(json: IJO<K, V>, path: String, name: String, indent: Int = 0): IJO<K, V> {
        val filePath = buildString {
            if (path.isNotEmpty() && path.isNotBlank()) {
                append(path)
                append("/")
            }
            append(name)
        }

        return FileOutputStream(filePath).use { fileOutputStream ->
            BufferedOutputStream(fileOutputStream).use { buffer ->
                buffer.write(json.toString(indent).toByteArray())
                buffer.flush()
                json
            }
        }
    }

    fun open(path: String, name: String): JsonObject {
        val filePath = buildString {
            if (path.isNotEmpty() && path.isNotBlank()) {
                append(path)
                append("/")
            }
            append(name)
        }

        return JsonObject.from(File(filePath).readText())
    }
}