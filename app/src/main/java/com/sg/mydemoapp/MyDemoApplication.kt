package com.sg.mydemoapp

import android.app.Application
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyDemoApplication : Application() {

    companion object {
        const val TAG = "MyDemoApplicationT"
    }

    var isNetworkConnected: Boolean = false

    override fun onCreate() {
        super.onCreate()
        registerNetworkCallback()
    }

    private fun registerNetworkCallback() {
        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .build()

        val connectivityManager = getSystemService(ConnectivityManager::class.java) as ConnectivityManager
        connectivityManager.requestNetwork(networkRequest, networkCallback)
    }

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        // network is available for use
        override fun onAvailable(network: Network) {
            Log.d(TAG, "networkCallback onAvailable:")
            isNetworkConnected = true
            super.onAvailable(network)
        }

        // Network capabilities have changed for the network
        override fun onCapabilitiesChanged(
            network: Network,
            networkCapabilities: NetworkCapabilities
        ) {
            super.onCapabilitiesChanged(network, networkCapabilities)
            val unMetered = networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_METERED)
            Log.d(TAG, "networkCallback onCapabilitiesChanged: $unMetered")
        }

        // lost network connection
        override fun onLost(network: Network) {
            Log.d(TAG, "networkCallback onLost:")
            isNetworkConnected = false
            super.onLost(network)
        }
    }
}