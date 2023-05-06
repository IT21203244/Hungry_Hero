package com.example.groceryadd



import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class PrList : AppCompatActivity() {

    private lateinit var delete: ImageView
    private lateinit var update: ImageView
    private lateinit var rview:RecyclerView
    private lateinit var usersArrayList: ArrayList<Pview>
    private lateinit var database: DatabaseReference

    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pr_list)

        update = findViewById(R.id.update)
        update.setOnClickListener {
            val intent = Intent(this, UpdateActivity::class.java)
            startActivity(intent)
        }

        delete = findViewById(R.id.delete)
        delete.setOnClickListener {
            val intent = Intent(this, DeleteInf::class.java)
            startActivity(intent)
        }

        imageView = findViewById(R.id.imageView)
        imageView.setOnClickListener {
            onBackPressed()
        }

        rview = findViewById(R.id.rview)
        rview.layoutManager = LinearLayoutManager(this)



        usersArrayList = arrayListOf()



        database = FirebaseDatabase.getInstance("https://hungryhero-d44b4-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Product")
        database.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (dataSnapShot in snapshot.children){
                        val user = dataSnapShot.getValue(Pview::class.java)
                        if(!usersArrayList.contains(user)){
                            usersArrayList.add(user!!)
                        }
                    }
                    rview.adapter = MyAdapter(usersArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@PrList, error.toString(), Toast.LENGTH_SHORT).show()
            }

        })


    }

}