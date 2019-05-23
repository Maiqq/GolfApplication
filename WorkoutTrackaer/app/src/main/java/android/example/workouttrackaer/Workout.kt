package android.example.workouttrackaer

import java.time.LocalDate
import java.util.*

class Workout (d:LocalDate?, n:String?){

    private lateinit var date: LocalDate
    private lateinit var name:String
    private lateinit var exerciseList:MutableList<Exercise>

    init {
        if(d==null) date = LocalDate.now()
        else date = d
        if(n == null) name= date.toString()
        else name=n
    }
}