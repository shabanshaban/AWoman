package com.example.awoman.feature.addNewGroup.cost.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.awoman.data.model.ModelCost
import com.example.awoman.R
import com.example.awoman.Utills.formatPrice
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_recy_report.view.*

class AdapterCost(val item: List<ModelCost>, val callBackBtnMore: CallBackBtnMore): RecyclerView.Adapter<AdapterCost.vhCost>() {

   inner class vhCost(override val containerView: View) : RecyclerView.ViewHolder(containerView),LayoutContainer {
        fun bindView(item: ModelCost){
            // DST_OFFSET
           val pricec=  formatPrice(item.price.toString().trim().toLong()*item.count.toLong())
            containerView.tv_date_item.text=item.date
            containerView.tv_item_price.text = pricec
            containerView.tv_item_group.text=item.group
            containerView.tv_title_item_recy.text=item.title
            containerView.image_more.setOnClickListener {
                callBackBtnMore.more(item)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): vhCost = vhCost(
        LayoutInflater.from(
            parent.context
        )
            .inflate(R.layout.item_recy_report, parent, false)
    )

    override fun onBindViewHolder(holder: vhCost, position: Int)=holder.bindView(item[position])

    override fun getItemCount(): Int=item.size
    interface CallBackBtnMore{

        fun more(modelCost: ModelCost)
    }
}
