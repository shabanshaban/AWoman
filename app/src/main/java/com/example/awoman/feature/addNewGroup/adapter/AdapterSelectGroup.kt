package com.example.awoman.feature.addNewGroup.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.awoman.R
import com.example.awoman.data.model.ModelGroupDb
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_recy_select_group.view.*


class AdapterSelectGroup(val item: List<ModelGroupDb>, val callBackTitleGroup: CallBackTitleGroup): RecyclerView.Adapter<AdapterSelectGroup.vhCost>() {
    private var title = ""
    private var selectedPosition = -1;
    private   var lastCheckedPos = 0

   inner class vhCost(override val containerView: View) : RecyclerView.ViewHolder(containerView),LayoutContainer {
        fun bindView(itemm: ModelGroupDb){
            if (selectedPosition == adapterPosition) {
                itemView.isSelected = true //using selector drawable
                containerView.Rad_all_adapter.isChecked = true
            } else {
                itemView.isSelected = false
                containerView.Rad_all_adapter.isChecked = false
            }

            containerView.Rad_all_adapter.text=itemm.name
            containerView.Rad_all_adapter.setOnClickListener {
                title=itemm.name
                callBackTitleGroup.more(itemm)
                if (selectedPosition >= 0)
                    notifyItemChanged(selectedPosition);
                selectedPosition = adapterPosition;
                notifyItemChanged(selectedPosition);
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): vhCost = vhCost(
        LayoutInflater.from(
            parent.context
        )
            .inflate(R.layout.item_recy_select_group, parent, false)
    )

    override fun onBindViewHolder(holder: vhCost, position: Int)=holder.bindView(item[position])

    override fun getItemCount(): Int=item.size
    interface CallBackTitleGroup{

        fun more(modelCost: ModelGroupDb)
    }
}
