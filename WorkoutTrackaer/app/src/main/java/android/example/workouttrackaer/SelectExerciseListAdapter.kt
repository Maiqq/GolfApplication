package android.example.workouttrackaer



import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.select_exercise_recycler_view_item.view.*


class SelectExerciseListAdapter(private val parentActivity: MainActivity): RecyclerView.Adapter<SelectExerciseListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectExerciseListAdapter.ViewHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.select_exercise_recycler_view_item,parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = 0

    override fun onBindViewHolder(holder: SelectExerciseListAdapter.ViewHolder, position: Int) {
        holder.exerciseNameTextView
    }

    inner class ViewHolder(view:View) : RecyclerView.ViewHolder(view){

        val exerciseNameTextView:TextView = view.exerciseNameTextVIew

    }

}