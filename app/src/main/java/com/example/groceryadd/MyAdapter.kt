package com.example.groceryadd


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class MyAdapter(private val usersList: ArrayList<Pview>):RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val textView3:TextView = itemView.findViewById(R.id.textView3)
        val textView4:TextView = itemView.findViewById(R.id.textView4)
        val textView5:TextView = itemView.findViewById(R.id.textView5)
        val textView244:TextView = itemView.findViewById(R.id.textView244)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.plist_view, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.textView3.text = usersList[position].name
        holder.textView4.text = usersList[position].price
        holder.textView5.text = usersList[position].qty
        holder.textView244.text = usersList[position].offer


    }
}