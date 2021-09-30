package com.app_devs.tvshowsapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app_devs.tvshowsapp.*
import com.app_devs.tvshowsapp.adapter.ShowsAdapter
import com.app_devs.tvshowsapp.databinding.FragmentHomeBinding
import com.app_devs.tvshowsapp.retrofit.RetroService
import com.app_devs.tvshowsapp.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    private lateinit var mShowAdapter: ShowsAdapter
    private val mViewModel: ShowsViewModel by viewModels()
    private var current_page=1
    private var total_pages=1
    private var showList= ArrayList<Show>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentHomeBinding.inflate(layoutInflater,container,false)
        initRecyclerView()
        binding.btnShowSaved.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_savedShowsFragment)
        }
        return binding.root
    }

    private fun initViewModel() {
        Log.d("SUMIT","calling for page $current_page")
        mViewModel.getShowResponse(current_page)
        mViewModel.getShowResponseObservable().observeOnce(viewLifecycleOwner, Observer {
            if(it == null){
                Toast.makeText(requireContext(),"No records found. Check your internet connection or restart the app.",Toast.LENGTH_LONG).show()
            }
            else{
                Log.d("SUMIT","Requesting for page $current_page  ")
                total_pages=it.pages!!
                val oldCount=showList.size
                showList.addAll(it.tv_shows!!)
                Log.d("SUMIT",it.tv_shows.toString())
                mShowAdapter.notifyItemRangeInserted(oldCount,showList.size)
                Log.d("SUMIT","in else block")
            }
        })

        Log.d("SUMIT","initViewModelFinished")

    }

    private fun initRecyclerView() {
        val layoutManager=LinearLayoutManager(requireContext())
        binding.rvPopularShow.layoutManager=layoutManager
        val decoration=DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL)
        binding.rvPopularShow.addItemDecoration(decoration)
        mShowAdapter= ShowsAdapter(requireContext(),showList, true)
        binding.rvPopularShow.setHasFixedSize(true)
        binding.rvPopularShow.adapter=mShowAdapter
        binding.rvPopularShow.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if(!binding.rvPopularShow.canScrollVertically(1))
                {
                    if(current_page<=total_pages){
                        Log.d("SUMIT","at last item")
                        current_page+=1
                        initViewModel()
                    }
                }
            }
        })
        Log.d("SUMIT","not in onScroll listener")
        Log.d("SUMIT","initRvFinished")
        initViewModel()
    }

}

fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
    observe(lifecycleOwner, object : Observer<T> {
        override fun onChanged(t: T?) {
            observer.onChanged(t)
            removeObserver(this)
        }
    })
    Log.d("SUMIT","not observing")
}
