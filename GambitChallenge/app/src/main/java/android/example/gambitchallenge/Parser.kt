package android.example.gambitchallenge
import android.example.gambitchallenge.RegistryTypes
import android.example.gambitchallenge.RegistryText

import org.json.JSONArray
import org.json.JSONObject

public class Parser(meter_values:JSONArray, manual_values:JSONArray) {

    var meter_values: JSONArray = meter_values
    var manual_values: JSONArray = manual_values
    var helper: HexConverter = HexConverter()
    var parsedList: MutableList<String> = mutableListOf<String>()

    public fun CreateList(): MutableList<String> {
        val l: Int = meter_values.length()
        var count: Int = 0
        var string: String = ""
        var float: Float = 0f
        var long: Long = 0
        var int: Int = 0
        var string2: String = ""
        var byte: Byte = 1

        while (count < l) {
            string = ""
            float = 0f
            int = 0
            string2 = ""
            byte = 1
            var meter: JSONObject = meter_values.getJSONObject(count)
            var manual: JSONObject = manual_values.getJSONObject(count)
            var registry: Registry = Registry(meter["id"].toString().toInt(), meter["value"].toString().toInt())
            var text: RegistryText = RegistryText(
                manual["regisers"].toString(),
                manual["text"].toString(),
                manual["amount"].toString().toInt()
            )
            val q: Int = registry.number

            if (text.number > 1) {
                var p: Int = text.number - 1

                for (x in 0 until p) {
                    meter = meter_values.getJSONObject(count + p)
                    registry.number = meter["value"].toString().toInt()
                    p--
                    var hex: String = helper.DecimalToHex(registry.number.toString())
                    string += hex
                }
                if (registry.type == RegistryTypes.Real4) {
                    float = helper.FloatConverter(string)
                    parsedList.add(count, text.text + ": " + float.toString())
                } else if (registry.type == RegistryTypes.Long) {
                    long = helper.HexToDecimal(string)
                    parsedList.add(count, text.text + ": " + long.toString())
                } else if (registry.type == RegistryTypes.Integer) {
                    string2 = helper.StringCutterFromRight(string, 2)
                    long = helper.HexToDecimal(string2)
                    parsedList.add(count, text.text + ": " + long.toString())

                } else if (registry.type == RegistryTypes.BCD) {
                    string2 = helper.toBCD(string.toInt())
                    parsedList.add(count, text.text + ": " + string2)

                } else {
                    string2 = helper.hexToBin(string)
                    parsedList.add(count, text.text + ": " + string2)
                }


            } else {


            }
            count++


        }
        return parsedList
    }
}