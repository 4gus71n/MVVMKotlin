package com.kimboo.mvvmkotlin.retrofit.repositories

import com.kimboo.mvvmkotlin.extensions.DataSource
import com.kimboo.mvvmkotlin.model.UserProfile
import io.reactivex.Observable

/**
 * Created by Agustin Tomas Larghi on 27/2/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */
interface RandomUserRepository {

    fun getUserProfiles(page: Int, size: Int): Observable<DataSource<List<UserProfile>>>

    fun getUserProfile(id: String): Observable<DataSource<UserProfile>>

}