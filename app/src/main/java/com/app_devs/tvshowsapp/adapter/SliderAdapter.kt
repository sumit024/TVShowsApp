package com.app_devs.tvshowsapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.app_devs.tvshowsapp.R
import com.app_devs.tvshowsapp.databinding.SliderItemBinding
import com.bumptech.glide.Glide

 class SliderAdapter(val context: Context, val list:List<String>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

     class MyViewHolder(val binding: SliderItemBinding):RecyclerView.ViewHolder(binding.root)

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
         return MyViewHolder(SliderItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
     }

     override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
         if(holder is MyViewHolder)
         {
             Glide.with(context).load(list[position]).into(holder.binding.image)
         }
     }

     override fun getItemCount(): Int {
        return list.size
     }

 }