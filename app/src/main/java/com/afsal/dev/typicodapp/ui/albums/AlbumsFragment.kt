package com.afsal.dev.typicodapp.ui.albums

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.afsal.dev.typicodapp.adapters.AlbumAdapter
import com.afsal.dev.typicodapp.databinding.FragmentAlbumBinding
import com.afsal.dev.typicodapp.netWork.Resource
import com.afsal.dev.typicodapp.uttil.handleApiError
import com.afsal.dev.typicodapp.view_model.HomeViewModel

class AlbumsFragment : Fragment() {

    private val TAG = "AlbumFragment"
    private var _binding: FragmentAlbumBinding? = null
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var albumAdapter: AlbumAdapter


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentAlbumBinding.inflate(inflater, container, false)


        homeViewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.getAllAlbums()

        albumAdapter = AlbumAdapter() {

            //homeViewModel.getPhotosOnAlbum(it)

            Log.d(TAG, "Clicked id $it")
        }

        binding.apply {

            albumRv.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = albumAdapter
            }
        }


        homeViewModel.albumsList.observe(viewLifecycleOwner, Observer {


            when (it) {
                is Resource.Success -> {
                    val data = it.value.body()!!
                    albumAdapter.differ.submitList(data)
                }

                is Resource.Failure -> {
                    handleApiError(it)
                }

            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}