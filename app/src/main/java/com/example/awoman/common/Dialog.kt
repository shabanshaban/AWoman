package com.example.awoman.common

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.view.Gravity
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.awoman.Utills.NumberTextWatcher
import com.example.awoman.data.model.ModelCost
import com.example.awoman.feature.viewModel.ViewmodelCost
import com.example.awoman.R
import com.example.awoman.Utills.JalaliCalendar
import com.example.awoman.Utills.closeKeyBoard
import com.example.awoman.Utills.openKeyBoard
import com.example.awoman.data.model.ModelGroupDb
import com.example.awoman.feature.addNewGroup.adapter.AdapterSelectGroup
import com.example.awoman.feature.addNewGroup.activity.AddNewGroupActivity
import kotlinx.android.synthetic.main.dialog_add_new_cost.*
import kotlinx.android.synthetic.main.dialog_add_new_group.*
import kotlinx.android.synthetic.main.dialog_add_new_group.btn_create_group_dialog_addNewGroup
import kotlinx.android.synthetic.main.dialog_delete_group.*
import kotlinx.android.synthetic.main.dialog_select_grop.*
import kotlinx.android.synthetic.main.dialog_select_grop.btn_select_group
import kotlinx.android.synthetic.main.dialog_sheet.*
import java.util.*


private var group = ""


fun baseDialog(context: Context, layout: Int): Dialog {
    val dialog = Dialog(context, R.style.Theme_Dialog)
    dialog.setCancelable(false)
    dialog.window!!.attributes.windowAnimations = R.style.DialogAnimation
    dialog.window!!.setGravity(Gravity.CENTER)
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog.setContentView(layout)
    dialog.show()
    return dialog
}
fun baseDialogSheet(context: Context, layout: Int): Dialog {
    val dialog = Dialog(context, R.style.Theme_Dialog_sheet)
    dialog.setCancelable(true)
    dialog.window!!.attributes.windowAnimations = R.style.DialogAnimation
    dialog.window!!.setGravity(Gravity.BOTTOM)
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog.setContentView(layout)
    dialog.show()
    return dialog
}
private fun createID(): String {
    return UUID.randomUUID().toString()
}
private fun getDataDialog(dialog: Dialog,context:Context,modelCost: ModelCost?): ModelCost? {
    group = dialog.tv_dialog_select_group.text.trim().toString()
    var count = dialog.ed_count_Dialog.text.trim().toString()
    val price = dialog.ed_price_dialog.text.trim().toString().replace("٬", "")
    val title = dialog.ed_title_dialog.text.trim().toString()
    val calender = Calendar.getInstance()
    val yer = calender.get(Calendar.YEAR)
    val month = calender.get(Calendar.MONTH)
    val day = calender.get(Calendar.DAY_OF_MONTH)
    if (count == "") {
        count = "1"
    }

    val miladiDate = JalaliCalendar.gDate(yer, month + 1, day + 1)
    val jalaliDate = JalaliCalendar.MiladiToJalali(miladiDate)
    val date = jalaliDate.year.toString() + "/" + jalaliDate.month + "/" + jalaliDate.day
    return if (title.isNotEmpty() && price.isNotEmpty() && group.isNotEmpty()){
        val id:String = if (modelCost?.id!=null){
            modelCost.id

        }else{
            createID()
        }
        ModelCost(id, group, title, date, price.replace(",", "").toLong() , count)
    }else{
        setErrorDialog(title, group,price,dialog, context)
        null
    }


}
private fun setErrorDialog(title:String,group:String,price:String, dialog: Dialog, context: Context) {
    when {
         title.trim().isEmpty() -> {
            dialog.ed_title_dialog.error = context.getString(R.string.place_enter_title)
        }
        price.trim().isEmpty() -> {
            dialog.ed_price_dialog.error = context.getString(R.string.place_enter_price)
        }
        group.trim().isEmpty() -> {
            dialog.frame_tv_dialog_select_group.setBackgroundColor(ContextCompat.getColor(context, R.color.purple_700))
        }
    }
}
fun dialogAddNewCost(context: Context, viewModelCost: ViewmodelCost) {
    val dialog = baseDialog(context, R.layout.dialog_add_new_cost)

    openKeyBoard(context, dialog.ed_title_dialog)
    dialog.ed_price_dialog.addTextChangedListener(NumberTextWatcher(dialog.ed_price_dialog))

    dialog.btn_add_newCost.setOnClickListener {
        val data = getDataDialog(dialog,context,null)

        if (data!=null) {
            viewModelCost.addCost( data)
            Toast.makeText(context, context.getString(R.string.message_succec_create_cost), Toast.LENGTH_LONG).show()
            dialog.dismiss()
        }

    }
    dialog.btn_cancel_add_new_cost.setOnClickListener {
        dialog.dismiss()
        closeKeyBoard(context, dialog.ed_title_dialog)
    }
    dialog.tv_dialog_select_group.setOnClickListener {
        dialogSelectGroup(context, object : CallBackSelectGroup {
            override fun nameGroup(nameGroup: String) {
                group = nameGroup
                dialog.tv_dialog_select_group.text = nameGroup
                closeKeyBoard(context, dialog.ed_title_dialog)
                dialog.frame_tv_dialog_select_group.setBackgroundColor(ContextCompat.getColor(context, R.color.white))
            }
        }, viewModelCost)

    }
    dialog.btn_manage_group.setOnClickListener {

        context.startActivity(Intent(context, AddNewGroupActivity::class.java))
    }

}
fun dialogUpdateCost(context: Context, viewmodelCost: ViewmodelCost, modelCost: ModelCost) {
    val dialog = baseDialog(context, R.layout.dialog_add_new_cost)
    dialog.tv_title_add_newCost.text = context.getString(R.string.EditeCost)
    dialog.ed_title_dialog.setText(modelCost.title)
    dialog.ed_price_dialog.setText(modelCost.price.toString())
    dialog.ed_count_Dialog.setText(modelCost.count)
    dialog.tv_dialog_select_group.text = modelCost.group
    dialog.btn_add_newCost.text = context.getString(R.string.Edite)
    dialog.ed_price_dialog.addTextChangedListener(NumberTextWatcher(dialog.ed_price_dialog))
    dialog.btn_add_newCost.setOnClickListener {
        val data = getDataDialog(dialog,context,modelCost)
        if (data!=null){
            viewmodelCost.updateCost(data)
            Toast.makeText(context, context.getString(R.string.meassage_cost_succes), Toast.LENGTH_LONG).show()
            dialog.dismiss()
        }
    }
    dialog.tv_dialog_select_group.setOnClickListener {
        dialogSelectGroup(context, object : CallBackSelectGroup {
            override fun nameGroup(nameGroup: String) {
                group = nameGroup
                dialog.tv_dialog_select_group.text = nameGroup
                closeKeyBoard(context, dialog.ed_title_dialog)
                dialog.frame_tv_dialog_select_group.setBackgroundColor(ContextCompat.getColor(context, R.color.white))
            }
        }, viewmodelCost)

    }
    dialog.btn_cancel_add_new_cost.setOnClickListener {
        dialog.dismiss()
    }

}

