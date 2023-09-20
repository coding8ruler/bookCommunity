<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri= "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<style>
	.error {color: red; font-size:0.8em;}
</style>
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href='/css/util.css? after'/>
<link rel="stylesheet" type="text/css" href='/css/main.css? after'/>
<!--===============================================================================================-->
</head>
<body>
<a href="<%=request.getContextPath()%>/index.do">HOME</a>
<%-- 컨트롤러를 거쳐오면 아래와 같은 모델을 받아온다.
	 request객체.setAttribute("속성명", 값);
	 session객체.setAttribute("속성명", 값); 
	 --%>
<div style="padding-left: 350px; padding-right: 350px; padding-top: 100px;">
 <div class="login100-form-title" style="background-image: url(<c:url value='/images/book.jfif'/>);">
 	 <span class="login100-form-title-1">로 그 인</span>
  </div>

<form class="login100-form" action="login.do" id="loginFrm" name="loginFrm" method="post">
<c:if test="${errors.idOrPwNotMatch}"><span class="error">아이디와 암호가 일치하지 않습니다.</span></c:if>

<div class="wrap-input100 validate-input m-b-26">   
	     <span class="label-input100">아이디</span>
	     <input class="input100" type="text" id="mId" name="mId" size="20" value="adminid" placeholder="Enter username">
	      <c:if test="${errors.mId}"><span class="error">아이디를 입력하세요</span></c:if> 
</div>

<div class="wrap-input100 validate-input m-b-18">
     	<span class="label-input100">비밀번호</span>
     	<input class="input100" type="password" id="mPwd" name="mPwd" value="1234" placeholder="Enter password">
     	<c:if test="${errors.mPwd}"><span class="error">패스워드를 입력하세요</span></c:if>
</div>


<div class="container-login100-form-btn" style="padding-left:27%;" >
	     <button class = "login100-form-btn" type="submit">LOGIN</button>
		 <button class = "login100-form-btn" type="button" onclick="location.href='<c:url value="join.do"/>'">JOIN</button>
     </div>

<div style="padding-left: 34%; padding-top: 25px">
	  	 <a href="findId.do">아이디 찾기 </a>/<a href="findPwd.do"> 비밀번호 찾기</a>
    	</div>

</form>
</div>
</body>
</html>