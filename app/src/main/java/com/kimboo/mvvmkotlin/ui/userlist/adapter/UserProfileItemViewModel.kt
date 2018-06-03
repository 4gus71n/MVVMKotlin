package com.kimboo.mvvmkotlin.ui.userlist.adapter

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

    fun onPhoneClicked(view : View?) {
        callback.onPhoneClicked(userProfile.get()!!) //TODO Check this
    }

    fun onEmailClicked(view : View?) {
        callback.onEmailClicked(userProfile.get()!!) //TODO Check this
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
        fun onPhoneClicked(userProfile: UserProfile)
        fun onEmailClicked(userProfile: UserProfile)
    }
    //endregion

}