<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html lang="ko">
<head>
 <meta charset="UTF-8">
 <title></title>
 <style></style>
 <script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
</head>
<body>
 <c:if test="${cnt==1}">
	 <script>
	  alert("삭제성공");
	 </script>
 </c:if>
  <c:if test="${cnt eq 0}">	 
	 <script>  
	  alert("삭제실패");
	 </script>
  </c:if>
  <script>      
  location.href="<%=request.getContextPath()%>/fList.do";
  </script>
</body>
</html>











