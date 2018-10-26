<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${conPath }/css/reset.css" rel="stylesheet">
<title>Insert title here</title>
<style>
	footer{
		margin-left: 250px;
		width: calc(100% - 250px);
		height: 100px;
		background-color: #192325;
	}
	footer img{
		height: 100px;
		width: 130px;
		margin-left: 100px;
		float: left;
	}
	footer span{
		color: white;;
		margin-left: 30px;
		float: left;
		line-height: 20px;
		display: block;
		margin-top: 20px;
	}
</style>
</head>
<body>
<footer>
	<img src="${conPath }/img/puppy.jpg">
	<span>jang's 동물병원<br>
	서울시 도봉구 도봉1동 610-23<br>
	jang9do@naver.com</span>
</footer>
</body>
</html>