package org.s4jk.jvm.json

import org.junit.jupiter.api.Test
import org.s4jk.jvm.json.core.JsonList
import org.s4jk.jvm.json.core.JsonObject

class CreateJson {

    @Test
    fun createJsonObjectFromData() {
        val json = jsonObjectOf {
            "key 1" to 123456789
            "key 2" to -123456789
            "key 3" to 1.2345678e9
            "key 4" to "string value"
            "key 5" to true
            "key 6" to false
            "key 7" to null
            "key 8" to jsonListOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
            "key 9" to jsonObjectOf { "key 1" to 1; "key 2" to 2; "key 3" to 3; "key 4" to 4; "key 5" to 5 }
        }
        /**
         * create [JsonObject] form existing [JsonObject]
         */
        JsonObject.from(json)
    }

    @Test
    fun createJsonObjectFromMap() {
        mapOf(
            "key 1" to 123456789,
            "key 2" to -123456789,
            "key 3" to 1.2345678e9,
            "key 4" to "string value",
            "key 5" to true,
            "key 6" to false,
            "key 7" to null,
            "key 8" to jsonListOf(1, 2, 3, 4, 5, 6, 7, 8, 9),
            "key 9" to mapOf("key 1" to 1, "key 2" to 2, "key 3" to 3, "key 4" to 4, "key 5" to 5).toJsonObject()
        ).toJsonObject()
    }

    @Test
    fun createJsonObjectFromString() {
        """{
            "key 1": 123456789,
            "key 2": -123456789,
            "key 3": 1.2345678E9,
            "key 4": "string value",
            "key 5": true,
            "key 6": false,
            "key 7": null,
            "key 8": [
                1,
                2,
                3,
                4,
                5,
                6,
                7,
                8,
                9
            ],
            "key 9": {
                "key 1": 1,
                "key 2": 2,
                "key 3": 3,
                "key 4": 4,
                "key 5": 5
            }
        }""".trimIndent().toJsonObject()
    }

    @Test
    fun createJsonListFromData() {
        val list = jsonListOf(
            123456789,
            -123456789,
            1.2345678e9,
            "string value",
            true,
            false,
            null,
            jsonListOf(1, 2, 3, 4, 5, 6, 7, 8, 9),
            jsonObjectOf { "key 1" to 1; "key 2" to 2; "key 3" to 3; "key 4" to 4; "key 5" to 5 }
        )

        JsonList.from(list)
    }

    @Test
    fun createJsonListFromCollection() {
        listOf(
            123456789,
            -123456789,
            1.2345678e9,
            "string value",
            true,
            false,
            null,
            listOf(1, 2, 3, 4, 5, 6, 7, 8, 9).toJsonList(),
            mapOf("key 1" to 1, "key 2" to 2, "key 3" to 3, "key 4" to 4, "key 5" to 5).toJsonObject()
        ).toJsonList()

        setOf(
            123456789,
            -123456789,
            1.2345678e9,
            "string value",
            true,
            false,
            null,
            setOf(1, 2, 3, 4, 5, 6, 7, 8, 9).toJsonList(),
            mapOf("key 1" to 1, "key 2" to 2, "key 3" to 3, "key 4" to 4, "key 5" to 5).toJsonObject()
        ).toJsonList()
    }

    @Test
    fun createJsonListFromArray() {
        arrayOf(
            123456789,
            -123456789,
            1.2345678e9,
            "string value",
            true,
            false,
            null,
            arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9).toJsonList(),
            mapOf("key 1" to 1, "key 2" to 2, "key 3" to 3, "key 4" to 4, "key 5" to 5).toJsonObject()
        ).toJsonList()

        charArrayOf('a', 'b', 'c', 'd', 'e', 'f').toJsonList()
        byteArrayOf(Byte.MIN_VALUE, 0, Byte.MAX_VALUE).toJsonList()
        shortArrayOf(Short.MIN_VALUE, 0, Short.MAX_VALUE).toJsonList()
        intArrayOf(Int.MIN_VALUE,0, Int.MAX_VALUE).toJsonList()
        longArrayOf(Long.MIN_VALUE,0, Long.MAX_VALUE).toJsonList()
        doubleArrayOf(Double.MIN_VALUE,0.0, Double.MAX_VALUE).toJsonList()
        floatArrayOf(Float.MIN_VALUE,0f, Float.MAX_VALUE).toJsonList()
    }


    @Test
    fun createJsonListFromString() {
        """[
            123456789,
            -123456789,
            1.2345678E9,
            "string value",
            true,
            false,
            null,
            [
                1,
                2,
                3,
                4,
                5,
                6,
                7,
                8,
                9
            ],
            {
                "key 4": 4,
                "key 3": 3,
                "key 2": 2,
                "key 1": 1,
                "key 5": 5
            }
        ]""".trimIndent().toJsonList()
    }
}