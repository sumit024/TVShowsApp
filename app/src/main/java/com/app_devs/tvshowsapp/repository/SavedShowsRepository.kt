package com.app_devs.tvshowsapp.repository

import androidx.lifecycle.LiveData
import com.app_devs.tvshowsapp.Show
import com.app_devs.tvshowsapp.TvShow
import com.app_devs.tvshowsapp.dao.ShowsDao

class SavedShowsRepository(private val dao: ShowsDao) {
    fun getAllSavedShows(): LiveData<List<Show>> = dao.getAllSavedShows()
    suspend fun insertSaveMovie(show: Show)= dao.insertSaveMovie(show)

}