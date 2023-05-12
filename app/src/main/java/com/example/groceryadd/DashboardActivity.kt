package com.example.groceryadd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class DashboardActivity : AppCompatActivity() {

    private lateinit var img4:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val img1 = findViewById<ImageView>(R.id.img1)
        val img2 = findViewById<ImageView>(R.id.img2)
        val img3 = findViewById<ImageView>(R.id.img3)
        img4 = findViewById(R.id.img4)

        img1.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }

        img2.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        img4.setOnClickListener {
            Firebase.auth.signOut()

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}