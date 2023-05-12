package com.example.healthwayz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddNewStock : AppCompatActivity() {
    private lateinit var etStockName: EditText
    private lateinit var etStockQty: EditText
    private lateinit var etStockPrice: EditText
    private lateinit var btnADd: Button
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_stock)
        val actionbar = supportActionBar
        actionbar!!.title = "Add New Feedback"
        actionbar.setDisplayHomeAsUpEnabled(true)
        dbRef = FirebaseDatabase.getInstance("https://hungryhero-d44b4-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Feedback")
        btnADd  = findViewById(R.id.btAdd)
        etStockName = findViewById(R.id.StockName)
        etStockQty = findViewById(R.id.Qty)
        etStockPrice = findViewById(R.id.Price)
        btnADd.setOnClickListener {
            addStock()
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun addStock() {
        val stock_name= etStockName.text.toString()
        val stock_qty= etStockQty.text.toString()
        val stock_price= etStockPrice.text.toString()
        val stockId = dbRef.push().key!!
        val stock = StockModel(stock_name,stock_qty,stock_price)
        dbRef.child(stock_name).setValue(stock)
            .addOnCompleteListener {
                Toast.makeText(this, "Feedback inserted successfully", Toast.LENGTH_LONG).show()
                etStockName.text.clear()
                etStockQty.text.clear()
                etStockPrice.text.clear()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }.addOnFailureListener { err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }

    }
}