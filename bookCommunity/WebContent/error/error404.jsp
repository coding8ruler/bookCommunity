<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
 /*이미지를 full 배경으로 적용*/
 html { 
  background: url(images/error404.png) no-repeat center center fixed;
  -webkit-background-size: cover;
  -moz-background-size: cover;
  -o-background-size: cover;
  background-size: cover;}
</style>
</head>
<body>
<!--  기준  http://localhost:8088/webPro/error/error404.jsp
                              ../images/error404.png
 이미지http://localhost:8088/webPro/images/error404.png -->
 <h2 >요청하신 페이지는 존재하지 않습니다</h2>
 <p>주소를 올바르게 입력했는지 확인해보시기 바랍니다</p>
<!--  <img src="./images/error404.png"/> -->
</body>
</html>