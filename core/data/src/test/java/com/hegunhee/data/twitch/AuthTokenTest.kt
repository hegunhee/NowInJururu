package com.hegunhee.data.twitch

import com.hegunhee.data.getMoshi
import com.hegunhee.data.getTwitchAuthRetrofit
import com.hegunhee.data.getTwitchAuthService
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
