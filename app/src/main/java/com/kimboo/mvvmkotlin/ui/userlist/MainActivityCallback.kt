package com.kimboo.mvvmkotlin.ui.userlist

import android.view.View
import com.kimboo.mvvmkotlin.model.UserProfile

/**
 * Created by Agustin Tomas Larghi on 31/5/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */
interface MainActivityCallback {
    fun onUserProfileClicked(view: View?, userProfile: UserProfile)
}