package com.example.groceryadd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class HomeList : AppCompatActivity() {

    private lateinit var menuRecycler: RecyclerView
    private lateinit var usersArrayList: ArrayList<Pview>
    private lateinit var database: DatabaseReference

    private lateinit var barr: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_list)

        barr = findViewById(R.id.barr)
        barr.setOnClickListener {
            onBackPressed()
        }

        menuRecycler = findViewById(R.id.menuRecycler)
        menuRecycler.layoutManager = LinearLayoutManager(this)



        usersArrayList = arrayListOf()

        database = FirebaseDatabase.getInstance("https://hungryhero-d44b4-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Product")
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (dataSnapShot in snapshot.children){
                        val user = dataSnapShot.getValue(Pview::class.java)
                        if(!usersArrayList.contains(user)){
                            usersArrayList.add(user!!)
                        }
                    }
                    menuRecycler.adapter = MyAdapter(usersArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@HomeList, error.toString(), Toast.LENGTH_SHORT).show()
            }

        })


    }
}