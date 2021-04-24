package com.example.awoman.feature.addNewGroup.fragment

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.awoman.R
import com.example.awoman.feature.addNewGroup.cost.adapter.AdapterReport
import com.example.awoman.feature.viewModel.ViewmodelCost
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.android.synthetic.main.report_layout.*
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.collections.ArrayList

class FragmentViewpagerChart : Fragment()  {
      var    id:String=""
    private val viewModel: ViewmodelCost by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getGroupAndSetChart()

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.report_layout, container, false)




    }
    private fun setUpChart(  listEntry: ArrayList<Entry>, labels: ArrayList<String>){
        val  tfLight = Typeface.createFromAsset(requireActivity().assets, "iran.ttf")
        myChart.setUsePercentValues(true)
        myChart.isDrawHoleEnabled = false
        myChart.animateY(1400, Easing.EasingOption.EaseInOutQuad)
           val payDataSet = PieDataSet(listEntry, "")


        val colorsActive = resources.getIntArray(R.array.colorChart)
        val data =   PieData(labels, payDataSet)
        data.setValueFormatter(PercentFormatter())
        data.setValueTypeface(tfLight)
        data.setValueTextSize(14f)
        data.setValueTextColor(Color.WHITE)
        myChart.data = data
        myChart.setDescription("");
        myChart.legend.isEnabled=false
        payDataSet.setColors(colorsActive)
        myChart.animateY(3000)




    }

    private fun getGroupAndSetChart(){
        viewModel.getGroup().observe(viewLifecycleOwner, {
            recyClerView_CHart.layoutManager = LinearLayoutManager(requireActivity())
            recyClerView_CHart.adapter = AdapterReport(it)

            val colors = ArrayList<Int>()

            for (color in ColorTemplate.PASTEL_COLORS) {
                colors.add(color)
            }
            val entries = ArrayList<Entry>()
            val lables = ArrayList<String>()
            for (i in it.indices) {
                entries.add(Entry(it[i].price.toFloat(), i))
                lables.add("")

            }
            setUpChart(  entries, lables)
        })
    }

}