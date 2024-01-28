package com.jean.moviesarchitectcoders.movie.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.jean.moviesarchitectcoders.domain.models.Movie
import com.jean.moviesarchitectcoders.movie.databinding.ItemFavoriteBinding

class FavoriteAdapter(
    private val onFavoriteClick: (movieId: Int) -> Unit
) : ListAdapter<Movie, FavoriteAdapter.FavoriteViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val itemBinding = ItemFavoriteBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FavoriteViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class FavoriteViewHolder(
        private val binding: ItemFavoriteBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) = with(binding) {
            ivPoster.load(movie.posterPath) {
                crossfade(true)
            }
            tvTitle.text = movie.title
        }

    }

    class DiffCallback : DiffUtil.ItemCallback<Movie>() {

        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

    }

}