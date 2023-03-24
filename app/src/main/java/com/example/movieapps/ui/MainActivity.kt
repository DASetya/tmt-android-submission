package com.example.movieapps.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import com.example.movieapps.R
import com.example.movieapps.databinding.ActivityMainBinding
import com.example.movieapps.ui.favorite.FavoriteMovieActivity
import com.example.movieapps.ui.movie.MovieListActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences("user", MODE_PRIVATE)
        val textUsername = sharedPreferences.getString("username", "")

        title = "Hi, $textUsername"

        val menuId = listOf(
            binding.menu1, binding.menu2, binding.menu3, binding.menu4, binding.menu5, binding.menu6, binding.menu7, binding.menu8, binding.menu9
        )
        menuId.forEach{ menu ->
            menu.setOnClickListener {
                MovieListActivity.open(this@MainActivity, null)
            }
        }
        binding.menu10.setOnClickListener {
            startActivity(Intent(this@MainActivity, FavoriteMovieActivity::class.java))
        }
        binding.searchMovie.setOnClickListener {
            val query = binding.searchMovie.text.toString()
            MovieListActivity.open(this@MainActivity, query)
        }
        binding.searchMovie.setOnKeyListener { _, keyCode, event ->
            val query = binding.searchMovie.text.toString()
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN) {
                MovieListActivity.open(this@MainActivity, query)
                return@setOnKeyListener true
            }
            false
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_items, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.logout -> {
                val sharedPref = getSharedPreferences("user", Context.MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.clear()
                editor.apply()
                startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                finish()
            }
            R.id.favorite -> {
                startActivity(Intent(this@MainActivity, FavoriteMovieActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }
}