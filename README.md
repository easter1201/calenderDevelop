# calenderDevelop

## 개요
Spring JPA를 사용해 구현한 일정관리 프로그램.

회원가입 후 로그인 시 일정을 자유롭게 작성할 수 있으며, 본인의 일정인 경우 수정 및 삭제도 가능합니다.<br/>
모든 일정에 댓글을 달 수 있습니다.
## 구조
Backend : Spring<br/>
DB : MySQL<br/>
ORM : Spring Data JPA<br/>
Authentication : Cookie / Session 기반 로그인 인증<br/><br/>
Package<br/>
controller : API 요청<br/>
dto : 요청 및 응답 객체<br/>
entity : JPA entity<br/>
exception : 예외정의 및 처리<br/>
repository : JPA repository<br/>
config : 비밀번호 암호화<br/>
filter : 필터 및 인증<br/>
service : 비즈니스 로직
## 기능
1. 회원가입
2. 로그인 / 로그아웃
3. 회원 정보 수정
4. 회원 정보 삭제
5. 일정 생성
6. 전체 일정 조회
7. 특정 일정 조회
8. 일정 수정
9. 일정 삭제
10. 댓글 생성
11. 댓글 조회
12. 댓글 수정
13. 댓글 삭제
14. 비밀번호 암호화
## 사용방법

