package android.example.workouttrackaer

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.Toast
import kotlinx.android.synthetic.main.calender.*
import kotlinx.android.synthetic.main.calender.view.*

class calenderFragment: Fragment() {





    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var rootview = inflater.inflate(R.layout.calender, container, false)
        rootview.calendarView.setOnDateChangeListener{view, year, month, dayOfMonth ->
            val msg = "Selected date is " + dayOfMonth + "/" + (month + 1) + "/" + year
            rootview.textView2.text = msg

        }






        return rootview
    }
}