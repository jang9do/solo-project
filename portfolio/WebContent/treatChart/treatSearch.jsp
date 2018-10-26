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
	#content table tr .tDate{
		width: 200px;
	}
</style>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
</head>
<body>
<div id="wrap">
	<jsp:include page="../main/header.jsp" />
	<div id="content">
		<form action="${conPath }/treatSearch.tre">
			<p>날짜 : <input type="date" name="tDate" value="${param.tDate }"> 동물이름 : <input type="text" name="aName">&nbsp;&nbsp;<input type="submit" value="검색"></p>
		</form>
		<table>
			<tr>
				<th>진료번호</th><th class="tDate">진료 날짜</th><th>진료내용</th><th>이름</th>
			</tr>
			<c:forEach items="${tSearchList }" var="t">
				<fmt:formatDate value="${t.tDate }" var="tDate" pattern="YYYY-MM-dd HH:mm" />
				<tr>
					<td>${t.tNum }</td><td>${tDate }</td><td><a href="${conPath }/treatView.tre?tNum=${t.tNum }">${t.tDetail}</a></td><td>${t.aName }</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<jsp:include page="../main/footer.jsp" />
</div>
</body>
</html>