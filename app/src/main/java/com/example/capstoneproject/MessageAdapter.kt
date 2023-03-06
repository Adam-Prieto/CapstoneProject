package com.example.capstoneproject

import android.annotation.SuppressLint
import android.view.*
import android.widget.*
import androidx.recyclerview.widget.RecyclerView


class MessageAdapter(private var messageList: List<Message>) :
    RecyclerView.Adapter<MessageAdapter.MyViewHolder>()
{
    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MyViewHolder
    {
        val chatView = LayoutInflater.from(parent.context)
            .inflate(R.layout.chat_item, null)
        return MyViewHolder(chatView)
    } // End onCreateViewHolder
    
    override fun onBindViewHolder(holder: MyViewHolder, position: Int)
    {
        val message = messageList[position]
        if (message.sentBy == Message.SENT_BY_ME)
        {
            holder.leftChatView.visibility = View.GONE
            holder.rightChatView.visibility = View.VISIBLE
            holder.rightTextView.text = message.message
        } // End if
        else
        {
            holder.rightChatView.visibility = View.GONE
            holder.leftChatView.visibility = View.VISIBLE
            holder.leftTextView.text = message.message
        } // End else
    } // End
    
    override fun getItemCount(): Int
    {
        return messageList.size
    } // End getItemCount
    
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        var leftChatView: LinearLayout
        var rightChatView: LinearLayout
        var leftTextView: TextView
        var rightTextView: TextView
        
        init
        {
            leftChatView = itemView.findViewById(R.id.left_chat_view)
            rightChatView = itemView.findViewById(R.id.right_chat_view)
            leftTextView = itemView.findViewById(R.id.left_chat_text_view)
            rightTextView = itemView.findViewById(R.id.right_chat_text_view)
        } // End init
    } // End MyViewHolder inner class
} // End MessageAdapter class
