package android.example.gambitchallenge
import java.math.BigInteger


public class HexConverter{

    public fun DecimalToHex(DecimalNumber:String):String{
        val number: Int = DecimalNumber.toInt()
        val hex_value:String = Integer.toHexString(number)
        return hex_value.toUpperCase()

    }

    public fun HexToDecimal(hexnumber:String):Long{

        val error:Long = 0
        if(hexnumber == ""){
            return error}
        else
        {
            var decimal_value:Long = java.lang.Long.parseLong(hexnumber, 16)

            if(decimal_value > 2147483647.5)
            {
                decimal_value= decimal_value-4294967296
            }
            else decimal_value=decimal_value

            return decimal_value


        }}

    public fun FloatConverter(HexNumber:String):Float{

       val error:Float = 0f
        if (HexNumber=="")

        {return error}
        else
        {
            val i: Long = java.lang.Long.parseLong(HexNumber, 16)
            val float_value:Float = java.lang.Float.intBitsToFloat(i.toInt())
            return float_value
        }

    }
    public fun StringCutterFromRight(input:String, count:Int):String{
        val output: String = input.reversed()
        val result:String = output.substring(0,count)
        return result.reversed()
    }

    public fun toBCD(num: Int): String {
        var num = num
        var BCD = ""
        while (num != 0) {
            val t = num % 10
            var tBCD = Integer.toBinaryString(t)
            while (tBCD.length < 4) {
                tBCD = "0$tBCD"
            }
            BCD = tBCD + BCD
            num /= 10
        }
        return BCD
    }
   public fun hexToBin(s:String):String{
       val result: String = BigInteger(s, 16).toString(2);
       return result
   }

}
