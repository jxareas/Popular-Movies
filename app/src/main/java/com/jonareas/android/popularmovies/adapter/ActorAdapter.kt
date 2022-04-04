package com.jonareas.android.popularmovies.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jonareas.android.popularmovies.R
import com.jonareas.android.popularmovies.adapter.ActorAdapter.ActorViewHolder
import com.jonareas.android.popularmovies.databinding.ListItemActorBinding
import com.jonareas.android.popularmovies.model.entities.Actor
import com.jonareas.android.popularmovies.utils.IMAGE_PATH_PREFIX
import com.jonareas.android.popularmovies.utils.help

class ActorAdapter(private val onActorClicked : (Actor) -> Unit) :
    ListAdapter<Actor, ActorViewHolder>(asyncConfig) {

    private companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Actor>() {
            override fun areItemsTheSame(oldItem: Actor, newItem: Actor): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Actor, newItem: Actor): Boolean =
                oldItem.name == newItem.name

        }

        private val asyncConfig = AsyncDifferConfig.Builder(diffCallback).build()
    }

    private val requestOptions by lazy {
        RequestOptions()
            .error(R.drawable.no_internet)
            .placeholder(R.drawable.ic_movie_placeholder)
    }


    inner class ActorViewHolder(private val binding: ListItemActorBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(actor: Actor): Unit = binding.run {
            textViewActorName.text = actor.name

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load("${IMAGE_PATH_PREFIX}${actor.profile_path}")
                .into(imageViewMoviePoster)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder =
        ActorViewHolder(parent help ListItemActorBinding::inflate)

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) : Unit =
        holder.bind(currentList[position])

}