package com.example.awoman.data.Repository

import androidx.lifecycle.LiveData
import com.example.awoman.data.model.ModelCost
import com.example.awoman.data.dataSource.CostDataSource
import com.example.awoman.data.dataSource.LocalDataSourceCost
import com.example.awoman.data.model.ModelGroup
import com.example.awoman.data.model.ModelGroupDb
import io.reactivex.Completable

class CostRepositoryImpl (val costDataSource: CostDataSource, private val localDataSourceCost: LocalDataSourceCost): CostRepository {
    override fun addCost(modelCost: ModelCost): Completable {

        return  localDataSourceCost.addCost(modelCost)
    }

    override fun addNewGroup(modelGroupDb: ModelGroupDb): Completable {
         return localDataSourceCost.addNewGroup(modelGroupDb)
    }

    override fun getCost( ): LiveData<List<ModelCost>> =localDataSourceCost.getCost()
    override fun delete(modelCost: ModelCost): Completable {
         return localDataSourceCost.deleteCost(modelCost  )
    }

    override fun update(modelCost: ModelCost): Completable {
         return localDataSourceCost.updateCost(modelCost)
    }

    override fun updateGroup(modelGroupDb: ModelGroupDb): Completable {
       return  localDataSourceCost.updateGroup(modelGroupDb)
    }

    override fun deleteGroup(modelGroupDb: ModelGroupDb): Completable {
        return localDataSourceCost.deleteGroup(modelGroupDb)
    }

    override fun deleteGroupByTitle(title: String): Completable {
        return localDataSourceCost.deleteGroupByTitle(title)
    }

    override fun getGroup(): LiveData<List<ModelGroup>> {
      return localDataSourceCost.getCountGroup()
    }

    override fun getGroupDb(): LiveData<List<ModelGroupDb>> {
        return localDataSourceCost.getGroupDb()
    }

    override fun updateGroupCost(oldGroup: String, newGroup: String): Completable {
        return localDataSourceCost.updateGroupCost(oldGroup, newGroup)
    }
}