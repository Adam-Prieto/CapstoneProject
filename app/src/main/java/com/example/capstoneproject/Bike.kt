package com.example.capstoneproject

class Bike(var id: Int = 0,
    //var title: String = "",
           var year: String = "", var make: String = "", var model: String = "",
           var size: String = "", var totalMile: Double = 0.0,
    //var serviceMile: Double = 0.0,
           var bikeHours: String = "")
{
    override fun toString(): String
    {
        return "Bike(id=$id, year=$year, make=$make, model=$model, size=$size, totalMile=$totalMile, bikeHours=$bikeHours)"
    }
}