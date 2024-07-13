package source4jk.json

enum class JsonAccessMode(value: Int) {
    READ(0),
    MODIFY(1),
    WRITE(2);

    companion object Static {
        val ALL: Set<JsonAccessMode> = JsonAccessMode.entries.toSet()
    }

}