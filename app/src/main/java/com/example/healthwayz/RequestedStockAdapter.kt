package com.example.healthwayz

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RequestedStockAdapter (private val stockList: List<StockModel>) : RecyclerView.Adapter<RequestedStockAdapter.StockViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_requsted, parent, false)
        return StockViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: StockViewHolder, position: Int) {
        val currentItem = stockList[position]
        holder.stockName.text = currentItem.stock_name
        holder.stockQty.text = currentItem.stock_qty

    }

    override fun getItemCount(): Int {
        return stockList.size
    }
    class StockViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val stockName: TextView = itemView.findViewById(R.id.stock_name)
        val stockQty: TextView = itemView.findViewById(R.id.stock_qty)

    }
}