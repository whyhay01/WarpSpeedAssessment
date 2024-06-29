package me.tap.warpspeedassessment.domain.movie

import me.tap.domain.common.usecase.SuspendUseCase
import me.tap.warpspeedassessment.domain.model.Movie
import javax.inject.Inject

class FetchMovieFromDBUseCase @Inject constructor(private val movieRepository: MovieRepository): SuspendUseCase<Unit, List<Movie>?>() {
    override suspend fun invoke(param: Unit): List<Movie>? {
        return movieRepository.fetchMovies()
    }
}