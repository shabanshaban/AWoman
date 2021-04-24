package com.example.awoman.data.Repository

import androidx.lifecycle.LiveData
import com.example.awoman.data.model.ModelCost
import com.example.awoman.data.model.ModelGroup
import com.example.awoman.data.model.ModelGroupDb
import io.reactivex.Completable

interface CostRepository {

    fun addCost(modelCost: ModelCost):Completable
    fun addNewGroup(modelGroupDb: ModelGroupDb):Completable
    fun getCost( ):LiveData<List<ModelCost>>
    fun delete( modelCost: ModelCost):Completable
    fun update( modelCost: ModelCost):Completable
    fun updateGroup( modelGroupDb: ModelGroupDb):Completable
    fun deleteGroup( modelGroupDb: ModelGroupDb):Completable
    fun deleteGroupByTitle( title: String):Completable
    fun getGroup():LiveData<List<ModelGroup>>
    fun getGroupDb():LiveData<List<ModelGroupDb>>
    fun updateGroupCost(oldGroup:String,newGroup:String):Completable
}