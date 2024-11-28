package com.hegunhee.data.api.twitch

import com.hegunhee.data.api.getMoshi
import com.hegunhee.data.api.getTwitchAuthRetrofit
import com.hegunhee.data.api.getTwitchAuthService
import com.hegunhee.data.network.TwitchAuthService
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class AuthTokenTest {

    private lateinit var sut: TwitchAuthService

    @Before
    fun initMoshiAndRetrofit() {
        sut = getTwitchAuthRetrofit(getMoshi()).getTwitchAuthService()
    }

    @Test
    fun given_whenGetAuthToken_thenReturnToken() = runBlocking {
        // Given

        // When
        val token = sut.getAuthToken()

        // Then
        Assert.assertEquals(token.tokenType, "bearer")
    }

}
