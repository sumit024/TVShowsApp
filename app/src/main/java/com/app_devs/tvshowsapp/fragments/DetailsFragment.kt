package com.app_devs.tvshowsapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.app_devs.tvshowsapp.Show
import com.app_devs.tvshowsapp.viewmodels.ShowsViewModel
import com.app_devs.tvshowsapp.adapter.SliderAdapter
import com.app_devs.tvshowsapp.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {
    private val showData by navArgs<DetailsFragmentArgs>()
    private lateinit var binding: FragmentDetailsBinding
    private val mViewModel: ShowsViewModel by viewModels()
    private lateinit var mAdapter:SliderAdapter
    private lateinit var show: Show
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentDetailsBinding.inflate(layoutInflater, container, false)
        Log.d("SUMIT",showData.data.id.toString())
        initViewModel()
        binding.btnSave.setOnClickListener {
            Log.d("SUMIT",show.toString())
            mViewModel.saveShow(show)
            Toast.makeText(requireContext(),"Added to watchlist",Toast.LENGTH_SHORT).show()
        }
        return binding.root

    }

    private fun initViewModel() {
        mViewModel.getShowDetails(showData.data.id!!)
        mViewModel.getShowDetailsObservable().observe(viewLifecycleOwner, Observer {
            if(it == null){
                Toast.makeText(requireContext(),"Check your internet connection",Toast.LENGTH_LONG).show()
            }
            else {
                show = Show(it.tvShow.id, it.tvShow.name, it.tvShow.network, it.tvShow.start_date, it.tvShow.status, it.tvShow.image_thumbnail_path)
                Log.d("SUMIT", it.toString())
                binding.description.text = it.tvShow.description
                binding.networkName.text = it.tvShow.network
                binding.status.text = it.tvShow.status
                val list: List<String> = it.tvShow.pictures!!
                mAdapter = SliderAdapter(requireContext(), list)
                binding.viewPager.adapter = mAdapter
            }
        })
    }


}