package com.app_devs.tvshowsapp

import androidx.lifecycle.ViewModel
import com.app_devs.tvshowsapp.retrofit.RetroService
import com.app_devs.tvshowsapp.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShowsViewModel:ViewModel() {

    fun getShowsList()
    {
        val retroService: RetroService =
            RetrofitInstance.getRetrofitInstance().create(RetroService::class.java)
        val call=retroService.getPopularShows()
        call.enqueue(object:Callback<ShowsList>{
            override fun onResponse(call: Call<ShowsList>, response: Response<ShowsList>) {
                if(response.isSuccessful){

                }
                else
                {

                }
            }

            override fun onFailure(call: Call<ShowsList>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}