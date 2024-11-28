package com.hegunhee.data.utilTest

import org.junit.Assert
import org.junit.Test
import java.net.URL

class UrlDomainSplitTest {

    @Test
    fun givenUrl_WhenGetDomainName_ThenReturnDomainName() {
        // Given
        val domainName = "gameinsight.co.kr"
        val url = "http://www.gameinsight.co.kr/news/articleView.html?idxno=30985"

        // When
        val urlDomain = URL(url).host.removePrefix("www.")

        // Then
        Assert.assertEquals(urlDomain, domainName)
    }

}
