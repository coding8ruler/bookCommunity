<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri= "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
<link rel="stylesheet" type="text/css" href='/css/util.css? after'/>
<link rel="stylesheet" type="text/css" href='/css/main.css? after'/>
<style>
	.error {color: red; font-size:0.8em;}
</style>
</head>
<body>
<a href="<%=request.getContextPath()%>/index.do">HOME</a>
<form Name="findPwdFrm" method="post" action="findPwd.do" >
<br/>
<center><font size='5'><b> 비밀번호 찾기 </b></font>
<div id="contact-form-overlay" class="clearfix bgcolor-grey">
<div class="fancy-title title-dotted-border"><h3>FIND PASSWORD</h3></div>

<div style="padding-left: 36%; padding-right: 36%;  padding-top: 100px;">
 <c:if test="${errors.idOrPwNotMatch}"><span class="error">일치하는 회원이 없습니다.</span></c:if>
		<div class="wrap-input100 validate-input m-b-26">   
	     <span class="label-input100">아이디</span>
	     <input class="input100" type="text" id="mId" name="mId" size="20" placeholder="Enter userid">
	      <c:if test="${errors.mId}"><span class="error">가입시 입력한 아이디를 입력하세요(필수 입력)</span></c:if>
		  <c:if test="${errors.zzmId}">일치하는 아이디가 없습니다.</c:if> </div>

	<div class="wrap-input100 validate-input m-b-26">   
	     <span class="label-input100">이름</span>
	     <input class="input100" type="text" id="name" name="name" size="20" placeholder="Enter username">
	      <c:if test="${errors.mName}"><span class="error">이름을 입력하세요</span></c:if> </div>

<div class="wrap-input100 validate-input m-b-26">   
	     <span class="label-input100">이메일</span>
	     <input class="input100" type="text" id="email" name="email" size="20" placeholder="Enter email">
	      <c:if test="${errors.email}"><span class="error">이메일을 입력하세요</span></c:if> 
</div>
	
</div>
<div class="container-login100-form-btn" style="padding-left:42%;" >
	     <button class = "login100-form-btn" type="submit">찾기</button>
		 <button class = "login100-form-btn" type="button" onclick="location.href='/login.do' ">취소</button>
</div>
</div>
</form>
</body>
</html>