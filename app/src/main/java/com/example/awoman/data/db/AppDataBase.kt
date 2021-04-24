package com.example.awoman.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.awoman.data.dataSource.LocalDataSourceCost
import com.example.awoman.data.model.ModelCost
import com.example.awoman.data.model.ModelGroupDb

@Database(entities = [(ModelCost::class),(ModelGroupDb::class)], version = 2, exportSchema = false)
abstract class AppDataBase :RoomDatabase(){

    abstract fun getDao():LocalDataSourceCost

}