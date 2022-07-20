package com.atakanmadanoglu.artbookapplication.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.atakanmadanoglu.artbookapplication.databinding.FragmentArtDetailsBinding
import com.bumptech.glide.RequestManager
import javax.inject.Inject


class ArtDetailsFragment @Inject constructor(
    private val glide: RequestManager
) : Fragment() {

    private var _binding: FragmentArtDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentArtDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.artImage.setOnClickListener {
            val action = ArtDetailsFragmentDirections.actionArtDetailsFragmentToImagesApiFragment()
            findNavController().navigate(action)
        }

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}