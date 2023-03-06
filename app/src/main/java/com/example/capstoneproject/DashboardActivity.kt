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
    private lateinit var cardBikes: CardView
    private lateinit var cardTrips: CardView
    private lateinit var cardSettings: CardView
    private lateinit var cardChatbot: CardView
    private lateinit var cardLogout: CardView
    
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
    
        // Support Action Bar
        supportActionBar!!.title = "Home Dashboard"
        
        // Intents
        val intentLogOut = Intent(this, MainActivity::class.java)
        val intentHomeScreen = Intent(this, DashboardActivity::class.java)
        val intentTripList = Intent(this, TripListActivity::class.java)
        val intentConnectivity = Intent(this, CheckInternetAndSettingsActivity::class.java)
        val intentChatbotActivity = Intent(this, ChatbotActivity::class.java)
//****************************************************************************
        
        // CARD VIEW STUFF
        
        // Find layouts
        cardHome = findViewById(R.id.cardHome)
        cardBikes = findViewById(R.id.cardMyBikes)
        cardTrips = findViewById(R.id.cardTrips)
        cardSettings = findViewById(R.id.cardSettings)
        cardChatbot = findViewById(R.id.cardConnectivity)
        cardLogout = findViewById(R.id.cardLogout)
        
        
        // Actions
        
        // Home
        cardHome.setOnClickListener {
            startActivity(intentHomeScreen)
        } // End cardHome.setOnClickListener
    
        // Bikes
        cardBikes.setOnClickListener {
            Toast.makeText(this, "Coming Soon", Toast.LENGTH_SHORT).show()
        } // End cardBikes.setOnClickListener
        
        // Trips
        cardTrips.setOnClickListener {
            startActivity(intentTripList)
            Toast.makeText(this, "Trips", Toast.LENGTH_SHORT).show()
        } // End cardTrips.setOnClickListener
        
        // Settings
        cardSettings.setOnClickListener {
            Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show()
            startActivity(intentConnectivity)
        } // End cardSettings.setOnClickListener
        
        // Chatbot
        cardChatbot.setOnClickListener {
            startActivity(intentChatbotActivity)
        }// End cardChatbot.setOnClickListener
        
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
                
                // Settings
                R.id.nav_settings ->
                {
                    Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show()
                    startActivity(intentConnectivity)
                } // End R.id.nav_settings
                
                // Chatbot
                R.id.nav_chatbot ->
                {
                    startActivity(intentChatbotActivity)
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