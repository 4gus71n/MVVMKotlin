package com.kimboo.mvvmkotlin.ui.userlist

import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.kimboo.mvvmkotlin.R
import com.kimboo.mvvmkotlin.model.UserProfile
import com.kimboo.mvvmkotlin.ui.userdetail.UserDetailActivity
import com.kimboo.mvvmkotlin.ui.userdetail.UserDetailProfileFragment
import kotlinx.android.synthetic.main.activity_master_detail.*

/**
 * Just acting as a fragment holder of the {@link UserProfileListFragment}
 */
class MainActivity : AppCompatActivity(), MainActivityCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_master_detail)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentMasterContainer, UserProfileListFragment.newInstance(), UserProfileListFragment.TAG)
                    .commit()
        }
    }

    override fun onUserProfileClicked(view: View?, userProfile: UserProfile) {
        if (fragmentDetailContainer != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            supportFragmentManager.beginTransaction()
                    .addSharedElement(view, userProfile.email)
                    .replace(R.id.fragmentDetailContainer, UserDetailProfileFragment.newInstance(userProfile), UserDetailProfileFragment.TAG)
                    .commit()
        } else {
            // Animate transition
            val activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                    Pair(view, userProfile.email))
            startActivity(UserDetailActivity.getStartIntent(this, userProfile),
                    activityOptions.toBundle())
        }
    }
}
