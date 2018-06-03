package com.kimboo.mvvmkotlin.ui.edituserdetail

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.text.Editable
import com.kimboo.mvvmkotlin.model.UserProfile
import com.kimboo.mvvmkotlin.retrofit.repositories.RandomUserRepository
import javax.inject.Inject

/**
 * Created by Agustin Tomas Larghi on 5/3/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */
//If you need a context aware ViewModel you should use AndroidViewModel
class EditUserDetailViewModel @Inject constructor (val randomUserRepository: RandomUserRepository): ViewModel() {

    lateinit var user: UserProfile

    var title = ObservableField<String>()
    var titleError = ObservableField<Boolean>()

    var name = ObservableField<String>()
    var nameError = ObservableField<Boolean>()

    var lastname = ObservableField<String>()
    var lastNameError = ObservableField<Boolean>()

    var saveButtonEnabled = ObservableField<Boolean>()

    fun setUserProfile(profile: UserProfile) {
        user = profile
        //Hook the ObservableField to the DB changes
        randomUserRepository.getUserProfile(profile.email).subscribe {
            user = it
            title.set(it.title)
            title.notifyChange()

            name.set(it.name)
            name.notifyChange()

            lastname.set(it.lastname)
            lastname.notifyChange()
        }
    }

    fun onValidateTitle(editable: Editable) {
        titleError.set(editable.isNullOrBlank())
        title.set(editable.toString())
        onValidateSaveButton()
    }

    fun onValidateName(editable: Editable) {
        nameError.set(editable.isNullOrBlank())
        name.set(editable.toString())
        onValidateSaveButton()
    }

    fun onValidateLastname(editable: Editable) {
        lastNameError.set(editable.isNullOrBlank())
        lastname.set(editable.toString())
        onValidateSaveButton()
    }
    
    fun onValidateSaveButton() {
        try {
            saveButtonEnabled.set(titleError.get()!!.not().and(nameError.get()!!.not()).and(lastNameError.get()!!.not()))
        } catch (e: Exception) {
            saveButtonEnabled.set(false)
        }
        saveButtonEnabled.notifyChange()
    }

    fun onSaveClicked() {
        user.name = name.get()!!
        user.title = title.get()!!
        user.lastname = lastname.get()!!
        //Note: Using INSERTS instead of UPDATES removes the record from the PagedLive that we are using
        //in the main screen.
        randomUserRepository.updateProfile(user)
    }

}