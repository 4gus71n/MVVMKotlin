package com.kimboo.mvvmkotlin.ui.userdetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kimboo.mvvmkotlin.R
import com.kimboo.mvvmkotlin.model.UserProfile

/**
 * Created by Agustin Tomas Larghi on 31/3/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */
class UserDetailActivity: AppCompatActivity() {

    //region Constant variables declartion
    companion object {
        val ARG_USER_PROFILE_BUNDLE = "arg_user_profile_bundle"

        fun getStartIntent(context: Context, userProfile: UserProfile): Intent {
            val intent = Intent(context, UserDetailActivity::class.java)
            intent.putExtra(ARG_USER_PROFILE_BUNDLE, userProfile)
            return intent
        }
    }
    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportPostponeEnterTransition()
        setContentView(R.layout.activity_no_toolbar)

        if (savedInstanceState == null) {

            if (intent.extras.containsKey(ARG_USER_PROFILE_BUNDLE)) {
                val userProfile = intent.extras.get(ARG_USER_PROFILE_BUNDLE) as UserProfile

                supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, UserDetailProfileFragment.newInstance(userProfile), UserDetailProfileFragment.TAG)
                        .commit()
            }

        }
    }

    override fun onNavigateUp(): Boolean {
        supportFinishAfterTransition()
        return super.onNavigateUp()
    }

    override fun onSupportNavigateUp(): Boolean {
        supportFinishAfterTransition()
        return super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        supportFinishAfterTransition()
    }

}