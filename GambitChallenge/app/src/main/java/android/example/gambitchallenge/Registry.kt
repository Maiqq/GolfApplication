package android.example.gambitchallenge

import android.example.gambitchallenge.RegistryTypes


public class Registry {



    public var number:Int
    public var integer:Int
    public var type:RegistryTypes

    constructor(n:Int,i:Int)
    {
            this.number = n
            this.integer = i


        if ((((((number > 0) && (number < 9))) || (((((number > 10) && (number < 13))) || (((((number > 14) && (number < 17))) || (((((number > 19) && (number < 21))) || (((((number > 22) && (number < 25))) || (((((number > 26) && (number < 29))) || (((((number > 30) && (number < 49))) || (((((number > 76) && (number < 91))) || (((number > 96) && (number < 101))))))))))))))))))))
        {
            this.type = RegistryTypes.Real4
        }
        else if ((((number > 48) && (number < 57))))
        {
            this.type = RegistryTypes.BCD
        }
        else if ((((((number > 58) && (number < 63))) || (((number > 91) && (number < 97))))))
        {
            this.type = RegistryTypes.Integer
        }
        else if ((number == 72))
        {
           this.type = RegistryTypes.Bit
        }
        else
        {
            this.type = RegistryTypes.Long
        }
    }




}