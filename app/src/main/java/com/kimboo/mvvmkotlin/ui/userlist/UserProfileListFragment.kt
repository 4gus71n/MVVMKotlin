package com.kimboo.mvvmkotlin.ui.userlist

import android.Manifest
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kimboo.mvvmkotlin.MyApp
import com.kimboo.mvvmkotlin.R
import com.kimboo.mvvmkotlin.databinding.FragmentUserProfileListBinding
import com.kimboo.mvvmkotlin.di.modules.MyViewModelFactory
import com.kimboo.mvvmkotlin.model.UserProfile
import com.kimboo.mvvmkotlin.ui.userlist.adapter.UsersAdapter
import kotlinx.android.synthetic.main.fragment_user_profile_list.*
import javax.inject.Inject



/**
 * Created by Agustin Tomas Larghi on 3/3/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */
class UserProfileListFragment: Fragment(), UsersAdapter.Callback {

    //region Constant variables declaration
    companion object {
        var TAG: String = UserProfileListFragment.javaClass.simpleName

        fun newInstance(): UserProfileListFragment {
            val fragment = UserProfileListFragment()
            fragment.arguments = Bundle()
            return fragment
        }
    }
    //endregion

    //region Variables declaration
    @Inject
    lateinit var viewModelFactory: MyViewModelFactory

    private lateinit var fragmentUserProfileListBinding: FragmentUserProfileListBinding //Generated automatically
    private lateinit var userProfileListViewModel: UserProfileListViewModel

    private val usersAdapter = UsersAdapter(this)
    //endregion

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_user_profile_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MyApp.viewInjector.inject(this)

        fragmentUserProfileListBinding = FragmentUserProfileListBinding.bind(view!!)
        userProfileListViewModel = ViewModelProviders.of(this, viewModelFactory).get(UserProfileListViewModel::class.java)
        fragmentUserProfileListBinding.userProfileListViewModel = userProfileListViewModel

        fragmentMainRecyclerView.layoutManager = LinearLayoutManager(context)
        fragmentMainRecyclerView.adapter = usersAdapter
        fragmentMainRecyclerView.setHasFixedSize(true)

        //Here we are listening for Room's flowable changes
        userProfileListViewModel.userProfiles.subscribe {
            flowableList -> usersAdapter.submitList(flowableList)
        }

        userProfileListViewModel.fetchUserProfiles()
    }
    //endregion

    //region UsersAdapter.Callback implementation
    override fun onWholeLayoutClicked(userProfile: UserProfile) {
        if (activity is MainActivityCallback) {
            with (activity as MainActivityCallback) {
                onUserProfileClicked(userProfile)
            }
        }
    }

    override fun onPhoneClicked(userProfile: UserProfile) {
        val permissionCheck = ContextCompat.checkSelfPermission(context!!, Manifest.permission.CALL_PHONE)
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity!!, arrayOf<String>(Manifest.permission.CALL_PHONE), 123)
        } else {
            startActivity(Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:" + userProfile.phone)))
        }
    }

    override fun onEmailClicked(userProfile: UserProfile) {
        val emailIntent = Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto",userProfile.email, null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
        startActivity(Intent.createChooser(emailIntent, "Send email..."));
    }
    //endregion
}