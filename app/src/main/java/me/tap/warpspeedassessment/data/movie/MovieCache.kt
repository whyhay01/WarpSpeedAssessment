package me.tap.warpspeedassessment.data.movie

import kotlinx.coroutines.flow.Flow
import me.tap.warpspeedassessment.data.model.GetMoviesResponse

interface MovieCache {

    fun saveMovies(movies:List<GetMoviesResponse>): List<Long>

    fun getMovies(): Flow<List<GetMoviesResponse>>?

    fun fetchMovies(): List<GetMoviesResponse>?

    fun deleteMovies(): Int
}