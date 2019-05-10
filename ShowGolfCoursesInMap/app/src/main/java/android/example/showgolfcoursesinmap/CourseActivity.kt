package android.example.showgolfcoursesinmap

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_course.*
import kotlinx.android.synthetic.main.activity_course.imageView
import kotlinx.android.synthetic.main.course_item.view.*
import org.json.JSONObject

class CourseActivity : AppCompatActivity() {

    // EmployeeActivity's onCreate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course)

        // get data from intent
        val bundle: Bundle? = intent.extras;
        if (bundle != null) {
            val courseString = bundle!!.getString("course")
            val course = JSONObject(courseString)

            val name = course["course"].toString()
            val type = course["type"].toString()
            val address = course["address"].toString()
            val phone = course["phone"].toString()
            val email = course["email"].toString()
            val web = course["web"].toString()
            val info = course["text"].toString()


            val imageView = findViewById<ImageView>(R.id.imageView).apply{
                Glide.with(imageView.context).load("http://ptm.fi/materials/golfcourses/" + course["image"]).into(imageView)
            }

            //Capture the layout's TextView and set the string as its text
            val courseTextView = findViewById<TextView>(R.id.courseTextView).apply {
                text = name
            }

            typeTextView.text = type
            addressTextView.text = email
            phoneTextView.text = phone
            emailTextView.text = email
            webTextView.text = web
            courseInformation.text = info



       //     val imageView = findViewById<ImageView>(R.id.imageView).apply{
         //       Glide.with(imageView.context).load(course["image"]).into(imageView)
         //   }



        }

    }
}