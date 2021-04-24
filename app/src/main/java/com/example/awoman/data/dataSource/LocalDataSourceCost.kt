package com.example.awoman.data.dataSource

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.awoman.data.model.ModelCost
import com.example.awoman.data.model.ModelGroup
import com.example.awoman.data.model.ModelGroupDb
import io.reactivex.Completable


@Dao
interface LocalDataSourceCost: CostDataSource {
    @Insert
    override fun addCost(modelCost: ModelCost): Completable

    @Delete
    override fun deleteCost(modelCost: ModelCost): Completable

    @Update
    override fun updateCost(modelCost: ModelCost): Completable
    @Update
    override fun updateGroup(modelCost: ModelGroupDb): Completable

    @Query(" select * from tbl_Cost")
    override fun getCost(): LiveData<List<ModelCost>>

    @Query(" select `group` , SUM(price) as price  from tbl_Cost    GROUP BY `group` ")
    override fun getCountGroup(): LiveData<List<ModelGroup>>
    @Insert
    override fun addNewGroup(modelGroupDb: ModelGroupDb): Completable

    @Query(" select * from tbl_group")
    override fun getGroupDb(): LiveData<List<ModelGroupDb>>
    @Delete
    override fun deleteGroup(modelCost: ModelGroupDb): Completable

    @Query(" delete   from tbl_Cost where `group`=:title ")
    override fun deleteGroupByTitle(title: String): Completable

    @Query("UPDATE tbl_Cost SET `group` = :newGroup WHERE `group` = :oldGroup")
    override fun updateGroupCost(oldGroup:String,newGroup:String): Completable

}