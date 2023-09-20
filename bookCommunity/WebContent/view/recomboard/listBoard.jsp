<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="description" content="member board Web Application">
	<meta name="keywords" content="member, board, article, mvc">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>게시판 목록</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/mP.css" />
	<script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<script> 
//스크롤 따라다니는 메뉴박스 만들기
$(document).ready(function(){
$(window).scroll(function(){  //스크롤이 움직일때마다 이벤트 발생
      var position = $(window).scrollTop()+200; // 현재 스크롤바의 위치값을 반환
      $("#Quick").stop().animate({top:position+"px"}, 400); //해당 오브젝트 위치값 재설정
   });

});
</script> 
	
	<style>
	body {
	  color: #666;
	  font: 14px/24px "Open Sans", "HelveticaNeue-Light", "Helvetica Neue Light", "Helvetica Neue", Helvetica, Arial, "Lucida Grande", Sans-Serif;
	}
	table {
	  border-collapse: separate;
	  border-spacing: 0;
	  width: 100%;
	  margin: auto;
	}
	th,	td {
	  padding: 6px 15px;
	}
	th {
	  background: #42444e;
	  color: #fff;
	  text-align: center;
	}
	tr:first-child th:first-child {
	  border-top-left-radius: 6px;
	}
	tr:first-child th:last-child {
	  border-top-right-radius: 6px;
	}
	td {
	  border-right: 1px solid #c6c9cc;
	  border-bottom: 1px solid #c6c9cc;
	}
	td:first-child {
	  border-left: 1px solid #c6c9cc;
	}
	tr:nth-child(even) td {
	  background: #eaeaed;
	}
	tr:last-child td:first-child {
	  border-bottom-left-radius: 6px;
	}
	tr:last-child td:last-child {
	  border-bottom-right-radius: 6px;
	}
	#formDiv, #tableDiv, #homeDiv {
		text-align: center;
	}
	</style>
	
	<script>
	$(function() {
		$("#btnMain").click(function() {
			location.href="<%=request.getContextPath()%>/index.jsp";
		});
		$("#btnWrite").click(function() {
			location.href="<%=request.getContextPath()%>/recomboardWrite.do?rowSize=${rowSize}";
		});
	});
	</script>
	 
</head>
<body>
	<%-- ListArticleHandler 컨트롤러에 의해 아래와 같이 Model 받는다
		request.setAttribute("boardPage", boardPage);
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("rowSize", rowSize);
		request.setAttribute("col", col);
		request.setAttribute("word", word);
	
		*세션 : ${authUser} <br/>
		*pageNo(보고싶은페이지) : ${pageNo} <br/>
		*rowSize(한페이지당 글 개수) : ${rowSize}  <br/>
		*col(조회조건) : ${col}  <br/>
		*word(조회한 단어) : ${word}  <br/>
		*starPage : ${boardPage.startPage} <br/>
		*endPage :  ${boardPage.endPage} <br/>
		*totalPages : ${boardPage.totalPages} <br/><br/>
	--%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/mP.css" />

<header id="header" role="banner">
        <div id="site-title" name="site-title">
        <c:if test="${! empty authUser && (authUser.grade==999)}">	 
            <a href=""><h1></h1></a>      <!--사이트 제목-->
            <nav id="gnb" class="gnb">          <!-- 네비게이션 바-->
                <ul id="nav-ul">
                  <!--각자 맞게 자기 게시판에 태그 걸면 됩니다.-->
                  
                  <li><a href="notice.do">공지사항</a></li>
                  <li><a href="event.do">이벤트</a></li>
                  <li><a href="recomboardListboard.do">도서추천</a></li>
                  <li><a href="blist.do">독후감</a></li>
                  <li><a href="fList.do">자유게시판</a></li>
                  <li><a href="qList.do">1:1문의</a></li>
                  <li><a href="memberList.do">회원관리</a></li>
                  <li><a href="logout.do">로그아웃</a></li>
                  <li><a href="myPage.do">내 정보</a></li>
                </ul>
            </nav>
          	</c:if>
       	 <c:if test="${! empty authUser && (authUser.grade!=999)}">	  
            <a href=""><h1></h1></a>      <!--사이트 제목-->
            <nav id="gnb" class="gnb">          <!-- 네비게이션 바-->
                <ul id="nav-ul">
                  <!--각자 맞게 자기 게시판에 태그 걸면 됩니다.-->
                  
                  <li><a href="notice.do">공지사항</a></li>
                  <li><a href="event.do">이벤트</a></li>
                  <li><a href="recomboardListboard.do">도서추천</a></li>
                  <li><a href="blist.do">독후감</a></li>
                  <li><a href="fList.do">자유게시판</a></li>
                  <li><a href="qList.do">1:1문의</a></li>
                  <li><a href="logout.do">로그아웃</a></li>
                  <li><a href="myPage.do">내 정보</a></li>
                </ul>
            </nav>
          	</c:if>
          	<c:if test="${empty authUser}">
            <a href=""><h1></h1></a>      <!--사이트 제목-->
            <nav id="gnb" class="gnb">          <!-- 네비게이션 바-->
                <ul id="nav-ul">
                  <!--각자 맞게 자기 게시판에 태그 걸면 됩니다.-->
                  
                  <li><a href="notice.do">공지사항</a></li>
                  <li><a href="event.do">이벤트</a></li>
                  <li><a href="recomboardListboard.do">도서추천</a></li>
                  <li><a href="blist.do">독후감</a></li>
                  <li><a href="fList.do">자유게시판</a></li>
                  <li><a href="qList.do">1:1문의</a></li>
                  <li><a href="login.do">로그인/회원가입</a></li>
                </ul>
            </nav>
          	</c:if>
        </div>
      </header>
      <div id="Quick" class="" style="position: absolute; right: 10px; top: 400px;">
    <table class="quickMenuBar" style="">
        <tr>
            <td colspan="2" style="cursor:pointer; background-color:white;" onclick="location.href='<%=request.getContextPath()%>/index.do'">HOME</td>
        </tr>
        <tr>
            <td colspan="2" style="cursor:pointer; background-color:#E2E2E2;" onclick="window.scrollTo(0,0);">TOP</td>
        </tr>
    </table>
