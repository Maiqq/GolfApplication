package android.example.gambitchallenge

import android.example.gambitchallenge.MainActivity.Companion.meter_values


import org.json.JSONArray
import org.json.JSONObject

public class Parser(meter:JSONArray, manual:JSONArray) {

    var metervalues: JSONArray = meter
    var manualvalues: JSONArray = manual


    var helper: HexConverter = HexConverter()
    var parsedvalues:MutableList<ParsedItem> = mutableListOf<ParsedItem>()



    public fun createList(): MutableList<ParsedItem>  {
        val l: Int = manualvalues.length()

        var string: String = ""
        var float: Float = 0f
        var long: Long = 0
        var string2: String = ""

        var listcounter:Int=0
        var string3: String= ""
        var index:Int=0
        var value1:String
        var value2:String
        var value3:String

        while (listcounter < l) {
            string = ""
            float = 0f
            string2 = ""
            long = 0
            string3 = ""
             value1 = ""
            value2 = ""
            value3 = ""

            var manual: JSONObject = manualvalues.getJSONObject(listcounter)

            var text= RegistryText(
                manual["registers"].toString(),
                manual["text"].toString(),
                manual["amount"].toString().toInt()

            )
            var meter: JSONObject = meter_values.getJSONObject(text.register.substringBefore("-").toInt()-1)
            var registry = Registry(meter["id"].toString().toInt(), text.register.substringBefore("-").toInt())


            if (text.number > 1) {
                var p: Int = text.number - 1
                index = text.register.substringBefore("-").toInt() + p -1

                if (index > 98){
                    index = meter_values.length()-1
                    for (x in 0..p){
                        meter = meter_values.getJSONObject(index-x)
                        registry.number = meter["value"].toString().toInt()
                        if (x==0)
                        {value1 = registry.number.toString()}
                        else if(x==1)
                        {value2 = registry.number.toString()}
                        else
                        {value3 = registry.number.toString()}
                        var hex: String = helper.DecimalToHex(registry.number.toString())
                        string += hex

                    }
                }
                else {
                    for (x in 0..p) {
                        index = text.register.substringBefore("-").toInt() + p -1
                        meter = meter_values.getJSONObject(index)


                    registry.number = meter["value"].toString().toInt()
                        if (x==0)
                        {value1 = registry.number.toString()}
                        else if(x==1)
                        {value2 = registry.number.toString()}
                        else
                        {value3 = registry.number.toString()}
                    p--
                    var hex: String = helper.DecimalToHex(registry.number.toString())
                    string += hex}
                }
                if (registry.type == RegistryTypes.Real4) {
                    float = helper.FloatConverter(string)


                    var item = ParsedItem("Registers: " + text.register,  text.text + ": " + float.toString(), "Hex: "+ string+ " , value1: "+ value1  + ", value2: " + value2 + " , value3: "+ value3 + " , register id: " + registry.integer.toString()+ " , register type: " + registry.type.toString()+ " , manual text: " + text.register+ " , manual amount of register: " + text.number.toString(), registry.type.toString())
                    parsedvalues.add(listcounter, item)

                } else if (registry.type == RegistryTypes.Long) {
                    long = helper.HexToDecimal(string)


                    var item = ParsedItem("Registers: " + text.register,  text.text + ": " + long.toString(), "Hex: "+ string+ " , value1: "+ value1  + ", value2: " + value2 + " , value3: "+ value3 + " , register id: "+ registry.integer.toString()+ " , register type: " + registry.type.toString()+ " , manual text: " + text.register+ " , manual amount of register: " + text.number.toString(), registry.type.toString())
                    parsedvalues.add(listcounter, item)


                } else if (registry.type == RegistryTypes.Integer) {
                    if (registry.integer == 92){
                    string2 = helper.StringCutterFromRight(string, 2)
                    long = helper.HexToDecimal(string2)}
                    else
                    {long = helper.HexToDecimal(string2)}

                    var item = ParsedItem("Registers: " + text.register,  text.text + ": " + long.toString(), "Hex: "+ string+ " , value1: "+ value1  + ", value2: " + value2 + " , value3: "+ value3 + " , register id: "+ registry.integer.toString()+ " , register type: " + registry.type.toString()+ " , manual text: " + text.register+ " , manual amount of register: " + text.number.toString(), registry.type.toString())
                    parsedvalues.add(listcounter, item)

                } else if (registry.type == RegistryTypes.BCD) {
                    if (string.toLong() > 2147483647)
                    {
                        for(x in 0 until string.length step 2)
                        {
                            string3 = string.substring(x, x+2)
                            string2 += helper.toBCD(string3.toInt())
                        }
                    }
                    else{

                    string2 = helper.toBCD(string.toInt())}


                    var item = ParsedItem("Registers: " + text.register,  text.text + ": " + string2, "Hex: "+ string+ " , value1: "+ value1  + ", value2: "+ value2 + " , value3: "+ value3 + " , register id: " + registry.integer.toString()+ " , register type: " + registry.type.toString()+ " , manual text: " + text.register+ " , manual amount of register: " + text.number.toString(), registry.type.toString())
                    parsedvalues.add(listcounter, item)

                } else {
                    string2 = helper.hexToBin(string)


                    var item = ParsedItem("Registers: " + text.register,  text.text + ": " + string2, "Hex: "+ string+ " , value1: "+ value1  + ", value2: " + value2 + " , value3: "+ value3 + " , register id: " + registry.integer.toString()+ " , register type: " + registry.type.toString()+ " , manual text: " + text.register+ " , manual amount of register: " + text.number.toString(), registry.type.toString())
                    parsedvalues.add(listcounter, item)

                }

            } else if (text.number == 1)
            {
                meter = meter_values.getJSONObject(text.register.toInt() -2)
                registry.number = meter["value"].toString().toInt()

                    var hex = helper.DecimalToHex(registry.number.toString())
                    string = hex
                if (registry.type == RegistryTypes.Real4) {
                    float = helper.FloatConverter(string)


                    var item = ParsedItem("Registers: " + text.register,  text.text + ": " + float.toString(), "Hex: "+ string+ " , value1: "+ value1  + ", value2: " + value2 + " , value3: "+ value3 + " , register id: " + registry.integer.toString()+ " , register type: " + registry.type.toString()+ " , manual text: " + text.register+ " , manual amount of register: " + text.number.toString(), registry.type.toString())
                    parsedvalues.add(listcounter, item)


                } else if (registry.type == RegistryTypes.Long) {
                    long = helper.HexToDecimal(string)

                    var item = ParsedItem("Registers: " + text.register,  text.text + ": " + long.toString(), "Hex: "+ string+ " , value1: "+ value1  + ", value2: " + value2 + " , value3: "+ value3 + " , register id: " + registry.integer.toString()+ " , register type: " + registry.type.toString()+ " , manual text: " + text.register+ " , manual amount of register: " + text.number.toString(), registry.type.toString())
                    parsedvalues.add(listcounter, item)

                } else if (registry.type == RegistryTypes.Integer)
                {   if (string.length > 2){
                    string2 = helper.StringCutterFromRight(string, 2)
                    long = helper.HexToDecimal(string2)}
                    else long = helper.HexToDecimal(string)

                    var item = ParsedItem("Registers: " + text.register,  text.text + ": " + long.toString(), "Hex: "+ string+ " , value1: "+ value1  + ", value2: " + value2 + " , value3: "+ value3 + " , register id: " + registry.integer.toString()+ " , register type: " + registry.type.toString()+ " , manual text: " + text.register+ " , manual amount of register: " + text.number.toString(), registry.type.toString())
                    parsedvalues.add(listcounter, item)


                } else if (registry.type == RegistryTypes.BCD) {
                    string2 = helper.toBCD(string.toInt())



                    var item = ParsedItem("Registers: " + text.register,  text.text + ": " + string2, "Hex: "+ string+ " , value1: "+ value1  + ", value2: " + value2 + " , value3: "+ value3 + " , register id: " + registry.integer.toString()+ " , register type: " + registry.type.toString()+ " , manual text: " + text.register+ " , manual amount of register: " + text.number.toString(), registry.type.toString())
                    parsedvalues.add(listcounter, item)

                } else {
                    string2 = helper.hexToBin(string)


                    var item = ParsedItem("Registers: " + text.register,  text.text + ": " + string2, "Hex: "+ string+ " , value1: "+ value1  + ", value2: " + value2 + " , value3: "+ value3 + " , register id: " + registry.integer.toString()+ " , register type: " + registry.type.toString()+ " , manual text: " + text.register+ " , manual amount of register: " + text.number.toString(), registry.type.toString())
                    parsedvalues.add(listcounter, item)

                }

            }
            else {
            var item = ParsedItem("Registers: " + text.register,  text.text + ": Error", "Hex: "+ string+ " , value1: "+ value1  + ", value2: " + value2 + " , value3: "+ value3 + " , register id: " + registry.integer.toString()+ " , register type: " + registry.type.toString()+ " , manual text: " + text.register+ " , manual amount of register: " + text.number.toString(), registry.type.toString())
            parsedvalues.add(listcounter, item)}


            listcounter++


        }

        parsedvalues.count()


        return parsedvalues
    }
}