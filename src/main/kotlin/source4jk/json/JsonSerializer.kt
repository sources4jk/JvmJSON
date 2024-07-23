package source4jk.json

import java.io.FileInputStream
import java.io.FileOutputStream

object JsonSerializer {

    fun <K, V> write(json: IJO<K, V>, name: String, path: String, indent: Int = 0) {
        if (path.isBlank() || path.isEmpty()) {
            throw IllegalJsonFilePath()
        }

        FileOutputStream("$path/$name.json").use { stream ->
            stream.bufferedWriter().use { writer ->
                writer.write(json.toString(indent))
                writer.flush()
            }
        }
    }

    fun open(name: String, path: String): JsonObject {
        if (path.isBlank() || path.isEmpty()) {
            throw IllegalJsonFilePath()
        }

        return FileInputStream("$path/$name.json").use { stream ->
            stream.bufferedReader().use { reader ->
                JsonObject.from(reader.readText())
            }
        }
    }
}