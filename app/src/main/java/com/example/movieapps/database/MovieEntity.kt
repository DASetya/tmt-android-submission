package com.example.movieapps.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity("movie")
data class MovieEntity(

    @ColumnInfo("overview")
    val overview: String? = null,

    @ColumnInfo("original_title")
    val originalTitle: String? = null,

    @ColumnInfo("title")
    val title: String? = null,

    @ColumnInfo("poster_path")
    val posterPath: String? = null,

    @ColumnInfo("backdrop_path")
    val backdropPath: String? = null,

    @ColumnInfo("release_date")
    val releaseDate: String? = null,

    @ColumnInfo("vote_average")
    val voteAverage: Double? = null,

    @PrimaryKey
    @ColumnInfo("id")
    val id: Int? = null,
) : Parcelable