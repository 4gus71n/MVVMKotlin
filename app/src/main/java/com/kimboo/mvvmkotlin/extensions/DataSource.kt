package com.kimboo.mvvmkotlin.extensions

import android.support.annotation.IntDef

class DataSource<MODEL>(var model: MODEL?, @param:DataSourceState @field:DataSourceState @get:DataSourceState var state: Int) {

    fun isSuccessfull(function: () -> Unit) {
        if (state == SOURCE_HTTP_SUCCESS) {
            model.let { function() }
        }
    }

    @Retention()
    @IntDef(SOURCE_HTTP_NOT_MODIFIED, SOURCE_HTTP_SUCCESS, SOURCE_DATABASE, SOURCE_MIXED)
    annotation class DataSourceState

    companion object {
        const val SOURCE_HTTP_NOT_MODIFIED = 304
        const val SOURCE_HTTP_SUCCESS = 200
        const val SOURCE_DATABASE = 666
        const val SOURCE_MIXED = 777
    }

}