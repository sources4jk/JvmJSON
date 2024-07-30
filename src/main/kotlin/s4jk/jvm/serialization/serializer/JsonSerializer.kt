package s4jk.jvm.serialization.serializer

import org.jetbrains.annotations.NotNull
import s4jk.jvm.serialization.objects.IJO
import java.nio.charset.Charset

/**
 * Class responsible for serializing and deserializing JSON objects to and from files.
 *
 * @param json The [IJO] instance representing the JSON object to be handled.
 */
class JsonSerializer(private val json: IJO) {

    /**
     * Returns the JSON object being handled by this serializer.
     *
     * @return The [IJO] instance representing the JSON object.
     */
    @NotNull
    fun asJsonObject(): IJO = this.json

    @NotNull
    fun file(charset: Charset): FileSerializer = FileSerializer(this, charset)

    @NotNull
    fun file(): FileSerializer = FileSerializer(this)

    @NotNull
    fun <T> data() {
        TODO()
    }

}
