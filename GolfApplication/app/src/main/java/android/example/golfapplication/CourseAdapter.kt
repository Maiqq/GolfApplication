package android.example.golfapplication

import android.content.Intent
import android.example.golfapplication.MainActivity.Companion.course
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.recycler_view_item.view.*
import org.json.JSONObject

class CourseAdapter (
    private val parentActivity: MainActivity
) : RecyclerView.Adapter<CourseAdapter.ViewHolder>() {


    companion object {
        var position: Int = 0
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.recycler_view_item, parent, false)
        return ViewHolder(view)
    }

    // return item count in employees
    override fun getItemCount(): Int = MainActivity.course.length()

    // bind data to UI View Holder
    override fun onBindViewHolder(holder: CourseAdapter.ViewHolder, position: Int) {
        // employee to bind UI
        val course: JSONObject = MainActivity.course.getJSONObject(position)
        // name
        holder.nameTextView.text = course["course"].toString()
        holder.addressTextView.text = course["address"].toString()
        // email
        holder.emailTextView.text = course["email"].toString()
        // phone
        holder.phoneTextView.text = course["phone"].toString()
        // department
        // image

        Glide.with(holder.imageView.context).load("http://ptm.fi/materials/golfcourses/" + course["image"])
            .apply(RequestOptions.circleCropTransform()).into(holder.imageView)
    }

    // View Holder class to hold UI views
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.nameTextView
        val addressTextView: TextView = view.addressTextView
        val emailTextView: TextView = view.emailTextView
        val phoneTextView: TextView = view.phoneTextView
        val imageView: ImageView = view.imageView

        // add a item click listener
        init {
            itemView.setOnClickListener {
                // create an explicit intent
                val intent = Intent(view.context, CourseActivity::class.java)
                // add selected employee json as a string data
                intent.putExtra("course", course[adapterPosition].toString())
                // start a new activity
                view.context.startActivity(intent)
            }

        }
    }
}