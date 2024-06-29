package me.tap.warpspeedassessment.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serial

data class MovieDetailResponse(
    @SerializedName("Title")
    val title: String,
    @SerializedName("Year")
    val year: String,
    @SerializedName("Released")
    val release: String,
    @SerializedName("Runtime")
    val runTime: String,
    @SerializedName("Genre")
    val genre: String,
    @SerializedName("Director")
    val director: String,
    @SerializedName("Writer")
    val writer: String,
    @SerializedName("Actors")
    val actors: String,
    @SerializedName("Plot")
    val description: String,
    @SerializedName("Language")
    val language: String,
    @SerializedName("Country")
    val country: String,
    @SerializedName("Poster")
    val moviePoster: String
)
