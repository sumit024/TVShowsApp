package com.app_devs.tvshowsapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.app_devs.tvshowsapp.R
import com.app_devs.tvshowsapp.ShowsViewModel
import com.app_devs.tvshowsapp.adapter.SliderAdapter
import com.app_devs.tvshowsapp.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {
    private val showData by navArgs<DetailsFragmentArgs>()
    private lateinit var binding: FragmentDetailsBinding
    private lateinit var mShowsViewModel: ShowsViewModel
    private lateinit var mAdapter:SliderAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentDetailsBinding.inflate(layoutInflater, container, false)
        Log.d("SUMIT",showData.data.id.toString())
        mShowsViewModel=ViewModelProvider(this).get(ShowsViewModel::class.java)
        initViewModel()
        return binding.root

    }

    private fun initViewModel() {
        mShowsViewModel.getShowDetails(showData.data.id!!)
        mShowsViewModel.getShowDetailsObservable().observe(viewLifecycleOwner, Observer {
            Log.d("SUMIT",it.toString())
            binding.description.text=it.tvShow.description
            binding.networkName.text=it.tvShow.network
            binding.status.text=it.tvShow.status
            val list:List<String> = it.tvShow.pictures
            mAdapter= SliderAdapter(requireContext(),list)
            binding.viewPager.adapter=mAdapter
        })
    }


}