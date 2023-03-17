package com.example.movieapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.res.ResourcesCompat
import com.example.movieapps.databinding.ActivityMovieDetailBinding

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = intent.getStringExtra(PAGE_TITLE)
        val movie = intent.getParcelableExtra<Movie>(EXTRA_MOVIE)
        if(movie?.title!=null){
            binding.apply {
                val imgBackdrop = resources.getIdentifier("@drawable/${movie.backdrop}", null, packageName)
                val imgPoster = resources.getIdentifier("@drawable/${movie.poster}", null, packageName)
                backdrop.setImageDrawable(ResourcesCompat.getDrawable(resources, imgBackdrop, null))
                poster.setImageDrawable(ResourcesCompat.getDrawable(resources, imgPoster, null))
                title.text = movie.title
                releaseDate.text = movie.release
                rating.text = movie.rating.toString()
                description.text = movie.overview
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        const val PAGE_TITLE = "page_title"
        const val EXTRA_MOVIE = "extra_movie"

        fun open(activity: AppCompatActivity, title: String, movie: Movie){
            val intent = Intent(activity, MovieDetailActivity::class.java)
            intent.putExtra(PAGE_TITLE, title)
            intent.putExtra(EXTRA_MOVIE, movie)
            ActivityCompat.startActivity(activity, intent, null)
        }
    }
}