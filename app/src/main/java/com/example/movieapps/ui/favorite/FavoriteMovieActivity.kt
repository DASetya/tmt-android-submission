package com.example.movieapps.ui.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapps.database.FavoriteDatabase
import com.example.movieapps.databinding.ActivityMovieListBinding
import com.example.movieapps.model.ResultsItem
import com.example.movieapps.ui.movie.MovieDetailActivity
import com.example.movieapps.ui.movie.MovieListAdapter

class FavoriteMovieActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieListBinding
    private lateinit var adapter: MovieListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = "Favorite Movie"
        adapter = MovieListAdapter()
        binding.rvMovies.adapter = adapter

        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvMovies.layoutManager = linearLayoutManager

        FavoriteDatabase
            .getDatabase(this)
            .movieDao()
            .getAll()
            .observe(this) { entities ->
                val resultsItem = entities.map{
                    ResultsItem(
                        id = it.id,
                        backdropPath = it.backdropPath,
                        posterPath = it.posterPath,
                        title = it.title,
                        releaseDate = it.releaseDate,
                        voteAverage = it.voteAverage,
                        overview = it.overview
                    )
                }
                adapter.setAdapter(resultsItem)
            }

        adapter.delegate = object : MovieListAdapter.MovieDelegate {
            override fun onItemClicked(movie: ResultsItem) {
                MovieDetailActivity.open(this@FavoriteMovieActivity, "Movie Detail", movie)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}