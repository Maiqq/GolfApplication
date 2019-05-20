package android.example.golfapplication

import android.content.Intent
import android.content.pm.ResolveInfo
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_course.*
import kotlinx.android.synthetic.main.activity_course.view.*
import org.json.JSONObject

class CourseActivity : AppCompatActivity() {

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
            val lat = course["lat"].toString()
            val lng = course["lng"].toString()

            val mToolbar:Toolbar = toolbar
            setSupportActionBar(mToolbar)
            mToolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_material)
            supportActionBar!!.setTitle("SGKY:"+name)
            mToolbar.setNavigationOnClickListener{
                val intent = Intent(this, MainActivity::class.java)
                this.startActivity(intent)
            }

            val imageView = findViewById<ImageView>(R.id.imageView).apply {
                Glide.with(imageView.context).load("http://ptm.fi/materials/golfcourses/" + course["image"])
                    .into(imageView)
            }
            nameTextView.text = name
            addressTextView.text = address
            phoneTextView.text = phone
            emailTextView.text = email
            webTextView.text = web
            descriptionTextView.text=info
            mapTextView.text = "Näytä kartalla"
            mapTextView.setOnClickListener{

                // Build the intent
                val geoUriString:String = "geo:"+lat+","+lng+"?q="+lat+","+lng+"("+name+")"
                val location = Uri.parse(geoUriString)
                val mapIntent = Intent(Intent.ACTION_VIEW, location)

                // Verify it resolves
                val activities: List<ResolveInfo> = packageManager.queryIntentActivities(mapIntent, 0)
                val isIntentSafe: Boolean = activities.isNotEmpty()

                // Start an activity if it's safe
                if (isIntentSafe) {
                    startActivity(mapIntent)
                } else {
                    Toast.makeText(this, "There is no activity to handle map intent!", Toast.LENGTH_LONG).show();
                }
            }
            }

            fun showMap(view: View, latitude:String, longitude:String){


                val lat:String = latitude
                val lng:String = longitude

                // Build the intent
                val geoUriString:String = "geo:"+lat+","+lng+"?q="+lat+","+lng
                val location = Uri.parse(geoUriString)
                val mapIntent = Intent(Intent.ACTION_VIEW, location)

                // Verify it resolves
                val activities: List<ResolveInfo> = packageManager.queryIntentActivities(mapIntent, 0)
                val isIntentSafe: Boolean = activities.isNotEmpty()

                // Start an activity if it's safe
                if (isIntentSafe) {
                    startActivity(mapIntent)
                } else {
                    Toast.makeText(this, "There is no activity to handle map intent!", Toast.LENGTH_LONG).show();
                }
            }


        }
    }

