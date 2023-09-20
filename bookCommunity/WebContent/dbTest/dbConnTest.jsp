<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jdbc.conn.ConnectionProvider" %>    
<%@ page import="java.sql.Connection" %>    
<%@ page import="java.sql.SQLException" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DB연동테스트</title>
</head>
<body>
 <h2>DB연동테스트(p586)</h2>
<%
 /* 이 문서는 web.xml & 
 		DBCPInitListener.java & 
 		ConnectionProvider.java 등을 이용하여 db연동여부를 체크하는 문서 */
 		try{
 			Connection conn = ConnectionProvider.getConnection();
 			out.println("connection얻기 성공");//브라우저출력
 		}catch(SQLException e){
 			e.printStackTrace();//콘솔출력
 			out.println("connection얻기 실패");//브라우저출력
 		}
%> 
</body>
</html>







