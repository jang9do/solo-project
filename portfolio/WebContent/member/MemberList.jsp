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
		white-space: nowrap; 
	}
</style>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
</head>
<body>
<div id="wrap">
	<jsp:include page="../main/header.jsp" />
	<div id="content">
		<table>
			<tr>
				<th>아이디</th><th>이름</th><th>계급</th><th>휴대전화</th><th>주소</th>
			</tr>
			<c:forEach items="${list }" var="l">
			<tr>
				<td><a href="${conPath }/MemberView.mem?mId=${l.mId}">${l.mId }</a></td>
				<td>${l.mName }</td>
				<td>${l.lName }</td>
				<td>${l.mPhone }</td>
				<td>${l.mAddress }</td>
			</tr>
			</c:forEach>
		</table>
		<p>
			<c:if test="${startPage>10 }"><a href="${conPath }/MemberList.mem?pageNum=${startPage-1 }">[이전]</a></c:if>
			<c:forEach begin="${startPage }" end="${endPage }" var="i" step="1">
				<c:if test="${i==currentPage }">${i }&nbsp;</c:if>
				<c:if test="${i!=currentPage }"><a href="${conPath }/MemberList.mem?pageNum=${i }">${i }&nbsp;</a></c:if>
			</c:forEach>
			<c:if test="${endPage!=pageCnt }"><a href="${conPath }/MemberList.mem?pageNum=${endPage+1 }">[다음]</a></c:if>
		</p>
	</div>
	<jsp:include page="../main/footer.jsp" />
</div>
</body>
</html>