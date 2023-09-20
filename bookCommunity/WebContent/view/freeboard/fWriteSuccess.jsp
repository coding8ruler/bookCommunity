<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html lang="ko">
<head>
 <meta charset="UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
 <title></title>
	 <script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
 	 <style></style>
	 <script></script>	
</head>
<body>
	 <a href="<%=request.getContextPath()%>/index.jsp">HOME</a>
	 <hr/>
	 <h3>자유게시판</h3>
	 <a href="<%=request.getContextPath()%>/fList.do?rowSize=${rowSize}">목록보기</a>
	 <a href="<%=request.getContextPath()%>/fRead.do?no=${newFreeboardNo}&pageNo=1&rowSize=${rowSize}">글상세보기</a>
</body>
</html>

