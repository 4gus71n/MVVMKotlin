package com.kimboo.mvvmkotlin.ui.main

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kimboo.mvvmkotlin.MyApp
import com.kimboo.mvvmkotlin.R
import com.kimboo.mvvmkotlin.databinding.FragmentMainBinding
import com.kimboo.mvvmkotlin.di.modules.MyViewModelFactory
import javax.inject.Inject

/**
 * Created by Agustin Tomas Larghi on 3/3/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */
class MainFragment: Fragment() {

    companion object {
        var TAG: String = MainFragment.javaClass.simpleName

        fun newInstance(): MainFragment {
            val fragment = MainFragment()
            fragment.arguments = Bundle()
            return fragment
        }
    }

    @Inject
    lateinit var viewModelFactory: MyViewModelFactory

    private lateinit var fragmentMainBinding: FragmentMainBinding //Generated automatically
    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?) =
            inflater!!.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        MyApp.viewInjector.inject(this)

        fragmentMainBinding = FragmentMainBinding.bind(view!!)
        mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        fragmentMainBinding.mainViewModel = mainViewModel
    }
}