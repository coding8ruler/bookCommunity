<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>글작성</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<script src="http://code.jquery.com/jquery-latest.js"></script>
 	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/writePage.css" />
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
	<h2>글작성</h2>
    <hr/>
	<form name="writeFrm" id="writeFrm" method="post" action="<%=request.getContextPath()%>/nWrite.do">
		<input type="hidden" name="rowSize" id="rowSize" value="${rowSize}"/>
		<div class="form-group">
			<label for="writer">작성자</label>
			<input type="text" name="writername" class="form-control" value="${authUser.mName}">
		 </div>
		 <div class="form-group">
			<label for="title">제목</label>
			<input type="text" name="N_TITLE" id="N_TITLE" class="form-control" placeholder="제목을 입력하세요">
			<span class="error"><c:if test="${errors.N_TITLE}">제목을 입력하세요</c:if></span>
		 </div>
		 <div class="form-group">
		 	<textarea name="N_CONTENT" id="N_CONTENT" rows="20" cols="100" placeholder="내용을 입력하세요"></textarea>
		 	<span class="error"><c:if test="${errors.N_CONTENT}">내용을입력하세요</c:if></span>
		 </div>
	<script src="https://cdn.ckeditor.com/ckeditor5/36.0.0/classic/ckeditor.js"></script>
	<script>
      ClassicEditor.create( document.querySelector( '#N_CONTENT' ));
      language: "ko"
    </script>
    <div class="find-btn">
		<button onClick="submit" class="write_btn">등록</button>
	</div>
	</form>
	<%-- <a href="<%=request.getContextPath()%>/notice.do?rowSize=${rowSize}">목록보기</a> --%>
    </div>
</body>
</html>






