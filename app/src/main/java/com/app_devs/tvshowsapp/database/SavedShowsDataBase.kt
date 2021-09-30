package com.app_devs.tvshowsapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app_devs.tvshowsapp.Show
import com.app_devs.tvshowsapp.dao.ShowsDao

@Database(entities = [Show::class],version = 1,exportSchema = false)
abstract class SavedShowsDataBase:RoomDatabase() {
    abstract fun getDao():ShowsDao
    companion object{
        @Volatile
        var INSTANCE: SavedShowsDataBase?=null
        fun getDatabaseInstance(context: Context): SavedShowsDataBase
        {
            val temp= INSTANCE
            if(temp!=null)
                return temp
            else
            {
                synchronized(this){
                    return Room.databaseBuilder(context, SavedShowsDataBase::class.java,"savedShows").build()
                }
            }
        }
    }
}