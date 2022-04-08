package com.jonareas.android.popularmovies.adapter

import android.graphics.Color
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jonareas.android.popularmovies.R
import com.jonareas.android.popularmovies.adapter.MediaListAdapter.MediaViewHolder
import com.jonareas.android.popularmovies.databinding.ListItemMovieBinding
import com.jonareas.android.popularmovies.model.entities.Media
import com.jonareas.android.popularmovies.model.entities.Movie
import com.jonareas.android.popularmovies.model.entities.TvShow
import com.jonareas.android.popularmovies.utils.IMAGE_PATH_PREFIX_DEFAULT
import com.jonareas.android.popularmovies.utils.help
import com.jonareas.android.popularmovies.utils.isColorDark
import com.jonareas.android.popularmovies.utils.loadAsBitmap
import com.jonareas.android.popularmovies.view.viewpager.MoviesViewPagerFragmentDirections
import com.jonareas.android.popularmovies.view.viewpager.TvShowsViewPagerFragmentDirections

class MediaListAdapter : ListAdapter<Media, MediaViewHolder>(asyncDiffConfig) {

    private companion object {

        private val diffCallback = object : DiffUtil.ItemCallback<Media>() {
            override fun areItemsTheSame(oldItem: Media, newItem: Media): Boolean =
                oldItem.posterPath == newItem.posterPath

            override fun areContentsTheSame(oldItem: Media, newItem: Media): Boolean =
                areItemsTheSame(oldItem, newItem)
        }

        private val asyncDiffConfig = AsyncDifferConfig.Builder(diffCallback).build()
    }


    inner class MediaViewHolder(private val binding: ListItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {


        private fun applyColorToBinding(dominantColor: Int): Unit = binding.run {
            innerConstraintLayout.setBackgroundColor(dominantColor)
            val textColor = if (isColorDark(dominantColor)) Color.WHITE else Color.BLACK

            textViewMovieTitle.setTextColor(textColor)
            textViewMovieOverview.setTextColor(textColor)
            textViewMovieRating.setTextColor(textColor)
            textViewMovieRating.setBackgroundColor(dominantColor)
        }

        fun bind(media: Media): Unit = binding.run {

            textViewMovieTitle.text = if (media is Movie) media.movieTitle else media.showTitle
            textViewMovieOverview.text = media.overview
            textViewMovieRating.text =
                itemView.context.getString(R.string.movie_rating, media.voteAverage)

            imageViewMoviePoster.loadAsBitmap("${IMAGE_PATH_PREFIX_DEFAULT}${media.posterPath}") { color ->
                applyColorToBinding(color)
            }


            root.setOnClickListener { view ->
                Navigation.findNavController(view).navigate(getDirection(media))
            }

        }

    }

    private fun getDirection(media : Media) : NavDirections =
        when(media) {
            is Movie -> MoviesViewPagerFragmentDirections.actionToMovieDetail(media.movieTitle, media.id)
            is TvShow -> TvShowsViewPagerFragmentDirections.actionToTvShowDetail(media.showTitle, media.id)
        }

    override fun onBindViewHolder(holder: MediaViewHolder, position: Int): Unit =
        holder.bind(currentList[position])

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaViewHolder =
        MediaViewHolder(parent help ListItemMovieBinding::inflate)


}
