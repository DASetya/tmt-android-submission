package com.example.movieapps.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.example.movieapps.databinding.ActivityMainBinding
import com.example.movieapps.ui.movie.MovieListActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(binding.root)
        supportActionBar?.hide()
        val menuId = listOf(
            binding.menu1, binding.menu2, binding.menu3, binding.menu4, binding.menu5, binding.menu6, binding.menu7, binding.menu8, binding.menu9
        )
        menuId.forEach{ menu ->
            menu.setOnClickListener {
                MovieListActivity.open(this@MainActivity)
            }
        }
    }
}