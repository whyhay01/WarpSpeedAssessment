package me.tap.warpspeedassessment.cache.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import me.tap.warpspeedassessment.cache.db.MovieDatabase.Companion.DATABASE_VERSION
import me.tap.warpspeedassessment.cache.movie.MovieDao
import me.tap.warpspeedassessment.cache.movie.MovieEntity

@Database(entities = [MovieEntity::class], version = DATABASE_VERSION )
abstract class MovieDatabase:RoomDatabase() {

    abstract val movieDao: MovieDao

    companion object {
        const val DATABASE_VERSION = 1

        private const val DATABASE_NAME = "movie_database"

        @Volatile
        private var database: MovieDatabase? = null

        fun build(
            context: Context
        ): MovieDatabase {
            return database ?: synchronized(this) {
                val databaseBuilder = Room.databaseBuilder(
                    context.applicationContext,
                    MovieDatabase::class.java,
                    DATABASE_NAME
                )
                databaseBuilder.fallbackToDestructiveMigration()
                val instance = databaseBuilder.build()
                database = instance
                instance
            }
        }
    }
}