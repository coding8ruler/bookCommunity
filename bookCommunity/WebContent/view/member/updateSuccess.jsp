<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title></title>
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<style></style>
	<script></script>
</head>
<body>
	 <script>
	  alert("수정완료 정보가 수정되어 다시 로그인바랍니다.");
	  location.href="<%=request.getContextPath()%>/logout.do";
	 </script>
</body>
</html>