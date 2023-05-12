package com.example.hungryhero

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegistrationForm : AppCompatActivity() {

    private lateinit var etEmpName:EditText
    private lateinit var etEmpAge:EditText
    private lateinit var etEmpAddress:EditText
    private lateinit var etEmpVehicle:EditText
    private lateinit var etEmpEmail:EditText
    private lateinit var etEmpNIC:EditText

    private lateinit var btnSaveData:Button

    private lateinit var dbRef :DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration_form)

        etEmpName = findViewById(R.id.etEmpName)
        etEmpAge = findViewById(R.id.etEmpAge)
        etEmpAddress = findViewById(R.id.etEmpAddress)
        etEmpVehicle = findViewById(R.id.etEmpVehicle)
        etEmpEmail = findViewById(R.id.etEmpEmail)
        etEmpNIC = findViewById(R.id.etEmpNIC)

        btnSaveData=findViewById(R.id.button2)

        dbRef = FirebaseDatabase.getInstance().getReference("Emp")

        btnSaveData.setOnClickListener{
            saveEmployeeData()
        }

    }

    private fun saveEmployeeData(){
        val empName = etEmpName.text.toString()
        val empAge = etEmpAge.text.toString()
        val empAddress = etEmpAddress.text.toString()
        val empVehicle = etEmpVehicle.text.toString()
        val empEmail = etEmpEmail.text.toString()
        val empNIC = etEmpNIC.text.toString()

        if(empName.isEmpty()){
            etEmpName.error = "please enter name"
        }

        if(empAge.isEmpty()){
            etEmpAge.error = "please enter age"
        }

        if(empAddress.isEmpty()){
            etEmpAddress.error = "please enter address"
        }

        if(empVehicle.isEmpty()){
            etEmpVehicle.error = "please enter Vehicle"
        }
        if(empEmail.isEmpty()){
            etEmpEmail.error = "please enter email"
        }

        if(empNIC.isEmpty()){
            etEmpNIC.error = "please enter NIC"
        }

        val empId = dbRef.push().key!!

        val employee = Emp(empId,empName,empAge,empAddress,empVehicle,empEmail,empNIC)

        dbRef.child(empId).setValue(employee)
            .addOnCompleteListener{
                Toast.makeText(this,"Data inserted successfully",Toast.LENGTH_LONG).show()
            }.addOnFailureListener{
                    err->
                Toast.makeText(this,"Error ${err.message}",Toast.LENGTH_LONG).show()
            }

    }
}

