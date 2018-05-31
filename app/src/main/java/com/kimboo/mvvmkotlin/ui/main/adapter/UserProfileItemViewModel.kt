package com.kimboo.mvvmkotlin.ui.main.adapter

import android.arch.lifecycle.ViewModel
import android.databinding.BindingAdapter
import android.databinding.ObservableField
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
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

    fun getUserProfileThumbnailPic(): String {
        val userProfile = userProfile?.get()!!

        return userProfile.pictureLarge ?: userProfile.pictureMedium ?: getUserProfileDefaultAvatar()
    }

    fun getUserProfileDefaultAvatar(): String {
        val userProfile = userProfile?.get()!!

        return if (userProfile.gender.contentEquals("male")) {
            "http://s3.amazonaws.com/37assets/svn/765-default-avatar.png"
        } else {
            "https://utahstatecapitol.utah.gov/wp-content/uploads/defaultfemale.png"
        }
    }

    companion object {
        @JvmStatic @BindingAdapter(value = "app:imageUrl", requireAll = true)
        fun loadImage(view: ImageView, imageUrl: String?) {
            imageUrl?.let {
                Glide.with(view.context).load(imageUrl).into(view);
            }
        }
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