package com.app_devs.tvshowsapp.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.app_devs.tvshowsapp.Show
import com.app_devs.tvshowsapp.ShowDetails
import com.app_devs.tvshowsapp.ShowResponse
import com.app_devs.tvshowsapp.database.SavedShowsDataBase
import com.app_devs.tvshowsapp.repository.SavedShowsRepository
import com.app_devs.tvshowsapp.retrofit.RetroService
import com.app_devs.tvshowsapp.retrofit.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShowsViewModel(application: Application):AndroidViewModel(application) {
    val repository:SavedShowsRepository
    init {
        val dao= SavedShowsDataBase.getDatabaseInstance(application).getDao()
        repository= SavedShowsRepository(dao)
    }

    private var showResponseData:MutableLiveData<ShowResponse> = MutableLiveData()
    private var showDetailsData:MutableLiveData<ShowDetails> = MutableLiveData()

    fun getShowResponseObservable():LiveData<ShowResponse>{
        return showResponseData
    }
    fun getShowDetailsObservable():LiveData<ShowDetails>
    {
        return showDetailsData
    }

    fun getShowResponse(page:Int)
    {
        val retroService: RetroService =
                RetrofitInstance.getRetrofitInstance().create(RetroService::class.java)
        val call=retroService.getPopularShowsResponse(page)
        call.enqueue(object:Callback<ShowResponse>{
            override fun onResponse(call: Call<ShowResponse>, response: Response<ShowResponse>) {
                if(response.isSuccessful){
                    showResponseData.postValue(response.body())
                }
                else
                {
                    showResponseData.postValue(null)
                }
            }

            override fun onFailure(call: Call<ShowResponse>, t: Throwable) {
                showResponseData.postValue(null)
            }
        })

    }

    fun getShowDetails(id:Int)
    {
        val retroService: RetroService =
                RetrofitInstance.getRetrofitInstance().create(RetroService::class.java)
        val call=retroService.getShowDetails(id)
        call.enqueue(object:Callback<ShowDetails>{
            override fun onResponse(call: Call<ShowDetails>, response: Response<ShowDetails>) {
                if(response.isSuccessful){
                    Log.d("SUMIT","SUCCESS")
                   // Log.d("SUMIT",response.body().toString())
                    showDetailsData.postValue(response.body())
                }
                else
                {
                    Log.d("SUMIT","NOT SUCCESS")
                    showDetailsData.postValue(null)
                }
            }

            override fun onFailure(call: Call<ShowDetails>, t: Throwable) {
                Log.d("SUMIT","FAILED")
                showDetailsData.postValue(null)
            }
        })

    }
    fun saveShow(show: Show)= viewModelScope.launch(Dispatchers.IO) { repository.insertSaveMovie(show) }
    fun getAllNotes():LiveData<List<Show>> =  repository.getAllSavedShows()

}