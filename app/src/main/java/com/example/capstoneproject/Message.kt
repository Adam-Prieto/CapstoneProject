package com.example.capstoneproject

class Message(val message: String, val sentBy: String)
{
    companion object
    {
        var SENT_BY_ME = "me"
        var SENT_BY_BOT = "bot"
    } // End companion object
} // End Message class