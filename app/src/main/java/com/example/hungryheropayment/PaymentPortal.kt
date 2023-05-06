package com.example.hungryheropayment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class PaymentPortal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_portal)

        val imageView3 = findViewById<ImageView>(R.id.imageView3)
        imageView3.setOnClickListener {
            val intent = Intent(this, AddCardDetails::class.java)
            startActivity(intent)

            val imageView4 = findViewById<ImageView>(R.id.imageView4)
            imageView4.setOnClickListener {
                val intent = Intent(this, AddCashOnDelivery::class.java)
                startActivity(intent)

                val imageView2 = findViewById<ImageView>(R.id.imageView2)
                imageView2.setOnClickListener {
                    val intent = Intent(this, CashOnDeliveryView::class.java)
                    startActivity(intent)


                    val imageView38 = findViewById<ImageView>(R.id.imageView38)
                    imageView38.setOnClickListener {
                        val intent = Intent(this, CardDetailsView::class.java)
                        startActivity(intent)


                        val imageView = findViewById<ImageView>(R.id.imageView)
                        imageView.setOnClickListener {
                            onBackPressed()
                        }
                    }
                }
            }
        }
    }
}