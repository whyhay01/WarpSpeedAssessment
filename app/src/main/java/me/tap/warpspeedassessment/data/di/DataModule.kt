package me.tap.warpspeedassessment.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.tap.warpspeedassessment.data.movie.MovieRepositoryImpl
import me.tap.warpspeedassessment.domain.movie.MovieRepository


@InstallIn(SingletonComponent::class)
@Module
interface DataModule {
    @get:[Binds]
    val MovieRepositoryImpl.bindsMovieRepository: MovieRepository
}