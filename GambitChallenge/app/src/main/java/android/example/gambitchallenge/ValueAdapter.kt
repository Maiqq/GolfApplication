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
class ValueAdapter(private val parsedlist:MutableList<String>) : RecyclerView.Adapter<ValueAdapter.ViewHolder>() {

    // bind data to UI View Holder
    override fun onBindViewHolder(holder: ValueAdapter.ViewHolder, position: Int) {
        // employee to bind UI
        val item: String = parsedlist[position]
        // name
        holder.manualTextView.text = item.substringBefore(",")
        holder.parsedTextView.text = item.substringAfter(",").substringBefore(",")
        holder.fullParsedTextView.text =  item.substringAfter("Unparsed")





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
    override fun getItemCount(): Int = parsedlist.size

    // View Holder class to hold UI views
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val manualTextView: TextView = view.manualTextView
        val parsedTextView: TextView = view.parsedTextView
        val fullParsedTextView: TextView = view.fullParsedTextView



    }




}