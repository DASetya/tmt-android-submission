package com.example.movieapps.ui.movie

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapps.BuildConfig.IMAGE_URL
import com.example.movieapps.R
import com.example.movieapps.databinding.ItemMovieBinding
import com.example.movieapps.model.ResultsItem

class MovieListAdapter: RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>() {

    private val movieList: MutableList<ResultsItem> = mutableListOf()
    var delegate: MovieDelegate? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        with(holder){
            with(movieList[position]){
                Glide.with(itemView.context)
                    .load(IMAGE_URL + this.posterPath)
                    .placeholder(R.drawable.baseline_image_24)
                    .into(ivPoster)
                binding.tvTitle.text = this.title
                binding.tvRating.text = this.voteAverage.toString()
                binding.tvOverview.text = this.overview
            }
            holder.itemView.setOnClickListener{
                delegate?.onItemClicked(movieList[position])
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setAdapter(movies: List<ResultsItem>){
        movieList.clear()
        movieList.addAll(movies)
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        val ivPoster = binding.ivPoster
        val tvTitle = binding.tvTitle
        val tvRating = binding.tvRating
        val tvOverview = binding.tvOverview
    }

    interface MovieDelegate{
        fun onItemClicked(movie: ResultsItem)
    }
}