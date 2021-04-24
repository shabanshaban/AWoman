package com.example.awoman.data.dataSource

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.awoman.data.model.ModelCost
import com.example.awoman.data.model.ModelGroup
import com.example.awoman.data.model.ModelGroupDb
import io.reactivex.Completable

class RemoteCostDataSource(context: Context) : CostDataSource {

    override fun addCost(modelCost: ModelCost): Completable {
        TODO("Not yet implemented")
    }

    override fun deleteCost(modelCost: ModelCost): Completable {
        TODO("Not yet implemented")
    }

    override fun deleteGroup(modelCost: ModelGroupDb): Completable {
        TODO("Not yet implemented")
    }

    override fun deleteGroupByTitle(title: String): Completable {
        TODO("Not yet implemented")
    }

    override fun updateCost(modelCost: ModelCost): Completable {
        TODO("Not yet implemented")
    }

    override fun updateGroup(modelCost: ModelGroupDb): Completable {
        TODO("Not yet implemented")
    }

    override fun getCost(): LiveData<List<ModelCost>> {
        TODO("Not yet implemented")
    }

    override fun getCountGroup(): LiveData<List<ModelGroup>> {
        TODO("Not yet implemented")
    }

    override fun addNewGroup(modelGroupDb: ModelGroupDb): Completable {
        TODO("Not yet implemented")
    }

    override fun getGroupDb(): LiveData<List<ModelGroupDb>> {
        TODO("Not yet implemented")
    }

    override fun updateGroupCost(oldGroup: String, newGroup: String): Completable {
        TODO("Not yet implemented")
    }


}