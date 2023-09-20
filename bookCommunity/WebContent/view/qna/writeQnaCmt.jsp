<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<%
System.out.println("${q_no}="+(Integer)request.getAttribute("q_no"));  
System.out.println("${pageNo}="+(String)request.getAttribute("pageNo"));%> 
<c:redirect url="../qRead.do">
	<c:param name="q_no" value="${q_no}"/>
	<c:param name="pageNo" value="${pageNo}"/>
</c:redirect>