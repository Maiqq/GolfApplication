package android.example.showgolfcoursesinmap

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response

import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.course_item.view.*
import org.json.JSONObject

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap


         //Instantiate the RequestQueue
        val queue = Volley.newRequestQueue(this)
        // URL to JSON data - remember use your own data here
        val url = "http://ptm.fi/materials/golfcourses/golf_courses.json"
        // Create request and listeners
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                //                // Get employees from JSON
                val courses = response.getJSONArray("courses")
                for (course in 0..(courses.length()-1)){
                    val course = courses.getJSONObject(course)
                    val name = course["course"].toString()
                    val lat = course["lat"].toString().toDouble()
                    val lng = course["lng"].toString().toDouble()
                    val placeOnMap = LatLng(lat, lng)
                    val markerColor = course["type"].toString()
                    val color:Int

                    if (markerColor == "Kulta")
                    {color = 60}
                    else if(markerColor == "Etu")
                    {color = 120}
                    else {color=240}

                    mMap.setInfoWindowAdapter(CustomInfoWindowAdapter(this))

                    if (course != null) {

                        val snippet = "Address: " + course["address"].toString() + "\n" +
                                "Phone Number: " + course["phone"].toString() + "\n" +
                                "Website: " + course["web"].toString() + "\n"


                        if (markerColor == "Kulta")
                        {
                        val options = MarkerOptions()
                            .position(placeOnMap)
                            .title(name)
                            .snippet(snippet)
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
                            mMap.addMarker(options)
                        }
                        else if(markerColor== "Etu")
                        {
                            val options = MarkerOptions()
                                .position(placeOnMap)
                                .title(name)
                                .snippet(snippet)
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))

                            mMap.addMarker(options)

                        }
                        else{ val options = MarkerOptions()
                            .position(placeOnMap)
                            .title(name)
                            .snippet(snippet)
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))

                            mMap.addMarker(options)
                        }








                    } else {
                        mMap.addMarker(MarkerOptions().position(placeOnMap))
                    }

                }

            },
            Response.ErrorListener { error ->
                Log.d("JSON",error.toString())
            }
        )
        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest)
    }




}



