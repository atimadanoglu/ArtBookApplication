package com.atakanmadanoglu.artbookapplication.views

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.atakanmadanoglu.artbookapplication.adapter.ArtRecyclerViewAdapter
import com.atakanmadanoglu.artbookapplication.adapter.ImageRecyclerViewAdapter
import com.bumptech.glide.RequestManager
import javax.inject.Inject

class ArtFragmentFactory @Inject constructor(
    private val artRecyclerViewAdapter: ArtRecyclerViewAdapter,
    private val imageRecyclerViewAdapter: ImageRecyclerViewAdapter,
    private val glide: RequestManager
): FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className) {
            ArtBookFragment::class.java.name -> ArtBookFragment(artRecyclerViewAdapter, glide)
            ImagesApiFragment::class.java.name -> ImagesApiFragment(imageRecyclerViewAdapter)
            ArtDetailsFragment::class.java.name -> ArtDetailsFragment(glide)
            else -> super.instantiate(classLoader, className)
        }
    }
}