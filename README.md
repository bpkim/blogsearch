# 블로그 검색 API
## 개요
블로그 검색 API<br>
카카오 블로그 검색을 기반으로 한다. <br>
카카오 블로그 검색 에러 또는 검색 결과가 없을 경우 네이버 블로그 검색을 한다.<br>
사용했던 검색어의 순위를 알 수 있다.

* Java 11
* Spring Boot 2.7.9
* h2
* JPA
* gradle 7.6.1
## jar 파일 다운로드
* [**다운로드**](https://drive.google.com/file/d/15n_Q_BBknybqosjRwN7T9WKwxy705AHr/view?usp=sharing)

* **실행**
~~~shell
java -jar blogsearch-api-1.0.0.jar
~~~

## 구현내용

### 1. Spring Boot 기능 활용
* AOP 활용하여 검색어 로그 저장 기능 구현
* validation 활용하여 API parameter 값 검증
* junit 이용 테스트 코드 작성
* RestControllerAdvice 이용하여 ExceptionHandler 구현하여 에러 발생시 결과값 모델 통일
* h2, jpa, jackson 등
### 2. 테스트 케이스
* Junit4, MocMvc 이용하여 테스트 구현
* API 결과 및 결과 값 검증 테스트 코드 구현
* Entity 생성 테스트 코드 구현
### 3. 에러 처리
* RestControllerAdvice 이용하여 ExceptionHandler 구현하여 에러 발생시 결과값 모델 통일
* validation 활용하여 API parameter 값 검증
* 카카오 블로그 검색 에러 발생 시 네이버 블로그 검색 진행
### 4. 트래픽이 많고 데이터가 많다면?
* 인기 검색어 기능 구현
    * 검색어 횟수 조회를 위한 위해 검색시 검색어를 db insert로 하여 기록하고, insert 된 row 수를 계산하여 검색어 횟수 기능 구현
    * insert 방식으로 하여 동시성 이슈 없음
    * 추후 배치를 이용해 검색어 횟수 미리 집계하여 트래픽 증가, 데이터 증가에 대한 서버 부담을 줄일 수 있음
### 5. 멀티모듈구성
* blogsearch-api, blogsearch-lib, blogsearch-client 세가지 모듈로 구성 
```bash
 blogsearch
  ├─ blogsearch-api : API 부분, 어플리케이션이 실행되는 부분 
  ├─ blogsearch-lib : 비즈니스 로직이 있는 부분 db 접근 및 blogearch-client 호출하여 값을 다룬다. 
  └─ blogsearch-client : 외부 API 호출 부분.  카카오 블로그, 네이버 블로그 API를 호출한다.  
```

### 6. 기본 기능 카카오블로그 장애 시 네이버 블로그 검색
* 팩토리 패턴 적용하여 카카오블로그 네이버블로그 검색 구현
* 추후 검색 소스가 추가될 수 있음을 고려하여 팩토리 패턴으로 구현
### 7. 외부 라이브러리 사용
* MapStruct : dto 변환 시 사용한 mapper
* OpenFeign : 외부 API 호출
* Lombok : dto, vo 에 getter, setter, toString 등
* junit : 테스트 코드 작성
* p6spy : SQL 로그 출력
### 8. Spec


## API 명세

## 1. 블로그 검색하기

### 설명
블로그를 검색합니다. <br>
다음블로그를 기본으로 검색하고, 다음블로그 에러 또는 검색된 문서가 없을 시 네이버블로그 검색합니다.
### URL
`GET` /api/blog
### Request
* **Parameter**


| Name  | Type    | Description                                                                                              | Required |
|-------|---------|----------------------------------------------------------------------------------------------------------|----------|
| query | String  | 검색을 원하는 질의어 <br/>특정 블로그 글만 검색하고 싶은 경우, 블로그 url과 검색어를 공백(' ') 구분자로 넣을 수 있음<br/>네이버 블로그 검색시에는 url제외 하여 검색. | O        |
| sort  | String  | 결과 문서 정렬 방식, accuracy(정확도순) 또는 recency(최신순), 기본 값 accuracy<br/>네이버블로그 검색시에는 입력값 상관 없이 정확도 순              | X        |
| page  | Integer | 결과 페이지 번호, 1~50 사이의 값, 기본 값 1                                                                            | X        |
| size  | Integer | 한 페이지에 보여질 문서 수, 1~50 사이의 값, 기본 값 10                                                                     | X        |

### Response
* **meta**

| Name          | Type    | Description                                                | Note                                 |
|---------------|---------|------------------------------------------------------------|--------------------------------------|
| service       | String  | 블로그 서비스 제공자 |  다음블로그 : kakao <br/> 네이버 블로그 : naver |
| totalCount    | Integer | 검색된 문서의 수                                                  |                                     |
| pageableCount | Integer | totalCount 중 노출 가능 문서 수| 네이버 블로그로 조회된 경우 0 으로 표시|
| isEnd         | boolean | 현제 페이지가 마지막 페이지인지 여부<br/>값이 false 이면 page를 증가시켜 다음페이지를 요청할 수 있음. ||

* **documents**

| Name       | Type     | Description                                                     | Note          |
|------------|----------|-----------------------------------------------------------------|---------------|
| title      | String   | 블로그 글 제목                                                        ||
| contents   | String   | 블로그 글 요약                                                        |               |
| url        | String   | 블로그 글 URL                                                       |
| blogname   | String   | 블로그 이름                                                          ||
| thumbnail  | String   | 검색 시스템에서 추출한 대표 미리보기 이미지 URL, 미리보기 크기 및 화질은 변경될 수 있음 | 네이버블로그는 null  |
| datetime   | Datetime | 블로그 글 작성시간, ISO 8601 ||


### Sample
* **Request**
```shell
curl -X GET \
  'http://localhost:8080/api/blog?query=%EA%B2%80%EC%83%89%EC%96%B4&page=1&sort=&size=10'
```

* **Response**
```json
HTTP/1.1 200 OK
Content-Type: application/hal+json;charset=UTF-8
        
{"meta":{"service":"kakao"
         ,"totalCount":1220169
         ,"pageableCount":795
         ,"isEnd":false
         }
 ,"documents":[{"title":"3월 14일 일별 구글 트렌드 <b>검색어</b> 컨설팅"
                ,"contents":"3월 14일 일별 구글 트렌드 <b>검색어</b> 컨설팅입니다. 오늘 대한민국 <b>검색어</b>에 오르내린 이정후, 연방준비제도, 김현수, 아반떼, 오에 겐자부로 등에 대해 알아보고 연관된 내용이 무엇이 있을지 찾아가는 시간입니다. 구글 트렌드를 읽다 보면 내가 써야 할 글의 방향이나 지식들이 조금씩 보이기 시작하니 재미있게 읽어..."
                ,"url":"http://do-something-and-fix-it.tistory.com/151"
                ,"blogname":"스스로 일 벌이고 수습하는아재의 일상"
                ,"thumbnail":"https://search2.kakaocdn.net/argon/130x130_85_c/9OKNtZqQM8i"
                ,"datetime":"2023-03-14T12:21:44.000+09:00"}
              ,{"title":"실시간 <b>검색어</b> 순위 확인하는 법","contents":"한때 이슈가 되었던 실시간 검색 순위를 ....}
              , ....
              }]
}
```


## 2. 인기 검색어 목록

### 설명
블로그 검색어의 랭킹을 검색합니다. <br>

### URL
`GET` /api/blog/searchword/rank/{rank}

### Request
* **PathVariable**

| Name  | Type    | Description                                         | Required |
|-------|---------|-----------------------------------------------------|----------|
| rank | String  | 조회을 원하는 랭킹의 수<br/> 기본값 10 <br/>미입력시 10개의 랭킹을 조회 한다. | X        |


### Response
* **meta**

| Name          | Type    | Description | Note |
|---------------|---------|-------------|------|
| totalCount    | Integer | 총 검색횟 수     | |
| searchwordCount | Integer | 검색어의 수      | |

* **documents**

| Name        | Type     | Description     | Note     |
|-------------|----------|-----------------|----------|
| rank        | Integer   | 순위              ||
| searchword  | String   | 검색어             ||
| count       | String   | 검색어 검색 수        ||
| minDatetime | Datetime   | 검색어의 가장 과거 검색시간 | ISO 8601 |
| maxDatetime | Datetime   | 검색어의 최근 검색시간    | ISO 8601 |

### Sample
* **Request**
```shell
curl -X GET \
  http://localhost:8080/api/blog/searchword/rank/5
 ```

* **Response**
```json
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
        
{
    "meta": {
        "totalCount": 173,
        "searchwordCount": 15
    },
    "documents": [
        {
            "rank": 1,
            "searchword": "판교 점심 추천",
            "count": 28,
            "minDatetime": "2023-03-22 02:17:05.572759",
            "maxDatetime": "2023-03-22 02:17:21.65681"
        },
        
        ....
        
        {
            "rank": 5,
            "searchword": "데이터베이스 시스템",
            "count": 16,
            "minDatetime": "2023-03-22 02:16:16.355367",
            "maxDatetime": "2023-03-22 02:16:25.023659"
        }
    ]
}
```
