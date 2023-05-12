package com.example.hungryheropayment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CardAdapter(private val usersList: ArrayList<AddCard>): RecyclerView.Adapter<CardAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val textViewcno: TextView = itemView.findViewById(R.id.textViewcno)
        val textViewexpire:TextView = itemView.findViewById(R.id.textViewexpire)
        val textViewcvc:TextView = itemView.findViewById(R.id.textViewcvc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.carddeleteview, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textViewcno.text = usersList[position].Card
        holder.textViewexpire.text = usersList[position].CardN
        holder.textViewcvc.text = usersList[position].CardC
    }

}

