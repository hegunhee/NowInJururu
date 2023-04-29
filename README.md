# NowInJururu  
## 소개  
이세계 아이돌 주르르의 현재 방송 정보를 알려주고 방송으로 이동 가능한 앱입니다.  
주르르 이외에도 스트리머를 검색할 수 있으며 방송 정보를 볼 수 있습니다.  
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
