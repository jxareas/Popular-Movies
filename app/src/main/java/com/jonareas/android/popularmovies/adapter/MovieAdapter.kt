package com.jonareas.android.popularmovies.adapter

import android.graphics.Bitmap
import android.graphics.Color
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.*
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.jonareas.android.popularmovies.R
import com.jonareas.android.popularmovies.adapter.MovieAdapter.MovieViewHolder
import com.jonareas.android.popularmovies.databinding.ListItemMovieBinding
import com.jonareas.android.popularmovies.model.entities.Media
import com.jonareas.android.popularmovies.utils.IMAGE_PATH_PREFIX
import com.jonareas.android.popularmovies.utils.help
import com.jonareas.android.popularmovies.utils.isColorDark
import com.jonareas.android.popularmovies.view.viewpager.MoviesViewPagerFragmentDirections
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asExecutor

class MovieAdapter : RecyclerView.Adapter<MovieViewHolder>() {

    private companion object {

        private val diffCallback = object : DiffUtil.ItemCallback<Media>() {
            override fun areItemsTheSame(oldItem: Media, newItem: Media): Boolean =
                oldItem.posterPath == newItem.posterPath

            override fun areContentsTheSame(oldItem: Media, newItem: Media): Boolean =
                oldItem.posterPath == newItem.posterPath
        }

        private val asyncDiffConfig = AsyncDifferConfig.Builder(diffCallback)
            .setBackgroundThreadExecutor { Dispatchers.Default.asExecutor() }
            .build()
    }

    private val diffList = AsyncListDiffer(AdapterListUpdateCallback(this), asyncDiffConfig)
    var itemList: List<Media>
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


        fun bind(media: Media): Unit = binding.run {
            if(media.movieTitle.isNotEmpty())
                textViewMovieTitle.text = media.movieTitle
            else textViewMovieTitle.text = media.showTitle
            textViewMovieOverview.text = media.overview
            textViewMovieRating.text =
                itemView.context.getString(R.string.movie_rating, media.voteAverage)

            Glide.with(root.context)
                .applyDefaultRequestOptions(requestOptions)
                .asBitmap()
                .load("${IMAGE_PATH_PREFIX}${media.posterPath}")
                .listener(object : RequestListener<Bitmap> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Bitmap>?,
                        isFirstResource: Boolean
                    ): Boolean = false

                    override fun onResourceReady(
                        resource: Bitmap?,
                        model: Any?,
                        target: Target<Bitmap>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        if (resource != null) {
                            Palette.from(resource)
                                .generate().dominantSwatch?.rgb?.let { dominantColor ->
                                    innerConstraintLayout.setBackgroundColor(dominantColor)
                                    val textColor =
                                        if (isColorDark(dominantColor)) Color.WHITE else Color.BLACK

                                    textViewMovieTitle.setTextColor(textColor)
                                    textViewMovieOverview.setTextColor(textColor)
                                    textViewMovieRating.setTextColor(textColor)
                                    textViewMovieRating.setBackgroundColor(dominantColor)
                                }

                        }
                        return false
                    }
                }).into(imageViewMoviePoster)

            root.setOnClickListener { view ->
                Navigation.findNavController(view).navigate(
                    MoviesViewPagerFragmentDirections.actionToMovieDetail(
                        media.movieTitle,
                        media.id
                    )
                )
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(parent help ListItemMovieBinding::inflate)

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int): Unit =
        holder.bind(itemList[position])

    override fun getItemCount(): Int = itemList.size

}
