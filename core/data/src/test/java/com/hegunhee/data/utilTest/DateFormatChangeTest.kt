package com.hegunhee.data.utilTest

import org.junit.Assert
import org.junit.Test
import java.text.SimpleDateFormat

class DateFormatChangeTest {

    @Test
    fun givenDate_whenChangeFormat_thenReturnChangedDate() {
        // Given
        val dateString = "2022-03-01T01:33:27.000+09:00"
        val formattedDateString = "2022년 03월 01일 01:33"
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
        val transferFormat = SimpleDateFormat("yyyy년 MM월 dd일 HH:mm")

        // When
        val changeFormatDate = format.parse(dateString) ?: NullPointerException()
        val changeFormatDateString = transferFormat.format(changeFormatDate)

        // Then
        Assert.assertEquals(changeFormatDateString, formattedDateString)
    }

}
