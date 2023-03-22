package com.example.movieapps.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import com.example.movieapps.BuildConfig
import com.example.movieapps.databinding.ActivityMainBinding
import com.example.movieapps.model.MovieResponse
import com.example.movieapps.model.ResultsItem
import com.example.movieapps.network.ApiConfig
import com.example.movieapps.ui.movie.MovieListActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
                MovieListActivity.open(this@MainActivity, null)
            }
        }
        binding.searchMovie.setOnClickListener {
            val query = binding.searchMovie.text.toString()
            MovieListActivity.open(this@MainActivity, query)
        }
    }
}