package com.example.groceryadd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DeleteInf : AppCompatActivity() {

    private lateinit var deleteButton: Button
    private lateinit var typeBox: EditText
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_inf)

        deleteButton = findViewById(R.id.dbutton)
        typeBox = findViewById(R.id.typebox)
        databaseReference = FirebaseDatabase.getInstance("https://hungryhero-d44b4-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Product")

        deleteButton.setOnClickListener {
            val name = typeBox.text.toString()
            if (name.isNotEmpty()){
                deleteData(name)
            }
            else{
                Toast.makeText(this, "Please Enter the Product Name", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun deleteData(name: String){
        databaseReference.child(name).removeValue().addOnSuccessListener {
            typeBox.text.clear()
            Toast.makeText(this, "Deleted.", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Unable to delete.", Toast.LENGTH_SHORT).show()
        }
    }

}