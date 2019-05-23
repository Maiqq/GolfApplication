package android.example.workouttrackaer

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class FragmentB: Fragment(),View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_main, container, false)
        Log.d("PTM", "Testing log function")
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
        Log.d("PTM", "Button clicked!")
        val intent = Intent(this.context, SelectExerciseActivity::class.java)
        startActivity(intent)

    }
}