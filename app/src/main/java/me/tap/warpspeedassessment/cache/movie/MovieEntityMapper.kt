package me.tap.warpspeedassessment.cache.movie

import me.tap.warpspeedassessment.common.Mapper
import me.tap.warpspeedassessment.data.model.GetMoviesResponse
import javax.inject.Inject

class MovieEntityMapper @Inject constructor() : Mapper<MovieEntity, GetMoviesResponse> {
    override fun from(cache: MovieEntity): GetMoviesResponse =
        cache.run {
            GetMoviesResponse(
                release = this.release,
                title = this.title,
                moviePoster = this.moviePoster,
                movieID = this.movieID,
                type = this.type

            )
        }

    override fun to(data: GetMoviesResponse): MovieEntity =
        data.run {
            MovieEntity(
                title = title,
                release = release,
                movieID = movieID,
                type = type,
                moviePoster = moviePoster
            )
        }
}