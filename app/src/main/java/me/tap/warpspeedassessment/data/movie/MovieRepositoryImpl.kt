package me.tap.warpspeedassessment.data.movie

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import me.tap.data.di.IoDispatcher
import me.tap.warpspeedassessment.data.api.ApiService
import me.tap.warpspeedassessment.data.common.Resource
import me.tap.warpspeedassessment.data.common.sendRequest
import me.tap.warpspeedassessment.data.common.failed
import me.tap.warpspeedassessment.data.common.getErrorMessage
import me.tap.warpspeedassessment.data.model.GetMoviesResponse
import me.tap.warpspeedassessment.domain.model.Movie
import me.tap.warpspeedassessment.domain.model.MovieDetails
import me.tap.warpspeedassessment.domain.movie.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val movieCache: MovieCache,
    private val mapper: MovieEntityMapper,
    private val detailMapper: MovieDetailEntityMapper,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : MovieRepository {

    override val getMovies: Flow<List<Movie>>?
        get() = movieCache.getMovies()?.let {
            it.map { movieResponse ->
                mapper.mapModelList(movieResponse)
            }
        }

    override suspend fun getMovieListByName(searchTerm: String): Resource<List<Movie>> =
        withContext(dispatcher) {
            val result = sendRequest { apiService.getMovie(searchTerm) }

            if (result.failed())
                return@withContext Resource.error(message = result.getErrorMessage())

            val content = result.data?.movies

            if (content!!.isEmpty())
                return@withContext Resource.success(emptyList())

            manageData(content)
            return@withContext Resource.success(result.data?.movies.map {
                mapper.from(it)
            })
        }


    override suspend fun fetchMovies(): List<Movie>? =
        withContext(dispatcher) {
            return@withContext movieCache.fetchMovies()?.let {
                mapper.mapModelList(it)} }

    override suspend fun getMovieDetails(movieID: String): Resource<MovieDetails> =
        withContext(dispatcher){
            val result = sendRequest { apiService.getMovieDetail(movieID) }

            if (result.isError())
                return@withContext Resource.error(message = result.message)

            return@withContext Resource.success(result.data.let {
                detailMapper.from(it!!)
            })
        }



    private fun manageData(content: List<GetMoviesResponse>){
        val moviesInDb = movieCache.fetchMovies()
        if (moviesInDb != null)
            movieCache.deleteMovies()

        movieCache.saveMovies(content)

    }



}