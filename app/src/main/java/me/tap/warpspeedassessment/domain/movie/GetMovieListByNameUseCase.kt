package me.tap.warpspeedassessment.domain.movie

import me.tap.domain.common.usecase.SuspendUseCase
import me.tap.warpspeedassessment.data.common.Resource
import me.tap.warpspeedassessment.domain.model.Movie
import javax.inject.Inject

class GetMovieListByNameUseCase @Inject constructor(private val movieRepository: MovieRepository) :
    SuspendUseCase<String, Resource<List<Movie>>>() {
    override suspend fun invoke(param: String): Resource<List<Movie>> =
        movieRepository.getMovieListByName(searchTerm = param)
}