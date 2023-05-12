package com.example.hungryheropayment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DeliveryAdapter (private val usersList: ArrayList<DeliveryViewData>):RecyclerView.Adapter<DeliveryAdapter.MyViewHolder>() {

        class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

            val textView244: TextView = itemView.findViewById(R.id.textView244)
            val textView3:TextView = itemView.findViewById(R.id.textView3)
            val textView4:TextView = itemView.findViewById(R.id.textView4)
            val textView5:TextView = itemView.findViewById(R.id.textView5)

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.delete_view, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textView244.text = usersList[position].deliveryName
        holder.textView3.text = usersList[position].deliveryAddress
        holder.textView4.text = usersList[position].deliveryNic
        holder.textView5.text = usersList[position].deliveryNumber
    }


}

