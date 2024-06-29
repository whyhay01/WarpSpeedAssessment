package me.tap.warpspeedassessment.domain.movie

import me.tap.domain.common.usecase.SuspendUseCase
import me.tap.warpspeedassessment.data.common.Resource
import me.tap.warpspeedassessment.domain.model.MovieDetails
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(private val movieRepository: MovieRepository): SuspendUseCase<String, Resource<MovieDetails>>() {

    override suspend fun invoke(param: String): Resource<MovieDetails> {
        return movieRepository.getMovieDetails(param)
    }
}