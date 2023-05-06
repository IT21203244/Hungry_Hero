package com.example.hungryheropayment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class CashOnDeliveryView : AppCompatActivity() {

    private lateinit var delete: ImageView
    private lateinit var update: ImageView
    private lateinit var rview: RecyclerView
    private lateinit var usersArrayList: ArrayList<DeliveryViewData>
    private lateinit var database: DatabaseReference

    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cash_on_delivery_view)


        update = findViewById(R.id.update)
        update.setOnClickListener {
            val intent = Intent(this, UpdateCash::class.java)
            startActivity(intent)
        }

        delete = findViewById(R.id.delete)
        delete.setOnClickListener {
            val intent = Intent(this, DeleteCash::class.java)
            startActivity(intent)
        }

        imageView = findViewById(R.id.imageView)
        imageView.setOnClickListener {
            onBackPressed()
        }

        rview = findViewById(R.id.rview)
        rview.layoutManager = LinearLayoutManager(this)

        usersArrayList = arrayListOf()

        database = FirebaseDatabase.getInstance("https://hungryhero-d44b4-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("DeliveryDetails")
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (dataSnapShot in snapshot.children){
                        val user = dataSnapShot.getValue(DeliveryViewData::class.java)
                        if(!usersArrayList.contains(user)){
                            usersArrayList.add(user!!)
                        }
                    }
                    rview.adapter = DeliveryAdapter(usersArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@CashOnDeliveryView, error.toString(), Toast.LENGTH_SHORT).show()
            }

        })


    }
    }
