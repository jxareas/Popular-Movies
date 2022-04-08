package com.jonareas.android.popularmovies.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jonareas.android.popularmovies.adapter.ActorListAdapter.ActorViewHolder
import com.jonareas.android.popularmovies.databinding.ListItemActorBinding
import com.jonareas.android.popularmovies.model.entities.Actor
import com.jonareas.android.popularmovies.utils.IMAGE_PATH_PREFIX_DEFAULT
import com.jonareas.android.popularmovies.utils.help
import com.jonareas.android.popularmovies.utils.load

class ActorListAdapter(private val onActorClicked : (Actor) -> Unit) :
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


    inner class ActorViewHolder(private val binding: ListItemActorBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(actor: Actor): Unit = binding.run {
            textViewActorName.text = actor.name
            imageViewMoviePoster.load("${IMAGE_PATH_PREFIX_DEFAULT}${actor.profile_path}")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder =
        ActorViewHolder(parent help ListItemActorBinding::inflate)

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) : Unit =
        holder.bind(currentList[position])

}
