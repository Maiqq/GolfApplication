package android.example.workouttrackaer

class Exercise(n:String) {

    private lateinit var name:String
    private lateinit var setList: MutableList<Set>
    private var personalBest: Double = 0.0
    init {
        name = n
    }
}