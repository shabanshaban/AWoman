package com.example.awoman.app

import android.app.Application
import androidx.room.Room
import com.example.awoman.data.Repository.CostRepository
import com.example.awoman.data.Repository.CostRepositoryImpl
import com.example.awoman.data.dataSource.CostDataSource
import com.example.awoman.data.dataSource.LocalDataSourceCost
import com.example.awoman.data.dataSource.RemoteCostDataSource
import com.example.awoman.data.db.AppDataBase
import com.example.awoman.feature.viewModel.ViewmodelCost
import org.koin.android.viewmodel.compat.ScopeCompat.viewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import timber.log.Timber

class App:Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
        val myModule= module {
            single { Room.databaseBuilder(this@App, AppDataBase::class.java,"db_room_app"). build()  }
            factory<CostRepository> {
                CostRepositoryImpl(RemoteCostDataSource(this@App) ,get<AppDataBase>().getDao()

                )
            }
            viewModel {ViewmodelCost(get() ) }
        }
        startKoin {

            modules(myModule)
        }
    }
}