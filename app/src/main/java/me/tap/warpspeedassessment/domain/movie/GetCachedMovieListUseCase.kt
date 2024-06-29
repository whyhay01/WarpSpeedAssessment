package me.tap.warpspeedassessment.domain.movie

import kotlinx.coroutines.flow.Flow
import me.tap.domain.common.usecase.UseCase
import me.tap.warpspeedassessment.domain.model.Movie
import javax.inject.Inject

class GetCachedMovieListUseCase @Inject constructor(private val movieRepository: MovieRepository) :
    UseCase<Unit, Flow<List<Movie>>?>() {

    override fun invoke(param: Unit): Flow<List<Movie>>? {
        return movieRepository.getMovies
    }
}