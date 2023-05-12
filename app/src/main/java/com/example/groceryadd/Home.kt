package com.example.groceryadd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class Home : AppCompatActivity() {

    private lateinit var harrow: ImageView
    private lateinit var add_to_cart_button:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        harrow = findViewById(R.id.harrow)
        harrow.setOnClickListener {
            onBackPressed()
        }

        add_to_cart_button = findViewById(R.id.add_to_cart_button)
        add_to_cart_button.setOnClickListener {
            val intent = Intent(this, HomeList::class.java)
            startActivity(intent)
        }
    }
}