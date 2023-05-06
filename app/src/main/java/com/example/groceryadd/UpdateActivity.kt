package com.example.groceryadd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateActivity : AppCompatActivity() {

    private lateinit var ubutton: Button
    private lateinit var typeboxu1: EditText
    private lateinit var typeboxu2: EditText
    private lateinit var typeboxu3: EditText
    private lateinit var typeboxu4: EditText
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        ubutton = findViewById(R.id.ubutton)
        typeboxu1 = findViewById(R.id.typeboxu1)
        typeboxu2 = findViewById(R.id.typeboxu2)
        typeboxu3 = findViewById(R.id.typeboxu3)
        typeboxu4 = findViewById(R.id.typeboxu4)

        ubutton.setOnClickListener {
            val name = typeboxu1.text.toString()
            val price = typeboxu2.text.toString()
            val qty = typeboxu3.text.toString()
            val offer = typeboxu4.text.toString()

            updateData(name, price, qty, offer)
        }
    }

    private fun updateData(name: String, price: String, qty: String, offer: String) {
        databaseReference = FirebaseDatabase.getInstance("https://hungryhero-d44b4-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Product")
        val user = mapOf<String, String>("name" to name, "price" to price, "qty" to qty, "offer" to offer)
        databaseReference.child(name).updateChildren(user).addOnSuccessListener {
            typeboxu1.text.clear()
            typeboxu2.text.clear()
            typeboxu3.text.clear()
            typeboxu4.text.clear()
            Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Unable to Update.", Toast.LENGTH_SHORT).show()
        }
    }
}
