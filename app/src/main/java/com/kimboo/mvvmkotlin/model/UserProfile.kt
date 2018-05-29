package com.kimboo.mvvmkotlin.model

/**
 * Created by Agustin Tomas Larghi on 28/5/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "user_profile",
        indices = [Index(value = ["id"], unique = false)])
class UserProfile(
        val email: String,

        @PrimaryKey
        val id: String,

        val idname: String,

        val street: String,

        val city: String,

        val state: String,

        val postcode: String,

        val gender: String,

        val dob: String, //TODO Implement formatter to convert automatically to Calendar or Date

        val registered: String, //TODO Implement formatter to convert automatically to Calendar or Date

        val phone: String,

        val cell: String,

        val pictureLarge: String,

        val pictureMedium: String,

        val pictureThumbnail: String,

        val nationality: String) {
    // to be consistent w/ changing backend order, we need to keep a data like this
    var indexInResponse: Int = -1
}