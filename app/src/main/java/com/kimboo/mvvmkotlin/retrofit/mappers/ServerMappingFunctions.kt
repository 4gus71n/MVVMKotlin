package com.kimboo.mvvmkotlin.retrofit.mappers

import com.kimboo.mvvmkotlin.model.UserProfile
import com.kimboo.mvvmkotlin.retrofit.responses.ApiRandomResponse

/**
 * Created by Agustin Tomas Larghi on 6/3/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */

fun serverUserProfileCollectionToModel(serverResponses: ApiRandomResponse): List<UserProfile> {
    var result = ArrayList<UserProfile>()

    for (response in serverResponses.results) {
        val userProfile = UserProfile(response.email, response.id.idValue, response.id.idName,
                response.name.title, response.name.firstName, response.name.lastName, response.location.street,
                response.location.city, response.location.state, response.location.postcode, response.gender,
                response.dob, response.registered, response.phone, response.cell, response.picture.large,
                response.picture.medium, response.picture.thumbnail, response.nationality)
        userProfile.indexPageNumber = serverResponses.info.page
        result.add(userProfile)
    }

    return result
}

fun serverUserProfileToModel(serverResponses: ApiRandomResponse): UserProfile {
    serverResponses.results.first().let {
        return UserProfile(it.email, it.id.idValue, it.id.idName,
                it.name.title, it.name.firstName, it.name.lastName, it.location.street,
                it.location.city, it.location.state, it.location.postcode, it.gender,
                it.dob, it.registered, it.phone, it.cell, it.picture.large,
                it.picture.medium, it.picture.thumbnail, it.nationality)
    }
}
