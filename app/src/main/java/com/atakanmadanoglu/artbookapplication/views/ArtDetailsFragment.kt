package com.atakanmadanoglu.artbookapplication.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.atakanmadanoglu.artbookapplication.databinding.FragmentArtDetailsBinding
import com.atakanmadanoglu.artbookapplication.util.Status
import com.atakanmadanoglu.artbookapplication.viewmodel.ArtViewModel
import com.bumptech.glide.RequestManager
import javax.inject.Inject


class ArtDetailsFragment @Inject constructor(
    private val glide: RequestManager
) : Fragment() {

    private var _binding: FragmentArtDetailsBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: ArtViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentArtDetailsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[ArtViewModel::class.java]
        subscribeToObservers()
        subscribeToClickListeners()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                viewModel.setSelectedImage("")
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }

    private fun subscribeToClickListeners() {
        binding.artImage.setOnClickListener {
            val action = ArtDetailsFragmentDirections.actionArtDetailsFragmentToImagesApiFragment()
            findNavController().navigate(action)
        }
        binding.apply {
            saveButton.setOnClickListener {
                viewModel.makeArt(
                    artNameEditText.text.toString(),
                    artistNameEditText.text.toString(),
                    artReleaseYearEditText.text.toString()
                )
            }
        }
    }

    private fun subscribeToObservers() {
        viewModel.selectedImage.observe(viewLifecycleOwner) {
            it?.let {
                glide.load(it).into(binding.artImage)
            }
        }
        viewModel.insertArtMsg.observe(viewLifecycleOwner) {
            it?.let {
                when (it.status) {
                    Status.SUCCESS -> {
                        Toast.makeText(
                            requireContext(),
                            "Success!",
                            Toast.LENGTH_LONG
                        ).show()
                        findNavController().navigateUp()
                        viewModel.resetImageInsertMsg()
                    }

                    Status.ERROR -> {
                        Toast.makeText(
                            requireContext(),
                            it.message ?: "",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    Status.LOADING -> {

                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}