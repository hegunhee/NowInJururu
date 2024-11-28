package com.hegunhee.data.utilTest

import org.junit.Test
import java.text.SimpleDateFormat

class DateFormatUnitTest {

    @Test
    fun dateStringFormat() {
        val dateTime = "2022-03-01T01:33:27.000+09:00"
        runCatching {
            val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
            val transferFormat = SimpleDateFormat("yyyy년 MM월 dd일 HH:mm")
            val date = format.parse(dateTime)
            transferFormat.format(date)
        }.onSuccess {
            println(it)
            assert(true)
        }.onFailure {
            println(it.message)
            assert(false)
        }
    }
}