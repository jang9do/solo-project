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
	#wrap #content table tr .rDate{
		width: 150px;
	}
</style>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
</head>
<body>
<c:if test="${not empty rDelete && rDelete==1}"><script>alert('삭제성공')</script></c:if>
<c:if test="${not empty rDelete && rDelete==0}"><script>alert('삭제실패')</script></c:if>
<div id="wrap">
	<jsp:include page="../main/header.jsp" />
	<div id="content">
		<table>
		 	<tr><th>진료시간</th><th>진료종류</th><th>진료동물</th></tr>
		 	<c:forEach items="${mIdList }" var="d">
		 		<fmt:formatDate value="${d.rDate }" pattern="YY-MM-dd HH:mm" var="rDate"/>
		 		<tr><td class="rDate">${rDate }</td><td><a href="${conPath }/reserveRead.res?rNum=${d.rNum }">${d.rDetail }</a></td><td>${d.aName }</td></tr>
		 	</c:forEach>
		</table>
	</div>
	<jsp:include page="../main/footer.jsp" />
</div>
</body>
</html>