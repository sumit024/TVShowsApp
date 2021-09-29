package com.app_devs.tvshowsapp

import android.os.Parcel
import android.os.Parcelable

data class ShowsList(val tv_shows:List<Show>)
data class ShowResponse(val total:Int?,val page:Int?, val pages:Int?,val tv_shows:List<Show>?)
data class Show(val id:Int?,val name:String?, val network:String?,
                val start_date:String?,val status:String?,val image_thumbnail_path:String?):Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(name)
        parcel.writeString(network)
        parcel.writeString(start_date)
        parcel.writeString(status)
        parcel.writeString(image_thumbnail_path)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Show> {
        override fun createFromParcel(parcel: Parcel): Show {
            return Show(parcel)
        }

        override fun newArray(size: Int): Array<Show?> {
            return arrayOfNulls(size)
        }
    }
}

data class TvShow (
        val id : Int,
        val description : String,
        val country : String,
        val status : String,
        val network : String,
        val pictures : List<String>
)
data class ShowDetails(val tvShow: TvShow)