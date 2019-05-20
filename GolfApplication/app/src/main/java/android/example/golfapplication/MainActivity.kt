package android.example.golfapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.support.design.widget.CollapsingToolbarLayout

import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*

import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    companion object {
        var course: JSONArray = JSONArray()
    }


        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
            val mToolbar:Toolbar = toolbar1
            setSupportActionBar(mToolbar)

        if(course.length()==0) loadGolfCoursesJsonData()
            else setupRecyclerView(course)

            val collapsingToolbar = findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout)
            collapsingToolbar.title = "SGKY - Kentät"



        }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    // Add layout manager and adapter to recycler view
    private fun setupRecyclerView(course: JSONArray) {
        recylerView.layoutManager = LinearLayoutManager(this)
        recylerView.adapter = CourseAdapter(this)
    }

    // Load JSON from the net
    private fun loadGolfCoursesJsonData() {
        // Instantiate the RequestQueue
        val queue = Volley.newRequestQueue(this)
        // URL to JSON data
        val url = "http://ptm.fi/materials/golfcourses/golf_courses.json"
        // Create request and listeners
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                course = response.getJSONArray("courses")
                setupRecyclerView(course)
            },
            Response.ErrorListener { error ->
                Log.d("JSON",error.toString())
            }
        )
        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest)

    }
    }





