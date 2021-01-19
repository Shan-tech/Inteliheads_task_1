package com.example.inteliheads.fragment

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.provider.Settings
import android.text.BoringLayout
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.inteliheads.R
import com.example.inteliheads.adapter.ProductAdapter
import com.example.inteliheads.data.ItemInfo
import java.lang.Exception
import java.util.*


class Product : Fragment() {

    lateinit var productRecycler: RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var progressLayout: RelativeLayout
    lateinit var progressBar: ProgressBar
    lateinit var productAdapter: ProductAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_product, container, false)

        productRecycler = view.findViewById(R.id.productRecycler)
        layoutManager = LinearLayoutManager(activity)
        progressBar = view.findViewById(R.id.progressBar)
        progressLayout = view.findViewById(R.id.progressLayout)
        progressLayout.visibility = View.VISIBLE
        val itemList = arrayListOf<ItemInfo>()
        val queue = Volley.newRequestQueue(activity)
        val url = "https://run.mocky.io/v3/3db7e1e8-614f-41ba-b4ba-8d809d842d2c"

        if (com.example.inteliheads.util.ConnectivityManager()
                .checkConnectivity(activity as Context)
        ) {
            val jsonObjectRequest = object : JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                Response.Listener {
                    try {
                        val data = it.getJSONArray("data")
                        progressLayout.visibility = View.GONE
                        for (i in 0 until data.length()) {
                            val dataObj = data.getJSONObject(i)
                            for (j in 0 until data.length()) {
                                val variant = dataObj.getJSONArray("variants")
                                             // ^ data nu potu try panalam
                                val variantObj = variant.getJSONObject(j)


                                val listOfInfo = ItemInfo(
                                    dataObj.getString("name"),
                                    variantObj.getString("mrp"),
                                    variantObj.getString("quantity"),
                                    variantObj.getString("quantity_unit_name"),
                                    variantObj.getString("image_url_original")
                                )

                                itemList.add(listOfInfo)
                                productAdapter = ProductAdapter(itemList)
                                productRecycler.adapter = productAdapter
                                productRecycler.layoutManager = layoutManager

                            }
                        }


                    } catch (e: Exception) {
                        Toast.makeText(activity, "some error in volley", Toast.LENGTH_SHORT).show()
                    }
                },
                Response.ErrorListener {
                    if (activity != null) {
                        Toast.makeText(activity, "error", Toast.LENGTH_SHORT).show()
                    }
                }) {
                override fun getHeaders(): MutableMap<String, String> {
                    val headers = HashMap<String, String>()
                    headers["Content-type"] = "application/json"
                    return headers
                }
            }
            queue.add(jsonObjectRequest)
        } else {
            val dialogue = AlertDialog.Builder(activity as Context)
            dialogue.setTitle("Connectivity Error")
            dialogue.setMessage("No internet connection")
            dialogue.setPositiveButton("open settings") { text, lisener ->
                val settings = Intent(Settings.ACTION_WIRELESS_SETTINGS)
                startActivity(settings)
                activity?.finish()
            }
            dialogue.setNegativeButton("Exit") { text, listner ->
                activity?.finishAffinity() //ActivityCompat.finishAffinity(activity as Activity)
            }
            dialogue.create()
            dialogue.show()
        }
        return view
    }

}