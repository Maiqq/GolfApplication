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


class ValueAdapter(private val list:MutableList<ParsedItem>) : RecyclerView.Adapter<ValueAdapter.ViewHolder>() {


    override fun onBindViewHolder(holder: ValueAdapter.ViewHolder, position: Int) {

        var item: ParsedItem = list[position]

        holder.manualTextView.text = item.register
        holder.parsedTextView.text = item.value







    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ValueAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.value_item, parent, false)
        return ViewHolder(view)
    }



    override fun getItemCount(): Int = list.size


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val manualTextView: TextView = view.manualTextView
        val parsedTextView: TextView = view.parsedTextView


        // add a item click listener
        init {
            itemView.setOnClickListener {

                val intent = Intent(view.context, itemActivity::class.java)
                intent.putExtra("item",list[adapterPosition])
                view.context.startActivity(intent)
            }
        }

    }




}