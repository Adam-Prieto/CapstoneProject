package com.example.capstoneproject

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.*
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.*
import java.io.IOException

class ChatbotActivity : AppCompatActivity()
{
    
    // Variables for later
    private lateinit var recyclerView: RecyclerView
    private lateinit var welcomeTextView: TextView
    private lateinit var messageEditText: EditText
    private lateinit var sendButton: ImageButton
    private lateinit var messageList: MutableList<Message>
    private lateinit var messageAdapter: MessageAdapter
    
    
    private val JSONMedia: MediaType =
        "application/json; charset=utf-8".toMediaType()
    private var client = OkHttpClient()
    
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chatbot)
    
        // Support Action Bar
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Chat bot"
    
        // Initialize lateinit vars
        recyclerView = findViewById(R.id.recycler_view)
        welcomeTextView = findViewById(R.id.welcome_text)
        messageEditText = findViewById(R.id.message_edit_text)
        sendButton = findViewById(R.id.send_btn)
        messageList = mutableListOf()
    
        // Recycler View Setup
        messageAdapter = MessageAdapter(messageList)
        recyclerView.adapter = messageAdapter
        val llm = LinearLayoutManager(this)
        llm.stackFromEnd = true
        recyclerView.layoutManager = llm
    
        // Send message to AI
        sendButton.setOnClickListener {
            val question = messageEditText.text.toString().trim()
            addToChat(question, Message.SENT_BY_ME)
            messageEditText.setText("")
            callAPI(question)
            welcomeTextView.visibility = View.GONE
        } // End setOnClickListener
    } // End onCreate
    
    //************************************************************************
    /**@methodName: addToChat
     * @param: message: String
     * @param: sentBy: String
     * @description:
     * */
    
    @SuppressLint("NotifyDataSetChanged")
    private fun addToChat(message: String, sentBy: String)
    {
        runOnUiThread {
            messageList.add(Message(message, sentBy))
            messageAdapter.notifyDataSetChanged()
            recyclerView.smoothScrollToPosition(messageAdapter.itemCount)
        } // End runOnUiThread
    } // End addToChat
    
    //************************************************************************
    /**@methodName: addResponse
     * @param: response: String
     * @description: This method gets the response from the api and prepares it
     * for the addToChat method.
     * */
    
    fun addResponse(response: String)
    {
        messageList.removeAt(messageList.size - 1)
        addToChat(response, Message.SENT_BY_BOT)
    } // End addResponse
    
    //************************************************************************
    /**@methodName: callAPI
     * @param: question: String?
     * @description: Here we call the api with a pre-provided question so we
     * can get insight into what the chat bot has to say.
     * */
    
    private fun callAPI(question: String?)
    {
        // API Key
        val apiKey = getString(R.string.openAI_api_key)
        
        // okhttp
        messageList.add(Message("Thinking... ", Message.SENT_BY_BOT))
        val jsonBody = JSONObject()
        
        // Begin try/ catch for adding relevant information to the json body.
        try
        {
            jsonBody.put("model", "text-davinci-003")
            jsonBody.put("prompt", question)
            jsonBody.put("max_tokens", 4000)
            jsonBody.put("temperature", 0)
        } // End try
        catch (e: JSONException)
        {
            e.printStackTrace()
        } // End catch
        
        // Body and request portions for message calls
        val body: RequestBody = jsonBody.toString().toRequestBody(JSONMedia)
        val request: Request =
            Request.Builder().url("https://api.openai.com/v1/completions")
                .header("Authorization", "Bearer $apiKey").post(body).build()
        
        // Begin new http client call
        client.newCall(request).enqueue(object : Callback
        {
            // If the call fails...
            override fun onFailure(call: Call, e: IOException)
            {
                addResponse("Failed to load response due to " + e.message)
            } // End onFailure
            
            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response)
            {
                if (response.isSuccessful)
                {
                    val jsonObject: JSONObject?
                    try
                    {
                        jsonObject = JSONObject(response.body!!.string())
                        val jsonArray = jsonObject.getJSONArray("choices")
                        val result =
                            jsonArray.getJSONObject(0).getString("text")
                        addResponse(result.trim { it <= ' ' })
                    } // End try
                    
                    catch (e: JSONException)
                    {
                        e.printStackTrace()
                    } // End catch
                } // End if
                
                else
                {
                    addResponse(
                        "Failed to load response due to " + response.body.toString())
                } // End else
            } // End onResponse
        }) // End client.newCall
    } // End callAPI
} // End ChatbotActivity class