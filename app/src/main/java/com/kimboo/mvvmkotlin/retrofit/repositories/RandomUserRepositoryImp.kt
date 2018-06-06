package com.kimboo.mvvmkotlin.retrofit.repositories

import android.arch.paging.PagedList
import android.arch.paging.RxPagedListBuilder
import android.support.annotation.MainThread
import com.kimboo.mvvmkotlin.db.UserDao
import com.kimboo.mvvmkotlin.db.UserProfileBoundaryCallback
import com.kimboo.mvvmkotlin.extensions.DataSource
import com.kimboo.mvvmkotlin.extensions.transformEntity
import com.kimboo.mvvmkotlin.model.UserProfile
import com.kimboo.mvvmkotlin.retrofit.api.RandomUserApi
import com.kimboo.mvvmkotlin.retrofit.mappers.serverUserProfileCollectionToModel
import com.kimboo.mvvmkotlin.retrofit.mappers.serverUserProfileToModel
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Agustin Tomas Larghi on 27/2/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */
class RandomUserRepositoryImp(val randomUserApi: RandomUserApi, val userDao: UserDao): RandomUserRepository {

    @MainThread
    override fun getUserProfiles(): Observable<PagedList<UserProfile>> =
        RxPagedListBuilder(userDao.getUserProfiles(), 50)
                .setBoundaryCallback(UserProfileBoundaryCallback(randomUserApi, userDao))
                .buildObservable()


    override fun getUserProfile(email: String) = userDao.getUserProfile(email).toObservable()

    override fun fetchUserProfiles(page: Int, size: Int): Observable<DataSource<List<UserProfile>>> {
        return randomUserApi.getUserProfiles(page, size)
            .subscribeOn(Schedulers.io())
            .transformEntity(::serverUserProfileCollectionToModel)
            .doOnNext {
                it.isSuccessfull{
                    userDao.storeUserProfiles(it.model!!)
                }
            }
    }

    override fun updateProfile(userProfile: UserProfile) {
        userDao.updateProfile(userProfile)
    }

    override fun fetchUserProfile(email: String): Observable<DataSource<UserProfile>> {
        return randomUserApi.getUserProfile(email)
            .subscribeOn(Schedulers.io())
            .transformEntity(::serverUserProfileToModel)
            .doOnNext {
                it.isSuccessfull{
                    userDao.storeUserProfile(it.model!!)
                }
            }
    }
}