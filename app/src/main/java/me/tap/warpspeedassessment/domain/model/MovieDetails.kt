package me.tap.warpspeedassessment.domain.model

data class MovieDetails(
    val title: String,
    val year: String,
    val release: String,
    val runTime: String,
    val genre: String,
    val director: String,
    val writer: String,
    val actors: String,
    val description: String,
    val language: String,
    val country: String,
    val moviePoster: String
)
