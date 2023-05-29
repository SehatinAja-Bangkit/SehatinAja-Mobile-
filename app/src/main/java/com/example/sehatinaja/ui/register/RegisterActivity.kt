package com.example.sehatinaja.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.sehatinaja.HomeActivity
import com.example.sehatinaja.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)


        auth = FirebaseAuth.getInstance()
        binding.registerBtn.setOnClickListener{
            val email = binding.editTextEmail.text.toString().trim()
            val password = binding.editTextPassword.text.toString().trim()
            val username = binding.editTextUsername.text.toString().trim()
            if(email.isEmpty()){
                binding.editTextEmail.error = "Email tidak boleh kosong"
                binding.editTextEmail.requestFocus()
                return@setOnClickListener
            }
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.editTextEmail.error = "Email yang anda masukkan tidak valid"
                binding.editTextEmail.requestFocus()
                return@setOnClickListener
            }
            if(password.isEmpty() || password.length < 8 ){
                binding.editTextPassword.error = "Passwornd harus lebih dari 8 Karakter"
                binding.editTextPassword.requestFocus()
                return@setOnClickListener
            }
            registerUser(email, password)
        }






    }

    private fun registerUser(email: String, password: String) {

        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){
                if(it.isSuccessful){
                    Intent(this@RegisterActivity,HomeActivity::class.java).also{
                        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(it)
                    }
                } else {
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }

    }
    override fun onStart(){
        super.onStart()
        if(auth.currentUser !=null){
            Intent(this@RegisterActivity, HomeActivity::class.java).also{
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }
        }
    }
}