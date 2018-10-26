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
<script>

</script>
</head>
<body>
<c:if test="${not empty rDelete && rDelete==1}"><script>alert('삭제성공')</script></c:if>
<c:if test="${not empty rDelete && rDelete==0}"><script>alert('삭제실패')</script></c:if>
<c:if test="${not empty rFinish && rFinish==1}"><script>alert('진료완료')</script></c:if>
<c:if test="${not empty rFinish && rFinish==0}"><script>alert('페이지 오류')</script></c:if>
<div id="wrap">
	<jsp:include page="../main/header.jsp" />
	<div id="content">
		<form action="${conPath }/ReserveMList.res">
		 	<p><input type="date" name="day" value="${param.day }"><input type="submit" value="검색"></p>
		</form>
		<table>
		 	<tr><th>진료시간</th><th>진료종류</th></tr>
		 	<c:forEach items="${dayList }" var="d">
		 		<fmt:formatDate value="${d.rDate }" pattern="HH:mm" var="rDate"/>
		 		<tr><td>${rDate }</td><td><a href="${conPath }/reserveRead.res?rNum=${d.rNum }">${d.rDetail }</a></td></tr>
		 	</c:forEach>
		</table>
	</div>
	<jsp:include page="../main/footer.jsp" />
</div>
</body>
</html>