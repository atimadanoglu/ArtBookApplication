package com.atakanmadanoglu.artbookapplication.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.atakanmadanoglu.artbookapplication.databinding.FragmentArtBookBinding
import com.bumptech.glide.RequestManager
import javax.inject.Inject


class ArtBookFragment @Inject constructor(
    private val glide: RequestManager
) : Fragment() {

    private var _binding: FragmentArtBookBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentArtBookBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.floatingActionButton.setOnClickListener {
            val action = ArtBookFragmentDirections.actionArtBookFragmentToArtDetailsFragment()
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}