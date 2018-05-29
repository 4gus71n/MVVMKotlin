package com.kimboo.mvvmkotlin.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.kimboo.mvvmkotlin.model.UserProfile

/**
 * Created by Agustin Tomas Larghi on 28/5/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */
@Dao
interface UserDao {
    // The Integer type parameter tells Room to use a PositionalDataSource
    // object, with position-based loading under the hood.
    @Query("SELECT * FROM user_profile ORDER BY id DESC")
    fun getUserProfiles(): List<UserProfile>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun storeUserProfiles(userProfiles: List<UserProfile>)
}