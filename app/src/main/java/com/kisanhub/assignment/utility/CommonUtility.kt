package com.kisanhub.assignment.utility

import android.content.Context
import android.net.ConnectivityManager

class CommonUtility(){

    companion object {
        fun isNetworkAvailable(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo.isConnected
        }
    }
}
