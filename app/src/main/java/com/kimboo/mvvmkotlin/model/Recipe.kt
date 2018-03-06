package com.kimboo.mvvmkotlin.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Agustin Tomas Larghi on 3/3/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */
class Recipe(var name: String, var description: String, var ingredients: String) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeString(ingredients)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Recipe> {
        override fun createFromParcel(parcel: Parcel): Recipe {
            return Recipe(parcel)
        }

        override fun newArray(size: Int): Array<Recipe?> {
            return arrayOfNulls(size)
        }
    }
}