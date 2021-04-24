package com.example.awoman.feature.addNewGroup.cost.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.awoman.feature.addNewGroup.fragment.FragmentViewpagerChart
import com.example.awoman.feature.addNewGroup.fragment.FragmentViewpagerListCost


class AdapterViewpager(fragmentActivity: FragmentActivity    ) :
    FragmentStateAdapter(fragmentActivity) {
    private val listFragment:ArrayList<Fragment> = arrayListOf(
        FragmentViewpagerListCost()
    ,
        FragmentViewpagerChart()
    )
    override fun getItemCount(): Int {
         return 2
    }

    override fun createFragment(position: Int): Fragment {

        return listFragment[position]
    }

}