package android.example.workouttrackaer

import android.app.Activity
import android.content.Intent
import android.example.workouttrackaer.SelectExerciseListRoomDatebase.Companion.datebaseItemCounter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText



class NewExerciseActivity : androidx.appcompat.app.AppCompatActivity() {

    private lateinit var exerciseNameEditText: EditText
    private lateinit var exercisePbEditText:EditText

    companion object {
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
        var count:Int = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_exercise)
        exerciseNameEditText = findViewById(R.id.exerciseNameEditText)
        exercisePbEditText = findViewById(R.id.pbEditText)


        val button = findViewById<Button>(R.id.btn_addNewExercise)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(exerciseNameEditText.text) && TextUtils.isEmpty(exercisePbEditText.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                count = datebaseItemCounter + 1
                val name = exerciseNameEditText.text.toString()
                val exercise: String = count.toString() + ", " + name
                replyIntent.putExtra(EXTRA_REPLY, exercise)
                setResult(Activity.RESULT_OK, replyIntent)
            }

            finish()
        }
    }


}
