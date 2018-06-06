package com.kimboo.mvvmkotlin.ui.userdetail

import android.arch.lifecycle.ViewModel
import android.content.Context
import android.content.Intent
import android.databinding.ObservableField
import com.kimboo.mvvmkotlin.model.UserProfile
import com.kimboo.mvvmkotlin.retrofit.repositories.RandomUserRepository
import com.kimboo.mvvmkotlin.ui.edituserdetail.EditUserDetailActivity
import javax.inject.Inject

/**
 * Created by Agustin Tomas Larghi on 5/3/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */
//If you need a context aware ViewModel you should use AndroidViewModel
class UserDetailViewModel @Inject constructor (val context: Context,
                                               val randomUserRepository: RandomUserRepository): ViewModel() {

    //Notice that the variables are read-only, but not their properties
    var userProfile = ObservableField<UserProfile>()

    fun setUserProfile(profile: UserProfile) {
        //Hook the ObservableField to the DB changes
        randomUserRepository.getUserProfile(profile.email).subscribe {
            userProfile.set(it)
            userProfile.notifyChange()
        }

        //Query the API just in case that this data is deprecated
        randomUserRepository.fetchUserProfile(profile.email)
    }

    fun onEditClicked() {
        var userProfile = userProfile.get()!!
        val startIntent = EditUserDetailActivity.getStartIntent(context, userProfile)
        startIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK;
        context.startActivity(startIntent)
    }


}