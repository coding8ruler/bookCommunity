<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 조회</title>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/mP.css" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/noticePage.css" />
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
<br/><br/><br/><br/><br/>

	<div class="container" style="padding-top:10px;">
	<center><h2>회원관리</h2></center>
	<hr>
	<br>
<form action="memberUpdate.do" id="updateFrm" name="updateFrm" method="post">
<table class="bbsList">
    <colgroup>
		<col style="width:10%;" data-table="number">
		<col style="width:10%;" data-table="id">
		<col style="width:10%;" data-table="name">
		<col style="width:10%;" data-table="email">
		<col style="width:10%;" data-table="postcode">
		<col style="width:20%;" data-table="Addr">
		<col style="width:10%;" data-table="gender">
		<col style="width:5%;" data-table="grade">
		<col style="width:30%;" data-table="join">
		<col style="width:15%;" data-table="delete">
	</colgroup>
    	<thead>
    		<tr>
    			<th scope="col" data-table="number">회원 번호</th>
    			<th scope="col" data-table="id">회원 아이디</th>
    			<th scope="col" data-table="name">회원 이름</th>
    			<th scope="col" data-table="email">이메일</th>
    			<th scope="col" data-table="postcode">우편 번호</th>
    			<th scope="col" data-table="Addr">주소</th>
    			<th scope="col" data-table="gender">성별</th>
    			<th scope="col" data-table="grade">등급</th>
    			<th scope="col" data-table="join">가입일</th>
    			<th scope="col" data-table="delete"></th>
    		</tr>
    	</thead>
    	<tbody>
	    	<c:if test="${empty memberList }">
			<tr>
				<td colspan="6" style="text-aling:center">회원이 없습니다.</td>
			</tr>
			</c:if>
			<c:if test="${!empty memberList }">
			<c:forEach var="member" items="${memberList }">
	    	<tr> 
	   			<td data-table="number">${member.m_no}</td>
	   			<td data-table="subject">${member.mId }</td>
	   			<td>${member.mName }</td>
	   			<td>${member.email }</td>
	   			<td>${member.postcode }</td>
	   			<td>${member.jibunAddress }</td>
	   			<td>${member.gender }</td>
				<td><select name="grade" id="grade">
 				<option value="${member.grade }">${member.grade}</option>  
 				<option value="1">회원</option>
 				<option value="999">관리자</option>
 			    </select></td>
	   			<td data-table="datd">
			        <fmt:formatDate pattern="yyyy년 MM월 dd일" value="${member.mJoin }" /><br/>
	   			</td>
	   			<input type="hidden" id="mId${member.m_no}" name="mId" value="${member.mId}">
	   			<td><a href="/memberDelete.do?no=${member.m_no}">삭제</a></td>
	   		</tr>	
	    	</c:forEach>
			</c:if>
    	</tbody>
    </table>
    <span style="padding-left:90%;"><input type="submit" value="등급 변경"></span>
</form>
</body>
</html>