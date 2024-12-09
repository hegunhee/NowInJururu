package com.hegunhee.ui_component.style

const val LoadingMessage = "로딩중 입니다..."
const val ErrorMessage = "에러가 발생했습니다."
const val RetryMessage = "재시도"

const val RecommendOnlineMessage = "추천 생방송 채널"
const val TwitchShowMessage = "트위치 앱에서 보기"

fun followCancelMessage(streamerId: String): String {
    return "$streamerId 를 팔로우 취소하시겠습니까?"
}

const val AccuracyText = "연관순"
const val RecencyText = "최신순"

const val ShareText = "공유하기"

const val RequestText = "데이터 요청"

const val OnlineText = "온라인"
const val OfflineText = "오프라인"

object BottomSheetTitle {
    const val KakaoSearchTitle = "카카오 검색"
    const val StreamerTitle = "스트리머"
    const val TwitchSearchTitle = "스트리머 검색"
}
