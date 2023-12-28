package com.ramya.chatapplication

import android.R.attr
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.sql.DatabaseMetaData
import kotlin.jvm.internal.Ref


class SignUp : AppCompatActivity() {

    private lateinit var edtName: EditText
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnSignUp: Button
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        supportActionBar?.hide()


        mAuth=FirebaseAuth.getInstance();



        edtName=findViewById(R.id.edt_name)
        edtEmail=findViewById(R.id.edt_Email)
        edtPassword=findViewById(R.id.edt_Password)
        btnSignUp=findViewById(R.id.btnSignUp)

        btnSignUp.setOnClickListener {
            val name=edtName.text.toString()
            val email=edtEmail.text.toString()
             val password=edtPassword.text.toString()

            signUp(name, email, password)
        }

    }
 private fun signUp(name:String, email: String, password: String){
     mAuth.createUserWithEmailAndPassword(email, password)
         .addOnCompleteListener(this) { task ->
             if (task.isSuccessful) {
                 addUserToDatabase(name, email, mAuth.currentUser?.uid!!)
                 val intent=Intent(this@SignUp, MainActivity::class.java)
                 finish()
                 startActivity(intent)
             } else {
                 Toast.makeText(this@SignUp, "Some error occured", Toast.LENGTH_SHORT).show()
             }
         }
 }
    private fun addUserToDatabase(name: String, email: String, uid: String){
mDbRef=FirebaseDatabase.getInstance().getReference()

        mDbRef.child("user").child(uid).setValue(User(name, email, uid))

    }



}
