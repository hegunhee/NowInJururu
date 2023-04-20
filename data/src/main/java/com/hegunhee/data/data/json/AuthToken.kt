package com.hegunhee.data.data.json

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthToken(
    @Json(name = "access_token") val accessToken : String,
    @Json(name = "expires_in") val expiresIn : Int,
    @Json(name = "token_type") val tokenType : String
) {
    fun getFormattedToken() : String{
        return "${tokenType.replaceFirst('b','B')} $accessToken"
    }
}