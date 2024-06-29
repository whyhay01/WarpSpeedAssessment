package me.tap.warpspeedassessment.cache.movie

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.tap.warpspeedassessment.data.model.GetMoviesResponse
import me.tap.warpspeedassessment.data.movie.MovieCache
import javax.inject.Inject

class MovieCacheImpl @Inject constructor(
    private val movieDao: MovieDao,
    private val mapper: MovieEntityMapper
) : MovieCache {

    override fun saveMovies(movies: List<GetMoviesResponse>): List<Long> {
        return movieDao.saveMovies(movies = mapper.mapEntityList(movies))
    }

    override fun getMovies(): Flow<List<GetMoviesResponse>>? =
        movieDao.getMovies()?.map {
            mapper.mapModelList(it)
        }

    override fun fetchMovies(): List<GetMoviesResponse>? =
        movieDao.fetchMovies()?.let {
            it.map {
                mapper.from(it)
            }
        }

    override fun deleteMovies(): Int =
        movieDao.deleteMovies()
}