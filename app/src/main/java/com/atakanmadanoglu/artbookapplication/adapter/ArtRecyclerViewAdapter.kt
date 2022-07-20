package com.atakanmadanoglu.artbookapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.atakanmadanoglu.artbookapplication.databinding.ArtBookRowBinding
import com.atakanmadanoglu.artbookapplication.roomdb.Art
import com.bumptech.glide.RequestManager
import javax.inject.Inject

class ArtRecyclerViewAdapter @Inject constructor(
    private val glide: RequestManager
): ListAdapter<Art, ArtRecyclerViewAdapter.ArtViewHolder>(ArtDiffUtil()) {
    class ArtViewHolder(private val binding: ArtBookRowBinding): RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun inflateFrom(parent: ViewGroup): ArtViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ArtBookRowBinding.inflate(layoutInflater, parent, false)
                return ArtViewHolder(binding)
            }
        }

        fun bind(art: Art, glide: RequestManager) {
            binding.apply {
                artName.text = art.name
                artistName.text = art.artistName
                artReleaseYear.text = art.year.toString()
                glide.load(art.imageUrl).into(artImage)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtViewHolder {
        return ArtViewHolder.inflateFrom(parent)
    }

    override fun onBindViewHolder(holder: ArtViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, glide)
    }
}

class ArtDiffUtil: DiffUtil.ItemCallback<Art>() {
    override fun areItemsTheSame(oldItem: Art, newItem: Art): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Art, newItem: Art): Boolean {
        return oldItem == newItem
    }
}