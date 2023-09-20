# bookCommunity/도서커뮤니티
Java Servlet과 JSP를 활용한 MVC 패턴 기반 도서커뮤니티 사이트

## 🖥️ 프로젝트 소개
책에 대한 정보를 공유하고 의견 교환을 위해 만든 도서커뮤니티 사이트입니다.
<br>

## 🕰️ 개발 기간 & 참여인원
* 22.01.09일 - 22.02.12일
* 7명

### 🧑‍🤝‍🧑 역할
 - 1대1 문의 Q&A 게시판(CRUD) 구현
 - ERDcloud를 활용한테이블 구조 (상관관계) 정리 DB 설계

### ⚙️ 개발 환경
- `Java 8`
- `JDK 1.8.0`
- `Javascript`
- `HTML5`
- `CSS3`
- **IDE** : eclipse 2020-03
- **Database** : MySQL 5.7
- **ETC** : notion, Apache Tomcat

## 핵심 기능
```
          <!-- 제목 - 비회원 -->
	   <c:if test="${empty authUser}">
	   <td><a href="<%=request.getContextPath()%>/login.do">${qna.q_title}</a></td>
	   </c:if>
	   <!-- 제목 - 글작성자가 아닌 회원 -->
	   <c:if test="${not empty authUser && authUser.grade != 999 && (authUser.mId != qna.q_writer.q_writerid) }">
	   <td><a href="javascript:alert('글을 읽을 권한이 없습니다.');">${qna.q_title}</a></td>
	   </c:if>
	   <!-- 제목 - 글작성자와 관리자 -->
	   <c:if test="${authUser.mId == qna.q_writer.q_writerid || authUser.grade == 999 }">
	   <td><a href="<%=request.getContextPath()%>/qRead.do?no=${qna.q_no}&pageNo=${qnaPage.currentPage}&rowSize=${size}">${qna.q_title}</a></td>
	   </c:if>
```
#### 권한별 글내용 확인 - <a href="https://github.com/coding8ruler/bookCommunity/blob/main/bookCommunity/WebContent/view/qna/listQna.jsp" >상세보기 - 이동</a>

## 📌 
- 기본 CRUD 구현
- 댓글 기능 구현
- 페이징 처리 기능 구현
- 조회수 기능 구현
- 비회원 모드, 회원모드, 관리자 모드 등급에 따른 권한 설정 기능 구현
- java MVC 패턴 구조 이해
- MySQL CRUD 기본 구조 및 sequence 원리 파악
- 프로젝트관련 보고서 작성 요령 및 일정 관리 파악
