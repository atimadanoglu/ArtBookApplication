package com.atakanmadanoglu.artbookapplication.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.atakanmadanoglu.artbookapplication.adapter.ArtRecyclerViewAdapter
import com.atakanmadanoglu.artbookapplication.databinding.FragmentArtBookBinding
import com.atakanmadanoglu.artbookapplication.viewmodel.ArtViewModel
import com.bumptech.glide.RequestManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ArtBookFragment @Inject constructor(
    private val artRecyclerViewAdapter: ArtRecyclerViewAdapter,
    private val glide: RequestManager
) : Fragment() {

    private var _binding: FragmentArtBookBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ArtViewModel

    private val swipeCallBack = object: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val layoutPosition = viewHolder.layoutPosition
            val selectedArt = artRecyclerViewAdapter.currentList[layoutPosition]
            viewModel.deleteArt(selectedArt)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentArtBookBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[ArtViewModel::class.java]
        binding.artBookRecyclerView.adapter = artRecyclerViewAdapter
        subscribeObservers()
        ItemTouchHelper(swipeCallBack).attachToRecyclerView(binding.artBookRecyclerView)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.floatingActionButton.setOnClickListener {
            val action = ArtBookFragmentDirections.actionArtBookFragmentToArtDetailsFragment()
            findNavController().navigate(action)
        }
    }

    private fun subscribeObservers() {
        viewModel.artList.observe(viewLifecycleOwner) {
            it?.let {
                artRecyclerViewAdapter.submitList(it)
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}