package android.example.workouttrackaer

import android.content.Intent
import android.os.Bundle
import android.util.Log


import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem

import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

import com.google.android.material.tabs.TabLayout
import org.json.JSONArray


class MainActivity : AppCompatActivity() {

    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    lateinit var tablayout: TabLayout
    lateinit var viewPager: androidx.viewpager.widget.ViewPager


    companion object{
        var exerciseList:JSONArray = JSONArray()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        viewPager = findViewById(R.id.view_pager) as androidx.viewpager.widget.ViewPager
        setupViewPager(viewPager)
        tablayout = findViewById(R.id.tabs) as TabLayout
        tablayout.setupWithViewPager(viewPager)
        viewPager.setCurrentItem(1)
        loadExerciseListJsonData()




    }

    private fun setupViewPager(viewPager: androidx.viewpager.widget.ViewPager) {

        var adapter:ViewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(FragmentA(),"Yesterday")
        adapter.addFragment(FragmentA(),"Today")
        adapter.addFragment(FragmentA(), "Tomorrow")
        adapter.addFragment(FragmentA(), "Day after tomorrow")
        viewPager.adapter = adapter

    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){

            R.id.calendar_button ->{
                val intent = Intent(this, CalendarActivity::class.java)
                this.startActivity(intent)

                true

            }

        else -> super.onOptionsItemSelected(item)
        }
    }
    private fun loadExerciseListJsonData()
    {
        // Instantiate the RequestQueue
        val queue = Volley.newRequestQueue(this)
        // URL to JSON data
        val url = "https://952417e5-ea21-47a9-a5e3-f2be17aa53c8.mock.pstmn.io/exercise_list"
        // Create request and listeners
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                exerciseList = response.getJSONArray("exercise_list")

            },
            Response.ErrorListener { error ->
                Log.d("JSON",error.toString())
            }
        )
        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest)

    }

}