# 비밀키 생성하기
## 비밀키를 생성하기 위한 전제조건
* keytool(만약 JDK를 설치할 경우 함께 설치된다.)
* openssl(git bash를 사용할 경우 설치할 필요없다.)

## 설정정보
* \<alias>: 별칭을 지정한다.
* \<keypass>: 키에 대한 비밀번호를 6자리 이상을 설정한다.
* \<storepass>: 키에 대한 비밀번호를 6자리 이상을 설정한다.
* \<keyalg>: 시스템이 사용할 키를 암호화할 알고리즘을 설정한다.(ex. RSA)
* \<keystore>: 파일명을 작성한다.

### 비밀 키 생성
```
keytool -genkeypair -alias <alias> -keyalg <keyalg> -keypass <keypass> -keystore <keystore>.jks -storepass <storepass>
```

### 생성한 비밀 키에 대한 공개키 얻기
```
keytool -list -rfc --keystore <keystore>.jks | openssl x509 -inform pem -public
```
