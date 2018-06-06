package com.kimboo.mvvmkotlin.model

/**
 * Created by Agustin Tomas Larghi on 28/5/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable

@Entity(tableName = "user_profile",
        indices = [Index(value = ["email"], unique = false)])
data class UserProfile(
        @PrimaryKey
        val email: String,

        val id: String?,

        val idname: String?,

        var title: String,

        var name: String,

        var lastname: String,

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

        val nationality: String?) : Parcelable {
    // to be consistent w/ changing backend order, we need to keep a data like this
    var indexPageNumber: Int = 0

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
        indexPageNumber = parcel.readInt()
    }

    fun getFormattedAddress() = street?.capitalize() + ", " + state?.capitalize() + ", " + city?.capitalize()

    fun getFormattedName() = title.capitalize() + " " + name.capitalize() + " " + lastname.capitalize()

    fun getUserProfileThumbnailPic(): String {
        return pictureLarge ?: pictureMedium ?: getUserProfileDefaultAvatar()
    }

    fun getUserProfileDefaultAvatar(): String {
        return if (gender.contentEquals("male")) {
            "http://s3.amazonaws.com/37assets/svn/765-default-avatar.png"
        } else {
            "https://utahstatecapitol.utah.gov/wp-content/uploads/defaultfemale.png"
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(email)
        parcel.writeString(id)
        parcel.writeString(idname)
        parcel.writeString(title)
        parcel.writeString(name)
        parcel.writeString(lastname)
        parcel.writeString(street)
        parcel.writeString(city)
        parcel.writeString(state)
        parcel.writeString(postcode)
        parcel.writeString(gender)
        parcel.writeString(dob)
        parcel.writeString(registered)
        parcel.writeString(phone)
        parcel.writeString(cell)
        parcel.writeString(pictureLarge)
        parcel.writeString(pictureMedium)
        parcel.writeString(pictureThumbnail)
        parcel.writeString(nationality)
        parcel.writeInt(indexPageNumber)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun equals(other: Any?): Boolean {
        return if (other is UserProfile) {
            arrayOf(email, title, name, lastname) contentEquals
                    arrayOf(other.email, other.title, other.name, other.lastname)
        } else {
            false
        }
    }

    companion object CREATOR : Parcelable.Creator<UserProfile> {
        override fun createFromParcel(parcel: Parcel): UserProfile {
            return UserProfile(parcel)
        }

        override fun newArray(size: Int): Array<UserProfile?> {
            return arrayOfNulls(size)
        }
    }
}