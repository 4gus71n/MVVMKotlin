package com.kimboo.mvvmkotlin.ui.main

import android.arch.lifecycle.ViewModel
import android.databinding.BindingAdapter
import android.databinding.ObservableField
import android.support.design.widget.Snackbar
import android.view.View
import com.kimboo.mvvmkotlin.extensions.DataSourceSubscriber
import com.kimboo.mvvmkotlin.extensions.subscribe
import com.kimboo.mvvmkotlin.model.UserProfile
import com.kimboo.mvvmkotlin.retrofit.repositories.RandomUserRepository
import javax.inject.Inject

/**
 * Created by Agustin Tomas Larghi on 5/3/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */
//If you need a context aware ViewModel you should use AndroidViewModel
class MainViewModel @Inject constructor (val randomUserRepository: RandomUserRepository): ViewModel() {

    //Notice that the variables are read-only, but not their properties
    val isLoading = ObservableField<Boolean>()
    val snackBarMessage = ObservableField<String>()
    val userProfiles = randomUserRepository.getUserProfiles()


    //Note: The ViewModel method's signature needs to match the View callback method's signature.
    //For example, if we are hooking this method to a View's OnClickListener, we need to pass through a View parameter
    //In this case the SwipeRefresLayoutListener's onRefresh() method takes no arguments.
    fun fetchUserProfiles() {
        isLoading.set(true)
        randomUserRepository.fetchUserProfiles(1, 10)
            .subscribe(object: DataSourceSubscriber<List<UserProfile>>() {
                override fun onError(t: Throwable?) {
                    snackBarMessage.set(t?.localizedMessage)
                    snackBarMessage.notifyChange()
                }

                override fun onComplete() {
                    isLoading.set(false)
                }
            });
    }

    companion object {
        @JvmStatic @BindingAdapter(value = "app:showSnackbar", requireAll = true)
        fun bindSnackBar(view: View, showSnackbar: String?) {
            if (showSnackbar != null) {
                Snackbar.make(view, showSnackbar, Snackbar.LENGTH_LONG).show();
            }
        }
    }

}