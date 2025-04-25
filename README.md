개요
음식 레시피를 효과적으로 작성하고 관리할 수 있는 개인 블로그를 구현하는 Spring Boot 프로젝트. JAVA기반으로 MVC 모델링 기법을 활용해 제작한다.
템플릿 엔진은 Thymleaf로 구현.
ORM은 MyBatis를 이용.

컨셉
디자인 컨셉
전반적인 디자인은 모던 톤
포인트컬러와 보더레이더스를 활용해서 귀엽고 산뜻한 느낌을 가미해 음식 블로그의 정체성을 확립
화면 계획
Index
(1) 헤더, 푸터
(2) 메인페이지
(3) 각 섹션별 페이지

대분류
(1) 메인 페이지
(2) 섹션 1: 음식 레시피
(3) 섹션 2: 매거진(재밌고 다양한 음식 정보)
(4) 섹션 3: 방명록

소분류
(1) 메인페이지

비주얼 영역
각 섹션(List) 바로가기 버튼
인기글 노출
(2) 섹션 1, 2, 3 : 음식 레시피

CRUD
C : 새 글 작성 버튼 및 새 글 작성 페이지 (New)
R : 글 보기 페이지 (Detail) (방명록은 댓글처럼 구현할 예정이라 제외)
U : 글 수정 버튼 및 글 수정 페이지 (Update)
D : 글 삭제 버튼
섹션 1, 2의 게시물에는 댓글 기능 구현
DTO 구성
섹션 1 : 음식 레시피 게시글 DTO
Id
SectionNum
Title
Visual image
Caption
조회수
음식 용량(몇 인분인지..)
조리시간
난이도
재료 1~8
유튜브 링크
조리 상세 1~8
조리 상세 Thumb 1~8
섹션 2 : 매거진 게시글 DTO
Id
SectionNum
Title
Visual image
Content text
댓글 및 방명록 DTO
Id
Article id
Name
Pw
Content
SectionNum
이미지 파일 DTO
Id
Article id
SectionNum
OriginalFileName
StoredFileName
