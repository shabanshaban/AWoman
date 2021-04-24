package com.example.awoman.feature.addNewGroup.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.awoman.R
import com.example.awoman.common.dialogAddNewGroup
import com.example.awoman.common.dialogDeleteNewGroup
import com.example.awoman.common.dialogEditNewGroup
import com.example.awoman.data.model.ModelGroupDb
import com.example.awoman.feature.addNewGroup.adapter.AdapterNewGroup
import com.example.awoman.feature.viewModel.ViewmodelCost
import kotlinx.android.synthetic.main.add_new_group.*
import kotlinx.android.synthetic.main.toolbar.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

class AddNewGroupActivity : AppCompatActivity() {
    val viewmodelCost:ViewmodelCost by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_new_group)


        iv_back_toolbar.setOnClickListener { finish() }
        recyNewGroup.layoutManager=LinearLayoutManager(this)

        viewmodelCost.getGroupDb().observe(this,{

            Collections.reverse(it)
            recyNewGroup.adapter= AdapterNewGroup(it,object : AdapterNewGroup.CallBackClickAdapterAddNewGroup{
                override fun edit(modelGroupDb: ModelGroupDb) {

                    dialogEditNewGroup(this@AddNewGroupActivity,viewmodelCost ,modelGroupDb )
                }

                override fun delete(modelGroupDb: ModelGroupDb) {
                    dialogDeleteNewGroup(this@AddNewGroupActivity ,viewmodelCost,modelGroupDb)
                }
            })
        })

        floatingActionButton_add_new_group.setOnClickListener { dialogAddNewGroup(this,viewmodelCost ) }
        tv_btn_fab_addNew_group.setOnClickListener { dialogAddNewGroup(this,viewmodelCost ) }
    }
}