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
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.app_devs.tvshowsapp.R
import com.app_devs.tvshowsapp.Show
import com.app_devs.tvshowsapp.ShowsViewModel
import com.app_devs.tvshowsapp.TvShow
import com.app_devs.tvshowsapp.adapter.SavedShowsAdapter
import com.app_devs.tvshowsapp.adapter.ShowsAdapter
import com.app_devs.tvshowsapp.databinding.FragmentSavedShowsBinding


class SavedShowsFragment : Fragment() {
    private lateinit var binding:FragmentSavedShowsBinding
    private val mShowsViewModel:ShowsViewModel by viewModels()
    private lateinit var mAdapter:ShowsAdapter
    private var list= ArrayList<Show>()
    private lateinit var mShow:Show
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding= FragmentSavedShowsBinding.inflate(layoutInflater,container,false)
        initViewModel()
       // initRecyclerView()
        Log.d("SUMIT",list.toString())
        return binding.root
    }

    private fun initViewModel() {
        mShowsViewModel.getAllNotes().observe(viewLifecycleOwner, Observer {
            if(it == null){
                Toast.makeText(requireContext(),"No records found", Toast.LENGTH_LONG).show()
            }
            else {
                Log.d("SUMIT","save fragment k else mein")
                Log.d("SUMIT",it.toString())
               initRecyclerView(it as ArrayList<Show>)
            }
        })
    }

    private fun initRecyclerView(list:ArrayList<Show>)
    {
        binding.rvSavedShows.apply{
            layoutManager=LinearLayoutManager(requireContext())
            val decoration= DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            setHasFixedSize(true)
            mAdapter=ShowsAdapter(requireContext(),list,false)
            adapter=mAdapter
        }
    }

}