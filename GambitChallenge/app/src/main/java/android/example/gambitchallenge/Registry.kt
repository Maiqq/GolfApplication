package android.example.gambitchallenge




public class Registry {



    public var number:Int
    public var integer:Int
    public var type:RegistryTypes

    constructor(n:Int,i:Int)
    {
            this.number = n
            this.integer = i


        if ((((((integer > 0) && (integer < 9))) || (((((integer > 10) && (integer < 13))) || (((((integer > 14) && (integer < 17))) || (((((integer > 18) && (integer < 21))) || (((((integer > 22) && (integer < 25))) || (((((integer > 26) && (integer < 29))) || (((((integer > 30) && (integer < 49))) || (((((integer > 76) && (integer < 91))) || (((integer > 96) && (integer < 101))))))))))))))))))))
        {
            this.type = RegistryTypes.Real4
        }
        else if ((((integer > 48) && (integer < 57))))
        {
            this.type = RegistryTypes.BCD
        }
        else if ((((((integer > 58) && (integer < 63))) || (((integer > 91) && (integer < 97))))))
        {
            this.type = RegistryTypes.Integer
        }
        else if ((integer == 72))
        {
           this.type = RegistryTypes.Bit
        }
        else
        {
            this.type = RegistryTypes.Long
        }
    }




}