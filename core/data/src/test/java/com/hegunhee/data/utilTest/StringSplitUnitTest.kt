package com.hegunhee.data.utilTest

import org.junit.Test
import java.net.URL

class StringSplitUnitTest {


    @Test
    fun `get url domain`() {
        val url = "http://www.gameinsight.co.kr/news/articleView.html?idxno=30985"
        val urlDomain = URL(url).host.removePrefix("www.") ?: ""
        println(urlDomain)
        assert(true)
    }
}