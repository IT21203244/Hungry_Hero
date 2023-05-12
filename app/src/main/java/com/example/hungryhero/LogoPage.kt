package com.example.hungryhero

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class LogoPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView16 = findViewById<ImageView>(R.id.imageView16)
        imageView16.setOnClickListener {
            val intent = Intent(this, Deliverypartner::class.java)
            startActivity(intent)
        }

    }

}





