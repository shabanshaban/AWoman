package com.example.awoman.feature.addNewGroup.cost

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.print.PrintAttributes
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.awoman.R
import com.example.awoman.Utills.formatPrice
import com.example.awoman.Utills.makeFile
import com.example.awoman.common.dialogAddNewCost
import com.example.awoman.data.model.ModelCost
import com.example.awoman.feature.addNewGroup.activity.AddNewGroupActivity
import com.example.awoman.feature.addNewGroup.cost.adapter.AdapterViewpager
import com.example.awoman.feature.viewModel.ViewmodelCost
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.uttampanchasara.pdfgenerator.CreatePdf
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.costome_tab.*
import kotlinx.android.synthetic.main.costome_tab.view.*
import kotlinx.android.synthetic.main.test.*
import kotlinx.android.synthetic.main.toolbar.*
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber
import java.text.DecimalFormat


class MainActivity : AppCompatActivity() {
    val tabTitle= arrayOf("لیست هزینه ها", "گزارش")
    val viewmodel: ViewmodelCost by viewModel()
    lateinit var item:ArrayList<ModelCost>
    lateinit var adapter: AdapterViewpager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test)
        init()


    }
    fun init(){
        scroolview2.scrollY=0
        item= ArrayList()
        floatingActionButton.setOnClickListener { dialogAddNewCost(this, viewmodel) }
        tv_btn_fab.setOnClickListener { dialogAddNewCost(this, viewmodel) }
        btn_manage_group.setOnClickListener { startActivity(Intent(this, AddNewGroupActivity::class.java)) }
        getCostAndSumPrice()
        setTab()


        iv_pdf_toolbar.setOnClickListener { getGroupAndCreatePdf() }
    }

    private fun getGroupAndCreatePdf(){



        val path33 = makeFile(this)
        viewmodel.getGroup().observe(this, {
            if (it.isNotEmpty()) {
                var mod = ""
                for (i in it.indices) { mod += "\n" + " گروه :  " + it[i].group + " قیمت :  " + formatPrice(it[i].price.toLong()) + "\n" }
                CreatePdf(applicationContext)
                    .setPdfName("FirstPdf")
                    .openPrintDialog(false)
                    .setContentBaseUrl(null)
                    .setPageSize(PrintAttributes.MediaSize.ISO_A4)
                    .setContent(mod + "\n")
                    .setFilePath(path33)
                    .setCallbackListener(object : CreatePdf.PdfCallbackListener {
                        override fun onFailure(s: String) {
                            Toast.makeText(this@MainActivity, "" + s, Toast.LENGTH_SHORT).show()
                        }
                        override fun onSuccess(s: String) {
                            Toast.makeText(
                                this@MainActivity,
                                "خروجی pdf با موفقیت ساخته شد",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    })
                    .create()
            } else {
                Toast.makeText(this, "شما هیچ گذارشی ندارید", Toast.LENGTH_LONG).show()
            }


        })

    }
    private fun getCostAndSumPrice(){
        var sum: Long = 0

        viewmodel.getCost().observe(this, {
            scroolview2.scrollY = 0
            item.clear()
            sum = 0
            if (it.isNotEmpty()) {

                item.addAll(it)
                for (i in item.indices) {

                    sum += (item[i].price.toLong()) * item[i].count.toLong()
                }

            } else {
                sum = 0
            }
            scroolview2.scrollY = 0
            Timber.e(item.toString())
            adapter = AdapterViewpager(this)
            viewPager2.adapter = adapter
            val pricec = DecimalFormat("###,###").format(sum)
            tv_price_money.text = "$pricec تومان "
            scroolview2.scrollY = 0

            Handler(Looper.myLooper()!!).postDelayed({
                scroolview2.scrollY = 0
            }, 800)

        })

    }
    private fun setTab( ){
        viewPager2.adapter= AdapterViewpager(this)

        tablsyout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {

                scroolview2.scrollY = 0

                if (tab?.position == 0) {
                    tabContent
                        .setTextColor(
                            ContextCompat.getColor(
                                this@MainActivity,
                                R.color.gray
                            )
                        )
                } else {


                    tabContent
                        .setTextColor(
                            ContextCompat.getColor(
                                this@MainActivity,
                                R.color.black
                            )
                        )

                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                scroolview2.scrollY = 0
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                scroolview2.scrollY = 0
            }
        })
        val tabLayoutMediator= TabLayoutMediator(tablsyout, viewPager2) { tab, position ->
            scroolview2.scrollY=0
            tab.text=tabTitle[position]
            if (position==1) {
                val vieww = layoutInflater.inflate(R.layout.costome_tab, null)
                vieww.tabContent.text = tabTitle[position]
                vieww.tabContent.compoundDrawablePadding=8
                vieww.tabContent.setTextColor(ContextCompat.getColor(this, R.color.black))
                tab.customView=vieww
            }


//
        }

        tabLayoutMediator.attach()
    }
}