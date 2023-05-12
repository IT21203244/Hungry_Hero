package com.example.groceryadd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var login_button:Button
    private lateinit var email_field:EditText
    private lateinit var password_field:EditText

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login2)

        // Initialize Firebase Auth
        auth = Firebase.auth

        login_button = findViewById(R.id.login_button)
        email_field = findViewById(R.id.email_field)
        password_field = findViewById(R.id.password_field)

        val sEmail = email_field.text.toString().trim()
        val sPassword = password_field.text.toString().trim()

        login_button.setOnClickListener {

            auth.signInWithEmailAndPassword(sEmail, sPassword)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val user = auth.currentUser
                        updateUI()
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()
                        updateUI()
                    }
                }

        }
    }

    private fun updateUI() {

        val intent = Intent(this, DashboardActivity::class.java)
        startActivity(intent)
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            updateUI()
        }
    }
}