package com.hegunhee.data.api.twitch

import com.hegunhee.data.di.NetworkModule.provideTwitchAuthMoshi
import com.hegunhee.data.di.NetworkModule.provideTwitchAuthService
import com.hegunhee.data.network.TwitchAuthService
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class AuthTokenTest {

    private lateinit var sut: TwitchAuthService

    @Before
    fun initMoshiAndRetrofit() {
        sut = provideTwitchAuthService(provideTwitchAuthMoshi())
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
