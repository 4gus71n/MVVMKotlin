package com.kimboo.mvvmkotlin.db

import android.arch.paging.DataSource
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
    @Query("SELECT * FROM user_profile")
    fun getUserProfiles(): DataSource.Factory<Int, UserProfile>

    /**
     * We use this query in the {@link UserProfileDataSource} to get the data paginated from the DB
     */
    @Query("SELECT * FROM user_profile LIMIT :limit OFFSET :offset")
    fun getUserProfileWithLimitAndOffset(limit: Int, offset: Int): List<UserProfile>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun storeUserProfiles(userProfiles: List<UserProfile>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun storeUserProfile(userProfile: UserProfile)
}