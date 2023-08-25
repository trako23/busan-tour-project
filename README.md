# TRAKO

Team TRAKO입니다.

----------



## 프로젝트 개요

### 주제

다시 방문하고 싶은 부산과 부산 관광 활성화를 위한 외국인 중점 관광 정보 제공 서비스 프로젝트



### 문제점

- 엔데믹 이후, 다시 방한 관광객의 수가 증가하고 있어 여행·관광산업이 코로나 이전  수준으로 회복하고 있으나, 방한 관광객이 겪는 불편사항에 대한 대책이나 방안이  아직 없거나 미비하여 코로나 이전처럼 다수의 불편접수가 발생할 것으로 보임
- 2021년에는 코로나의 영향으로 방한 여행객의 수가 감소하여 불편접수 건수가 적지만,  코로나 이전 4년간 불편신고 접수건수는 연간 평균 1200~1300건수로 코로나가 종식  된 현재, 수치는 비슷하거나 더욱 증가할 것으로 예상
- 시도별 방문율은 서울(2019년 1분기 대비 증가, 77.6% -> 81.8%)이 가장 높으며, 그 다음으로 부산(15.6%), 경기(10.7%) 순으로 대부분 서울에 집중화되어 있어 지방 관광 활성  화를 위해서는 지방을 알리고 확산시키는 것이 필요
- 한국 사람에게는 편리한 카카오네비, 네이버 지도와 같은 서비스는 국내에서는 압도적이  지만 해외에서는 대다수가 구글 맵스(Google Maps)에 의존적이고 익숙한데, 구글 맵스의  경우 국내 친화적인 환경이 아니기에 요금 정보, 시내버스 실시간 도착예정 시간 등  정보가 매우 부족
- AI 기반 여행 플래너와 같은 여행 플랫폼이나 정부 지자체에서 추천하는 맛집, 숙박과  같은 정보들 또한 정적인 데이터이며, 연령·성별 등 맞춤형 데이터나 실시간 대한민국 국 민들이 관심을 가지는 여행지나 맛집 트렌드 등에 대한 정보를 제공 X
- 방한 관광객이 뽑은 부족했던 한국 관련 정보로 가장 높았던 것이 교통정보(20.7%)이며, 다음으로 음식 및 맛집 정보(16.65%), 지역 축제 및 행사 정보(15.5%) 순으로 지역 관  광의 핵심적인 요소들에 대한 정보들이 부족
- *참조 - 외래관광객 조사 1분기 결과(잠정치), 한국문화관광정책연구원(2023)



### 해결방안

- 방한 관광객이 겪었던 불편함과 부족한 정보 문제는 구글 서비스를 주로 이용하는  다른 나라와 달리 한국에서는 네이버, 카카오에서 제공하는 서비스를 주로 이용하기에  정보 불균형이 발생하여 나타난 것인데, 이는 다양한 데이터를 통해 해결이 가능
-  대한민국 국민이 특정 달에 즐기는 문  화, 축제, 음식점과 같은 정보와 관광지 이동 동선, 교통 정보 등을 분석하여 제공할 수  있다. 이를 통해서 관광객에게 편리함을 줄 수 있으며, 맞춤형 데이터 정보 제공도 가능하다. 그리고 관광객 증가로 소극장, 공연 등 문화 분야나 오프라인 로드샵, 맛집, 카페 와 같이 지역 소상공인 발전에도 기여
- 마이데이터와 함께 네이버 검색 트렌드&키워드, 구글 트렌드 API를 사용  하여 특정 시점·지역에서 한국인들이 주로 검색하는 맛집, 관광지와 같은 정보를 제공
- 특정 지역에 방문하기 전 해당 지역에서 진행하는 문화 체험, 공연, 맛  집 등과 같은 광범위한 정보를 공공 데이터 포털의 API(예시 - 부산: 부산축제, 맛집 정  보 서비스, 전주: 문화 체험)를 활용하여 방한 관광객에게 제공



## 프로젝트 구성

### 프로젝트 전체 아키텍처

