package com.hegunhee.data


/**
 * data class의 copy함수의 경우 변경된 프로퍼티로 값이 변경되는 프로퍼티도
 * 변경되는지 테스트
 * 결과는 변경됨
 */
import org.junit.Test

class DataClassCopyTest {

    @Test
    fun `student copy change isStudent null`() {
        var student = Student("gunhee")
        println(student.isStudent)
        student = student.copy(name = null)
        println(student.toString())
        if(!student.isStudent) {
            assert(true)
        }else {
            assert(false)
        }
    }
}

data class Student(
    val name : String?,
    val isStudent : Boolean = name.isNullOrEmpty()
)