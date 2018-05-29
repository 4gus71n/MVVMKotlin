package com.kimboo.mvvmkotlin.db

import android.arch.paging.DataSource
import com.kimboo.mvvmkotlin.model.UserProfile

/**
 * Created by Agustin Tomas Larghi on 28/5/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */
class UserProfileDataSourceFactory(val userDao: UserDao):  DataSource.Factory<Int, UserProfile>() {
    override fun create(): DataSource<Int, UserProfile> = UserProfileDataSource(userDao)
}