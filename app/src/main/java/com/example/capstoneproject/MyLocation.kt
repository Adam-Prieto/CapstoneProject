package com.example.capstoneproject

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MyLocation : FragmentActivity(), OnMapReadyCallback
{
    private lateinit var currentLocation: Location
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private val permissionCode = 101
    
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_location)
        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(this)
        fetchLocation()
    } // End onCreate
    
    private fun fetchLocation()
    {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                permissionCode)
            return
        } // End if
        
        val task = fusedLocationProviderClient.lastLocation
        task.addOnSuccessListener { location ->
            if (location != null)
            {
                currentLocation = location
                Toast.makeText(applicationContext,
                    currentLocation.latitude.toString() + "" + currentLocation.longitude,
                    Toast.LENGTH_LONG).show()
                val supportMapFragment =
                    (supportFragmentManager.findFragmentById(
                        R.id.myMap) as SupportMapFragment?)!!
                supportMapFragment.getMapAsync(this)
            } // End if
        } // end task.addOnSuccessListener
    } // End fetchLocation
    
    
    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<out String>,
                                            grantResults: IntArray)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode)
        {
            permissionCode -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                fetchLocation()
            }
        }
    }
    
    override fun onMapReady(p0: GoogleMap)
    {
        val latLng = LatLng(currentLocation.latitude, currentLocation.longitude)
        val markerOptions =
            MarkerOptions().position(latLng).title("You are here!")
        p0?.animateCamera(CameraUpdateFactory.newLatLng(latLng))
        p0?.animateCamera(CameraUpdateFactory.newLatLng(latLng))
        p0?.addMarker(markerOptions)
    }
}