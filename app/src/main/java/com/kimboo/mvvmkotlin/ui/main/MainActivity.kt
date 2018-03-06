package com.kimboo.mvvmkotlin.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import com.kimboo.mvvmkotlin.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_no_toolbar)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, MainFragment.newInstance(), MainFragment.TAG)
                    .commit()
        }
    }

}
