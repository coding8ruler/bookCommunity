<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>이벤트게시판 글</title>
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
	.center {
		text-align: center;
	}
	body {
	  padding:1.5em;
	  background: #f5f5f5
	}	
	table {
	  border: 1px #a39485 solid;
	  font-size: .9em;
	  box-shadow: 0 2px 5px rgba(0,0,0,.25);
	  width: 100%;
	  border-collapse: collapse;
	  border-radius: 5px;
	  overflow: hidden;
	}	
	th {
	  text-align: center;
	}	  
	thead {
	  font-weight: bold;
	  color: #fff;
	  background: #73685d;
	}  
	td, th {
	  padding: 1em .5em;
	  vertical-align: middle;
	}  
	td {
	  border-bottom: 1px solid rgba(0,0,0,.1);
	  background: #fff;
	}
	a {
	  color: #73685d;
	} 
	.fileButton {
	  padding: 6px 25px;
	  background-color:#00a2ff;
	  border-radius: 4px;
	  color: white;
	  cursor: pointer;
	}
	input {
	  width: 400px;
	  height: 30px; 	
	}
	#btnDelete, #btnList, #btnUpdate {
	  width: 80px;
	  height: 30px; 	
	} 
	#homeDiv {
	  text-align: center;	
	}
	</style>
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
      <br/><br/><br/><br/>
    <div align="center">
    <h2>이벤트</h2>
    <table border="1" style="height:200px; width: 600px;">
    	<tr>
    		<th>번호</th>
    		<td>${eventData.event.e_no}</td>
    	</tr>
    	<tr>
    		<th>작성자명</th>
    		<td>${eventData.event.e_writer.e_writername}</td>
    	</tr>
    	<tr>
    		<th>작성일</th>
    		<td><fmt:formatDate pattern="yyyy년 MM월 dd일" value="${eventData.event.regdate}"/></td>
    	</tr>
    	<tr>
    		<th>제목</th>
    		<td>${eventData.event.e_title}</td>
    	</tr>
    	<tr>
    		<th>내용</th>
    		<td>${eventData.event.e_content}</td>
    	</tr>
    </table>
    	<c:set var="pgNo" value="${ (empty param.pageNo) ? '1' : param.pageNo }"></c:set>
    	<a href="<%=request.getContextPath()%>/event.do?rowSize=${rowSize}">목록보기</a>
    	<c:if test="${! empty authUser && (authUser.grade==999)}">
	    <a href="<%=request.getContextPath()%>/eUpdate.do?no=${eventData.event.e_no}&pageNo=${pgNo}&rowSize=${rowSize}">글 수정</a>
	    <a href="<%=request.getContextPath()%>/eDelete.do?no=${eventData.event.e_no}">글 삭제</a>
	    </c:if>
	    </div>
</body>
</html>