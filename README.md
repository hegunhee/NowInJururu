# NowInJururu  
## 소개  
이세계 아이돌 주르르의 현재 방송 정보와 각종 검색 결과를 알려주는 앱이었으나
트위치 스트리머 검색 및 카카오 검색을 하는 앱입니다.
트위치 코리아 철수 이후 SOOP 혹은 치지직 검색도 추가할 예정입니다.
## 앱 모식도  
본 프로젝트는 multi-module 구조이며 각 feature마다 모듈의 형태로 구성되어있습니다.  
엉클 밥의 클린아키텍쳐를 차용하였습니다.
![image](https://github.com/hegunhee/NowInJururu/assets/57277631/c743cba0-95ed-4077-8759-de86c71933d3) 

## 기술 정보  
- Retrofit  
  트위치 api를 통해 현재 방송정보, 스트리머 정보를 불러와야하기때문에  
  Retrofit을 사용중입니다.  
- Hilt  
  의존성 주입을 사용하여 종속성이 감소하여 보다 유연하고 테스트에 용이합니다.  
  멀티 모듈 프로젝트에서 의존성을 쉽게 주입 가능합니다.  
- VersionCatalog + gradle convention plugins
  VersionCatalog로 외부 라이브러리를 한 toml파일에서 관리합니다.  
  멀티모듈로 인해 동일한 gradle설정을 여러번 해야되는 수고로움을 덜어줍니다.
- Paging3 (compose도 적용)
  많은 양의 데이터를 효율적으로 분산시키기 위해 Paging3를 사용했습니다.
- Jetpack Compose
  선언형 UI인 Jetpack Compose를 사용했습니다.
  직관적이며 코드 재사용률이 높습니다. 보다 쉽게 UI를 꾸밀 수 있습니다.

## 비즈니스 로직 설명  
[api 명세](https://github.com/hegunhee/NowInJururu/issues/5)  
[Json/Entity 명세](https://github.com/hegunhee/NowInJururu/issues/4)  
## Issues  
- 특정 앱에 대한 검색을 하려면 그 앱의 패키지명을 manifest에 등록해야함(Android11(API30) 이상을 타게팅하는 앱의경우)  
[PackageInfo](https://github.com/hegunhee/NowInJururu/issues/9)  

- interceptor 함수 내에서 트위치 토큰을 불러오기(runBlocking)  
(https://github.com/hegunhee/NowInJururu/issues/55)  

- 여러 스트리머의 현재 방송 정보를 불러올 때 주의해야할점  
(https://github.com/hegunhee/NowInJururu/issues/15)

## 진척도  
먼저 업무는 칸반에 등록해서 작업하며 하나의 주제가 크다면 서브 이슈로 분리해서 작업합니다.  
만약 주제가 여러개의 이슈로 분리된다면 마일스톤으로 등록합니다.  
[칸반 링크](https://github.com/users/hegunhee/projects/5/views/1)  
[마일스톤 링크](https://github.com/hegunhee/NowInJururu/milestones)  

## 앱 사진  
![JururuFragment](https://github.com/hegunhee/NowInJururu/assets/57277631/bb71653c-ff1e-44aa-9e86-03627db31444)
![StreamerFragment](https://github.com/hegunhee/NowInJururu/assets/57277631/6ede4209-890d-4f58-a30b-0caaf6075b6b)
![DetailStreamerFragment](https://github.com/hegunhee/NowInJururu/assets/57277631/f81508ad-13e3-4db8-99d7-b35c49595635)
![SearchFragment](https://github.com/hegunhee/NowInJururu/assets/57277631/96134ed8-1696-4109-99ac-87427e00d5fb)

