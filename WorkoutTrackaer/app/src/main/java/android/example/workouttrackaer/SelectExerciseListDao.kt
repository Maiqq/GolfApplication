package android.example.workouttrackaer



import androidx.lifecycle.LiveData

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SelectExerciseListDao {

    @Query("SELECT * from exercise_list_table ORDER BY name ASC")
    fun getAll(): LiveData<List<SelectExerciseListItem>>


    @Query("SELECT COUNT(name) from exercise_list_table")
    fun count():Int

    @Insert
    suspend fun insert(selectExerciseListItem: SelectExerciseListItem)

    @Query("DELETE FROM exercise_list_table")
    fun deleteAll()

}