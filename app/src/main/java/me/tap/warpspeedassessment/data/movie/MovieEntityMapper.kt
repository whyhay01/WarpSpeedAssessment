package me.tap.warpspeedassessment.data.movie

import me.tap.warpspeedassessment.common.Mapper
import me.tap.warpspeedassessment.data.model.GetMoviesResponse
import me.tap.warpspeedassessment.domain.model.Movie
import javax.inject.Inject

class MovieEntityMapper @Inject constructor() : Mapper<GetMoviesResponse, Movie> {
    override fun from(cache: GetMoviesResponse): Movie =
        cache.run {
            Movie(
                title = title,
                release = release,
                movieID = movieID,
                type = type,
                moviePoster = moviePoster
            )
        }


    override fun to(data: Movie): GetMoviesResponse =
        data.run {
            GetMoviesResponse(
                title = title,
                release = release,
                movieID = movieID,
                type = type,
                moviePoster = moviePoster
            )
        }
}