### API
| 기능       | Method | URL                         | request                                                                                                         | response                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           | 상태코드                               |
|----------|--------|-----------------------------|-----------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------|
| 일정 생성    | POST   | /api/schedules              | 요청 쿠키<br/>userId: Long<br/><br/>요청 Body<br/>제목: String<br/>내용: String                                           | {<br/>"scheduleId": 4,<br/>"userId": 1,<br/>"scheduleTitle": "java",<br/>"scheduleContent": "12:00 ~ 14:00",<br/>"createdAt": "2025-05-23T15:53:07.8951144",<br/>"updatedAt": "2025-05-23T15:53:07.8951144",<br/>"userName": "updateUser1",<br/>"email": "newUser1@newUser.com"<br/>}                                                                                                                                                                                                                                                                                                                                                                                                              | 200: 정상등록                          |
| 선택 일정 조회 | GET    | /api/schedules/{scheduleId} | 요청 param<br/>scheduleID: Long                                                                                   | {<br/>"scheduleId": 4,<br/>"userId": 1,<br/>"scheduleTitle": "java",<br/>"scheduleContent": "12:00 ~ 14:00",<br/>"createdAt": "2025-05-23T15:53:07.8951144",<br/>"updatedAt": "2025-05-23T15:53:07.8951144",<br/>"userName": "updateUser1",<br/>"email": "newUser1@newUser.com"<br/>}                                                                                                                                                                                                                                                                                                                                                                                                              | 200: 정상조회                          |
| 전체 일정 조회 | GET    | /api/schedules              | 요청없음                                                                                                            | [<br/>{<br/>"scheduleTitle": "java",<br/>"scheduleContent": "12:00 ~ 14:00",<br/>"numberOfComment": 0,<br/>"createdAt": "2025-05-23T15:53:08",<br/>"updatedAt": "2025-05-23T15:53:08",<br/>"userName": "updateUser1"<br/>},<br/>{<br/>"scheduleTitle": "baseball",<br/>"scheduleContent": "play baseball at 14:00",<br/>"numberOfComment": 2,<br/>"createdAt": "2025-05-21T17:18:34",<br/>"updatedAt": "2025-05-21T17:19:20",<br/>"userName": "updateUser1"<br/>},<br/>{<br/>"scheduleTitle": "study",<br/>"scheduleContent": "studying java",<br/>"numberOfComment": 0,<br/>"createdAt": "2025-05-21T17:17:40",<br/>"updatedAt": "2025-05-21T17:17:40",<br/>"userName": "updateUser1"<br/>}<br/>] | 200: 정상조회                          |
| 일정 수정    | PUT    | /api/schedules/{scheduleId} | 요청 쿠키<br/>userId: Long<br/><br/>요청 param<br/>scheduleID: Long<br/><br/>요청 Body<br/>제목: String<br/>내용: String    | {<br/>"scheduleId": 4,<br/>"userId": 1,<br/>"scheduleTitle": "modifiedjava",<br/>"scheduleContent": "14:00 ~ 15:00",<br/>"createdAt": "2025-05-23T15:53:07.8951144",<br/>"updatedAt": "2025-05-23T15:53:08",<br/>"userName": "updateUser1",<br/>"email": "newUser1@newUser.com"<br/>}                                                                                                                                                                                                                                                                                                                                                                                                              | 200: 정상수정                          |
| 일정 삭제    | DELETE | /api/schedules/{scheduleId} | 요청 쿠키<br/>userId: Long<br/><br/>요청 param<br/>scheduleID: Long<br/><br/>요청 Body<br/>비밀번호: String                 | response 없음                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        | 200: 정상삭제                          |
|          |        |                             |                                                                                                                 |                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    |                                    |
| 유저 생성    | POST   | /sign                       | 요청 쿠키<br/>userId: Long<br/><br/>요청 Body<br/>이름: String<br/>이메일: String<br/>비밀번호: String                         | 회원 가입 완료                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           | 200: 정상등록                          |
| 유저 수정    | PUT    | /rename                     | 요청 쿠키<br/>userId: Long<br/><br/>요청 Body<br/>이름: String<br/>이메일: String<br/>비밀번호: String                         | 수정 완료                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              | 200: 정상수정                          |
| 유저 삭제    | DELETE | /quit                       | 요청 쿠키<br/>userId: Long<br/><br/>요청 Body<br/>비밀번호: String                                                        | 회원 탈퇴 완료                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           | 200: 정상삭제                          |
| 로그인      | POST   | /login                      | 요청 Body<br/>이메일: String<br/>비밀번호: String                                                                        | login                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              | 200: 정상접속<br/>401: 이메일 또는 비밀번호 불일치 |
| 로그아웃     | POST   | /logout                     | 요청 쿠키<br/>userId: Long                                                                                          | logout                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             | 200: 정상종료                          |
|          |        |                             |                                                                                                                 |                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    |                                    |
| 댓글 생성    | POST   | /comments/{scheduleId}      | 요청 쿠키<br/>userId: Long<br/><br/>요청 param<br/>scheduleID: Long<br/><br/>요청 Body<br/>댓글내용: String                 | {<br/>"commentId": 6,<br/>"content": "letsgo",<br/>"userName": "updateUser1",<br/>"createdAt": "2025-05-23T16:08:20.1567522",<br/>"updatedAt": "2025-05-23T16:08:20.1567522"<br/>}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   | 200: 정상등록                          |
| 댓글 조회    | GET    | /comments/{scheduleId}      | 요청 쿠키<br/>userId: Long<br/><br/>요청 param<br/>scheduleID: Long                                                   | [<br/>{<br/>"commentId": 4,<br/>"content": "letsgo",<br/>"userName": "updateUser1",<br/>"createdAt": "2025-05-23T14:37:36",<br/>"updatedAt": "2025-05-23T14:37:36"<br/>},<br/>{<br/>"commentId": 5,<br/>"content": "doit",<br/>"userName": "updateUser1",<br/>"createdAt": "2025-05-23T14:37:44",<br/>"updatedAt": "2025-05-23T14:37:44"<br/>}<br/>]                                                                                                                                                                                                                                                                                                                                               | 200: 정상조회                          |
| 댓글 수정    | PUT    | /comments/{commentId}       | 요청 쿠키<br/>userId: Long<br/><br/>요청 param<br/>contentID: Long<br/><br/>요청 Body<br/>댓글내용: String<br/>비밀번호: String | {<br/>"commentId": 4,<br/>"content": "updateLetsgo",<br/>"userName": "updateUser1",<br/>"createdAt": "2025-05-23T14:37:36",<br/>"updatedAt": "2025-05-23T14:37:36"<br/>}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           | 200: 정상수정                          |
| 댓글 삭제    | DELETE | /comments/{commentId}       | 요청 쿠키<br/>userId: Long<br/><br/>요청 param<br/>contentID: Long<br/><br/>요청 Body<br/>비밀번호: String                  | response 없음                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        | 200: 정상삭제                          |

### ERD
![ERD](/src/ERD.png)

## Version
#### v1.0.0
초기 구성

#### v1.1.0
일정 CRUD 구현

#### v1.2.0
유저 CRUD 구현

#### v1.3.0
유저 필드에 비밀번호 추가

#### v1.4.0
Cookie/Session 활용한 로그인 구현<br/>
이메일 및 비밀번호 일치하지 않는 경우 401 error 반환

#### v1.5.0
Validation 활용해 예외처리 적용<br/>
@Pattern 활용해 이메일 형식 검증

#### v1.6.0
비밀번호 암호화

#### v1.7.0
댓글 CRUD 구현

#### v1.7.1
댓글 수정 및 삭제 시 비밀번호 입력 의무화

#### v1.7.2
유저 CRUD 기능 재구현

#### v1.8.0
페이징조회 구현<br/>
README 업데이트

#### v1.9.0
README 업데이트

#### v1.9.1
주석 추가

#### v1.9.2
README 업데이트