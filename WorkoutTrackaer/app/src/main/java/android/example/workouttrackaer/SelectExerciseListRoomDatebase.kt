package android.example.workouttrackaer



import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject

@Database(entities = [SelectExerciseListItem::class], version = 4)
abstract class SelectExerciseListRoomDatebase: RoomDatabase() {

    abstract fun selectExerciseListDao(): SelectExerciseListDao

    companion object{
        @Volatile
        private var INSTANCE:SelectExerciseListRoomDatebase? = null
         var databaseItemCounter:Int = 0


        fun getDatabase(
            context:Context,
            scope: CoroutineScope
        ):SelectExerciseListRoomDatebase{
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SelectExerciseListRoomDatebase::class.java,
                    "exercise_list_database"
                )
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    // Migration is not part of this codelab.
                    .fallbackToDestructiveMigration()
                    .addCallback(SelectExerciseListDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
        private class SelectExerciseListDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            /**
             * Override the onOpen method to populate the database.
             * For this sample, we clear the database every time it is created or opened.
             */
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.selectExerciseListDao())
                    }
                }
            }
        }
        /**
         * Populate the database in a new coroutine.
         * If you want to start with more words, just add them.
         */
        suspend fun populateDatabase(selectExerciseListDao: SelectExerciseListDao) {

            selectExerciseListDao.deleteAll()

           if(selectExerciseListDao.count().toString().toInt() == 0) {

               for (x in 0 until MainActivity.exerciseList.length()) {
                   var exercise: JSONObject = MainActivity.exerciseList.getJSONObject(x)
                   var newExercise = SelectExerciseListItem(
                       exercise["id"].toString().toInt(),
                       exercise["name"].toString()
                   )
                   selectExerciseListDao.insert(newExercise)
               }
           }
            else
            {
            Log.d("PTM", "Database already populated")}

            databaseItemCounter = selectExerciseListDao.count()
        }




        }
    }
