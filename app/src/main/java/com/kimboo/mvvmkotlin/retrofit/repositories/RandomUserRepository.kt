package com.kimboo.mvvmkotlin.retrofit.repositories

import android.arch.paging.PagedList
import com.kimboo.mvvmkotlin.extensions.DataSource
import com.kimboo.mvvmkotlin.model.UserProfile
import io.reactivex.Observable

/**
 * Created by Agustin Tomas Larghi on 27/2/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */
interface RandomUserRepository {

    fun getUserProfiles(): Observable<PagedList<UserProfile>>

    fun getUserProfile(email: String): Observable<UserProfile>

    fun fetchUserProfiles(page: Int, size: Int): Observable<DataSource<List<UserProfile>>>

    fun fetchUserProfile(id: String): Observable<DataSource<UserProfile>>

    fun updateProfile(userProfile: UserProfile)

}