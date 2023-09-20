<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 정보</title>
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
	#submit {
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
    <table class="quickMenuBar" style="">
        <tr>
            <td colspan="2" style="cursor:pointer; background-color:white;" onclick="location.href='<%=request.getContextPath()%>/index.do'">HOME</td>
        </tr>
        <tr>
            <td colspan="2" style="cursor:pointer; background-color:#E2E2E2;" onclick="window.scrollTo(0,0);">TOP</td>
        </tr>
    </table>
</div>
<br/><br/><br/>
<form action="myPage.do" id="myPageFrm" name="myPageFrm" method="post">
<br/>
<h3 align="center">${authUser.mName}님</h3>
<table border="1" style="width: 200px; margin-left: auto; margin-right: auto;">
	<tr>
		<th>아이디</th>
	</tr>
	<tr>
		<td style="text-align:center">${authUser.mId }</td>
	</tr>
	<tr>
		<th>이름</th>
	</tr>
	<tr>
		<td style="text-align:center">${authUser.mName }</td>
	</tr>
	<tr>
		<th>비밀번호</th>
	</tr>
	<tr>
		<td><input type="password" name="mPwd" id="mPwd" value="${authUser.mPwd}"></td>
		<c:if test="${errors.mPwd}">비밀번호를 입력하세요.</c:if>
	</tr>
	<tr>
		<th>이메일</th>
	</tr>
	<tr>
		<td><input type="text" name="email" id="email" value="${authUser.email }" required="required"></td>
	</tr>
	<tr>
		<th>우편번호</th>
	</tr>
	<tr>
		<td><input type="text" name="postcode" id="posecode" value="${authUser.postcode }" min="5" max="5" required="required"></td>
	</tr>
	<tr>
		<th>주소</th>
	</tr>
	<tr>
		<td><input type="text" name="jibunAddress" id="jibunAddress" value="${authUser.jibunAddress }" required="required"></td>
	</tr>
	<tr>
		<th>성별</th>
	</tr>
	<tr>
	<c:choose>
    	<c:when test="${authUser.gender=='0' }">
      	 	<td style="text-align:center">남성</td>
    	</c:when>
    	<c:otherwise>
      	  	<td style="text-align:center">여성</td>
   		 </c:otherwise>
	</c:choose>
	</tr>
	<tr>
		<th>등급</th>
	</tr>
	<tr>
	<c:choose>
    	<c:when test="${authUser.grade==999 }">
      	 	<td style="text-align:center">관리자</td>
    	</c:when>
    	<c:otherwise>
      	  	<td style="text-align:center">회원</td>
   		 </c:otherwise>
	</c:choose>
	</tr>
</table>
<div id="submit">
<input type="submit" value="수정" >
</div>
</form>
</body>
</html>