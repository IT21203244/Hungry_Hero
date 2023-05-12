package com.example.hungryhero

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logo_page)

        val btn1 = findViewById<ImageView>(R.id.imageView3)
        btn1.setOnClickListener {
            val intent = Intent(this, LogoPage::class.java)
            startActivity(intent)
        }
    }

}

