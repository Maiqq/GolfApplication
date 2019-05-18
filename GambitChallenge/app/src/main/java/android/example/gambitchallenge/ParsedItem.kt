package android.example.gambitchallenge

import java.io.Serializable


public class ParsedItem(r:String,v:String,u:String, t:String): Serializable{


        public val register: String
        public val value: String
        public val unparsed: String
        public val type:String

        init {
            register = r
            value = v
            unparsed = u
            type = t
        }

}