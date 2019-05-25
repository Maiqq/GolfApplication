package android.example.workouttrackaer




import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.select_exercise_recycler_view_item.view.*


class SelectExerciseListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<SelectExerciseListAdapter.ExerciseViewHolder>() {


    private var exercises = emptyList<SelectExerciseListItem>() // Cached copy of words

    // create UI View Holder from XML layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectExerciseListAdapter.ExerciseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.select_exercise_recycler_view_item, parent, false)
        return ExerciseViewHolder(view)
    }

    override fun getItemCount():Int = exercises.size

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val item:SelectExerciseListItem = exercises[position]
        holder.exerciseItemView.text = item.name
    }


    inner class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val exerciseItemView: TextView = itemView.findViewById(R.id.exerciseNameTextView)
    }


    internal fun setWords(exercises: List<SelectExerciseListItem>) {
        this.exercises = exercises
        notifyDataSetChanged()
    }


}