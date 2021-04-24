package com.example.awoman.data.dataSource

import androidx.lifecycle.LiveData
import com.example.awoman.data.model.ModelCost
import com.example.awoman.data.model.ModelGroup
import com.example.awoman.data.model.ModelGroupDb
import io.reactivex.Completable


interface CostDataSource {

    fun addCost(modelCost: ModelCost): Completable

    fun deleteCost(modelCost: ModelCost): Completable
    fun deleteGroup(modelCost: ModelGroupDb): Completable
    fun deleteGroupByTitle(title: String): Completable

    fun updateCost(modelCost: ModelCost): Completable
    fun updateGroup(modelCost: ModelGroupDb): Completable

    fun getCost():LiveData<List<ModelCost>>

    fun getCountGroup():LiveData<List<ModelGroup>>
    fun addNewGroup(modelGroupDb: ModelGroupDb):Completable
    fun getGroupDb():LiveData<List<ModelGroupDb>>
    fun updateGroupCost(oldGroup:String,newGroup:String):Completable

}