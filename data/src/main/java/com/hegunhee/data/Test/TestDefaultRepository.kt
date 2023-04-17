package com.hegunhee.data.Test

import com.hegunhee.domain.Test.TestRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TestDefaultRepository @Inject constructor() : TestRepository {

    override fun testGetFive(): Int {
        return 5
    }
}