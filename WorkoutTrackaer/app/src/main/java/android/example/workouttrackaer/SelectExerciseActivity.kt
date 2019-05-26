package android.example.workouttrackaer

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast


import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SelectExerciseActivity: AppCompatActivity() {

    private val newExerciseActivityRequestCode = 1
    private lateinit var selectExerciseViewModel:SelectExerciseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.select_exercise)

        val recyclerView = findViewById<RecyclerView>(R.id.exerciseRecyclerView)
        val adapter = SelectExerciseListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Get a new or existing ViewModel from the ViewModelProvider.
        selectExerciseViewModel = ViewModelProviders.of(this).get(SelectExerciseViewModel::class.java)

        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        selectExerciseViewModel.allExercises.observe(this, Observer { exercises ->
            // Update the cached copy of the words in the adapter.
            exercises?.let { adapter.setExercises(it) }
        })

        val btnAddExercise = findViewById<Button>(R.id.btn_addNewExercise)
        btnAddExercise.setOnClickListener {
            val intent = Intent(this@SelectExerciseActivity, NewExerciseActivity::class.java)
            startActivityForResult(intent, newExerciseActivityRequestCode)

        }



    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == newExerciseActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.let { data ->
                val dataString = data.getStringExtra(NewExerciseActivity.EXTRA_REPLY)
                val count:String = dataString.substringBefore(",")
                val name:String = dataString.substringAfter(",")
                val exercise = SelectExerciseListItem(count.toInt(), name)
                selectExerciseViewModel.insert(exercise)
                SelectExerciseListRoomDatebase.databaseItemCounter++

            }
        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG
            ).show()
        }
    }
}

