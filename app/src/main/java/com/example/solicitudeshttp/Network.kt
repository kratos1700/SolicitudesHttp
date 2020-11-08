package com.example.solicituthttp

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService

class Network {

    companion object{
        fun hayRed(activity:AppCompatActivity):Boolean{
            val ConnectivityManager = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = ConnectivityManager.activeNetworkInfo
            return  networkInfo!=null && networkInfo.isConnected

        }
    }

}