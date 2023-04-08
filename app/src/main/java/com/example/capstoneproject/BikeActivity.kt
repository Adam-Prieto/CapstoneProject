package com.example.capstoneproject

import android.annotation.SuppressLint
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.lang.Exception

class BikeActivity : AppCompatActivity(), View.OnClickListener
{
    
    var op = CREATE_OP
    lateinit var db: SQLiteDatabase
    lateinit var edtId: EditText
    lateinit var edtYear: EditText
    lateinit var edtMake: EditText
    lateinit var edtModel: EditText
    lateinit var edtSize: EditText
    lateinit var edtTotalMile: EditText
    lateinit var edtHoursDriven: EditText
    
    companion object
    {
        const val CREATE_OP = 0
        const val UPDATE_OP = 1
    }
    
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crud_bike_details)
        
        // gets references to the view objects
        edtId = findViewById(R.id.edtId)
        edtYear = findViewById(R.id.edtYear)
        edtMake = findViewById(R.id.edtMake)
        edtModel = findViewById(R.id.edtModel)
        edtSize = findViewById(R.id.edtSize)
        edtTotalMile = findViewById(R.id.edtTotalMile)
        edtHoursDriven = findViewById(R.id.edtHoursDriven)
        
        // gets a reference to the "CREATE/UPDATE" button and sets its listener
        val btnCreateUpdate: Button = findViewById(R.id.btnCreateUpdate)
        btnCreateUpdate.setOnClickListener(this)
        
        // gets a "writable" db connection
        val dbHelper = BikeDatabaseHelper(this)
        db = dbHelper.writableDatabase
        
        // gets the operation and updates the view accordingly
        op = intent.getIntExtra("op", CREATE_OP)
        if (op == CREATE_OP) btnCreateUpdate.text = "CREATE"
        // write the code for the "update" operation
        else
        {
            btnCreateUpdate.text = "UPDATE"
            val id = intent.getIntExtra("id", 0)
            val newId = id.toString()
            //   edtId.setText(id).toString().toInt()
            edtId.setText(newId).toString()
            edtId.isEnabled = false
            val bike = retrieveItem(id)
            edtYear.setText(bike.year).toString()
            edtModel.setText(bike.model).toString()
            edtMake.setText(bike.make).toString()
            edtSize.setText(bike.size).toString()
            edtTotalMile.setText((bike.totalMile).toString()).toString()
            edtHoursDriven.setText(bike.bikeHours).toString()
        }
    }
    
    // returns the item based on the given name
    fun retrieveItem(id: Int): Bike
    {
        val cursor = db.query("bikes", null, "id = $id", null, null, null, null)
        with(cursor) {
            cursor.moveToNext()
            val year = cursor.getString(1)
            val make = cursor.getString(2)
            val model = cursor.getString(3)
            val size = cursor.getString(4)
            val totalMile = cursor.getDouble(5)
            val drivenHours = cursor.getString(6)
            return Bike(id, year, make, model, size, totalMile, drivenHours)
        }
    }
    
    override fun onClick(view: View?)
    {
        val id = findViewById<EditText>(R.id.edtId).text.toString()
        //val id = intent.getIntExtra("id",0)
        //val id = findViewById<EditText>(R.id.edtId).text.toString()
        val year = findViewById<EditText>(R.id.edtYear).text.toString()
        val make = findViewById<EditText>(R.id.edtMake).text.toString()
        val model = findViewById<EditText>(R.id.edtModel).text.toString()
        val size = findViewById<EditText>(R.id.edtSize).text.toString()
        val totalMile =
            findViewById<EditText>(R.id.edtTotalMile).text.toString()
        val hoursDriven =
            findViewById<EditText>(R.id.edtHoursDriven).text.toString()
        if (op == CREATE_OP)
        {
            try
            {
                db.execSQL("""
                        INSERT INTO bikes VALUES
                            ($id,"${year}", "${make}", "${model}", "${size}","${totalMile}", "${hoursDriven}")
                    """)
                Toast.makeText(this, "New bicycle is successfully created!",
                    Toast.LENGTH_SHORT).show()
            }
            catch (ex: Exception)
            {
                print(ex.toString())
                Toast.makeText(this,
                    "Exception when trying to create a new bicycle!",
                    Toast.LENGTH_SHORT).show()
            }
        }
        //  the code for the "update" operation
        else
        {
            //val id = intent.getIntExtra("id",0)
            try
            {
                db.execSQL("""
                        UPDATE bikes SET
                            year = "${year}",
                            make = "${make}", 
                            model = "${model}", 
                            size = "${size}",
                            totalMile = "${totalMile}",
                            hoursDriven = "$hoursDriven"
                            WHERE id = $id
                        
                    """)
                Toast.makeText(this, "Bicycle is successfully updated!",
                    Toast.LENGTH_SHORT).show()
            }
            catch (ex: Exception)
            {
                print(ex.toString())
                Toast.makeText(this,
                    "Exception when trying to update the bike!",
                    Toast.LENGTH_SHORT).show()
            }
        }
        finish()
    }
}