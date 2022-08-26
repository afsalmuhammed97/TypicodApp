package com.afsal.dev.typicodapp.ui.albums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.afsal.dev.typicodapp.adapters.AlbumAdapter
import com.afsal.dev.typicodapp.databinding.FragmentAlbumBinding

class AlbumsFragment : Fragment() {

    private  var _binding: FragmentAlbumBinding?=null
    private lateinit var albumAdapter: AlbumAdapter
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentAlbumBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        albumAdapter=AlbumAdapter()

        binding.apply {

             albumRv.apply {
                 layoutManager=LinearLayoutManager(context)
                 adapter=albumAdapter
             }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}