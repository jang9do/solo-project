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
<link href="${conPath}/css/reset.css" rel="stylesheet">
<link href="${conPath}/css/content.css" rel="stylesheet">
<style>
</style>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
</head>
<body>
<div id="wrap">
	<jsp:include page="../main/header.jsp" />
	<div id="content">
		<table>
			<tr>
				<td colspan="2">무엇을 잊어버리셨나요?</td>
			</tr>
			<tr>
				<td><a href="${conPath }/member/getId.jsp">아이디</a></td>
				<td><a href="${conPath }/member/getPassword.jsp">비밀번호</a></td>
			</tr>
		</table>
	</div>
	<jsp:include page="../main/footer.jsp" />
</div>
</body>
</html>