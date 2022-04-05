package com.jonareas.android.popularmovies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jonareas.android.popularmovies.R
import com.jonareas.android.popularmovies.databinding.ListItemMovieBinding
import com.jonareas.android.popularmovies.model.entities.Movie

class MovieListAdapter : ListAdapter<Movie, MovieListAdapter.MovieViewHolder>(DiffCallback) {

    private val IMAGE_PATH_PREFIX: String = "https://image.tmdb.org/t/p/w300"

    private companion object DiffCallback : DiffUtil.ItemCallback<Movie>() {

        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            oldItem.title == newItem.title && oldItem.overview == newItem.overview

    }

    private val requestOptions by lazy {
        RequestOptions()
            .error(R.drawable.no_internet)
            .placeholder(R.drawable.ic_movie_placeholder)
    }


    inner class MovieViewHolder(private val binding: ListItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(movie : Movie) = binding.run {

                Glide.with(itemView.context)
                    .applyDefaultRequestOptions(requestOptions)
                    .load("${IMAGE_PATH_PREFIX}${movie.poster_path}")
                    .into(imageViewMoviePoster)

                textViewMovieTitle.text = movie.title
                textViewMovieOverview.text = movie.overview

            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(ListItemMovieBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) : Unit =
        holder.bind(currentList[position])

}
