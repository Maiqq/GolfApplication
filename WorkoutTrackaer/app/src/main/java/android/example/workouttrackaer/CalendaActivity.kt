package android.example.workouttrackaer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.CalendarView
import kotlinx.android.synthetic.main.activity_calendar.*

import java.util.*

class CalendarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)
        val calendar:CalendarView = calendarView
        calendarView.setOnDateChangeListener{view, year, month, dayOfMonth ->
            val msg = "Selected date is " + dayOfMonth + "/" + (month + 1) + "/" + year
            textView2.text = msg


    }



    }
}
