package com.afsal.dev.typicodapp.ui.albums

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.afsal.dev.typicodapp.R
import com.afsal.dev.typicodapp.adapters.PhotosAdapter
import com.afsal.dev.typicodapp.databinding.FragmentPhotosBinding
import com.afsal.dev.typicodapp.netWork.Resource
import com.afsal.dev.typicodapp.uttil.handleApiError
import com.afsal.dev.typicodapp.view_model.HomeViewModel


class PhotosFragment : Fragment() {

  private  var _binding: FragmentPhotosBinding?= null
   private lateinit var homeViewModel: HomeViewModel
   private lateinit var photosAdapter: PhotosAdapter
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding=FragmentPhotosBinding.inflate(inflater,container,false)

      homeViewModel=ViewModelProvider(requireActivity())[HomeViewModel::class.java]

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        photosAdapter= PhotosAdapter()
        binding.photosRv.apply {

            layoutManager=LinearLayoutManager(context)
            adapter=photosAdapter
        }


        homeViewModel.photosList.observe(viewLifecycleOwner, Observer {

            when (it) {
                is Resource.Success -> {

                    binding.progressBar3.visibility=View.GONE
                    val data = it.value.body()!!

                    photosAdapter.differ.submitList(data)
                }

                is Resource.Failure -> {
                    binding.progressBar3.visibility=View.VISIBLE
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