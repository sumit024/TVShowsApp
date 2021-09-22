package com.app_devs.tvshowsapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app_devs.tvshowsapp.Show
import com.app_devs.tvshowsapp.databinding.ItemRowBinding
import com.bumptech.glide.Glide

class ShowsAdapter(private val context: Context):RecyclerView.Adapter<RecyclerView.ViewHolder>()
{
    var showList= mutableListOf<Show>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
       return(MyViewHolder(ItemRowBinding.inflate(LayoutInflater.from(context),parent,false)))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model=showList[position]
       if(holder is MyViewHolder)
       {
           holder.binding.showName.text=model.name
           holder.binding.networkName.text=model.network
           holder.binding.startDate.text=model.start_date
           holder.binding.status.text=model.status
           Glide.with(context).load(model.image_thumbnail_path).into(holder.binding.profileImage)
       }
    }

    override fun getItemCount(): Int {
       return showList.size
    }
    class MyViewHolder(val binding: ItemRowBinding):RecyclerView.ViewHolder(binding.root)

}