package com.afsal.dev.typicodapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.afsal.dev.typicodapp.adapters.PostesAdapter
import com.afsal.dev.typicodapp.databinding.FragmentHomeBinding
import com.afsal.dev.typicodapp.view_model.HomeViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
   private lateinit var postesAdapter: PostesAdapter
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postesAdapter= PostesAdapter()

        binding.apply {
            postsRv.layoutManager=LinearLayoutManager(context)
            postsRv.adapter=postesAdapter
        }

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}