package com.kimboo.mvvmkotlin.ui.main.adapter

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.kimboo.mvvmkotlin.databinding.ViewItemUserProfileBinding
import com.kimboo.mvvmkotlin.model.UserProfile

/**
 * Created by Agustin Tomas Larghi on 7/3/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */
class UsersAdapter(var callback: UsersAdapter.Callback): PagedListAdapter<UserProfile, UsersAdapter.MainItemViewHolder>(DIFF_CALLBACK),
        UserProfileItemViewModel.Callback {

    //region Diff callback implementation
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserProfile>() {
            // Concert details may have changed if reloaded from the database,
            // but ID is fixed.
            override fun areItemsTheSame(oldUserProfile: UserProfile,
                                         newUserProfile: UserProfile): Boolean =
                    oldUserProfile.email.equals(newUserProfile.email)

            override fun areContentsTheSame(oldUserProfile: UserProfile,
                                            newUserProfile: UserProfile): Boolean =
                    oldUserProfile == newUserProfile
        }
    }
    //endregion

    //region Adapter's lifecycle methods declaration
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainItemViewHolder {
        val itemMainBinding = ViewItemUserProfileBinding.inflate(LayoutInflater.from(parent!!.context))
        return MainItemViewHolder(itemMainBinding, this)
    }

    override fun onBindViewHolder(holder: MainItemViewHolder, position: Int) {
        val userProfile = getItem(position)
        if (userProfile != null) {
            holder.onBind(userProfile)
        } else {
            // Null defines a placeholder item - PagedListAdapter automatically
            // invalidates this row when the actual object is loaded from the
            // database.
            //holder.clear() TODO IMPLEMENT IT
        }
    }
    //endregion

    //region Adapter callback interface declaration

    /**
     * To communicate back to the Fragment/Activity
     */
    interface Callback {
        fun onWholeLayoutClicked(userProfile: UserProfile);
        fun onPhoneClicked(userProfile: UserProfile)
        fun onEmailClicked(userProfile: UserProfile)
    }
    //endregion

    //region UserProfileItemViewModel.Callback implementation
    override fun onWholeLayoutClicked(userProfile: UserProfile) {
        callback.onWholeLayoutClicked(userProfile)
    }

    override fun onPhoneClicked(userProfile: UserProfile) {
        callback.onPhoneClicked(userProfile)
    }

    override fun onEmailClicked(userProfile: UserProfile) {
        callback.onEmailClicked(userProfile)
    }
    //endregion

    //region ViewHolder class declaration
    class MainItemViewHolder(var itemMainBinding: ViewItemUserProfileBinding, var callback: UserProfileItemViewModel.Callback) :
            RecyclerView.ViewHolder(itemMainBinding.root) {
        fun onBind(userProfile: UserProfile) {
            itemMainBinding.userProfileItemViewModel = UserProfileItemViewModel(userProfile, callback)
            itemMainBinding.executePendingBindings()
        }
    }
    //endregion

}
