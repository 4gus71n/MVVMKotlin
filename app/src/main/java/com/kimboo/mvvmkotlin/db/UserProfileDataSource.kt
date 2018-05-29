package com.kimboo.mvvmkotlin.db

import android.arch.paging.PageKeyedDataSource
import com.kimboo.mvvmkotlin.model.UserProfile

/**
 * Created by Agustin Tomas Larghi on 28/5/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */
class UserProfileDataSource(val userDao: UserDao): PageKeyedDataSource<Int, UserProfile>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, UserProfile>) {
        callback.onResult(userDao.getUserProfileWithLimitAndOffset(50, 0), 0, 50)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, UserProfile>) {
        callback.onResult(userDao.getUserProfileWithLimitAndOffset(50, params.key), params.key + 50)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, UserProfile>) {
        //This one is not used
    }
}