package android.example.workouttrackaer

import android.os.Bundle


import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_calendar.*

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
        selectExerciseViewModel.allExercises.observe(this, Observer { words ->
            // Update the cached copy of the words in the adapter.
            words?.let { adapter.setWords(it) }
        })



    }
}

