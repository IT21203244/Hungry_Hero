package com.example.hungryhero

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class Description : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)

        val imageView4 = findViewById<ImageView>(R.id.imageView)
        imageView4.setOnClickListener {
            val intent = Intent(this, LogoPage::class.java)
            startActivity(intent)
        }
    }
}