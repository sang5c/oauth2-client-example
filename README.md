## 사전지식
- OAuth
- [Spring boot mvc](https://spring.io/guides/gs/serving-web-content/)
- https://www.joinc.co.kr/w/man/12/oAuth2/Google

### 명근이한테 물어보기
- https://developers.google.com/identity/sign-in/web

## Google OAuth2 login 프로세스
- https://developers.google.com/identity/protocols/oauth2/web-server#httprest_1
  https://developers.google.com/calendar/api/v3/reference/calendarList/list

1. application 등록
2. Google 로그인 버튼 링크
   ```
   https://accounts.google.com/o/oauth2/v2/auth?
   scope=https%3A//www.googleapis.com/auth/drive.metadata.readonly&
   access_type=offline&
   include_granted_scopes=true&
   response_type=code&
   state=state_parameter_passthrough_value&
   redirect_uri=https%3A//oauth2.example.com/code&
   client_id=client_id
   ```
3. Authorization code 획득, Access token 요청
   ```
   POST /token HTTP/1.1
   Host: oauth2.googleapis.com
   Content-Type: application/x-www-form-urlencoded

   code=4/P7q7W91a-oMsCeLvIaQm6bTrgtp7&
   client_id=your_client_id&
   client_secret=your_client_secret&
   redirect_uri=https%3A//oauth2.example.com/code&
   grant_type=authorization_code
   ```
4. API 호출
   ```
   GET /drive/v2/files HTTP/1.1
   Host: www.googleapis.com
   Authorization: Bearer access_token
   ```
