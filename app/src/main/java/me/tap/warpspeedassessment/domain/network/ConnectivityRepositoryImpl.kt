package me.tap.warpspeedassessment.domain.network

import android.net.ConnectivityManager
import javax.inject.Inject

class ConnectivityRepositoryImpl @Inject constructor(private val connectivityManager: ConnectivityManager) :
    ConnectivityRepository {

    override fun isDeviceConnectedToInternet(): Boolean =
        connectivityManager.activeNetworkInfo != null
}
