package me.tap.warpspeedassessment.data.model

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("Search")
    val movies: List<GetMoviesResponse>?,
    @SerializedName("Response")
    val response: Boolean,
    val totalResults: String?,
    @SerializedName("Error")
    val error: String?
)
