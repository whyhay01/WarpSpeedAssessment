package me.tap.warpspeedassessment.cache.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.tap.warpspeedassessment.cache.movie.MovieCacheImpl
import me.tap.warpspeedassessment.data.movie.MovieCache

@InstallIn(SingletonComponent::class)
@Module
interface CacheModule {
    @get:[Binds]
    val MovieCacheImpl.bindsMovieCache: MovieCache
}