fun dialogSelectGroup(context: Context, callBackSelectGroup: CallBackSelectGroup, viewmodelCost: ViewmodelCost) {
    val dialog = baseDialog(context, R.layout.dialog_select_grop)
    var nameGroup = ""
    dialog.btn_cancel_selectGorp.setOnClickListener {
        dialog.dismiss()
    }

    viewmodelCost.getGroupDb().observe(context as AppCompatActivity, {
        dialog.recyGroupDialog.layoutManager = LinearLayoutManager(context)
        dialog.recyGroupDialog.adapter = AdapterSelectGroup(it, object : AdapterSelectGroup.CallBackTitleGroup {
            override fun more(modelCost: ModelGroupDb) {
                nameGroup = modelCost.name
            }
        })
    })
    dialog.btn_select_group.setOnClickListener {
        callBackSelectGroup.nameGroup(nameGroup)
        dialog.dismiss()
        dialog.cancel()
    }


}

fun dialogAddNewGroup(context: Context, viewmodelCost: ViewmodelCost) {
    val dialog = baseDialog(context, R.layout.dialog_add_new_group)
openKeyBoard(context,dialog.ed_name_newGroup_dialog_add_newGroup)
    dialog.btn_create_group_dialog_addNewGroup.setOnClickListener {
        val newNameGroup = dialog.ed_name_newGroup_dialog_add_newGroup.text.toString().trim()
        if (newNameGroup.isEmpty()) {
            dialog.ed_name_newGroup_dialog_add_newGroup.error = context.getString(R.string.not_emptyNameGroup)
        } else {

            viewmodelCost.addNewGroup(ModelGroupDb( createID(), dialog.ed_name_newGroup_dialog_add_newGroup.text.toString().trim()))
            dialog.ed_name_newGroup_dialog_add_newGroup.setText("")
            Toast.makeText(context, context.getString(R.string.message_succec_create), Toast.LENGTH_LONG).show()
        }
    }
    dialog.btn_cancel_dialog_addNewGroup.setOnClickListener {
        dialog.dismiss()
    }

}

