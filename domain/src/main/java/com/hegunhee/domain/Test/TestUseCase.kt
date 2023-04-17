package com.hegunhee.domain.Test

import javax.inject.Inject


class TestUseCase @Inject constructor(private val repository: TestRepository){

    operator fun invoke() : Int{
        return repository.testGetFive()
    }
}