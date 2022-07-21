package com.atakanmadanoglu.artbookapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.atakanmadanoglu.artbookapplication.databinding.ImageRowBinding
import com.bumptech.glide.RequestManager
import javax.inject.Inject

class ImageRecyclerViewAdapter @Inject constructor(
    private val glide: RequestManager
): ListAdapter<String, ImageRecyclerViewAdapter.ImageViewHolder>(ImageDiffUtil()) {

    private var onItemClickListener: ((String) -> Unit)? = null

    class ImageViewHolder(private val binding: ImageRowBinding): RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun inflateFrom(parent: ViewGroup): ImageViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ImageRowBinding.inflate(layoutInflater, parent, false)
                return ImageViewHolder(binding)
            }
        }
        fun bind(url: String, glide: RequestManager, onItemClickListener: ((String) -> Unit)?) {
            binding.apply {
                glide.load(url).into(imageFromApi)
                root.setOnClickListener {
                    onItemClickListener?.invoke(url)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder.inflateFrom(parent)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, glide, onItemClickListener)
    }

    fun setOnItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }

}

class ImageDiffUtil: DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}