fun dialogEditNewGroup(context: Context, viewmodelCost: ViewmodelCost, modelGroupDb: ModelGroupDb) {
    val dialog = baseDialog(context, R.layout.dialog_add_new_group)
    dialog.ed_name_newGroup_dialog_add_newGroup.setText(modelGroupDb.name.toString().trim())
    dialog.tv_title_dialog_editeGroup.text = context.getString(R.string.edite_group)
    dialog.btn_create_group_dialog_addNewGroup.text = context.getString(R.string.edite_group)
    dialog.btn_create_group_dialog_addNewGroup.setOnClickListener {

        val newNameGroup = dialog.ed_name_newGroup_dialog_add_newGroup.text.toString().trim()
        if (newNameGroup.isEmpty()) {
            dialog.ed_name_newGroup_dialog_add_newGroup.error = context.getString(R.string.not_emptyNameGroup)
        } else {
            viewmodelCost.updateGroupCost(modelGroupDb.name, newNameGroup)
            modelGroupDb.name = newNameGroup
            viewmodelCost.UpdateGroup(modelGroupDb)
            dialog.ed_name_newGroup_dialog_add_newGroup.setText("")
            Toast.makeText(context, context.getString(R.string.meassage_edite_succes), Toast.LENGTH_LONG).show()
            dialog.dismiss()
        }


    }
    dialog.btn_cancel_dialog_addNewGroup.setOnClickListener {
        dialog.dismiss()
    }

}

fun dialogDeleteNewGroup(context: Context, viewmodelCost: ViewmodelCost, modelGroupDb: ModelGroupDb) {
    val dialog = baseDialog(context, R.layout.dialog_delete_group)
    dialog.btn_delete_dialog_addnewGroup.setOnClickListener {
        viewmodelCost.deleteGroup(modelGroupDb)
        viewmodelCost.deleteGroupByTitle(modelGroupDb.name)
        Toast.makeText(context, context.getString(R.string.message_delete_Succec), Toast.LENGTH_LONG).show()
        dialog.dismiss()
    }
    dialog.btn_cancel_dialog_addNewGroup_delte.setOnClickListener {
        dialog.dismiss()
    }

}

fun dialogSheet(context: Context, modelCost: ModelCost, viewmodelCost: ViewmodelCost) {
    val dialog = baseDialogSheet(context, R.layout.dialog_sheet)


    dialog.tv_sheet_name_group.text = modelCost.group
    dialog.tv_sheet_name_title.text = modelCost.title
    dialog.btn_delete_dialog.setOnClickListener {
        dialogDeleteCostSheet(context, viewmodelCost, modelCost)
        dialog.dismiss()
    }
    dialog.lin_edit_sheet.setOnClickListener {
        dialogUpdateCost(context, viewmodelCost, modelCost)
        dialog.dismiss()
    }
    dialog.btn_cancel.setOnClickListener {
        dialog.dismiss()
    }
}
fun dialogDeleteCostSheet(context: Context, viewmodelCost: ViewmodelCost, modelGroupDb: ModelCost) {
    val dialog = baseDialog(context, R.layout.dialog_delete_cost)
    dialog.btn_delete_dialog_addnewGroup.setOnClickListener {
        viewmodelCost.delete(modelGroupDb)
        Toast.makeText(context, context.getString(R.string.message_delete_Cost_Succec), Toast.LENGTH_LONG).show()
        dialog.dismiss()

    }
    dialog.btn_cancel_dialog_addNewGroup_delte.setOnClickListener {
        dialog.dismiss()
    }

}


interface CallBackSelectGroup {
    fun nameGroup(nameGroup: String)
}
