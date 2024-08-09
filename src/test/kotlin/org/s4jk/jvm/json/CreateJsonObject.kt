package org.s4jk.jvm.json

import org.junit.jupiter.api.Test
import org.s4jk.jvm.json.core.jsonListOf
import org.s4jk.jvm.json.core.jsonObjectOf
import org.junit.jupiter.api.Assertions.assertEquals
import org.s4jk.jvm.json.core.JsonObject

class CreateJsonObject {

    private val sample = jsonObjectOf {
        "null_key_1" to null
        "number_key_1" to 123456789
        "number_key_2" to -123456789
        "number_key_3" to Math.PI
        "number_key_4" to 1.23e4
        "string_key_1" to "string value 1"
        "string_key_2" to "string " + "value " + 2
        "boolen_key_1" to true
        "boolen_key_2" to false
        "object_key_1" to jsonObjectOf { "key1" to 123456789 }
        "list_key_1" to jsonListOf(1, 2, 3, 4, 5, 6, 7, 8, 9)

    }


    @Test
    fun createFromUserData() {
        assertEquals(null, this.sample["null_key_1"].asNullable())

        assertEquals(123456789, this.sample["number_key_1"].asNumber())
        assertEquals(-123456789, this.sample["number_key_2"].asNumber())
        assertEquals(Math.PI, this.sample["number_key_3"].asNumber())
        assertEquals(1.23e4, this.sample["number_key_4"].asNumber())

        assertEquals("string value 1", this.sample["string_key_1"].asString())
        assertEquals("string value 2", this.sample["string_key_2"].asString())

        assertEquals(true, this.sample["boolen_key_1"].asBoolean())
        assertEquals(false, this.sample["boolen_key_2"].asBoolean())

        val nestedObject = this.sample["object_key_1"].asObject()
        assertEquals(123456789, nestedObject["key1"].asNumber())

        val list = this.sample["list_key_1"].asList()
        assertEquals(9, list.size)
        assertEquals(1, list[0].asNumber())
        assertEquals(9, list[8].asNumber())
    }


    @Test
    fun createJsonObjectFromMap() {
        val map = mapOf(
            "null_key_1" to null,
            "number_key_1" to 123456789,
            "number_key_2" to -123456789,
            "number_key_3" to Math.PI,
            "number_key_4" to 1.23e4,
            "string_key_1" to "string value 1",
            "string_key_2" to "string " + "value " + 2,
            "boolen_key_1" to true,
            "boolen_key_2" to false,
            "object_key_1" to jsonObjectOf { "key1" to 123456789 },
            "list_key_1" to jsonListOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
        )

        val json = JsonObject.from(map)

        assertEquals(json["null_key_1"].asNullable(), this.sample["null_key_1"].asNullable())
        assertEquals(json["number_key_1"].asNumber(), this.sample["number_key_1"].asNumber())
        assertEquals(json["number_key_2"].asNumber(), this.sample["number_key_2"].asNumber())
        assertEquals(json["number_key_3"].asNumber(), this.sample["number_key_3"].asNumber())
        assertEquals(json["number_key_4"].asNumber(), this.sample["number_key_4"].asNumber())

        assertEquals(json["string_key_1"].asString(), this.sample["string_key_1"].asString())
        assertEquals(json["string_key_2"].asString(), this.sample["string_key_2"].asString())

        assertEquals(json["boolen_key_1"].asBoolean(), this.sample["boolen_key_1"].asBoolean())
        assertEquals(json["boolen_key_2"].asBoolean(), this.sample["boolen_key_2"].asBoolean())

        val nestedObjectJson = json["object_key_1"].asObject()
        val nestedObjectSample = this.sample["object_key_1"].asObject()
        assertEquals(nestedObjectJson["key1"].asNumber(), nestedObjectSample["key1"].asNumber())

        val list = json["list_key_1"].asList()
        assertEquals(list.size, this.sample["list_key_1"].asList().size)
        assertEquals(list[0].asNumber(), this.sample["list_key_1"].asList()[0].asNumber())
        assertEquals(list[8].asNumber(), this.sample["list_key_1"].asList()[8].asNumber())

    }

}