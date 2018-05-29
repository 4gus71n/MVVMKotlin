package com.kimboo.mvvmkotlin.ui.main

import android.arch.lifecycle.ViewModel
import android.arch.paging.PagedList
import android.arch.paging.RxPagedListBuilder
import android.databinding.BindingAdapter
import android.databinding.ObservableField
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import com.kimboo.mvvmkotlin.db.UserDao
import com.kimboo.mvvmkotlin.db.UserProfileBoundaryCallback
import com.kimboo.mvvmkotlin.db.UserProfileDataSourceFactory
import com.kimboo.mvvmkotlin.model.UserProfile
import com.kimboo.mvvmkotlin.retrofit.repositories.RandomUserRepository
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by Agustin Tomas Larghi on 5/3/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */
//If you need a context aware ViewModel you should use AndroidViewModel
class MainViewModel @Inject constructor (val userProfileDao: UserDao, val randomUserRepository: RandomUserRepository): ViewModel() {

    //Notice that the variables are read-only, but not their properties
    val isLoading = ObservableField<Boolean>()
    val snackBarMessage = ObservableField<String>()

    val userProfiles: Flowable<PagedList<UserProfile>> = RxPagedListBuilder(
            UserProfileDataSourceFactory(userProfileDao), 50) //Is very important that the page size is the same everywhere!
            .setBoundaryCallback(UserProfileBoundaryCallback(randomUserRepository, userProfileDao))
            .buildFlowable(BackpressureStrategy.LATEST)

    //Note: The ViewModel method's signature needs to match the View callback method's signature.
    //For example, if we are hooking this method to a View's OnClickListener, we need to pass through a View parameter
    //In this case the SwipeRefresLayoutListener's onRefresh() method takes no arguments.
    fun fetchUserProfiles() {
        isLoading.set(true)

       /* randomUserRepository.getUserProfiles(1, 10)
                .subscribe(object: DataSourceSubscriber<List<UserProfile>>() {
                    override fun onResultNext(model: List<UserProfile>) {
                        onUserProfilesFetched(model)
                    }

                    override fun onError(t: Throwable?) {
                        onErrorFetchingRecipes()
                    }
                });*/
    }

    private fun onUserProfilesFetched(model: List<UserProfile>) {
        isLoading.set(false)
        //recipes.set(model)
    }

    private fun onErrorFetchingRecipes() {
        isLoading.set(false)
        snackBarMessage.set("Ups error!");
        snackBarMessage.notifyChange()
    }

    companion object {
        @JvmStatic @BindingAdapter(value = "app:showSnackbar", requireAll = true)
        fun bindSnackBar(view: View, showSnackbar: String?) {
            if (showSnackbar != null) {
                Snackbar.make(view, showSnackbar, Snackbar.LENGTH_LONG).show();
            }
        }
    }

    fun onSaveInstanceState(outState: Bundle?) {
        //recipes.get()?.let {
        //    outState?.putParcelableArray("col", it.toTypedArray())
        //}
    }

    fun onViewStateRestored(savedInstanceState: Bundle?) {
        savedInstanceState?.let {
            //recipes.set(it.getParcelableArrayList("col"))
        }
    }

}