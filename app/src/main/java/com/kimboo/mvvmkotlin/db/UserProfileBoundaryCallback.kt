package com.kimboo.mvvmkotlin.db

import android.arch.paging.PagedList
import com.kimboo.mvvmkotlin.extensions.DataSourceSubscriber
import com.kimboo.mvvmkotlin.extensions.subscribe
import com.kimboo.mvvmkotlin.extensions.transformEntity
import com.kimboo.mvvmkotlin.model.UserProfile
import com.kimboo.mvvmkotlin.retrofit.api.RandomUserApi
import com.kimboo.mvvmkotlin.retrofit.mappers.serverUserProfileCollectionToModel
import io.reactivex.schedulers.Schedulers

/**
 * Created by Agustin Tomas Larghi on 28/5/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */
class UserProfileBoundaryCallback(val randomUserApi: RandomUserApi,
                                  val userDao: UserDao) : PagedList.BoundaryCallback<UserProfile>() {


    /**
     * Database returned 0 items. We should query the backend for more items.
     */
    override fun onZeroItemsLoaded() {
        randomUserApi.getUserProfiles(0, UserProfileDataSource.PAGE_SIZE)
            .transformEntity(::serverUserProfileCollectionToModel)
            .subscribeOn(Schedulers.io()) //We shouldn't write in the db over the UI thread!
            .subscribe(object: DataSourceSubscriber<List<UserProfile>>() {
                override fun onResultNext(userProfiles: List<UserProfile>) {
                    userDao.storeUserProfiles(userProfiles)
                }
            })
    }

    /**
     * User reached to the end of the list.
     */
    override fun onItemAtEndLoaded(itemAtEnd: UserProfile) {
        randomUserApi.getUserProfiles(itemAtEnd.indexPageNumber + 1, UserProfileDataSource.PAGE_SIZE)
            .transformEntity(::serverUserProfileCollectionToModel)
            .subscribeOn(Schedulers.io()) //We shouldn't write in the db over the UI thread!
            .subscribe(object: DataSourceSubscriber<List<UserProfile>>() {
                override fun onResultNext(userProfiles: List<UserProfile>) {
                    userDao.storeUserProfiles(userProfiles)
                }
            })
    }

}