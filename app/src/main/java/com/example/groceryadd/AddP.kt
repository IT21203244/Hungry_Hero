package com.example.groceryadd



import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage


class AddP : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var imageView2: ImageView
    private lateinit var editText2: EditText
    private lateinit var editText3: EditText
    private lateinit var editText4: EditText
    private lateinit var editText5: EditText
    private lateinit var editText6: EditText
    private lateinit var button: Button

    private var storageRef =Firebase.storage

    private lateinit var uri: Uri

    private lateinit var auth: FirebaseAuth


    private lateinit var category:String
    private lateinit var name:String
    private lateinit var price:String
    private lateinit var qty:String
    private lateinit var offer:String

    private var imageURL: String = ""
    private lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_p)

        auth = Firebase.auth

        storageRef = FirebaseStorage.getInstance()

        database = Firebase.database.reference

        imageView2 = findViewById(R.id.imageView2)
        editText2 = findViewById(R.id.editText2)
        editText3 = findViewById(R.id.editText3)
        editText4 = findViewById(R.id.editText4)
        editText5 = findViewById(R.id.editText5)
        editText6 = findViewById(R.id.editText6)
        button = findViewById(R.id.button)

        imageView = findViewById(R.id.imageView)
        imageView.setOnClickListener {
            onBackPressed()
        }

        val galleryImage = registerForActivityResult(ActivityResultContracts.GetContent(),
            ActivityResultCallback {
                imageView2.setImageURI(it)
                if (it != null) {
                    imageView2.setImageURI(it)
                    uri = it
                }
            })

        imageView2.setOnClickListener {
            galleryImage.launch("image/*")
        }

        button.setOnClickListener {
            saveData()
        }
    }

    private fun saveData() {

        storageRef.getReference("images").child(System.currentTimeMillis().toString())
            .putFile(uri)
            .addOnSuccessListener { task ->
                task.metadata!!.reference!!.downloadUrl
                    .addOnSuccessListener {
                        val userId = FirebaseAuth.getInstance().currentUser!!.uid

                        val mapImage = mapOf(
                            "url" to it.toString()
                        )

                        val databaseReference = FirebaseDatabase.getInstance().getReference("Product")
                        databaseReference.child(userId).setValue(mapImage)
                            .addOnSuccessListener {
                                Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show()
                            }
                            .addOnFailureListener {error ->
                                Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
                            }
                    }
            }

        category = editText2.text.toString().trim()
        name = editText3.text.toString().trim()
        price = editText4.text.toString().trim()
        qty = editText5.text.toString().trim()
        offer = editText6.text.toString().trim()

        database = FirebaseDatabase.getInstance("https://hungryhero-d44b4-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Product")
        val userModel = UserModel(category,name,price,qty,offer,imageURL)
        database.child(name).setValue(userModel).addOnSuccessListener {

            Toast.makeText(this,"Successfully saved",Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
        }


    }


}

