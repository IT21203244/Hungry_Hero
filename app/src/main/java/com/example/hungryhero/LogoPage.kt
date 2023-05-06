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

        val imageView8 = findViewById<ImageView>(R.id.imageView8)
        imageView8.setOnClickListener {
            val intent = Intent(this, Description::class.java)
            startActivity(intent)
        }

        val imageView7 = findViewById<TextView>(R.id.textView13)
        imageView7.setOnClickListener {
            val intent = Intent(this, Topdeals::class.java)
            startActivity(intent)
        }
    }

}



