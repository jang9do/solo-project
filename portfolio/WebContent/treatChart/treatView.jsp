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
	#content table tr td{
		width: 250px;
	}
</style>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
</head>
<body>
<div id="wrap">
	<jsp:include page="../main/header.jsp" />
	<div id="content">
		<table>
			<caption>진료기록</caption>
			<tr>
				<th>진료번호</th>
				<td>${tRead.tNum }</td>
			</tr>
			<tr>
				<th>진료대상</th>
				<td>(등록번호 ${tRead.aNum }) ${tRead.aName }</td>
			</tr>
			<tr>
				<th>진료일</th>
				<fmt:formatDate value="${tRead.tDate }" var="tDate" pattern="YYYY-MM-dd HH:mm" />
				<td>${tDate }</td>
			</tr>
			<tr>
				<th>의뢰내용</th>
				<td>${tRead.tDetail }</td>
			</tr>
			<tr>
				<th>진료결과</th>
				<td>${tRead.tResult }</td>
			</tr>
			<tr><td colspan="2"><button onclick="history.back()">뒤로가기</button></td></tr>
		</table>
	</div>
	<jsp:include page="../main/footer.jsp" />
</div>
</body>
</html>