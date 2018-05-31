package com.kimboo.mvvmkotlin.model

/**
 * Created by Agustin Tomas Larghi on 28/5/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "user_profile",
        indices = [Index(value = ["email"], unique = false)])
data class UserProfile(
        @PrimaryKey
        val email: String,

        val id: String?,

        val idname: String?,

        val title: String,

        val name: String,

        val lastname: String,

        val street: String?,

        val city: String?,

        val state: String?,

        val postcode: String?,

        val gender: String,

        val dob: String?, //TODO Implement formatter to convert automatically to Calendar or Date

        val registered: String?, //TODO Implement formatter to convert automatically to Calendar or Date

        val phone: String?,

        val cell: String?,

        val pictureLarge: String?,

        val pictureMedium: String?,

        val pictureThumbnail: String?,

        val nationality: String?) {
    // to be consistent w/ changing backend order, we need to keep a data like this
    var indexPageNumber: Int = 0

    fun getFormattedName() = title.capitalize() + " " + name.capitalize() + " " + lastname.capitalize()
}