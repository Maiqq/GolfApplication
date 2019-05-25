package android.example.workouttrackaer


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="exercise_list_table")
data class SelectExerciseListItem(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var name:String?
)


