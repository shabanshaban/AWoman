package com.example.awoman.feature.addNewGroup.fragment

import  android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.awoman.R
import com.example.awoman.common.dialogSheet
import com.example.awoman.data.model.ModelCost
import com.example.awoman.feature.addNewGroup.cost.adapter.AdapterCost
import com.example.awoman.feature.viewModel.ViewmodelCost
import kotlinx.android.synthetic.main.fragment_pager.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*


class FragmentViewpagerListCost : Fragment(), AdapterCost.CallBackBtnMore {
    var id: String = ""
    private val viewModel: ViewmodelCost by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pager, container, false)
    }

    private fun setData() {
        RecyclerviewReport.layoutManager = LinearLayoutManager(requireActivity())
        viewModel.getCost().observe(viewLifecycleOwner, { item ->
            if (item.isNotEmpty()) {
                Collections.reverse(item as List<ModelCost>)
                RecyclerviewReport.adapter = AdapterCost(item, this)
                linearLayoutEmptyStatus.visibility = View.GONE
            } else {
                linearLayoutEmptyStatus.visibility = View.VISIBLE
                RecyclerviewReport.visibility = View.GONE
            }
        })

    }


    override fun more(modelCost: ModelCost) {
        dialogSheet(requireActivity(), modelCost, viewModel)
    }
}