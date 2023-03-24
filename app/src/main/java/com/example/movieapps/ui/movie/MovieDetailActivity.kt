package com.example.movieapps.ui.movie

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.movieapps.BuildConfig.IMAGE_URL
import com.example.movieapps.R
import com.example.movieapps.database.FavoriteDatabase
import com.example.movieapps.database.MovieEntity
import com.example.movieapps.databinding.ActivityMovieDetailBinding
import com.example.movieapps.model.ResultsItem
import com.example.movieapps.ui.LoginActivity
import com.example.movieapps.ui.favorite.FavoriteMovieActivity
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.createSkeleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding
    private var movie: ResultsItem? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = intent.getStringExtra(PAGE_TITLE)
        movie = intent.getParcelableExtra(EXTRA_MOVIE)

        if(movie?.title!=null){
            binding.apply {
                val backdropSkeleton: Skeleton = backdrop.createSkeleton()
                backdropSkeleton.showSkeleton()
                Glide.with(backdrop)
                    .load(IMAGE_URL+movie!!.backdropPath)
                    .addListener(object: RequestListener<Drawable>{
                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            backdropSkeleton.showOriginal()
                            return false
                        }
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            backdropSkeleton.showOriginal()
                            return false
                        }
                    })
                    .into(backdrop)
                val posterSkeleton: Skeleton = poster.createSkeleton()
                posterSkeleton.showSkeleton()
                Glide.with(poster)
                    .load(IMAGE_URL+movie!!.posterPath)
                    .addListener(object: RequestListener<Drawable>{
                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            posterSkeleton.showOriginal()
                            return false
                        }
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            posterSkeleton.showOriginal()
                            return false
                        }
                    })
                    .into(poster)
                title.text = movie!!.title
                releaseDate.text = movie!!.releaseDate
                rating.text = movie!!.voteAverage.toString()
                description.text = movie!!.overview
            }
        }

        FavoriteDatabase
            .getDatabase(this)
            .movieDao()
            .isExist(movie?.id!!)
            .observe(this) { isExist ->
                binding.apply {
                    addFavorite.isVisible = !isExist
                    removeFavorite.isVisible = isExist
                }
            }

        binding.removeFavorite.setOnClickListener {
            val entity = MovieEntity(
                id = movie?.id,
                backdropPath = movie?.backdropPath,
                posterPath = movie?.posterPath,
                title = movie?.title,
                releaseDate = movie?.releaseDate,
                voteAverage = movie?.voteAverage,
                overview = movie?.overview
            )
            CoroutineScope(Dispatchers.IO).launch {
                FavoriteDatabase
                    .getDatabase(this@MovieDetailActivity)
                    .movieDao()
                    .delete(entity)
            }
        }

        binding.addFavorite.setOnClickListener {
            val insert = MovieEntity(
                id = movie?.id,
                backdropPath = movie?.backdropPath,
                posterPath = movie?.posterPath,
                title = movie?.title,
                releaseDate = movie?.releaseDate,
                voteAverage = movie?.voteAverage,
                overview = movie?.overview
            )
            CoroutineScope(Dispatchers.IO).launch {
                FavoriteDatabase
                    .getDatabase(this@MovieDetailActivity)
                    .movieDao()
                    .insert(insert)
            }
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
                startActivity(Intent(this@MovieDetailActivity, LoginActivity::class.java))
                finish()
            }
            R.id.favorite -> {
                startActivity(Intent(this@MovieDetailActivity, FavoriteMovieActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        const val PAGE_TITLE = "page_title"
        const val EXTRA_MOVIE = "extra_movie"

        fun open(activity: AppCompatActivity, title: String, movie: ResultsItem){
            val intent = Intent(activity, MovieDetailActivity::class.java)
            intent.putExtra(PAGE_TITLE, title)
            intent.putExtra(EXTRA_MOVIE, movie)
            ActivityCompat.startActivity(activity, intent, null)
        }
    }
}