package com.example.awoman.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "tbl_Cost")
@Parcelize
data class ModelCost(@PrimaryKey    var id:String ="1" , var group:String, var title:String, var date:String, var price:Long, var count:String):Parcelable
