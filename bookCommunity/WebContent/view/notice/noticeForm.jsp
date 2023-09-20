<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
   
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>게시판 목록</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<script src="http://code.jquery.com/jquery-latest.js"></script>
 	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/noticePage.css" />
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
    <table class="quickMenuBar" style=" border: 1px solid red; border-radius: 20px;">
        <tr>
            <td colspan="2" style="cursor:pointer; background-color:white;" onclick="location.href='<%=request.getContextPath()%>/index.do'">HOME</td>
        </tr>
        <tr>
            <td colspan="2" style="cursor:pointer; background-color:#E2E2E2;" onclick="window.scrollTo(0,0);">TOP</td>
        </tr>
    </table>
</div>
	<div class="container">
	<br/><br/><br/><br/><br/>
	<h2>공지사항</h2>
	<hr>
	<br>
	<div class="ui-board-info">
	<div class="page-search-box fl-right">
	<form method="get" action="<%=request.getContextPath()%>/search.do" name="searchFrm" id="searchFrm">
	<div class="search_bbs">
	<p class="page_num">전체 <span> ${noticePage.total}</span>건 <span>${noticePage.currentPage}/${noticePage.totalPages}</span> 페이지</p>
	<fieldset>
		<div class="board_search">
		<select name="rowSize" id="rowSize">
			<option value="10">10건</option>
			<option value="20">20건</option>
			<option value="30">30건</option>
		</select>
		<select name="choice" id="choice">
			<option value="all" ${(param.choice == "all")?"selected":""}>전체</option>
			<option value="title" ${(param.choice == "title")?"selected":""} >제목</option>
			<option value="content" ${(param.choice == "content")?"selected":""}>내용</option>		
		</select>
		<input type="text" name="searchText" id="searchText" value="${param.searchText}" class="inputText" placeholder="검색어를 입력하세요">
		<button  title="검색" class="txt_button" onClick="submit">검색</button>
		<%-- <input type="submit" value="검색"> --%> 
	</div>
	</fieldset>
	</div>
	</form>
	</div>
	</div>
	</div>
	<br><br>
	<div class="container">
	<form>
    <table class="bbsList">
    <colgroup>
		<col style="width:10%;" data-table="number">
		<col style="width:40%;" data-table="title">
		<col style="width:12%;" data-table="write">
		<col style="width:13%;" data-table="date">
		<col style="width:10%;" data-table="number">
	</colgroup>
    	<thead>
    		<tr>
    			<th scope="col" data-table="number">번호</th>
    			<th scope="col" data-table="title">제목</th>
    			<th scope="col" data-table="write">작성자</th>
    			<th scope="col" data-table="date">작성일</th>
    			<th scope="col" data-table="number">조회수</th>
    		</tr>
    	</thead>
    	<tbody>
	    	<c:if test="${noticePage.hasNoNotices()}">
	    	<tr>
	    		<td colspan="5" style="text-align: center;">게시글이 없습니다</td>
	    	</tr>
	    	</c:if>
			<c:if test="${noticePage.hasNotices()}">
	    	<c:forEach var="notice" items="${noticePage.content}">
	    	<tr> 
	   			<td data-table="number">${notice.n_no}</td>
	   			<td data-table="subject"><a href="<%=request.getContextPath()%>/nRead.do?no=${notice.n_no}&pageNo=${noticePage.currentPage}">${notice.n_title}</a></td>
	   			<td>${notice.n_writer.n_writername}</td>
	   			<td data-table="datd">
			        <fmt:formatDate pattern="yyyy년 MM월 dd일" value="${notice.regdate}" /><br/>
	   			</td>
	   			<td data-table="number">${notice.n_cnt}</td>
	   		</tr>	
	    	</c:forEach>
			</c:if>
    	</tbody>
    </table>
	</form>
   	<c:if test="${! empty authUser && (authUser.grade==999)}">
	<button onclick="location.href='<%=request.getContextPath()%>/nWrite.do?rowSize=${size}'" class="writebtn">글쓰기</button> 
	</c:if>
    <div class="pagination">
	    <c:if test="${noticePage.startPage > 5}">
	    <a href="<%=request.getContextPath()%>/notice.do?pageNo=${noticePage.startPage-5}&rowSize=${size}">prev</a>
	    </c:if>
	    <c:forEach var="pNo" begin="${noticePage.startPage}" end="${noticePage.endPage}">
	    <a href="<%=request.getContextPath()%>/notice.do?pageNo=${pNo}&rowSize=${size}">${pNo}</a>
	   	</c:forEach>
	    <c:if test="${noticePage.endPage < noticePage.totalPages}">
	    <a href="<%=request.getContextPath()%>/notice.do?pageNo=${noticePage.startPage+5}&rowSize=${size}">next</a>
	    </c:if>
	    </div>  
	</div>
</body>
</html>

