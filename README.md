# KS Portal (Spring Boot)

회사 홈페이지 (KSNET 유사 레이아웃) + 관리자 파일 업로드

## 기능
- 퍼블릭: `/`, `/public/services`, `/public/support`, `/public/news`
- 관리자: `/admin` (로그인 필요), 업로드 `/admin/upload`, 파일 제공 `/uploads/{filename}`

## 실행
1) Java 17, Maven 설치
2) 루트에서 실행
```
mvn spring-boot:run
```
3) 접속
- 퍼블릭: `http://localhost:8080/`
- 관리자: `http://localhost:8080/admin`

기본 계정
- ID: `admin`
- PW: `admin1234`

## 설정
- 업로드 경로: `application.yml`의 `file.upload-dir` (기본: `uploads`)

## 구조
- 컨트롤러: `src/main/java/com/company/portal/controller`
- 템플릿: `src/main/resources/templates`
- 정적: `src/main/resources/static/assets`

## 주의
운영 시 보안/검증/로그/오브젝트스토리지/CDN 등의 강화를 권장합니다.
"# dy" 
