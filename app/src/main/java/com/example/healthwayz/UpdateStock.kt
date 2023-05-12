package com.example.healthwayz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateStock : AppCompatActivity() {
    private lateinit var etStockName: EditText
    private lateinit var etStockQty: EditText
    private lateinit var etStockPrice: EditText
    private lateinit var btnUpdate: Button
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_stock)
        val actionbar = supportActionBar
        actionbar!!.title = "Update Feedback"
        actionbar.setDisplayHomeAsUpEnabled(true)
        dbRef = FirebaseDatabase.getInstance("https://hungryhero-d44b4-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Feedback")
        btnUpdate  = findViewById(R.id.btUpdate)
        etStockName = findViewById(R.id.StockName)
        etStockQty = findViewById(R.id.Qty)
        etStockPrice = findViewById(R.id.Price)

        btnUpdate.setOnClickListener {
            upDateStock()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun upDateStock() {
        val stock_name= etStockName.text.toString()
        val stock_qty= etStockQty.text.toString()
        val stock_price= etStockPrice.text.toString()

        val stock = mapOf<String,String>(
            "stock_name" to stock_name,
            "stock_qty" to stock_qty,
            "stock_price" to stock_price,
        )
        dbRef.child(stock_name).updateChildren(stock).addOnSuccessListener {
            Toast.makeText(this,"Successfuly Updated", Toast.LENGTH_SHORT).show()
            etStockName.text.clear()
            etStockQty.text.clear()
            etStockPrice.text.clear()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }.addOnFailureListener{

            Toast.makeText(this,"Failed to Update", Toast.LENGTH_SHORT).show()

        }
    }
}