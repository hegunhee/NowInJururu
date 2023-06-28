package com.hegunhee.feature.streamer

import org.junit.Test

class GroupByListSizeTest() {

    @Test
    fun `test element with the highest frequency`() {
        val list = listOf<String>("banana","apple","pear","banana","pear","grapes","banana")
        val groupList = list.groupBy { it }
        println("groupList : $groupList")
        val groupMaxByFrequency = groupList.maxBy { it.value.size }
        println("groupListSortedByFrequency : $groupMaxByFrequency")
    }
}