</div>
      <br/><br/><br/><br/><br/>
	<h2 align="center">추천게시판</h2>
	
	<br/>
	
	<div id="formDiv">
	<form name="frm" method="get" action="<%=request.getContextPath()%>/recomboardSearch.do">	
		조회조건 :
		<select name="col">
			<option value="">선택안함(전체조회)</option>
			<option value="searchId">아이디</option>
			<option value="searchTitle">제목</option>
			<option value="searchContent">내용</option>
			<!-- <option value="searchTitleContent">제목+내용</option> -->
			<!-- <option value="all">아이디+제목+내용</option> -->
		</select> <br/>
		
		페이지당 게시물 개수 :
		<select name="rowSize" id="rowSize">
			<option value="10">선택</option>
			<option value="3">3</option>
			<option value="5">5</option>
			<option value="10">10</option>
		</select> <br/>	
		
		<input type="text" name="word" value="" placeholder="특수문자는 사용 불가능">
		<button type="submit">검색</button>   	 
	</form>
	</div>
	
	<br/><br/>
	<div id="homeDiv">
		
	    <c:if test="${not empty authUser}">
	    	<input type="button" value="글쓰기" id="btnWrite">
		</c:if>
	</div>


    
    <div id="tableDiv">
    <table border="1" style="width: 1200px;">
    	<thead>
    		<tr>
    			<th>번호</th>
    			<th>제목</th>
    			<th>작성자 아이디</th>
    			<th>작성일</th>
    			<th>조회수</th>
    			<th>좋아요</th>
    		</tr>
    	</thead>
    	<tbody>
	    	<%-- 게시글이 없는 경우 --%>
	    	<c:if test="${boardPage.hasNoBoards()}">
	    	<tr>
	    		<td colspan="6" style="text-align: center;">게시글이 없습니다</td>
	    	</tr>
	    	</c:if>

			<%-- 게시글이 있는 경우 --%>
			<c:if test="${boardPage.hasBoards()}">
	    	<c:forEach var="item" items="${boardPage.listAll}">
	    	<tr> 
	   			<td>${item.rNo}</td>
	   			<td><a href="/recomboardRead.do?no=${item.rNo}&pageNo=${boardPage.currentPage}&rowSize=${rowSize}">${item.rTitle}</a></td>
	   			<td>${item.member.mId}</td>
	   			<td>
			        <fmt:formatDate pattern="yyyy.MM.dd. HH:mm:ss" value="${item.regDate}" />
	   			</td>
	   			<td>${item.rCnt}</td>
	   			<td>${item.likeIt}</td>
	   		</tr>	
	    	</c:forEach>
			</c:if>
	   
	   		<%--반복문 이용해서 출력 --%>
	   		
	   		<%-- paging 처리 --%>
	   		<tr>
	    		<td colspan="6" style="text-align: center;">
	    			<%-- JSTL if조건문 : 이전출력 --%>
	    			<c:if test="${boardPage.startPage > 5}">
	    				<a href="/recomboardListboard.do?pageNo=${boardPage.startPage-5}&rowSize=${rowSize}">prev</a>
	    			</c:if>
	    			
	    			<%-- JSTL forEach조건문 : 페이지번호출력 --%>
	    			<c:forEach var="pNo" begin="${boardPage.startPage}" end="${boardPage.endPage}">
	    				<a href="/recomboardListboard.do?pageNo=${pNo}&rowSize=${rowSize}">${pNo}</a>
	    			</c:forEach>
	    			
	    			<%-- JSTL if조건문 : 다음출력 --%>
	    			<c:if test="${boardPage.endPage < boardPage.totalPages}">
	    				<a href="/recomboardListboard.do?pageNo=${boardPage.startPage+5}&rowSize=${rowSize}">next</a>
	    			</c:if>
	    		</td>
	    	</tr>
    	</tbody>
    </table>
	</div>
	
</body>
</html>