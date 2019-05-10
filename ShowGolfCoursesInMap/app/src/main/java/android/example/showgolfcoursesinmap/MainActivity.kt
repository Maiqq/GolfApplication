package android.example.showgolfcoursesinmap

import android.content.Intent
import android.content.pm.ResolveInfo
import android.net.Uri
import android.os.Bundle


import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.android.volley.Request

import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Use LinearManager as a layout manager for recyclerView
        recylerView.layoutManager = LinearLayoutManager(this)

        // Instantiate the RequestQueue
        val queue = Volley.newRequestQueue(this)
        // URL to JSON data - remember use your own data here
        val url = "http://ptm.fi/materials/golfcourses/golf_courses.json"
        // Create request and listeners
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            com.android.volley.Response.Listener { response ->
                //                // Get employees from JSON
                val courses = response.getJSONArray("courses")
                // Create Employees Adapter with employees data
                recylerView.adapter = CourseAdapter(courses)
            },
            com.android.volley.Response.ErrorListener { error ->
                Log.d("JSON",error.toString())
            }


        )

        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest)


        val button = this.findViewById<Button>(R.id.btnShowMap)
        button.setOnClickListener{
            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
        }


    }

    }
