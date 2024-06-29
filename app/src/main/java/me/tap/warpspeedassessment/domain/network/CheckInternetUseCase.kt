package me.tap.warpspeedassessment.domain.network

import me.tap.domain.common.usecase.UseCase
import javax.inject.Inject

class CheckInternetUseCase @Inject constructor(private val connectivityRepository: ConnectivityRepository) :
    UseCase<Unit, Boolean>() {
    override fun invoke(param: Unit): Boolean = connectivityRepository.isDeviceConnectedToInternet()
}