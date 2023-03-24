package com.example.movieapps.ui.movie

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.movieapps.BuildConfig
import com.example.movieapps.BuildConfig.API_KEY
import com.example.movieapps.R
import com.example.movieapps.databinding.ActivityMovieListBinding
import com.example.movieapps.model.MovieResponse
import com.example.movieapps.model.ResultsItem
import com.example.movieapps.network.ApiConfig
import com.example.movieapps.ui.LoginActivity
import com.example.movieapps.ui.favorite.FavoriteMovieActivity
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieListBinding
    private lateinit var adapter: MovieListAdapter
    private lateinit var skeleton: Skeleton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = "Movie List"
        val query = intent.getStringExtra(EXTRA_QUERY)
        adapter = MovieListAdapter()
        binding.rvMovies.adapter = adapter

        val linearLayoutManager = LinearLayoutManager(this, VERTICAL, false)
        binding.rvMovies.layoutManager = linearLayoutManager
        skeleton = binding.rvMovies.applySkeleton(R.layout.item_movie, 7)
        skeleton.showSkeleton()

        val apiService = ApiConfig.getApiService()
        val call = apiService.getMovies(API_KEY)

        if (query != null) {
            searchMovie(query)
        }

        call.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    val list = response.body()?.results
                    list?.let {
                        adapter.setAdapter(it)
                    }
                }
                skeleton.showOriginal()
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.d("foo", "onFailure: ${t.message}")
                Toast.makeText(this@MovieListActivity, "Failed get data", Toast.LENGTH_SHORT).show()
            }
        })


        adapter.delegate = object : MovieListAdapter.MovieDelegate {
            override fun onItemClicked(movie: ResultsItem) {
                MovieDetailActivity.open(this@MovieListActivity, "Movie Detail", movie)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_list, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_list -> {
                binding.rvMovies.layoutManager = LinearLayoutManager(this)
            }
            R.id.action_grid -> {
                binding.rvMovies.layoutManager =
                    GridLayoutManager(this, 1, RecyclerView.HORIZONTAL, false)
            }
            R.id.action_staggered -> {
                binding.rvMovies.layoutManager =
                    StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            }
            R.id.logout -> {
                val sharedPref = getSharedPreferences("user", Context.MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.clear()
                editor.apply()
                startActivity(Intent(this@MovieListActivity, LoginActivity::class.java))
                finish()
            }
            R.id.favorite -> {
                startActivity(Intent(this@MovieListActivity, FavoriteMovieActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun searchMovie(query: String) {
        val apiService = ApiConfig.getApiService()
        val call = apiService.searchMovies(BuildConfig.API_KEY, query)
        call.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    val list = response.body()?.results
                    list?.let {
                        adapter.setAdapter(it)
                    }
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.d("foo", "onFailure: ${t.message}")
                Toast.makeText(this@MovieListActivity, "Failed get data", Toast.LENGTH_SHORT).show()
            }
        })
    }

    companion object {
        const val EXTRA_QUERY = "extra_query"
        fun open(activity: AppCompatActivity, query: String?) {
            val intent = Intent(activity, MovieListActivity::class.java)
            intent.putExtra(EXTRA_QUERY, query)
            ActivityCompat.startActivity(activity, intent, null)
        }
    }
}