
package jamk.fi

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.EditText
import android.widget.Button



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    fun buttonClicked(view:View)
    {
        val number1 = findViewById<EditText>(R.id.number1EditText)
        val number2 = findViewById<EditText>(R.id.number2EditText)


        if(number1.text.trim().length>0 || number2.text.trim().length>0)
        {
            resultTextView.text = number1.text.toString() + " + " + number2.text.toString() + " = " + sum(number1.text.toString().toInt(), number2.text.toString().toInt()).toString()
        }
        else {
            resultTextView.text = "Please enter two numbers in order for the app to calculate!"

        }
        number1EditText.text = null
        number2EditText.text = null
    }




    fun sum(a: Int, b: Int): Int{
        return a + b
    }
}
