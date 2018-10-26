<%@page import="com.tj.dto.BoardDto"%>
<%@page import="com.tj.dao.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${conPath }/css/reset.css" rel="stylesheet">
<link href="${conPath }/css/content.css" rel="stylesheet">
<style>
</style>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
</head>
<body>
<div id="wrap">
<%
	BoardDao dao = BoardDao.getInstance();
	BoardDto dto = dao.view(1);
	
	for(int i=0; i<150; i++){
		dao.insert(dto);
	}
	
	dto.setBaNum(2);
	for(int i=0; i<150; i++){
		dao.insert(dto);
	}
	
	dto.setBaNum(1);
	for(int i=0; i<150; i++){
		dao.insert(dto);
	}
%>
</div>
</body>
</html>