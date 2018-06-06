package com.kimboo.mvvmkotlin.db

import android.arch.paging.DataSource
import android.arch.persistence.room.*
import com.kimboo.mvvmkotlin.model.UserProfile
import io.reactivex.Flowable

/**
 * Created by Agustin Tomas Larghi on 28/5/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */
@Dao
interface UserDao {
    @Query("SELECT * FROM user_profile")
    fun getUserProfiles(): DataSource.Factory<Int, UserProfile>

    @Query("SELECT * FROM user_profile where email = :email")
    fun getUserProfile(email: String): Flowable<UserProfile>

    /**
     * We use this query in the {@link UserProfileDataSource} to get the data paginated from the DB
     */
    @Query("SELECT * FROM user_profile LIMIT :limit OFFSET :offset")
    fun getUserProfileWithLimitAndOffset(limit: Int, offset: Int): List<UserProfile>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun storeUserProfiles(userProfiles: List<UserProfile>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun storeUserProfile(userProfile: UserProfile)

    @Update()
    fun updateProfile(userProfile: UserProfile)
}