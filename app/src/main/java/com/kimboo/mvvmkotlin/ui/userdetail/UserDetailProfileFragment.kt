package com.kimboo.mvvmkotlin.ui.userdetail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.kimboo.mvvmkotlin.MyApp
import com.kimboo.mvvmkotlin.R
import com.kimboo.mvvmkotlin.databinding.FragmentUserDetailProfileBinding
import com.kimboo.mvvmkotlin.di.modules.MyViewModelFactory
import com.kimboo.mvvmkotlin.model.UserProfile
import com.kimboo.mvvmkotlin.ui.edituserdetail.EditUserDetailActivity
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
        arguments?.let {
            val userProfile = it.get(ARG_USER_PROFILE_BUNDLE) as UserProfile
            userDetailViewModel.setUserProfile(userProfile);

            // Usually we would load the image using DataBinding, but since we need to know when the loading
            // has finished, we don't have other choice but to do it here
            Glide.with(context!!).load(userProfile.getUserProfileThumbnailPic())
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                            activity?.startPostponedEnterTransition()
                            return false
                        }

                        override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                            activity?.startPostponedEnterTransition()
                            return false
                        }
                    })
                    .into(userDetailImageView)
        }

        userDetailViewModel.uiEventOnEdit.observe(this, Observer {
            onEditButtonClicked(it)
        })
    }

    private fun onEditButtonClicked(userProfile: UserProfile?) {
        userProfile?.let {
            startActivity(EditUserDetailActivity.getStartIntent(context!!, it))
        }
    }
    //endregion
}