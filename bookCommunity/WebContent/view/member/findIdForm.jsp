<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri= "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
<link rel="stylesheet" type="text/css" href='/css/util.css? after'/>
<link rel="stylesheet" type="text/css" href='/css/main.css? after'/>
<style>
	.error {color: red; font-size:0.8em;}
</style>
</head>
<body>
<a href="<%=request.getContextPath()%>/index.do">HOME</a>
<form Name="findIdFrm" method="post" action="findId.do" >
<br/>
<center><font size='5'><b> 아이디 찾기 </b></font>
<div id="contact-form-overlay" class="clearfix bgcolor-grey">
<div class="fancy-title title-dotted-border"><h3>FIND ID</h3></div>

<div style="padding-left: 36%; padding-right: 36%;  padding-top: 100px;">
	<div class="wrap-input100 validate-input m-b-26">   
	     <span class="label-input100">이름</span>
	     <c:if test="${errors.idOrPwNotMatch}"><span class="error">일치하는 회원이 없습니다.</span></c:if>
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