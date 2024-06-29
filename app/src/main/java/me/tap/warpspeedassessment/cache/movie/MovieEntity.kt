package me.tap.warpspeedassessment.cache.movie

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movie")
data class MovieEntity(
    val title: String,
    val release: String,
    @PrimaryKey
    val movieID: String,
    val type: String,
    val moviePoster: String
)
