package com.kimboo.mvvmkotlin.retrofit.repositories

import com.kimboo.mvvmkotlin.extensions.DataSource
import com.kimboo.mvvmkotlin.extensions.transformEntity
import com.kimboo.mvvmkotlin.model.UserProfile
import com.kimboo.mvvmkotlin.retrofit.api.RandomUserApi
import com.kimboo.mvvmkotlin.retrofit.mappers.RandomUserMapper
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Agustin Tomas Larghi on 27/2/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */
class RandomUserRepositoryImp(val randomUserApi: RandomUserApi): RandomUserRepository {

    override fun getUserProfiles(page: Int, size: Int): Observable<DataSource<List<UserProfile>>> {
        return randomUserApi.getUserProfiles(page, size)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .transformEntity(RandomUserMapper()::fromServerToModel)
    }

    override fun getUserProfile(id: String): Observable<DataSource<UserProfile>> {
        return Observable.empty()
        /*return randomUserApi.getUserProfile(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .transformEntity(RandomUserMapper()::fromServerToModel)*/
    }
}