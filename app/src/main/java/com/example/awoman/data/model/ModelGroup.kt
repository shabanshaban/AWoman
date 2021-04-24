package com.example.awoman.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ModelGroup(  val group:String,   val price:Long ):Parcelable
