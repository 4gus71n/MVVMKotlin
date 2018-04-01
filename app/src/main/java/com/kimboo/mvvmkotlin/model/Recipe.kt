package com.kimboo.mvvmkotlin.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Agustin Tomas Larghi on 3/3/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */
class Recipe(var name: String, var description: String, var ingredients: List<Ingredient>,
             var chefName: String, var chefId: String) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.createTypedArrayList(Ingredient),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeTypedList(ingredients)
        parcel.writeString(chefName)
        parcel.writeString(chefId)
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

    override fun equals(other: Any?): Boolean {
        return if (other != null && other is Recipe) {
            other.name.equals(name)
        } else {
            false
        }
    }

}