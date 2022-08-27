package com.afsal.dev.typicodapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.afsal.dev.typicodapp.adapters.CommentsAdapter
import com.afsal.dev.typicodapp.databinding.FragmentCommentsBinding
import com.afsal.dev.typicodapp.netWork.Resource
import com.afsal.dev.typicodapp.uttil.handleApiError
import com.afsal.dev.typicodapp.view_model.HomeViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CommentSheetFragment : BottomSheetDialogFragment() {
    private val TAG = "CommentSheet"
    private var _binding: FragmentCommentsBinding? = null

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var commentsAdapter: CommentsAdapter

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


            homeViewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]

        _binding = FragmentCommentsBinding.inflate(inflater, container, false)


        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
          commentsAdapter=CommentsAdapter()

        binding.commentsRv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = commentsAdapter
        }

        homeViewModel.commentList.observe(viewLifecycleOwner, Observer {


            when(it){
                is Resource.Success ->{
                    val data=it.value.body()!!
                    commentsAdapter.differ.submitList(data)
                }

                is Resource.Failure ->{
                    handleApiError(it)
                }

            }

            Log.d(TAG, "Comments ${it.toString()}")
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}