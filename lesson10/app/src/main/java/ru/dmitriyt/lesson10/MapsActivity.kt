package ru.dmitriyt.lesson10

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import ru.dmitriyt.lesson10.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private var map: GoogleMap? = null
    private lateinit var binding: ActivityMapsBinding

    private var userMarker: Marker? = null

    private val fusedLocationProviderClient by lazy { LocationServices.getFusedLocationProviderClient(this) }
    private val locationRequest by lazy {
        LocationRequest.create()
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            .setInterval(5000L)
            .setFastestInterval(1000L)
    }
    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(result: LocationResult) {
            updateUserMarker(result.lastLocation.latitude, result.lastLocation.longitude)
            fusedLocationProviderClient.removeLocationUpdates(this)
        }
    }

    private val locationPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) {
            enableLocation()
        } else {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION)) {
                Toast.makeText(this, "Нужны разрешения", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        googleMap.uiSettings.apply {
            isMapToolbarEnabled = false
        }
        enableLocation()

        val sydney = LatLng(-34.0, 151.0)
        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    private fun enableLocation() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
                    Log.d("LOCATION", "${location?.latitude} ${location?.longitude}")
                    if (location != null) {
                        updateUserMarker(location.latitude, location.longitude)
                    } else {
                        startLocationUpdates()
                    }
                }
            }
            else -> {
                locationPermissionLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
            }
        }
    }

    private fun updateUserMarker(lat: Double, lng: Double) {
        Log.d("LOCATION_UPDATE_MARKER", "${lat} ${lng}")
        userMarker?.remove()
        userMarker = map?.addMarker(MarkerOptions().position(LatLng(lat, lng)))
    }

    @SuppressLint("MissingPermission")
    private fun startLocationUpdates() {
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())
    }
}