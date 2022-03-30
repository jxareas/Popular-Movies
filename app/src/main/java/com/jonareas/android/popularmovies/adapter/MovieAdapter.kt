package com.jonareas.android.popularmovies.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.*
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jonareas.android.popularmovies.R
import com.jonareas.android.popularmovies.adapter.MovieAdapter.MovieViewHolder
import com.jonareas.android.popularmovies.databinding.ListItemMovieBinding
import com.jonareas.android.popularmovies.model.entities.Movie
import com.jonareas.android.popularmovies.utils.help
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asExecutor

class MovieAdapter : RecyclerView.Adapter<MovieViewHolder>() {

    private companion object {
        const val POSTER_PATH_PREFIX : String = "https://image.tmdb.org/t/p/w300"

        private val diffCallback = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem.title == newItem.title

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem == newItem

        }

        private val asyncDiffConfig = AsyncDifferConfig.Builder(diffCallback)
            .setBackgroundThreadExecutor { Dispatchers.Default.asExecutor() }
            .build()
    }

    private val diffList = AsyncListDiffer(AdapterListUpdateCallback(this), asyncDiffConfig)
    var itemList : List<Movie>
        get() = diffList.currentList
        set(value) {
            diffList.submitList(value)
        }

    private val requestOptions by lazy {
        RequestOptions()
            .error(R.drawable.no_internet)
            .placeholder(R.drawable.ic_movie_placeholder)
    }

    inner class MovieViewHolder(private val binding: ListItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie): Unit = binding.run {
            textViewMovieTitle.text = movie.title
            textViewMovieOverview.text = movie.overview

            Glide.with(root.context)
                .applyDefaultRequestOptions(requestOptions)
                .load("${POSTER_PATH_PREFIX}${movie.posterPath}")
                .into(imageViewMoviePoster)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(parent help ListItemMovieBinding::inflate)

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) : Unit =
        holder.bind(itemList[position])

    override fun getItemCount(): Int = itemList.size

}