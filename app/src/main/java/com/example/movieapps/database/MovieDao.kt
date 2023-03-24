package com.example.movieapps.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie ORDER BY id ASC")
    fun getAll(): LiveData<List<MovieEntity>>

    @Query("SELECT EXISTS(SELECT * FROM movie WHERE id = :id)")
    fun isExist(id: Int) : LiveData<Boolean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: MovieEntity)

    @Delete
    fun delete(movie: MovieEntity)
}