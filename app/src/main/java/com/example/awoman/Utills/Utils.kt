package com.example.awoman.Utills

import android.content.Context
import android.os.Environment
import android.view.View
import android.view.inputmethod.InputMethodManager
import java.io.File
import java.text.DecimalFormat

fun formatPrice(number:Any):String{
    return DecimalFormat("###,###").format(number )
}
fun openKeyBoard(context: Context, view: View){

    val inputMethodManager = context. getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager


    view.requestFocus();
    view.postDelayed({

        inputMethodManager.showSoftInput(view,0);
        inputMethodManager.showSoftInput(view,0);
    },200)


}
fun closeKeyBoard(context: Context, view: View){

    val inputMethodManager = context. getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager


    view.requestFocus();
    view.postDelayed({

        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0);
    },0)


}
fun makeFile(context: Context): String {
   // val folderName = "pdf"
    val file = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)
    val path = file?.path
    val newFolder = File(path  )
    if (!newFolder.exists())
        newFolder.mkdir()

    return file!!.path

}