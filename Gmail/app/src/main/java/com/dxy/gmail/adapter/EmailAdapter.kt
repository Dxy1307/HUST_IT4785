package com.dxy.gmail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dxy.gmail.R
import com.dxy.gmail.model.Email

class EmailAdapter(private val emailList: List<Email>) : RecyclerView.Adapter<EmailAdapter.EmailViewHolder>() {

    class EmailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val from: TextView = itemView.findViewById(R.id.from)
        val content: TextView = itemView.findViewById(R.id.content)
        val time: TextView = itemView.findViewById(R.id.time)
        val icon: TextView = itemView.findViewById(R.id.icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmailViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.email, parent, false)
        return EmailViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: EmailViewHolder, position: Int) {
        val email = emailList[position]
        holder.from.text = email.from
        holder.content.text = email.content
        holder.time.text = email.time
        holder.icon.setBackgroundResource(email.iconBackground)
        holder.icon.text = email.iconText
    }

    override fun getItemCount() = emailList.size
}