<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
   
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>자유게시판 목록</title>
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
	<script type="text/javascript">
	$(document).ready(function () {
		$("#rowSize").change(function(){
		   $("#rowSizeFrm").submit();
		});
	});
	</script>
</head>
<body>
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
<br/><br/><br/><br/>
	<h2 align="center">자유게시판</h2>

    <div id="tableDiv">
    <table border="1" style="width: 1200px;">
    	<thead>
    		<tr>
    			<th>글번호</th>
    			<th>작성자</th>
    			<th>제목</th>
    			<th>작성일</th>
    			<th>조회수</th>
    		</tr>
    	</thead>
    	<tbody>
	    	<c:if test="${freeboardPage.hasNoFreeboards()}">
	    	<tr>
	    		<td colspan="5" style="text-align: center;">게시글이 없습니다</td>
	    	</tr>
	    	</c:if>
			<c:if test="${freeboardPage.hasFreeboards()}">
	    	<c:forEach var="freeboard" items="${freeboardPage.content}">
	    	<tr> 
	   			<td>${freeboard.fNo}</td>
	   			<td>${freeboard.fWriter.writerName}</td>
	   			<td><a href="<%=request.getContextPath()%>/fRead.do?no=${freeboard.fNo}&pageNo=${freeboardPage.currentPage}&rowSize=${size}">${freeboard.fTitle}</a></td>
	   			<td>
			        <fmt:formatDate pattern="yyyy년 MM월 dd일" value="${freeboard.regDate}" /><br/>
	   			</td>
	   			<td>${freeboard.fCnt}</td>
	   		</tr>	
	    	</c:forEach>
			</c:if>
	   		<tr>
	    		<td colspan="5" style="text-align: center;">
	    			<c:if test="${freeboardPage.startPage > 5}">
	    				<a href="<%=request.getContextPath()%>/fList.do?pageNo=${freeboardPage.startPage-5}&rowSize=${size}">prev</a>
	    			</c:if>
	    			<c:forEach var="pNo" begin="${freeboardPage.startPage}" end="${freeboardPage.endPage}">
	    				<a href="<%=request.getContextPath()%>/fList.do?pageNo=${pNo}&rowSize=${size}">${pNo}</a>
	    			</c:forEach>
	    			<c:if test="${freeboardPage.endPage < freeboardPage.totalPages}">
	    				<a href="<%=request.getContextPath()%>/fList.do?pageNo=${freeboardPage.startPage+5}&rowSize=${size}">next</a>
	    			</c:if>
	    		</td>
	    	</tr>
    	</tbody>
    </table>
    </div>
   
   
   <div id="formDiv">
    <form name="rowSizeFrm" id="rowSizeFrm" action="<%=request.getContextPath()%>/fList.do" method="get">
        게시물수 :
		<select name="rowSize" id="rowSize">
			<option value="3">선택</option>
			<option value="3">3</option>
			<option value="5">5</option>
			<option value="10">10</option>
		</select>
    </form>
	<a href="<%=request.getContextPath()%>/fWrite.do?rowSize=${size}">글 쓰기</a><br/>
   </div>
</body>
</html>

