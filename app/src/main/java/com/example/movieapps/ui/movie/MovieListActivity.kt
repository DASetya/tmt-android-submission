package com.example.movieapps.ui.movie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.movieapps.R
import com.example.movieapps.databinding.ActivityMovieListBinding
import com.example.movieapps.dummy.MovieData
import com.example.movieapps.model.Movie

class MovieListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieListBinding
    private lateinit var adapter: MovieListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = "Movie List"
        adapter = MovieListAdapter()
        binding.rvMovies.adapter = adapter

        val linearLayoutManager = LinearLayoutManager(this, VERTICAL, false)
        binding.rvMovies.layoutManager = linearLayoutManager

        adapter.delegate = object : MovieListAdapter.MovieDelegate{
            override fun onItemClicked(movie: Movie) {
                MovieDetailActivity.open(this@MovieListActivity, "Movie Detail", movie)
            }
        }

        adapter.setAdapter(MovieData.getList())
        binding.rvMovies.isVisible = MovieData.getList().isNotEmpty()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_list, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_list -> {
                binding.rvMovies.layoutManager = LinearLayoutManager(this)
            }
            R.id.action_grid -> {
                binding.rvMovies.layoutManager = GridLayoutManager(this, 1, RecyclerView.HORIZONTAL, false)
            }
            R.id.action_staggered -> {
                binding.rvMovies.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object{
        fun open(activity: AppCompatActivity){
            val intent = Intent(activity, MovieListActivity::class.java)
            ActivityCompat.startActivity(activity, intent, null)
        }
    }
}