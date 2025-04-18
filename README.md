# NowInJururu  
## 소개  
트위치 스트리머 검색 및 카카오 검색을 하는 앱입니다.  
트위치 코리아 철수 이후 SOOP 혹은 치지직 검색도 추가할 예정입니다.  
## 개발환경
Kotlin : 1.8.0  
Java : 17  
gradle : 8.4.0  
AGP : 8.3.2  
## 모듈 구조  
본 프로젝트는 multi-module 구조이며 각 feature마다 모듈의 형태로 구성되어있습니다.  
📦plugins(build-logic)  
📦app  
📦feature(Compose)  
┣ 📂search  
┣ 📂searchKakao  
┣ 📂streamer  
┣ 📂ui-component  
📦core  
┣ 📂data  
┣ 📂designsystem  
┣ 📂domain  
┗ 📂navigation  

## 기술스택  
- network
  - Retrofit2, Moshi, Coroutine
- UI
  - Jetpack Compose, AAC-ViewModel, Coroutine Flows
- DI  
  - Hilt
- Test
  - Junit4, mockito-kotlin, Espresso
 
## 기술정보  
- Retrofit  
  http 통신을 보다 편하게 하기 위해 사용했습니다.  
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
| 카카오 검색 | 스트리머 생방송 | 스트리머 추천 |
| -------- | ----------- | --------- |
| ![카카오 검색](https://github.com/user-attachments/assets/00d470c3-bf6d-4ae1-b992-6bfa31a0494e) | ![스트리머 생방송](https://github.com/user-attachments/assets/de4e9cb3-4be8-44de-875d-f7fdc5c4e7f6) | ![스트리머 추천](https://github.com/user-attachments/assets/f04eadd2-0aa8-4c00-94de-a355a0cfdeb8) |
| 스트리머 검색 |           |           |
| ![스트리머 검색](https://github.com/user-attachments/assets/7b775012-ebfa-49a8-9e7a-2e4c49055f8c) |
