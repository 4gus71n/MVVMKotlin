package com.kimboo.mvvmkotlin.db

import android.arch.paging.PagedList
import android.support.annotation.MainThread
import com.kimboo.mvvmkotlin.extensions.DataSourceSubscriber
import com.kimboo.mvvmkotlin.extensions.subscribe
import com.kimboo.mvvmkotlin.model.UserProfile
import com.kimboo.mvvmkotlin.retrofit.repositories.RandomUserRepository
import io.reactivex.schedulers.Schedulers

/**
 * Created by Agustin Tomas Larghi on 28/5/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */
class UserProfileBoundaryCallback(val userRepository: RandomUserRepository,
                                  val userDao: UserDao) : PagedList.BoundaryCallback<UserProfile>() {


    /**
     * Database returned 0 items. We should query the backend for more items.
     */
    @MainThread
    override fun onZeroItemsLoaded() {
        userRepository.getUserProfiles(0, 50)
            .subscribeOn(Schedulers.io()) //We shouldn't write in the db over the UI thread!
            .observeOn(Schedulers.io()) //We shouldn't write in the db over the UI thread!
            .subscribe(object: DataSourceSubscriber<List<UserProfile>>() {
                override fun onResultNext(userProfiles: List<UserProfile>) {
                    userDao.storeUserProfiles(userProfiles)
                }
            })
    }

    /**
     * User reached to the end of the list.
     */
    @MainThread
    override fun onItemAtEndLoaded(itemAtEnd: UserProfile) {
        userRepository.getUserProfiles(itemAtEnd.indexPageNumber + 1, 50)
                .subscribeOn(Schedulers.io()) //We shouldn't write in the db over the UI thread!
                .observeOn(Schedulers.io()) //We shouldn't write in the db over the UI thread!
                .subscribe(object: DataSourceSubscriber<List<UserProfile>>() {
                    override fun onResultNext(userProfiles: List<UserProfile>) {
                        userDao.storeUserProfiles(userProfiles)
                    }
                })
    }

}