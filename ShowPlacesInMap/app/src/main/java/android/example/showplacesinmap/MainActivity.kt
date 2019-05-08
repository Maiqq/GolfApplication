


package android.example.showplacesinmap


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Use LinearManager as a layout manager for recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Instantiate the RequestQueue
        val queue = Volley.newRequestQueue(this)
        // URL to JSON data - remember use your own data here
        val url = "https://e947c3bf-8f97-4f7c-8f91-ded1c5b6d230.mock.pstmn.io/places.json"
        // Create request and listeners
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                // Get employees from JSON
                val places = response.getJSONArray("places")
                // Create Employees Adapter with employees data
                recyclerView.adapter = PlacesAdapter(places)

            },
            Response.ErrorListener { error ->
                Log.d("JSON",error.toString())
            }
        )
        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest)
    }
}