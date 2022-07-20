package com.atakanmadanoglu.artbookapplication.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.atakanmadanoglu.artbookapplication.adapter.ImageRecyclerViewAdapter
import com.atakanmadanoglu.artbookapplication.databinding.FragmentImagesApiBinding
import javax.inject.Inject

class ImagesApiFragment @Inject constructor(
    private val imageRecyclerViewAdapter: ImageRecyclerViewAdapter
): Fragment() {

    private var _binding: FragmentImagesApiBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentImagesApiBinding.inflate(inflater, container, false)
        binding.apiImagesRecyclerView.adapter = imageRecyclerViewAdapter
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}