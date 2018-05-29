package com.kimboo.mvvmkotlin.ui.main.adapter

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.view.View
import com.kimboo.mvvmkotlin.model.UserProfile

/**
 * Created by Agustin Tomas Larghi on 7/3/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */
class UserProfileItemViewModel(var callback: Callback): ViewModel() {

    //region Variables declaration
    val userProfile = ObservableField<UserProfile>()
    //endregion

    //region ViewModel lifecycle methods declaration
    constructor(userProfile: UserProfile, callback: Callback) : this(callback) {
        this.userProfile.set(userProfile)
    }
    //endregion

    //region Public methods declaration
    fun onWholeLayoutClicked(view : View?) {
        callback.onWholeLayoutClicked(userProfile.get()!!) //TODO Check this
    }
    //endregion

    //region Callback inferace declaration

    /**
     * To communicate back to the {@link UsersAdapter}
     */
    interface Callback {
        fun onWholeLayoutClicked(userProfile: UserProfile)
    }
    //endregion

}