package com.afsal.dev.typicodapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.afsal.dev.typicodapp.R
import com.afsal.dev.typicodapp.adapters.PostesAdapter
import com.afsal.dev.typicodapp.databinding.FragmentHomeBinding
import com.afsal.dev.typicodapp.netWork.Resource
import com.afsal.dev.typicodapp.uttil.handleApiError
import com.afsal.dev.typicodapp.view_model.HomeViewModel

class HomeFragment : Fragment() {
    private val TAG = "HomeFragment"
    private var _binding: FragmentHomeBinding? = null
    private lateinit var postesAdapter: PostesAdapter
    private lateinit var homeViewModel: HomeViewModel

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        homeViewModel =
            ViewModelProvider(requireActivity())[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postesAdapter = PostesAdapter() { commentId ->

            homeViewModel.getCommentsOnPost(commentId)
            navigateToComments()
        }

        homeViewModel.postList.observe(viewLifecycleOwner, Observer {

            when(it){
                is Resource.Success ->{
                    binding.progressBar.visibility=View.GONE

                    val data=it.value.body()!!
                    postesAdapter.differ.submitList(data)
                }

                is Resource.Failure ->{

                    binding.progressBar.visibility=View.VISIBLE
                   handleApiError(it)
                }

            }


          //  postesAdapter.differ.submitList(it)

            Log.d(TAG, "Posts list ${it.toString()}")
        })



        binding.apply {
            postsRv.layoutManager = LinearLayoutManager(context)
            postsRv.adapter = postesAdapter
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun navigateToComments(){
        findNavController().navigate(R.id.action_navigation_home_to_commentSheetFragment)
    }
}