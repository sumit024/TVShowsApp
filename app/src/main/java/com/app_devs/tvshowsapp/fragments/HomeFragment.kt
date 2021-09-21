package com.app_devs.tvshowsapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app_devs.tvshowsapp.retrofit.RetroService
import com.app_devs.tvshowsapp.retrofit.RetrofitInstance
import com.app_devs.tvshowsapp.ShowsList
import com.app_devs.tvshowsapp.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Response

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentHomeBinding.inflate(layoutInflater,container,false)

        val retroInstance: RetroService = RetrofitInstance.getRetrofitInstance()
            .create(RetroService::class.java)
        val call = retroInstance.getPopularShows()
        call.enqueue(object : retrofit2.Callback<ShowsList>{
            override fun onFailure(call: Call<ShowsList>, t: Throwable) {
               Log.d("SUMIT","FAILED")
            }

            override fun onResponse(call: Call<ShowsList>, response: Response<ShowsList>) {
                if(response.isSuccessful) {
                  Log.d("SUMIT","Success")
                } else {
                    Log.d("SUMIT","Unsuccessful")
                }
            }
        })
        return binding.root
    }

}