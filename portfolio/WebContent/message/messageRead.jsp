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
<link href="${conPath }/css/messageVR.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script>
	$(function(){
		window.resizeTo(600,430)
	});
</script>
</head>
<body>
<div id="wrap">
	<table>
		<tr>
			<th>제목</th>
			<td colspan="3">${mesRead.mesTitle }</td>
		</tr>
		<tr>
			<th>보낸날</th>
			<td colspan="3">${mesRead.mesDate }</td>
		</tr>
		<tr>
			<th>보낸사람</th>
			<td>${mesRead.mId }</td>
			<th>받는사람</th>
			<td>${mesRead.mName} (${mesRead.mesFrom })</td>
		</tr>
		<tr>
			<th colspan="4">내용</th>
		</tr>
		<tr>
			<td colspan="4" id="content">${mesRead.mesCon }</td>
		</tr>
		<tr>
			<td colspan="4">
				<button onclick="history.back()">뒤로가기</button>
			</td>
		</tr>
	</table>
</div>
</body>
</html>