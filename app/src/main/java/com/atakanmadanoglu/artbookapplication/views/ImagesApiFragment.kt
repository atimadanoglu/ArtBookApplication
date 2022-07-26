package com.atakanmadanoglu.artbookapplication.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.atakanmadanoglu.artbookapplication.adapter.ImageRecyclerViewAdapter
import com.atakanmadanoglu.artbookapplication.databinding.FragmentImagesApiBinding
import com.atakanmadanoglu.artbookapplication.util.Status
import com.atakanmadanoglu.artbookapplication.viewmodel.ArtViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class ImagesApiFragment @Inject constructor(
    val imageRecyclerViewAdapter: ImageRecyclerViewAdapter
): Fragment() {

    private var _binding: FragmentImagesApiBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: ArtViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentImagesApiBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[ArtViewModel::class.java]
        binding.apiImagesRecyclerView.adapter = imageRecyclerViewAdapter
        subscribeToObservers()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageRecyclerViewAdapter.setOnItemClickListener {
            findNavController().popBackStack()
            binding.searchImage.setText("")
            viewModel.setSelectedImage(it)
        }

        var job: Job? = null

        binding.searchImage.addTextChangedListener {
            job?.cancel()
            job = lifecycleScope.launch {
                delay(1000)
                it?.let {
                    if (it.toString().isNotEmpty()) {
                        viewModel.searchForImage(it.toString())
                    }
                }
            }
        }
    }

    private fun subscribeToObservers() {
        viewModel.images.observe(viewLifecycleOwner) {
            it?.let {
                when (it.status) {
                    Status.SUCCESS -> {
                        val urls = it.data?.hits?.map { imageResult ->
                            imageResult.previewURL
                        }
                        imageRecyclerViewAdapter.submitList(urls ?: listOf())
                    }

                    Status.ERROR -> {
                        Toast.makeText(
                            requireContext(),
                            it.message ?: "Error",
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