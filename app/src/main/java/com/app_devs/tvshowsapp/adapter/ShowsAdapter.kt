package com.app_devs.tvshowsapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.app_devs.tvshowsapp.Show
import com.app_devs.tvshowsapp.databinding.ItemRowBinding
import com.app_devs.tvshowsapp.fragments.HomeFragment
import com.app_devs.tvshowsapp.fragments.HomeFragmentDirections
import com.app_devs.tvshowsapp.fragments.SavedShowsFragmentDirections
import com.bumptech.glide.Glide

class ShowsAdapter(private val context: Context,private val showList:ArrayList<Show>, private val isHomeFragment:Boolean):RecyclerView.Adapter<RecyclerView.ViewHolder>()
{
   //var showList= mutableListOf<Show>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
       return(MyViewHolder(com.app_devs.tvshowsapp.databinding.ItemRowBinding.inflate(LayoutInflater.from(context),parent,false)))
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
           holder.itemView.setOnClickListener {
               if(isHomeFragment) {
                   val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(model)
                   Navigation.findNavController(it).navigate(action)
               }
               else
               {
                   val action= SavedShowsFragmentDirections.actionSavedShowsFragmentToDetailsFragment(model)
                   Navigation.findNavController(it).navigate(action)
               }
           }
       }
    }

    override fun getItemCount(): Int {
       return showList.size
    }
    class MyViewHolder(val binding: ItemRowBinding):RecyclerView.ViewHolder(binding.root)

}