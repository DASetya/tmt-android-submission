package com.example.movieapps.ui.movie

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapps.databinding.ItemMovieBinding
import com.example.movieapps.model.Movie

class MovieListAdapter: RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>() {

    private val movieList: MutableList<Movie> = mutableListOf()
    var delegate: MovieDelegate? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        with(holder){
            with(movieList[position]){
                binding.ivPoster.setImageResource(this.poster!!)
                binding.tvTitle.text = this.title
                binding.tvRating.text = this.rating.toString()
                binding.tvOverview.text = this.overview
            }
            holder.itemView.setOnClickListener{
                delegate?.onItemClicked(movieList[position])
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setAdapter(movies: List<Movie>){
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
        fun onItemClicked(movie: Movie)
    }
}