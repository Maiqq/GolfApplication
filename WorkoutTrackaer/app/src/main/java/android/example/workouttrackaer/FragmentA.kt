package android.example.workouttrackaer

import android.app.PendingIntent.getActivity
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import kotlinx.android.synthetic.main.fragmenta.*
import android.support.v4.content.ContextCompat.startActivity
import kotlinx.android.synthetic.main.activity_main.*




class FragmentA:Fragment(),View.OnClickListener {

    

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        val view = inflater.inflate(R.layout.fragment_main, container, false)
        val btnAddWorkout = view.findViewById<Button>(R.id.btn_addWorkout)
        btnAddWorkout.setOnClickListener(this)

        return view

    }

    override fun onClick(v: View) {
            when (v.id)
            {
                R.id.btn_addWorkout -> {
                    buttonClicked()
                }
            }

        }
    private fun buttonClicked(){
        val intent = Intent(this.context, SelectExerciseActivity::class.java)
        startActivity(intent)

    }


    }
