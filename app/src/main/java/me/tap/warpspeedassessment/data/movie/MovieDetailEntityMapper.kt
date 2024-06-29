package me.tap.warpspeedassessment.data.movie

import me.tap.warpspeedassessment.common.Mapper
import me.tap.warpspeedassessment.data.model.MovieDetailResponse
import me.tap.warpspeedassessment.domain.model.MovieDetails
import javax.inject.Inject

class MovieDetailEntityMapper @Inject constructor() : Mapper<MovieDetailResponse, MovieDetails> {
    override fun from(cache: MovieDetailResponse): MovieDetails {
       return cache.run {
            MovieDetails(
                title,
                year,
                release,
                runTime,
                genre,
                director,
                writer,
                actors,
                description,
                language,
                country,
                moviePoster
            )
        }
    }

    override fun to(data: MovieDetails): MovieDetailResponse {
       return data.run {
            MovieDetailResponse(
                title,
                year,
                release,
                runTime,
                genre,
                director,
                writer,
                actors,
                description,
                language,
                country,
                moviePoster
            )
        }
    }
}