package com.app_devs.tvshowsapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.app_devs.tvshowsapp.TvShow
import com.app_devs.tvshowsapp.databinding.ItemRowBinding
import com.app_devs.tvshowsapp.fragments.SavedShowsFragmentDirections
import com.bumptech.glide.Glide

class SavedShowsAdapter(private val context: Context,private val list:List<TvShow>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return(ShowsAdapter.MyViewHolder(
            ItemRowBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        ))

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model=list[position]
        if(holder is ShowsAdapter.MyViewHolder)
        {
            holder.binding.showName.text=model.name
            holder.binding.networkName.text=model.network
            holder.binding.startDate.text=model.start_date
            holder.binding.status.text=model.status
            Glide.with(context).load(model.image_thumbnail_path).into(holder.binding.profileImage)

        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}