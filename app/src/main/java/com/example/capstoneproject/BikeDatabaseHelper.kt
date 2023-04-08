package com.example.capstoneproject

import android.content.Context
import android.database.sqlite.*
import java.io.Serializable

class BikeDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION),
    Serializable
{
    
    companion object
    {
        const val DATABASE_NAME = "Bike.db"
        const val DATABASE_VERSION = 11
        //val TIME_FORMAT = Time("HH:MM")
    }
    
    override fun onCreate(p0: SQLiteDatabase?)
    {
        // create the table
        p0?.execSQL("""
            CREATE TABLE bikes (
                id      INTEGER PRIMARY KEY,
                year    TEXT NOT NULL, 
                make    TEXT NOT NULL, 
                model    TEXT NOT NULL,
                size    TEXT NOT NULL,
                totalMile DOUBLE NOT NULL,
                hoursDriven TEXT 
            )
        """)
        
        // populate the table with a few items
        p0?.execSQL("""
            INSERT INTO bikes VALUES 
                (1,"2020","Honda",  "MountainRacer", "25","00:00", "00:00"),
                (2,"2021","Paro","UltimateFunPro","20","01:20", "10:15")
                
        """)
        
    }
    
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int)
    {
        // drop the table
        p0?.execSQL("""
            DROP TABLE IF EXISTS bikes
        """)
        
        // then call "onCreate" again
        onCreate(p0)
    }
}