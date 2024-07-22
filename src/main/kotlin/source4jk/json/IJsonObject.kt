package source4jk.json

import java.io.Serializable

interface IJsonObject: MutableIterable<MutableMap.MutableEntry<String, Any?>>, Serializable {

}