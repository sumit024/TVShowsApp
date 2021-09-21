package com.app_devs.tvshowsapp.retrofit

import com.app_devs.tvshowsapp.ShowsList
import retrofit2.Call
import retrofit2.http.GET

interface RetroService {

    @GET("most-popular?page=1")
    fun getPopularShows():Call<ShowsList>
}