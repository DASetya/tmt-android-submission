package com.example.movieapps.ui.movie

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.RoundedCorner
import androidx.core.app.ActivityCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.movieapps.BuildConfig.IMAGE_URL
import com.example.movieapps.databinding.ActivityMovieDetailBinding
import com.example.movieapps.model.ResultsItem
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.createSkeleton

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = intent.getStringExtra(PAGE_TITLE)
        val movie = intent.getParcelableExtra<ResultsItem>(EXTRA_MOVIE)

        if(movie?.title!=null){
            binding.apply {
                val backdropSkeleton: Skeleton = backdrop.createSkeleton()
                backdropSkeleton.showSkeleton()
                Glide.with(backdrop)
                    .load(IMAGE_URL+movie.backdropPath)
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
                    .load(IMAGE_URL+movie.posterPath)
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
                title.text = movie.title
                releaseDate.text = movie.releaseDate
                rating.text = movie.voteAverage.toString()
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

        fun open(activity: AppCompatActivity, title: String, movie: ResultsItem){
            val intent = Intent(activity, MovieDetailActivity::class.java)
            intent.putExtra(PAGE_TITLE, title)
            intent.putExtra(EXTRA_MOVIE, movie)
            ActivityCompat.startActivity(activity, intent, null)
        }
    }
}