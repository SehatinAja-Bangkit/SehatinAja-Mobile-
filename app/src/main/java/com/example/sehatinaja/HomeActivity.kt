package com.example.sehatinaja

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sehatinaja.databinding.ActivityHomeBinding
import com.example.sehatinaja.ui.login.LoginActivity

class HomeActivity : AppCompatActivity() {


    private lateinit var binding:ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.cobaLogin.setOnClickListener {
            Intent(this@HomeActivity, LoginActivity::class.java).also{
                startActivity(it)
            }
        }
    }
}