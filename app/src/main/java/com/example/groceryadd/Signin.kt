package com.example.groceryadd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class Signin : AppCompatActivity() {

    private lateinit var supmail:EditText
    private lateinit var suppwd:EditText
    private lateinit var supbtn:Button
    private lateinit var supfname:EditText
    private lateinit var suplname:EditText
    private lateinit var suppn:EditText
    private lateinit var supadd:EditText
    private lateinit var signin_link:TextView

    private lateinit var auth: FirebaseAuth

    private lateinit var sEmail:String
    private lateinit var sPassword:String
    private lateinit var sFname:String
    private lateinit var sLname:String
    private lateinit var sPhone:String
    private lateinit var sAddress:String

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        // Initialize Firebase Auth
        auth = Firebase.auth

        database = Firebase.database.reference

        supmail = findViewById(R.id.supmail)
        suppwd = findViewById(R.id.suppwd)
        supbtn = findViewById(R.id.supbtn)
        supfname = findViewById(R.id.supfname)
        suplname = findViewById(R.id.suplname)
        suppn =findViewById(R.id.suppn)
        supadd = findViewById(R.id.supadd)

        signin_link = findViewById(R.id.signin_link)

        supbtn.setOnClickListener {

            sEmail = supmail.text.toString().trim()
            sPassword = suppwd.text.toString().trim()

            auth.createUserWithEmailAndPassword(sEmail, sPassword)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val user = auth.currentUser
                        saveData()
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()
                        updateUI(null)
                    }
                }

        }

        signin_link.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

    }

    private fun saveData() {
        sEmail = supmail.text.toString().trim()
        sPassword = suppwd.text.toString().trim()
        sLname = suplname.text.toString().trim()
        sFname = supfname.text.toString().trim()
        sPhone = suppn.text.toString().trim()
        sAddress = supadd.text.toString().trim()

        val user  = UModel(sEmail, sPassword, sLname,sFname, sPhone, sAddress)

        val userId = FirebaseAuth.getInstance().currentUser!!.uid

        database.child("User").child(userId).setValue(user)
    }

    private fun updateUI(user: FirebaseUser?) {

        val intent = Intent(this, DashboardActivity::class.java)
        startActivity(intent)
    }
}