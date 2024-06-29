package me.tap.warpspeedassessment.data.model

import com.google.gson.annotations.SerializedName

data class GetMoviesResponse(
    @SerializedName("Title")
    val title: String,
    @SerializedName("Year")
    val release: String,
    @SerializedName("imdbID")
    val movieID: String,
    @SerializedName("Type")
    val type: String,
    @SerializedName("Poster")
    val moviePoster: String
)
