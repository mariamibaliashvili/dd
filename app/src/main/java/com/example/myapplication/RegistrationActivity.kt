package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class RegistrationActivity : AppCompatActivity() {
    private lateinit var editTextEmail:EditText
    private lateinit var editTextPassword: EditText
    private lateinit var editTextConfirmPassword:EditText
    private lateinit var buttonRegister:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        init()

        registerListener()
    }

    private fun init(){
        editTextEmail=findViewById(R.id.editTextEmail)
        editTextPassword=findViewById(R.id.editTextPassword)
        editTextConfirmPassword=findViewById(R.id.editTextConfirmPassword)
        buttonRegister=findViewById(R.id.buttonRegister)
    }

    private fun registerListener(){
        buttonRegister.setOnClickListener() {
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()
            val pass = editTextConfirmPassword.text.toString()

            if (email.isEmpty() || password.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (pass != password) {
                Toast.makeText(this, "Incorrect Password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful){
                    Toast.makeText(this, "თქვენ წარმატებით დარეგისტრირდით!", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "რეგისტრაცია ვერ გაიარეთ!", Toast.LENGTH_SHORT).show()
                }

            }

        }
    }
}