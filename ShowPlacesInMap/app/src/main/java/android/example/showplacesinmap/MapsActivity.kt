package android.example.showplacesinmap

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
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

        // get data from intent
        val bundle: Bundle? = intent.extras;
        if (bundle != null) {
            val placeString = bundle!!.getString("place")
            val place = JSONObject(placeString)
            val name = place["street_name"].toString()
            val lat = place["longitude"].toString().toDouble()
            val lng = place["latitude"].toString().toDouble()


            // Add a marker in Sydney and move the camera
            val placeOnMap = LatLng(lat, lng)
            mMap.addMarker(MarkerOptions().position(placeOnMap).title(name))
            mMap.moveCamera(CameraUpdateFactory.newLatLng(placeOnMap))
        }
    }
}
