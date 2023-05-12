package com.example.healthwayz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class RequestedStock : AppCompatActivity() {
    private lateinit var dbRef: DatabaseReference
    private lateinit var stockList: ArrayList<StockModel>
    private lateinit var recyclerView: RecyclerView
    private lateinit var stockAdapter: RequestedStockAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_requested_stock)
        val actionbar = supportActionBar
        actionbar!!.title = "View Feedback"
        actionbar.setDisplayHomeAsUpEnabled(true)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        stockList = arrayListOf()
        stockAdapter = RequestedStockAdapter(stockList)
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
}