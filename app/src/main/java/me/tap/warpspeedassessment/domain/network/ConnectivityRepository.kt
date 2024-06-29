package me.tap.warpspeedassessment.domain.network

interface ConnectivityRepository {

    fun isDeviceConnectedToInternet(): Boolean
}