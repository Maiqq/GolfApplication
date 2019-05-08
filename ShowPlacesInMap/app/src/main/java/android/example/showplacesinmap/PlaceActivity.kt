package android.example.showplacesinmap

import android.content.Intent
import android.content.pm.ResolveInfo
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import org.json.JSONObject
import android.view.View
import kotlinx.android.synthetic.main.activity_place.*
import kotlinx.android.synthetic.main.activity_place.nameTextView
import kotlinx.android.synthetic.main.place_item.*

class PlaceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place)

        // get data from intent
        val bundle: Bundle? = intent.extras;
        if (bundle != null) {
            val placeString = bundle!!.getString("place")
            val place = JSONObject(placeString)
            val lat = place["longitude"].toString().toDouble()
            val lng = place["latitude"].toString().toDouble()



            // Build the intent
            val location = Uri.parse("geo:$lat,$lng")
            val mapIntent = Intent(Intent.ACTION_VIEW, location)

            // Verify it resolves
            val activities: List<ResolveInfo> = packageManager.queryIntentActivities(mapIntent, 0)
            val isIntentSafe: Boolean = activities.isNotEmpty()

            // Start an activity if it's safe
            if (isIntentSafe) {
                startActivity(mapIntent)
            } else {
                Toast.makeText(this, "There is no activity to handle map intent!", Toast.LENGTH_LONG).show();
            }
        }
    }
}
