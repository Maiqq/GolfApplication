package android.example.workouttrackaer




import android.content.Context
import android.example.workouttrackaer.MainActivity.Companion.exerciseList
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView



class SelectExerciseListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<SelectExerciseListAdapter.ExerciseViewHolder>(), Filterable {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var exercises = emptyList<SelectExerciseListItem>() // Cached copy of words
    private var filterableList = exercises


    inner class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val exerciseItemView: TextView = itemView.findViewById(R.id.exerciseNameTextView)
    }
    // create UI View Holder from XML layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectExerciseListAdapter.ExerciseViewHolder {
        val view = inflater.inflate(R.layout.select_exercise_recycler_view_item, parent, false)
        return ExerciseViewHolder(view)
    }



    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val item = filterableList[position]
        holder.exerciseItemView.text = item.name
    }


     internal fun setExercises(exercises: List<SelectExerciseListItem>) {
        this.exercises = exercises
         this.filterableList = exercises
        notifyDataSetChanged()
    }

    override fun getItemCount():Int = filterableList.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): Filter.FilterResults {
                val charString = charSequence.toString()
                if (charString.isEmpty()) {
                    //TODO FIX List to handle to update fully if empty
                    filterableList = exercises

                } else {
                    val filteredList = ArrayList<SelectExerciseListItem>()
                    for (row in exercises) {
                        if (row.name!!.toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row)
                        }
                    }
                    filterableList = filteredList
                }
                val filterResults = Filter.FilterResults()
                filterResults.values = filterableList
                return filterResults
            }
            override fun publishResults(charSequence: CharSequence, filterResults: Filter.FilterResults) {
                filterableList = filterResults.values as ArrayList<SelectExerciseListItem>
                notifyDataSetChanged()
            }
        }
    }
}