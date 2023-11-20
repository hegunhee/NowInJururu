# NowInJururu  
## 소개  
이세계 아이돌 주르르의 현재 방송 정보를 알려주고 방송으로 이동 가능한 앱입니다.  
주르르 이외에도 스트리머를 검색할 수 있으며 방송 정보를 볼 수 있습니다.  
현재 앱은 멀티모듈로 구성되어 있으며 data -> domain <- feature 모듈로 구성되어 있습니다.  
멀티 모듈로 인해 중복되는 gradle 설정을 줄이려고 customPlugin을 제작해 사용하고 있습니다.  
## Compose (2023.08.10 ~ 2023.08.25)  
컴포즈로도 앱을 제작했습니다.  
기존 코드가 클린 아키텍처를 기반으로 작성했기때문에  
Presentation Layer만 새로 작성했습니다.  
## 앱 사진  
![JururuFragment](https://github.com/hegunhee/NowInJururu/assets/57277631/bb71653c-ff1e-44aa-9e86-03627db31444)
![StreamerFragment](https://github.com/hegunhee/NowInJururu/assets/57277631/6ede4209-890d-4f58-a30b-0caaf6075b6b)
![DetailStreamerFragment](https://github.com/hegunhee/NowInJururu/assets/57277631/f81508ad-13e3-4db8-99d7-b35c49595635)
![SearchFragment](https://github.com/hegunhee/NowInJururu/assets/57277631/96134ed8-1696-4109-99ac-87427e00d5fb)

## 기술 정보  
- Retrofit  
  트위치 api를 통해 현재 방송정보, 스트리머 정보를 불러와야하기때문에  
  Retrofit을 사용중입니다.  
- Hilt  
  의존성 주입을 사용하여 종속성이 감소하여 보다 유연하고 테스트에 용이합니다.  
  멀티 모듈 프로젝트에서 의존성을 쉽게 주입 가능합니다.  
- VersionCatalog + customPlugin
  VersionCatalog로 외부 라이브러리를 한 toml파일에서 관리합니다.  
  멀티모듈로 인해 동일한 gradle설정을 여러번 해야되는 수고로움을 덜어줍니다.  
## 비즈니스 로직 설명  
[api 명세](https://github.com/hegunhee/NowInJururu/issues/5)  
[Json/Entity 명세](https://github.com/hegunhee/NowInJururu/issues/4)  
## Issues  
특정 앱에 대한 검색을 하려면 그 앱의 패키지명을 manifest에 등록해야함(Android11(API30) 이상을 타게팅하는 앱의경우)  
[PackageInfo](https://github.com/hegunhee/NowInJururu/issues/9)  

## 진척도  
https://github.com/hegunhee/NowInJururu/issues/51
