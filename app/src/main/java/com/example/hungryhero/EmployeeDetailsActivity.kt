package com.example.hungryhero

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import kotlin.concurrent.fixedRateTimer

class EmployeeDetailsActivity : AppCompatActivity() {

    private lateinit var empId:TextView
    private lateinit var empName: TextView
    private lateinit var empAge: TextView
    private lateinit var empAddress: TextView
    private lateinit var empVehicle: TextView
    private lateinit var empEmail: TextView
    private lateinit var empNIC: TextView
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee_details)






        initView()
        setValuesToViews()

        btnUpdate.setOnClickListener{
            openUpdateDialog(
                intent.getStringExtra("empId").toString() ,
                        intent.getStringExtra("empName").toString()


            )
        }

        btnDelete.setOnClickListener{
            deleteRecord(
                intent.getStringExtra("empId").toString()
            )
        }
    }

    private fun deleteRecord(
        id:String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Emp").child(id)
        val mTask= dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this,"Employee data deleted",Toast.LENGTH_LONG).show()

            val intent = Intent(this,FetchingActivity::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{error ->
        Toast.makeText(this,"Delete err ${error.message}",Toast.LENGTH_LONG).show()
    }
    }
    private fun initView() {
        empId = findViewById(R.id.tvEmpId)
        empName = findViewById(R.id.tvEmpName)
        empAge = findViewById(R.id.tvEmpAge)
        empAddress = findViewById(R.id.tvEmpAddress)
        empVehicle = findViewById(R.id.tvEmpVehicle)
        empEmail = findViewById(R.id.tvEmpEmail)
        empNIC = findViewById(R.id.tvEmpNIC)
        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
    }

    private fun setValuesToViews() {



            empId.text = intent.getStringExtra("empId")
            empName.text = intent.getStringExtra("empName")
            empAge.text = intent.getStringExtra("empAge")
            empAddress.text = intent.getStringExtra("empAddress")
            empVehicle.text = intent.getStringExtra("empVehicle")
            empEmail.text = intent.getStringExtra("empEmail")
            empNIC.text = intent.getStringExtra("empNIC")
        }

    @SuppressLint("SuspiciousIndentation")
    private fun openUpdateDialog(

        empId:String,
        empName:String

    ){
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.update_dialog,null)

          mDialog.setView(mDialogView)

        val etEmpVehicle = mDialogView.findViewById<EditText>(R.id.etEmpVehicle)
        val etEmpEmail = mDialogView.findViewById<EditText>(R.id.etEmpEmail)
        val etEmpAddress = mDialogView.findViewById<EditText>(R.id.etEmpAddress)
        val btnUpdateData = mDialogView.findViewById<Button>(R.id.btnUpdateData)

        etEmpVehicle.setText(intent.getStringExtra("empVehicle").toString())

        etEmpEmail.setText(intent.getStringExtra("empEmail").toString())


        etEmpAddress.setText(intent.getStringExtra("empAddress").toString())

        mDialog.setTitle("Updating $empName Data")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener{
            updateEmpData(
                empId,
                etEmpVehicle.text.toString(),
                etEmpEmail.text.toString(),
                etEmpAddress.text.toString()




            )
            Toast.makeText(applicationContext,"Employee data updated", Toast.LENGTH_LONG).show()


            empEmail.text = etEmpEmail.text.toString()
            empAddress.text = etEmpAddress.text.toString()
            empVehicle.text = etEmpVehicle.text.toString()

            alertDialog.dismiss()
        }


    }
    private fun updateEmpData(

        id:String,
        Vehicle:String,
        Email: String,
        Address:String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Emp").child(id)
        val empInfo = Emp(id,Vehicle,Email,Address)
        dbRef.setValue(empInfo)
    }

        }

