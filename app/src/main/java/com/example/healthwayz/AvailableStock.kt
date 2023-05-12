package com.example.healthwayz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AvailableStock : AppCompatActivity() {
    private lateinit var dbRef: DatabaseReference
    private lateinit var stockList: ArrayList<StockModel>
    private lateinit var recyclerView: RecyclerView
    private lateinit var stockAdapter: StockAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_available_stock)
        val actionbar = supportActionBar
        actionbar!!.title = "Delete Feedback"
        actionbar.setDisplayHomeAsUpEnabled(true)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        stockList = arrayListOf<StockModel>() // Make sure to initialize stockList here
        stockAdapter = StockAdapter(stockList) { stockToDelete ->
            deleteStockItem(stockToDelete)
        }
        recyclerView.adapter = stockAdapter

        dbRef = FirebaseDatabase.getInstance("https://hungryhero-d44b4-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Feedback")
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                stockList.clear()
                for (dataSnapshot in snapshot.children) {
                    val stock = dataSnapshot.getValue(StockModel::class.java)
                    if (stock != null) {
                        stockList.add(stock)
                    }
                }
                stockAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle errors here
            }
        })
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    private fun deleteStockItem(stock: StockModel) {
        val stockQuery = dbRef.orderByChild("stock_name").equalTo(stock.stock_name)

        stockQuery.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnapshot in snapshot.children) {
                    dataSnapshot.ref.removeValue()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle errors here
            }
        })
    }
}
