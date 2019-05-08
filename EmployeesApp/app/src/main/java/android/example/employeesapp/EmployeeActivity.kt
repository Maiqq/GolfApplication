package android.example.employeesapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.employee_item.*
import kotlinx.android.synthetic.main.employee_item.view.*
import org.json.JSONObject

class EmployeeActivity : AppCompatActivity() {

    // EmployeeActivity's onCreate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee)

        // get data from intent
        val bundle: Bundle? = intent.extras;
        if (bundle != null) {
            val employeeString = bundle!!.getString("employee")
            val employee = JSONObject(employeeString)
            val name = employee["firstName"].toString() + " " +  employee["lastName"].toString()
            val title = employee["title"].toString()
            val email = employee["email"].toString()
            val phone = employee["phone"].toString()
            val department = employee["department"].toString()

             //Capture the layout's TextView and set the string as its text
            val nameTextView = findViewById<TextView>(R.id.nameTextView).apply {
                text = name
            }
            val titleTextView = findViewById<TextView>(R.id.titleTextView).apply {
                text = title
            }

            val emailTextView = findViewById<TextView>(R.id.emailTextView).apply {
                text = email
            }
            val phoneTextView = findViewById<TextView>(R.id.phoneTextView).apply {
                text = phone
            }

            departmentTextView.text = department
          

            val imageView = findViewById<ImageView>(R.id.imageView).apply{
                Glide.with(imageView.context).load(employee["image"]).into(imageView)
            }
            val employeeInformation = findViewById<TextView>(R.id.employeeInformation).apply {
                text = name + getString(R.string.employeeinformation)
            }


        }

    }
}

