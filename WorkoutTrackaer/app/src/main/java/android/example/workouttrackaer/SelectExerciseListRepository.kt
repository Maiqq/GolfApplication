package android.example.workouttrackaer

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class SelectExerciseListRepository(private val selectExerciseListDao: SelectExerciseListDao) {


         // Room executes all queries on a separate thread.
        // Observed LiveData will notify the observer when the data has changed.
        val allExercises: LiveData<List<SelectExerciseListItem>> = selectExerciseListDao.getAll()

        // You must call this on a non-UI thread or your app will crash. So we're making this a
        // suspend function so the caller methods know this.
        // Like this, Room ensures that you're not doing any long running operations on the main
        // thread, blocking the UI.
        @WorkerThread
        suspend fun insert(selectExerciseListItem: SelectExerciseListItem) {
            selectExerciseListDao.insert(selectExerciseListItem)

    }
}