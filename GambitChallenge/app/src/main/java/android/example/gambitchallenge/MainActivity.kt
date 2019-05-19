package android.example.gambitchallenge

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.android.volley.toolbox.JsonArrayRequest
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {




    companion object {
        var meter_values:JSONArray = JSONArray()
        var manual_values:JSONArray = JSONArray()
        var parsedvalues:MutableList<ParsedItem> = mutableListOf<ParsedItem>()



    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

      loadManualJsonData()
        if (manual_values.length()==0)
        {
            loadManualJsonData()
            loadMeterJsonData()
        }
        else {
            loadMeterJsonData()
        }
        val btn_submit = findViewById<Button>(R.id.btnSubmit)
        btn_submit.setOnClickListener{
            var data:JSONArray = createJSONARRAY(parsedvalues)

            postJson(data)
        }

    }

    private fun createJSONARRAY(list:MutableList<ParsedItem>):JSONArray
    {
        var jsonArray:JSONArray = JSONArray()
        for (x in list)
        {
            var jsonString:String = Gson().toJson(x.register + "," + x.value)
            jsonArray.put(jsonString)
        }

        return jsonArray
    }
    // Load JSON from the net
    private fun loadMeterJsonData(){
        // Instantiate the RequestQueue
        val queue = Volley.newRequestQueue(this)
        // URL to JSON data
        val url = "https://5cd8d20f-0a03-4412-9300-6e5f015d54a6.mock.pstmn.io/registry"

        // Create request and listeners
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->

                meter_values = response.getJSONArray("meter_values")

                Log.d("PTM", meter_values.toString())
                Log.d("PTM", manual_values.toString())

                var Parser = Parser(meter_values, manual_values)
               var list = Parser.createList()

                Log.d("PTM", list.toString())
                recyclerView.layoutManager = LinearLayoutManager(this)
                recyclerView.adapter = ValueAdapter(Parser.parsedvalues)
                parsedvalues = Parser.parsedvalues


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
        val url = "https://0e0cb4da-66bc-4108-b9a9-469221f03406.mock.pstmn.io/manual"
        // Create request and listeners
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                manual_values = response.getJSONArray("manual_text")

            },
            Response.ErrorListener { error ->
                Log.d("JSON",error.toString())
            }
        )
        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest)

    }
    // Post Json
    private fun postJson(jsonArray:JSONArray) {
        // Instantiate the RequestQueue
        val senderArray:JSONArray = jsonArray

        val queue = Volley.newRequestQueue(this)
        // URL to JSON data
        val url = "https://98b75b94-db5e-46a5-9f6c-de12ae3e6e12.mock.pstmn.io/post"
        // Create request and listeners
        val jsonArray= JsonArrayRequest(
            Request.Method.POST, url, senderArray,
            Response.Listener { response ->

                val r = response.getJSONArray(0)
                var message:String = "Successful post"
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()



            },
            Response.ErrorListener { error ->
                Log.d("JSON",error.toString())

            }

        )
        // Add the request to the RequestQueue.
        queue.add(jsonArray)

    }


}
