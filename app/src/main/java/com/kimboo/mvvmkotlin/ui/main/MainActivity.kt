package com.kimboo.mvvmkotlin.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import com.kimboo.mvvmkotlin.R

/**
 * Just acting as a fragment holder of the {@link MainFragment}
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_no_toolbar)

        if (savedInstanceState == null) {
            //TODO Replace with Android KTX https://developer.android.com/kotlin/ktx#ktx
            supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, MainFragment.newInstance(), MainFragment.TAG)
                    .commit()
        }
    }

}
