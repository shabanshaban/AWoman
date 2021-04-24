package com.example.awoman.feature.addNewGroup.cost.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.awoman.data.model.ModelGroup
import com.example.awoman.R
import com.example.awoman.Utills.formatPrice
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_report.view.*

class AdapterReport(val item: List<ModelGroup> ): RecyclerView.Adapter<AdapterReport.vhCost>() {

   inner class vhCost(override val containerView: View) : RecyclerView.ViewHolder(containerView),LayoutContainer {

       val colorsActive =containerView.context. resources.getIntArray(R.array.colorChart)

        fun bindView(item: ModelGroup){
            // DST_OFFSET
           val c= ColorTemplate.createColors(ColorTemplate.PASTEL_COLORS)

           val pricec=  formatPrice(item.price.toString().trim().toLong())
            containerView.tv_price_report_recy.text= "$pricec تومان "
            containerView.tv_title_report_recy.text = item.group
            containerView.circleTextview.solidColor = colorsActive[adapterPosition]


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): vhCost = vhCost(
        LayoutInflater.from(
            parent.context
        )
            .inflate(R.layout.item_report, parent, false)
    )

    override fun onBindViewHolder(holder: vhCost, position: Int)=holder.bindView(item[position])

    override fun getItemCount(): Int=item.size

}
