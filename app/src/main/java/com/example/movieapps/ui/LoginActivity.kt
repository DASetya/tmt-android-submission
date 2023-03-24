package com.example.movieapps.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movieapps.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Login"

        binding.btnLogin.setOnClickListener {
            if (!validate()){
                return@setOnClickListener
            }
            else{
                val sharedPref = getSharedPreferences("user", Context.MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.putString("username", binding.etUsername.text.toString().trim())
                editor.putString("password", binding.etPassword.text.toString().trim())
                editor.apply()
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            }
        }
    }
    fun validate(): Boolean {
        val username = binding.etUsername.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()

        if(username.isEmpty()) {
            binding.etUsername.error = "username harus diisi"
            binding.etUsername.requestFocus()
            return false
        }
        else if(password.isEmpty()) {
            binding.etPassword.error = "password harus diisi"
            binding.etPassword.requestFocus()
            return false
        }
        else {
            return true
        }
    }
}