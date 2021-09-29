package com.app_devs.tvshowsapp.retrofit

import com.app_devs.tvshowsapp.ShowDetails
import com.app_devs.tvshowsapp.ShowResponse
import com.app_devs.tvshowsapp.ShowsList
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {

//    @GET("most-popular")
//    fun getPopularShows(@Query("page") page:Int):Call<ShowsList>

    @GET("most-popular")
    fun getPopularShowsResponse(@Query("page") page:Int):Call<ShowResponse>
    @GET("show-details")
    fun getShowDetails(@Query("q") id:Int):Call<ShowDetails>
}