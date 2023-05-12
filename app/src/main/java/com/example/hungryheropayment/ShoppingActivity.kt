package com.example.hungryheropayment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class ShoppingActivity : AppCompatActivity() {

    private lateinit var harrow:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        harrow = findViewById(R.id.harrow)
        harrow.setOnClickListener {
            onBackPressed()
        }


    }
}