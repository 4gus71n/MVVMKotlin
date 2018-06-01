package com.kimboo.mvvmkotlin.ui.userdetail

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kimboo.mvvmkotlin.MyApp
import com.kimboo.mvvmkotlin.R
import com.kimboo.mvvmkotlin.databinding.FragmentUserDetailProfileBinding
import com.kimboo.mvvmkotlin.di.modules.MyViewModelFactory
import com.kimboo.mvvmkotlin.model.UserProfile
import kotlinx.android.synthetic.main.fragment_user_detail_profile.*
import javax.inject.Inject

/**
 * Created by Agustin Tomas Larghi on 31/3/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */
class UserDetailProfileFragment: Fragment() {

    //region Constant variables declaration
    companion object {
        var TAG: String = UserDetailProfileFragment.javaClass.simpleName
        val ARG_USER_PROFILE_BUNDLE = "arg_user_profile_bundle"

        fun newInstance(userProfile: UserProfile): UserDetailProfileFragment {
            val fragment = UserDetailProfileFragment()
            val bundle = Bundle()
            bundle.putParcelable(ARG_USER_PROFILE_BUNDLE, userProfile)
            fragment.arguments = bundle
            return fragment
        }
    }
    //endregion

    //region Variables declaration
    @Inject
    lateinit var viewModelFactory: MyViewModelFactory

    private lateinit var userDetailProfileBinding: FragmentUserDetailProfileBinding //Generated automatically
    private lateinit var userDetailViewModel: UserDetailViewModel
    //endregion

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            inflater!!.inflate(R.layout.fragment_user_detail_profile, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MyApp.viewInjector.inject(this)

        with (activity as AppCompatActivity) {
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setHomeButtonEnabled(true)
        }

        userDetailProfileBinding = FragmentUserDetailProfileBinding.bind(view!!)
        userDetailViewModel = ViewModelProviders.of(this, viewModelFactory).get(UserDetailViewModel::class.java)
        userDetailProfileBinding.userDetailViewModel = userDetailViewModel

        if (arguments != null) {
            val userProfile = arguments?.get(ARG_USER_PROFILE_BUNDLE) as UserProfile
            userDetailViewModel.setUserProfile(userProfile);
        }
    }
    //endregion
}