<img src="https://github.com/trako23/busan-tour-project/assets/50001184/d975c622-789e-467e-98b7-769be38d659a" alt="trako_system_architecture" />



### 공통

- github 원격 저장소에 push하면 github action을 통해서 클라이언트와 서버 각각에 맞게 배포 자동화
- 로그인 인증 방식에는 JWT를 사용하며 refresh token을 더해 보안성을 높임



### Client

#### 개요

- react + typescript를 기반으로 개발 진행
- typescript에 prettier와 eslint 추가
- REST API 통신은 'axios' 라이브러리 사용
  - api 요청 등 로그인 후 서버와 통신할 때 토큰 검증에 axios interceptor를 사용
- 배포는 'Netlify'를 통해서 진행

#### 버전

- react 버전 - ![Badge](https://img.shields.io/badge/version-18.2.0-3f49c4.svg)



### Server

#### 개요

- spring boot를 기반으로 개발을 진행
- 로그인 인증 방식에 JWT 사용 (token, Refresh Token)
- 회원가입 시 비밀번호를 암호화하여 데이터베이스(postgreSQL)에 저장 및 관리
- Nginx를 통해 리버스 프록시, https 구성 진행
- 로그인 중복 처리 방지 등을 위해 redis 구성(AWS Elastic Cache)
- api 정보를 주기적으로 DB에 저장하기 위해 스프링 배치로 DB-백엔드간 구성
- 배포
  - AWS 서비스를 기본으로 하며, github에서 push하면 github action(webhook)이 실행되어 빌드 진행
  - jar파일이 AWS S3로 전달되고 CodeDeploy를 통해 배포가 진행
  - EC2에는 CI/CD 자동화의 젠킨스, Ngnix, Spring boot가 포함되며 해당 요소들은 각각의 도커로 구성

#### 버전

- spring boot 버전 - ![Badge](https://img.shields.io/badge/version-'default'-3f49c4.svg)



### DB

#### 개요

- postgreSQL로 설계&middot;개발을 진행

#### 버전

- PostgreSQL 버전 - ![Badge](https://img.shields.io/badge/version-11-3f49c4.svg)



### API

- 공공데이터포털 오픈 API
- 구글맵스 API
- 카카오맵 API
- 네이버 검색 트렌드 API



## 프로젝트 세팅


* #### Remote Info - origin: https://github.com/trako23/busan-tour-project.git

  1. git clone 또는 git pull을 통해 원격 저장소로부터 가져오기

  1. 로컬 저장소에서 작업 요소에 따른 브랜치 생성 후, 작업 진행

  1. 작업 완료 후, main 브랜치에 merge하기 위한 PR 요청

  1. Code Review 후, merge assign

  1. 완료 후, issue close 및 프로젝트 내 항목 이동

     


----------



## 작업 방식 및 정보

  ### Remote Info

  - origin: [trako repository]("https://github.com/trako23/busan-tour-project")

### Folder별 Push, Pull 설정

- git version 확인 -> ```git --version```

- 버전이 2.25 이상일 경우

  #### (1) Pull

  - mkdir 등 pull 받을 폴더로 이동

  - ```git init``` 으로 초기화

  - sparse-checkout 세팅

  - ```tex 
    $ git sparse-checkout init
    $ git sparse-checkout set "/directory path/"
    $ git sparse-checkout list
    # /<Path>/
    ```

    * client의 경우 -> "/client/", server의 경우 -> "/server/" 지정

  - git remote add origin https://github.com/trako23/busan-tour-project.git

  - git pull origin main

  

  #### (2) Push

  - Pull 진행 후에 지정한 폴더를 받은 후, 작업하고 push
  - Push 방법은 기존과 동일

  <a href="https://bitlog.tistory.com/132">참조 사이트</a>

### Branch Info

  * main 브랜치를 주축으로 각 요소 별 branch를 생성 후, 작업

    - ex) feature/login, feature/header 등 feature/[branch name]으로 부여

    - 생성은 git branch 'feature/[branch name]'

    - 생성 후, 브랜치 변경은 git checkout 'feature/[branch name]'

    - ```tex
      $ git branch feature/header
      $ git checkout feature/header
      Switched to branch 'feature/footer'
      $ git branch -a
      * feature/footer
        main
        remotes/origin/feature/amazing
        remotes/origin/feature/header
        remotes/origin/main
      ```

    - <b>작업에 앞서 브랜치 위치 파악 중요</b>

    - 정상적으로 Switched된 경우라도 git branch -a를 통해 현재 어느 브랜치가 선택되어 있는지 확인 후에 작업

### Commit

  * Commit 분류는 간단하게 다음과 같이 구성하며, 추가로 필요한 경우 규칙 생성 (Initial 등)
    - [UPDATE] : 코드 수정 및 변경
    - [STYLE] : 디자인, 애니메이션 관련 변경 사항
    - [ADD] : 신규 파일 추가
    - [FIX] : 잘못된 정보 변경, 문제 수정
    - [DELETE] : 파일 삭제

### PR 요청

#### (1) 최초 브랜치 push 경우

  * 작업 후, 해당 브랜치로 원격 저장소에 push

![git-push-1](C:\Users\USER\Desktop\git-project\integrated-banking-system\setupImages\initial-push-branch-pr.png)

  * Compare & pull request 버튼 클릭 후, write에서 마크다운 형식으로 코멘트를 작성 ( 프리뷰에서 확인 가능)

![git-push-2](C:\Users\USER\Desktop\git-project\integrated-banking-system\setupImages\open-pull-request.png)

  * 각 옵션에서 Reviewers를 눌려서 review를 요청할 사람 선택
  * pull request를 수락해줄 Assignees 사람 선택
  * Labels에서는 해당 pr이 이슈를 해결한 것인지, 새로 진행한 것인지 각 작업에 맞는 라벨을 선택하여 클릭
  * 프로젝트에서는 내역에서 LHSK에 맞는 프로젝트 선택

![open-issue](C:\Users\USER\Desktop\git-project\integrated-banking-system\setupImages\open-issue.png)

  * 정상적으로 세팅 후, create pull request를 하면 open 문구 나옴
  * 그 후, 프로젝트에 들어가면 Choose Columns에 PR한 내역이 나오게 됨

#### (2) 원격 저장소에 브랜치가 존재할 경우

- 이미 브랜치가 존재하는 경우에는 자동으로 pull request 요청이 이루어지지 않음
- 그래서 github 탭의 Pull requests로 들어가서 직접 생성을 해야 함

![new-pull-request](C:\Users\USER\Desktop\git-project\integrated-banking-system\setupImages\new-pull-request.png)

- 이후는 (1)의 과정과 같이 동일하게 진행

### PR 생성 후, review & assign pull request

- PR에서 어떤 요소가 변경되었는지 Commits, Files changed 등 항목에서 확인

[변경 전]

![assign-merge](C:\Users\USER\Desktop\git-project\integrated-banking-system\setupImages\request-review.png)

[변경 후]

![require-review](C:\Users\USER\Desktop\git-project\integrated-banking-system\setupImages\require-review.png)

- 리뷰가 필요한 경우, 아래 comment쪽에서 서로 진행하도록 정했으나, 최소 한 명 이상의 팀원이 리뷰를 하도록 지정
- Comment 등 작성 후, 이상 요소가 없다면 merge
- 이 외에도 rebase 등이 가능하나 상황에 맞춰서 진행

![merged](C:\Users\USER\Desktop\git-project\integrated-banking-system\setupImages\merged.png)

- Merge를 confirm하면 open에서 merged라고 변경됨

### Issue 생성 (수정 중)

- 어떤 부분이 작동하지 않거나 충돌 등의 에러가 발생한 경우뿐만 아니라 meeting 등 각종 이벤트에 대해서 추가 및 선택
- 타이틀은 '[이슈 요소 #number] text'로 진행 => 이슈, PR은 생성 후에도 프로젝트 작업 등에서 타이틀 수정이 가능 (Issue, Todo, PR 등)

