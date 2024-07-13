package source4jk.json

class JsonReader(private val jsonObject: AbstractJsonObject) {

    @Suppress("UNCHECKED_CAST")
    fun <T> get(key: String): T? {
        return this.jsonObject.entries.find { it.key == key }?.value as? T
    }


}