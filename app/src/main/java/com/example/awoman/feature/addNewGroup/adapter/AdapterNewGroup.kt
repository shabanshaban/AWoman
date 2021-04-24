package com.example.awoman.feature.addNewGroup.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.awoman.R
import com.example.awoman.data.model.ModelGroupDb
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_recy_add_new_group.view.*

class AdapterNewGroup(val item: List<ModelGroupDb>, val callBackClickAdapterAddNewGroup: CallBackClickAdapterAddNewGroup): RecyclerView.Adapter<AdapterNewGroup.VhCost>() {

   inner class VhCost(override val containerView: View) : RecyclerView.ViewHolder(containerView),LayoutContainer {
        fun bindView(item: ModelGroupDb){
            containerView.tv_name_group_add_new_group.text=item.name
            containerView.image_edit_add_new_group.setOnClickListener { callBackClickAdapterAddNewGroup.edit(item) }
            containerView. image_delete_add_new_group.setOnClickListener { callBackClickAdapterAddNewGroup.delete(item) }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VhCost = VhCost(
        LayoutInflater.from(
            parent.context
        )
            .inflate(R.layout.item_recy_add_new_group, parent, false)
    )

    override fun onBindViewHolder(holder: VhCost, position: Int)=holder.bindView(item[position])

    override fun getItemCount(): Int=item.size
    interface CallBackClickAdapterAddNewGroup{

        fun edit(modelGroupDb: ModelGroupDb)
        fun delete(modelGroupDb: ModelGroupDb)
    }
}
