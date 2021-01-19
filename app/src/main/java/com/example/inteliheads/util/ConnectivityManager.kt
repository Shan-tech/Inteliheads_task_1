package com.example.inteliheads.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class ConnectivityManager {

    // @SuppressLint("ServiceCast")
    fun checkConnectivity(context: Context):Boolean{
        val connectivityManager=context.getSystemService(Context.CONNECTIVITY_SERVICE)as ConnectivityManager
        val activeNetwork: NetworkInfo?=connectivityManager.activeNetworkInfo
        return if (activeNetwork?.isConnected!=null){
            activeNetwork.isConnected
        } else{
            false
        }
    }
}