//****************************************************************************
/**@methodName:
 * @param:
 * @return:
 * @description:
 * */

package com.example.capstoneproject

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.*
import androidx.cardview.widget.CardView
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView


class DashboardActivity : AppCompatActivity()
{
    // Drawer toggle variables
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    
    // Card view variables
    private lateinit var cardHome: CardView
    private lateinit var cardTrips: CardView
    private lateinit var cardAccount: CardView
    private lateinit var cardSettings: CardView
    private lateinit var cardConnectivity: CardView
    private lateinit var cardLogout: CardView
    
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_directory)
        
        // Intents
        val intentLogOut = Intent(this, MainActivity::class.java)
        val intentHomeScreen = Intent(this, DashboardActivity::class.java)
        val intentTripList = Intent(this, TripListActivity::class.java)
        val intentConnectivity = Intent(this, CheckInternetActivity::class.java)

//****************************************************************************
        
        // CARD VIEW STUFF
        
        // Find layouts
        cardHome = findViewById(R.id.cardHome)
        cardTrips = findViewById(R.id.cardTrips)
        cardAccount = findViewById(R.id.cardAccount)
        cardSettings = findViewById(R.id.cardSettings)
        cardConnectivity = findViewById(R.id.cardConnectivity)
        cardLogout = findViewById(R.id.cardLogout)
        
        
        // Actions
        
        // Home
        cardHome.setOnClickListener {
            startActivity(intentHomeScreen)
        } // End cardHome.setOnClickListener
        
        // Trips
        cardTrips.setOnClickListener {
            startActivity(intentTripList)
            Toast.makeText(this, "Trips", Toast.LENGTH_SHORT).show()
        } // End cardTrips.setOnClickListener
        
        // Account
        cardAccount.setOnClickListener {
            Toast.makeText(this, "Account", Toast.LENGTH_SHORT).show()
        } // End cardAccount.setOnClickListener
        
        // Settings
        cardSettings.setOnClickListener {
            Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show()
        } // End cardSettings.setOnClickListener
        
        // Internet connectivity
        cardConnectivity.setOnClickListener {
            startActivity(intentConnectivity)
        }// End cardConnectivity.setOnClickListener
        
        // Logout
        cardLogout.setOnClickListener {
            startActivity(intentLogOut)
            Toast.makeText(this, "Logged Out", Toast.LENGTH_SHORT).show()
        } // End cardLogout.setOnClickListener

//****************************************************************************
        // Drawer
        
        // Variables to locate and toggle drawer
        drawerLayout = findViewById(R.id.my_drawer_layout)
        actionBarDrawerToggle =
            ActionBarDrawerToggle(this, drawerLayout, R.string.open,
                R.string.close)
        
        // Pass Open and Close toggle for drawer layout listener
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        
        // Make Navigation drawer icon always appear on action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        
        // Sidebar
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
                
                // Internet connectivity status
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
} // End DirectoryActivity class