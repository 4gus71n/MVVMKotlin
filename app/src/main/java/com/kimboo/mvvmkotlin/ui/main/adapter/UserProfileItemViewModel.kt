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
        return userProfile.pictureThumbnail ?: userProfile.pictureMedium ?: userProfile.pictureLarge ?: getUserProfileDefaultAvatar()
    }

    fun getUserProfileDefaultAvatar(): String {
        val userProfile = userProfile?.get()!!

        return if (userProfile.gender.contentEquals("male")) {
            "asdads"
        } else {
            "sdfsdf"
        }
    }

    companion object {
        @JvmStatic @BindingAdapter(value = "app:imageUrl", requireAll = true)
        fun loadImage(view: ImageView, imageUrl: String) {
            Glide.with(view.context).load(imageUrl).into(view);
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