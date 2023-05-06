package com.example.groceryadd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn1 = findViewById<Button>(R.id.textView)
        btn1.setOnClickListener {
            val intent = Intent(this, PrList::class.java)
            startActivity(intent)
        }

        val btn2 = findViewById<Button>(R.id.button)
        btn2.setOnClickListener {
            val intent = Intent(this, AddP::class.java)
            startActivity(intent)
        }
    }
}