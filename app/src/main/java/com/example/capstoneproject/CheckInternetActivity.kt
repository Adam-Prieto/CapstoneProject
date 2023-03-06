//****************************************************************************
/**@methodName:
 * @param:
 * @return:
 * @description:
 * */

package com.example.capstoneproject

import android.annotation.SuppressLint
import android.content.*
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.*
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.button.MaterialButton
import com.google.android.material.navigation.NavigationView


class CheckInternetActivity : AppCompatActivity()
{
    // Drawer toggle variables
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_internet)
        
        // Intents
        val intentLogOut = Intent(this, MainActivity::class.java)
        val intentHomeScreen = Intent(this, DashboardActivity::class.java)
        val intentTripList = Intent(this, TripListActivity::class.java)
        val intentConnectivity = Intent(this, CheckInternetActivity::class.java)
        
        val checkInternetButton: MaterialButton = findViewById(R.id.check)
        
        checkInternetButton.setOnClickListener {
            if (isConnected()) Toast.makeText(applicationContext,
                "INTERNET AVAILABLE", Toast.LENGTH_SHORT).show()
            else Toast.makeText(applicationContext, "INTERNET NOT AVAILABLE",
                Toast.LENGTH_SHORT).show()
            
        } // End checkInternetButton.setOnClickListener
        
        
        // SIDEBAR
        
        // Variables to locate and toggle drawer
        drawerLayout = findViewById(R.id.my_drawer_layout)
        actionBarDrawerToggle =
            ActionBarDrawerToggle(this@CheckInternetActivity, drawerLayout,
                R.string.open, R.string.close)
        
        // Pass Open and Close toggle for drawer layout listener
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        
        // Make Navigation drawer icon always appear on action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        
        // Navigation in the Sidebar
        val navView: NavigationView = findViewById(R.id.nav_view)
        navView.setNavigationItemSelectedListener {
            when (it.itemId)
            {
                // Home Screen
                R.id.nav_home ->
                {
                    startActivity(intentHomeScreen)
                } // End R.id.nav_home
                
                // Trip List
                R.id.nav_trips ->
                {
                    startActivity(intentTripList)
                    Toast.makeText(this, "Trips", Toast.LENGTH_SHORT).show()
                } // End R.id.nav_trips
                
                // Account Page
                R.id.nav_account ->
                {
                    Toast.makeText(this, "Account", Toast.LENGTH_SHORT).show()
                } // End R.id.nav_account
                
                // Settings
                R.id.nav_settings ->
                {
                    Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show()
                } // End R.id.nav_settings
                
                R.id.nav_connectivity_status ->
                {
                    startActivity(intentConnectivity)
                } // End R.id.nav_connectivity_status
                
                // Log out
                R.id.nav_logout ->
                {
                    startActivity(intentLogOut)
                    Toast.makeText(this, "Logged Out", Toast.LENGTH_SHORT)
                        .show()
                } // End R.id.nav_logout
                
            } // End when
            true
        } // End navView.setNavigationItemSelectedListener
    } // End onCreate
    
    //************************************************************************
    /**@methodName: isConnected
     * @return: Bool
     * @description: Checks to see if an internet connection exists and returns
     * a corresponding bool.
     * */
    private fun isConnected(): Boolean
    {
        val connectivityManager = getSystemService(
            Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo?.isConnected ?: false
    } // End isConnected
    
    //****************************************************************************
    /**@methodName:onOptionsItemSelected
     * @param: item: MenuItem
     * @return: bool
     * @description: Opens and closes nav drawer when button is clicked.
     * */
    
    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        return if (actionBarDrawerToggle.onOptionsItemSelected(item))
        {
            true
        } // End return if
        else super.onOptionsItemSelected(item)
    } // End onOptionsItemSelected
} // End CheckInternetActivity class