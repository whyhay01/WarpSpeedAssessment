package me.tap.warpspeedassessment.cache.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.tap.warpspeedassessment.cache.db.MovieDatabase
import me.tap.warpspeedassessment.cache.movie.MovieDao
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @[Provides Singleton]
    fun provideDatabase(@ApplicationContext context: Context): MovieDatabase =
        MovieDatabase.build(context)


    @[Provides Singleton]
    fun provideMovieDao(movieDatabase: MovieDatabase): MovieDao = movieDatabase.movieDao
}