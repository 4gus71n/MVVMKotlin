package com.kimboo.mvvmkotlin.ui.edituserdetail

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.databinding.BindingAdapter
import android.databinding.ObservableField
import android.support.design.widget.TextInputLayout
import android.text.Editable
import com.kimboo.mvvmkotlin.R
import com.kimboo.mvvmkotlin.model.UserProfile
import com.kimboo.mvvmkotlin.retrofit.repositories.RandomUserRepository
import javax.inject.Inject

/**
 * Created by Agustin Tomas Larghi on 5/3/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */
//If you need a context aware ViewModel you should use AndroidViewModel
class EditUserDetailViewModel @Inject constructor (val context: Context, val randomUserRepository: RandomUserRepository): ViewModel() {

    lateinit var user: UserProfile

    var title = ObservableField<String>()
    var titleError = ObservableField<String>()

    var name = ObservableField<String>()
    var nameError = ObservableField<String>()

    var lastname = ObservableField<String>()
    var lastNameError = ObservableField<String>()

    var saveButtonEnabled = ObservableField<Boolean>()

    var uiEvents = MutableLiveData<Int>()

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

    companion object {
        //region UI EVENTS constants declaration
        const val PROFILE_UPDATED = 100
        //endregion

        //region DataBinding's Adapters declaration
        @JvmStatic  @BindingAdapter(value = "app:errorMessage", requireAll = true)
        fun setErrorMessage(textInputLayout: TextInputLayout, string: String?) {
            if (!string.isNullOrBlank()) {
                textInputLayout.isErrorEnabled = true
                textInputLayout.error = string
            } else {
                textInputLayout.isErrorEnabled = false
            }
        }
        //endregion
    }

    fun onValidateTitle(editable: Editable) {
        if (editable.isNullOrBlank())  {
            titleError.set(context.getString(R.string.edit_user_profile_title_error))
        } else {
            titleError.set("")
        }
        title.set(editable.toString())
        onValidateSaveButton()
    }


    fun onValidateName(editable: Editable) {
        if (editable.isNullOrBlank()) {
            nameError.set(context.getString(R.string.edit_user_profile_name_error))
        } else {
            nameError.set("")
        }
        name.set(editable.toString())
        onValidateSaveButton()
    }

    fun onValidateLastname(editable: Editable) {
        if (editable.isNullOrBlank()) {
            lastNameError.set(context.getString(R.string.edit_user_profile_last_name_error))
        } else {
            lastNameError.set("")
        }
        lastname.set(editable.toString())
        onValidateSaveButton()
    }
    
    fun onValidateSaveButton() {
        saveButtonEnabled.set(title.get().isNullOrBlank().not()
                .and(name.get().isNullOrBlank().not())
                .and(lastname.get().isNullOrBlank().not())
        )
        saveButtonEnabled.notifyChange()
    }

    fun onSaveClicked() {
        user.name = name.get()!!
        user.title = title.get()!!
        user.lastname = lastname.get()!!
        //Note: Using INSERTS instead of UPDATES removes the record from the PagedLive that we are using
        //in the main screen.
        randomUserRepository.updateProfile(user)

        uiEvents.value = PROFILE_UPDATED
    }

}