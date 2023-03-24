package com.example.movieapps.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        val sharedPreferences = getSharedPreferences("user", MODE_PRIVATE)
        val textUsername = sharedPreferences.getString("username", "")
        val textPassword = sharedPreferences.getString("password", "")
        if (textUsername.isNullOrEmpty() && textPassword.isNullOrEmpty()) {
            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
        }
        else {
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        }
        finish()
    }
}