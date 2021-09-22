package com.app_devs.tvshowsapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.app_devs.tvshowsapp.adapter.ShowsAdapter
import com.app_devs.tvshowsapp.ShowsViewModel
import com.app_devs.tvshowsapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    private lateinit var mShowAdapter: ShowsAdapter
    private lateinit var mViewModel: ShowsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentHomeBinding.inflate(layoutInflater,container,false)
        initRecyclerView()
        initViewModel()
        return binding.root
    }

    private fun initViewModel() {
        mViewModel=ViewModelProvider(this).get(ShowsViewModel::class.java)
        mViewModel.getShowsList()
        mViewModel.getShowListObservable().observe(viewLifecycleOwner, Observer {
            if(it == null){
                Toast.makeText(requireContext(),"No records found",Toast.LENGTH_LONG).show()
            }
            else{
                mShowAdapter.showList=it.tv_shows.toMutableList()
                mShowAdapter.notifyDataSetChanged()
                Log.d("SUMIT","in else block")
            }
        })

    }

    private fun initRecyclerView() {
        binding.rvPopularShow.apply {
            layoutManager=LinearLayoutManager(requireContext())
            val decoration=DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            mShowAdapter= ShowsAdapter(requireContext())
            adapter=mShowAdapter
        }
    }

}