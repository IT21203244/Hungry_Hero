package com.example.hungryhero

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class Deliverypartner : AppCompatActivity() {

    private lateinit var btnInsertData :Button
    private lateinit var btnFetchData : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delivery_partner)

        btnInsertData = findViewById(R.id.button3)
        btnFetchData = findViewById(R.id.button4)

        btnInsertData.setOnClickListener{
            val intent = Intent(this,RegistrationForm::class.java)
            startActivity(intent)

        }

        btnFetchData.setOnClickListener{

            val intent = Intent(this,FetchingActivity::class.java)
            startActivity(intent)


        }










    }
}