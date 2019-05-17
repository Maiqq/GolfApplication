package android.example.gambitchallenge

import android.example.gambitchallenge.MainActivity.Companion.manual_values
import android.example.gambitchallenge.MainActivity.Companion.meter_values
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import android.example.gambitchallenge.Parser
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



    // employees static object - can be used as MainActivity.employees
    companion object {
        var meter_values:JSONArray = JSONArray()
        var manual_values:JSONArray = JSONArray()



    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (meter_values.length() == 0 && manual_values.length() == 0) {
            loadManualJsonData()
            loadMeterJsonData()
            var Parser = Parser(meter_values, manual_values)

        } else {
            var Parser = Parser(meter_values, manual_values)
            Parser.createList()
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = ValueAdapter(Parser.parsedList)

        }

    }

    // Load JSON from the net
    private fun loadMeterJsonData() {
        // Instantiate the RequestQueue
        val queue = Volley.newRequestQueue(this)
        // URL to JSON data
        val url = "https://82e472de-ec98-488d-a246-ad79c67e85df.mock.pstmn.io/meter_values.json"

        // Create request and listeners
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                // store loaded json to static employees
                meter_values = response.getJSONArray("meter_values")

                // open Logcat and you will see both lists
                Log.d("PTM", meter_values.toString())
                Log.d("PTM", manual_values.toString())
                // uncomment below lines to use your parser, now it gives some errors...
                var Parser = Parser(meter_values, manual_values)
               var list = Parser.createList()

                Log.d("PTM", list.toString())
                recyclerView.layoutManager = LinearLayoutManager(this)
                recyclerView.adapter = ValueAdapter(Parser.parsedList)



                // setup recycler view
                //setupRecyclerView(employees)

            },
            Response.ErrorListener { error ->
                Log.d("JSON",error.toString())
            }
        )
        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest)

    }
    // Load JSON from the net
    private fun loadManualJsonData() {
        // Instantiate the RequestQueue
        val queue = Volley.newRequestQueue(this)
        // URL to JSON data
        val url = "https://8f90af5c-01a1-41d5-8a29-006e1f57e7bb.mock.pstmn.io/manual.json"
        // Create request and listeners
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                // store loaded json to static employees
                manual_values = response.getJSONArray("manual_text")
                // setup recycler view
                //setupRecyclerView(employees)
            },
            Response.ErrorListener { error ->
                Log.d("JSON",error.toString())
            }
        )
        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest)

    }


}
