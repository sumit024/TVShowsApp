package com.app_devs.tvshowsapp

data class ShowsList(val data:List<Show>)
data class ShowResponse(val total:Int?,val page:Int?, val pages:Int?,val data:Show?)
data class Show(val id:Int?,val name:String?, val network:String?, val start_date:String?,val status:String?,val image_thumbnail_path:String?)