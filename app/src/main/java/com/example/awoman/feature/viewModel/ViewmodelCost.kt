package com.example.awoman.feature.viewModel

import androidx.lifecycle.LiveData
import com.example.awoman.common.BaseViewModel
import com.example.awoman.data.Repository.CostRepository
import com.example.awoman.data.model.ModelCost
import com.example.awoman.data.model.ModelGroup
import com.example.awoman.data.model.ModelGroupDb
import com.example.nikeshop.common.MyCompletableObserver
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class ViewmodelCost(val costRepository: CostRepository  ): BaseViewModel() {



    fun addCost(modelCost: ModelCost){

        costRepository.addCost(modelCost).subscribeOn(Schedulers.io())
            .subscribe(object :MyCompletableObserver(compositeDisposable){
                override fun onComplete() {

                }
            })
    }
    fun getCost( ) :LiveData<List<ModelCost>> =costRepository.getCost()
    fun delete(modelCost: ModelCost){
        costRepository.delete(modelCost).subscribeOn(Schedulers.io())
            .subscribe(object :MyCompletableObserver(compositeDisposable){
                override fun onComplete() {
                     Timber.e("delete")
                }
            })
    }
    fun deleteGroup(modelCost: ModelGroupDb){
        costRepository.deleteGroup(modelCost).subscribeOn(Schedulers.io())
            .subscribe(object :MyCompletableObserver(compositeDisposable){
                override fun onComplete() {
                     Timber.e("delete")
                }
            })
    }
    fun deleteGroupByTitle(title: String){
        costRepository.deleteGroupByTitle(title).subscribeOn(Schedulers.io())
            .subscribe(object :MyCompletableObserver(compositeDisposable){
                override fun onComplete() {
                     Timber.e("deleteAll")
                }
            })
    }
    fun getGroup():LiveData<List<ModelGroup>> =costRepository.getGroup()
    fun getGroupDb():LiveData<List<ModelGroupDb>> =costRepository.getGroupDb()
    fun addNewGroup(modelGroupDb: ModelGroupDb){

        costRepository.addNewGroup(modelGroupDb).subscribeOn(Schedulers.io())
            .subscribe(object :MyCompletableObserver(compositeDisposable){
                override fun onComplete() {
                    Timber.e("add_newGroup")
                }
            })
    }
    fun UpdateGroup(modelGroupDb: ModelGroupDb){

        costRepository.updateGroup(modelGroupDb).subscribeOn(Schedulers.io())
            .subscribe(object :MyCompletableObserver(compositeDisposable){
                override fun onComplete() {
                    Timber.e("add_newGroup")
                }
            })
    }
    fun updateCost(modelCost: ModelCost){

        costRepository.update(modelCost).subscribeOn(Schedulers.io())
            .subscribe(object :MyCompletableObserver(compositeDisposable){
                override fun onComplete() {
                    Timber.e("update_newGroup")
                }
            })
    }
    fun updateGroupCost(oldGroup:String,newGroup:String){

        costRepository.updateGroupCost(oldGroup, newGroup).subscribeOn(Schedulers.io())
            .subscribe(object :MyCompletableObserver(compositeDisposable){
                override fun onComplete() {
                    Timber.e("updateGroupCost")
                }
            })
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}