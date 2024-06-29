package me.tap.warpspeedassessment.domain.movie

import kotlinx.coroutines.flow.Flow
import me.tap.warpspeedassessment.data.common.Resource
import me.tap.warpspeedassessment.domain.model.Movie
import me.tap.warpspeedassessment.domain.model.MovieDetails

interface MovieRepository {

    val getMovies: Flow<List<Movie>>?

    suspend fun getMovieListByName(searchTerm: String): Resource<List<Movie>>

    suspend fun fetchMovies(): List<Movie>?

    suspend fun getMovieDetails(movieID: String): Resource<MovieDetails>
}