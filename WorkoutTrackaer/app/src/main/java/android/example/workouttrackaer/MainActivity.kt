package android.example.workouttrackaer

import android.os.Bundle

import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem


class MainActivity : AppCompatActivity() {

    lateinit var toolbar: android.support.v7.widget.Toolbar
    lateinit var tablayout:TabLayout
    lateinit var viewPager: ViewPager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        viewPager = findViewById(R.id.view_pager) as ViewPager
        setupViewPager(viewPager)
        tablayout = findViewById(R.id.tabs) as TabLayout
        tablayout.setupWithViewPager(viewPager)
        viewPager.setCurrentItem(1)

    }

    private fun setupViewPager(viewPager: ViewPager) {

        var adapter:ViewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(FragmentA(),"Yesterday")
        adapter.addFragment(FragmentB(),"Today")
        adapter.addFragment(FragmentC(), "Tomorrow")
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

            supportFragmentManager
                .beginTransaction()
                .replace(R.id.calendarView, calenderFragment())
                .commit()

                true
            }

        else -> super.onOptionsItemSelected(item)
        }
    }
}