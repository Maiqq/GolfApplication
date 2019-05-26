package android.example.workouttrackaer




import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView



class SelectExerciseListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<SelectExerciseListAdapter.ExerciseViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var exercises = emptyList<SelectExerciseListItem>() // Cached copy of words


    inner class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val exerciseItemView: TextView = itemView.findViewById(R.id.exerciseNameTextView)
    }
    // create UI View Holder from XML layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectExerciseListAdapter.ExerciseViewHolder {
        val view = inflater.inflate(R.layout.select_exercise_recycler_view_item, parent, false)
        return ExerciseViewHolder(view)
    }



    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val item = exercises[position]
        holder.exerciseItemView.text = item.name
    }


     internal fun setExercises(exercises: List<SelectExerciseListItem>) {
        this.exercises = exercises
        notifyDataSetChanged()
    }

    override fun getItemCount():Int = exercises.size


}