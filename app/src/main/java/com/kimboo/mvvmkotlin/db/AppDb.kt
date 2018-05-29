package com.kimboo.mvvmkotlin.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.kimboo.mvvmkotlin.model.UserProfile

/**
 * Created by Agustin Tomas Larghi on 28/5/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */
@Database(
        entities = arrayOf(UserProfile::class),
        version = 10,
        exportSchema = false
)
abstract class AppDb : RoomDatabase() {

    companion object {
        fun create(context: Context, useInMemory : Boolean): AppDb {
            val databaseBuilder = if(useInMemory) {
                Room.inMemoryDatabaseBuilder(context, AppDb::class.java)
            } else {
                Room.databaseBuilder(context, AppDb::class.java, "mvvm_example.db")
            }
            return databaseBuilder
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
        }
    }

    abstract fun users(): UserDao

}