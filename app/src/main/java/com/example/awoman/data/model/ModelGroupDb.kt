package com.example.awoman.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

import kotlinx.android.parcel.Parcelize

@Entity (tableName = "tbl_group")
@Parcelize
data class ModelGroupDb (@PrimaryKey val id:String, var name:String,var check:Boolean=false):Parcelable