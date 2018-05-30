package com.kimboo.mvvmkotlin.db

import android.arch.paging.PageKeyedDataSource
import com.kimboo.mvvmkotlin.model.UserProfile

/**
 * Created by Agustin Tomas Larghi on 28/5/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */
class UserProfileDataSource(val userDao: UserDao): PageKeyedDataSource<Int, UserProfile>() {

    companion object {
        const val PAGE_SIZE = 10
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, UserProfile>) {
        callback.onResult(userDao.getUserProfileWithLimitAndOffset(PAGE_SIZE, 0), 0, PAGE_SIZE)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, UserProfile>) {
        callback.onResult(userDao.getUserProfileWithLimitAndOffset(PAGE_SIZE, params.key), params.key + PAGE_SIZE)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, UserProfile>) {
        //We don't use this hook
    }
}