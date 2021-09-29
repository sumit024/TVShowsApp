package com.app_devs.tvshowsapp.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app_devs.tvshowsapp.Show
import com.app_devs.tvshowsapp.TvShow

@Dao
interface ShowsDao {

    @Query("SELECT * FROM savedShows")
     fun getAllSavedShows():LiveData<List<Show>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSaveMovie(show: Show)
}