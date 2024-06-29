package me.tap.warpspeedassessment.cache.movie

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovies(movies: List<MovieEntity>): List<Long>

    @Query("SELECT * FROM movie")
    fun fetchMovies(): List<MovieEntity>?

    @Query("SELECT * FROM movie")
    fun getMovies(): Flow<List<MovieEntity>>?

    @Query("DELETE FROM movie")
    fun deleteMovies(): Int
}