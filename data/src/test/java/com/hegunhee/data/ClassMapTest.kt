package com.hegunhee.data

/**
 * 현재 상황 유저 정보를 가지고
 * 해당 유저 정보의 일부로 apiResponse를 불러옴
 * Success -> 기존 유저정보의 profileImageUrl을 넣어줘야함
 * Failure -> 유저 정보를 내려줌
 * 이걸 리스트로 반환해야함
 * 유저 정보조차 불러오지 못했다면 빈 리스트를 보내줌
 */

class ClassMapTest {

    private fun mapTest() {
        val studentInfoList = listOf<StudentInfo>(StudentInfo(1,"",""),StudentInfo(2,"",""))
        runCatching {
            studentInfoList.map {
                val response = testApiResponse(it.studentId)
                return@map if(response.studentSubject.isNotBlank()){
                    StudentData(response.studentSubject,it.profileUrl)
                }else{
                    StudentData("",it.profileUrl)
                }
            }
        }

    }

}



data class StudentInfo(val studentId : Int,val name : String,val profileUrl : String)



data class StudentData(val studentSubject : String,val profileUrl : String)


data class StudentApiResponse(val studentSubject : String)

private fun testApiResponse(studentId : Int) : StudentApiResponse{
    return StudentApiResponse("$studentId 수학")
}
