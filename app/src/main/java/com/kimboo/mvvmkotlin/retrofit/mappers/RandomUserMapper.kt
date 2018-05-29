package com.kimboo.mvvmkotlin.retrofit.mappers

import com.kimboo.mvvmkotlin.model.UserProfile
import com.kimboo.mvvmkotlin.retrofit.responses.ApiRandomResponse
import com.kimboo.mvvmkotlin.retrofit.responses.ApiRandomUserResponse

/**
 * Created by Agustin Tomas Larghi on 6/3/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */
class RandomUserMapper {

    fun fromServerToModel(serverResponses: ApiRandomResponse): List<UserProfile> {
        var result = ArrayList<UserProfile>()

        for (response in serverResponses.results) {
            val userProfile = fromServerToModel(response)
            result.add(userProfile)
        }

        return result
    }

    fun fromServerToModel(response: ApiRandomUserResponse): UserProfile {
        return UserProfile(response.email, response.id.idValue, response.id.idName, response.location.street,
                response.location.city, response.location.state, response.location.postcode, response.gender,
                response.dob, response.registered, response.phone, response.cell, response.picture.large,
                response.picture.medium, response.picture.thumbnail, response.nationality)
    }

}