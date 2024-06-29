package me.tap.warpspeedassessment.data.api

import me.tap.warpspeedassessment.data.model.ApiResponse
import me.tap.warpspeedassessment.data.model.GetMoviesResponse
import me.tap.warpspeedassessment.data.model.MovieDetailResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/")
    suspend fun getMovie(@Query("s") name: String): ApiResponse

    @GET("/")
    suspend fun getMovieDetail(@Query("i") movieID: String) : MovieDetailResponse
}