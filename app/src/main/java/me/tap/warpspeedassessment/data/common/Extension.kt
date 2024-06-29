package me.tap.warpspeedassessment.data.common

import me.tap.warpspeedassessment.data.model.ApiResponse
import timber.log.Timber

/**
 * Handle and log exceptions
 */
suspend fun <T> sendRequest(action: suspend () -> T): Resource<T> = try {
    Resource.success(action())
} catch (e: Exception) {
    Timber.e(e)
    Resource.error(message = "Connection error. Try again")
}

/**
 * Returns true if the request fails as a result of an exception of if the API returns an error
 */
fun Resource<ApiResponse>.failed() = isError() || !data!!.response ?: true

/**
 * It checks the error message for a failed request first, if this is blank, the API must have been the
 * one that returned an error.
 */
fun Resource<ApiResponse>.getErrorMessage() = message.ifBlank { data?.error ?: "" }