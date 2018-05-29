package com.kimboo.mvvmkotlin.ui.main

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kimboo.mvvmkotlin.MyApp
import com.kimboo.mvvmkotlin.R
import com.kimboo.mvvmkotlin.databinding.FragmentMainBinding
import com.kimboo.mvvmkotlin.di.modules.MyViewModelFactory
import com.kimboo.mvvmkotlin.model.UserProfile
import com.kimboo.mvvmkotlin.ui.main.adapter.UsersAdapter
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject

/**
 * Created by Agustin Tomas Larghi on 3/3/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */
class MainFragment: Fragment(), UsersAdapter.Callback {

    //region Constant variables declaration
    companion object {
        var TAG: String = MainFragment.javaClass.simpleName

        fun newInstance(): MainFragment {
            val fragment = MainFragment()
            fragment.arguments = Bundle()
            return fragment
        }
    }
    //endregion

    //region Variables declaration
    @Inject
    lateinit var viewModelFactory: MyViewModelFactory

    private lateinit var fragmentMainBinding: FragmentMainBinding //Generated automatically
    private lateinit var mainViewModel: MainViewModel
    //endregion

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_main, container, false)
    }

    private val usersAdapter = UsersAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MyApp.viewInjector.inject(this)

        fragmentMainBinding = FragmentMainBinding.bind(view!!)
        mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        fragmentMainBinding.mainViewModel = mainViewModel

        fragmentMainRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        fragmentMainRecyclerView.adapter = usersAdapter

        mainViewModel.userProfiles.subscribe {
            userList -> usersAdapter.submitList(userList)
        }
    }
    //endregion

    //region UsersAdapter.Callback implementation
    override fun onWholeLayoutClicked(userProfile: UserProfile) {
        //startActivity(UserDetailActivity.getStartIntent(context, userProfile))
    }
    //endregion
}