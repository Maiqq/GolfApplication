package android.example.workouttrackaer

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SelectExerciseViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: SelectExerciseListRepository
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allExercises: LiveData<List<SelectExerciseListItem>>

    init {
        val selectExerciseListDao = SelectExerciseListRoomDatebase.getDatabase(application, viewModelScope).selectExerciseListDao()
        repository = SelectExerciseListRepository(selectExerciseListDao)
        allExercises = repository.allExercises
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert (selectExerciseListItem: SelectExerciseListItem) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(selectExerciseListItem)
    }
}