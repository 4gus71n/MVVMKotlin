package com.kimboo.mvvmkotlin.ui.main

import com.kimboo.mvvmkotlin.model.UserProfile

/**
 * Created by Agustin Tomas Larghi on 31/5/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */
interface MainActivityCallback {
    fun onUserProfileClicked(userProfile: UserProfile)
}