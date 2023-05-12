package com.example.healthwayz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var Available_Stock: Button
    private lateinit var AddNew_Stock: Button
    private lateinit var Requested_Stock: Button
    private lateinit var Update_Stock: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Available_Stock = findViewById(R.id.AvailableStock)
        AddNew_Stock = findViewById(R.id.AddNewStock)
        Requested_Stock = findViewById(R.id.RequestedStock)
        Update_Stock = findViewById(R.id.UpdateStock)

        Available_Stock.setOnClickListener {
            val intent = Intent(this,AvailableStock::class.java)
            startActivity(intent)
        }
        AddNew_Stock.setOnClickListener {
            val intent = Intent(this,AddNewStock::class.java)
            startActivity(intent)
        }
        Requested_Stock.setOnClickListener {
            val intent = Intent(this,RequestedStock::class.java)
            startActivity(intent)
        }
        Update_Stock.setOnClickListener {
            val intent = Intent(this,UpdateStock::class.java)
            startActivity(intent)
        }

    }
}