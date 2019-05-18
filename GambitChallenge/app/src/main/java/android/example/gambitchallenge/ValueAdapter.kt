package android.example.gambitchallenge

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.value_item.view.*
import org.json.JSONArray
import org.json.JSONObject

// Employees Adapter, used in RecyclerView in MainActivity
class ValueAdapter(private val list:MutableList<ParsedItem>) : RecyclerView.Adapter<ValueAdapter.ViewHolder>() {

    // bind data to UI View Holder
    override fun onBindViewHolder(holder: ValueAdapter.ViewHolder, position: Int) {
        // employee to bind UI
        var item: ParsedItem = list[position]
        // name
        holder.manualTextView.text = item.register
        holder.parsedTextView.text = item.value






        // title, email, phone, department, image
        // to get context in Glide, you can use holder.imageView.context
    }

    // create UI View Holder from XML layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ValueAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.value_item, parent, false)
        return ViewHolder(view)
    }


    // return item count in employees
    override fun getItemCount(): Int = list.size

    // View Holder class to hold UI views
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val manualTextView: TextView = view.manualTextView
        val parsedTextView: TextView = view.parsedTextView


        // add a item click listener
        init {
            itemView.setOnClickListener {
                // create an explicit intent
                val intent = Intent(view.context, itemActivity::class.java)
                // add selected employee json as a string data
                intent.putExtra("item",list[adapterPosition])
                // start a new activity
                view.context.startActivity(intent)
            }
        }

    }




}