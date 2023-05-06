package com.example.hungryheropayment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class AddCardDetails : AppCompatActivity() {


    private lateinit var editTextTextPersonName4: EditText
    private lateinit var editTextTextPersonName5: EditText
    private lateinit var editTextTextPersonName6: EditText
    private lateinit var button: Button


    private lateinit var auth: FirebaseAuth


    private lateinit var card:String
    private lateinit var cardN:String
    private lateinit var cardC:String
    private lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_card_details)

        val imageView37 = findViewById<ImageView>(R.id.imageView37)
        imageView37.setOnClickListener {
            onBackPressed()
        }

        auth = Firebase.auth
        database = Firebase.database.reference


        editTextTextPersonName4 = findViewById(R.id.editTextTextPersonName4)
        editTextTextPersonName5 = findViewById(R.id.editTextTextPersonName5)
        editTextTextPersonName6 = findViewById(R.id.editTextTextPersonName6)
        button = findViewById(R.id.button)


        button.setOnClickListener {
            saveData()
        }
    }

    private fun saveData() {


        card = editTextTextPersonName4.text.toString().trim()
        cardN = editTextTextPersonName5.text.toString().trim()
        cardC = editTextTextPersonName6.text.toString().trim()

        database = FirebaseDatabase.getInstance("https://hungryhero-d44b4-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("CardDetails")
        val addCard = AddCard(card, cardN, cardC)
        database.child(cardN).setValue(addCard).addOnSuccessListener {

            Toast.makeText(this,"Successfully saved",Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
        }

    }
}