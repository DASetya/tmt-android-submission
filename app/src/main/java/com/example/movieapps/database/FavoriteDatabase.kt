package com.example.movieapps.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.movieapps.model.ResultsItem

@Database(entities = [MovieEntity::class], version = 1)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object{
        @Volatile
        private var INSTANCE: FavoriteDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): FavoriteDatabase{
            if (INSTANCE==null){
                synchronized(FavoriteDatabase::class.java){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        FavoriteDatabase::class.java,
                        "favorite_movie")
                        .build()
                }
            }
            return INSTANCE as FavoriteDatabase
        }
    }
}

