package android.example.gambitchallenge

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_item.*
import org.json.JSONObject

class itemActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)

        // get data from intent
        val bundle: Bundle? = intent.extras;
        if (bundle != null) {
            val string:ParsedItem = bundle!!.getSerializable("item") as ParsedItem
            val registers = string.register
            val value = string.value
            val unparsed = string.unparsed
            val type = string.type

            //Capture the layout's TextView and set the string as its text
            val registerTextView = findViewById<TextView>(R.id.registersTextView).apply {
                text = registers
            }
            val valueTextView = findViewById<TextView>(R.id.valueTextView).apply {
                text = value
            }
            val unparsedTextView = findViewById<TextView>(R.id.unparsedTextView).apply {
                text = unparsed
            }
            var id = 0
            if (type == "Real4") {id = R.drawable.fun3
            desciptionTextView.text = resources.getString(R.string.typeReal4)}
            else if (type == "Long") {id = R.drawable.fun2
                desciptionTextView.text = resources.getString(R.string.typeLong)}
            else if (type == "BCD"){ id = R.drawable.fun5
                desciptionTextView.text = resources.getString(R.string.typeBCD)}
            else if (type == "Integer"){ id = R.drawable.fun2
                desciptionTextView.text = resources.getString(R.string.typeInteger)}
            else if (type == "Bit"){ id = R.drawable.fun6
                desciptionTextView.text = resources.getString(R.string.typeBit)}
            imageView.setImageResource(id)

            //Set TextViews to clickable
            val meterWebTextView = findViewById<TextView>(R.id.meterWebTextVIew).setMovementMethod(LinkMovementMethod.getInstance())
            val manualWebTextView = findViewById<TextView>(R.id.manualWebTextView).setMovementMethod(LinkMovementMethod.getInstance())
        }
        }
    }

