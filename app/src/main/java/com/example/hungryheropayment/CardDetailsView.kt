package com.example.hungryheropayment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class CardDetailsView : AppCompatActivity() {

    private lateinit var delete: ImageView
    private lateinit var update: ImageView
    private lateinit var rview2: RecyclerView
    private lateinit var usersArrayList: ArrayList<AddCard>
    private lateinit var database: DatabaseReference

    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_details_view)


        imageView = findViewById(R.id.imageView)
        imageView.setOnClickListener {
            onBackPressed()
        }

        rview2 = findViewById(R.id.rview2)
        rview2.layoutManager = LinearLayoutManager(this)

        usersArrayList = arrayListOf()

        database = FirebaseDatabase.getInstance("https://hungryhero-d44b4-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("CardDetails")
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (dataSnapShot in snapshot.children){
                        val user = dataSnapShot.getValue(AddCard::class.java)
                        if(!usersArrayList.contains(user)){
                            usersArrayList.add(user!!)
                        }
                    }
                    rview2.adapter = CardAdapter(usersArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@CardDetailsView, error.toString(), Toast.LENGTH_SHORT).show()
            }

        })


    }
}
