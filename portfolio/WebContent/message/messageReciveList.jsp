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
<link href="${conPath }/css/messageList.css" rel="stylesheet">
<style>
</style>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script>
	$(function(){
		$('.1').css('color', 'gray');
		$('.0').css('color', 'blue');
		window.resizeTo(530, 440);
	});
</script>
</head>
<body>
<div id="wrap">
	<a href="${conPath }/mesSendList.mes">보낸 메일함 보기</a>
	<table>
		<tr><th class="from">보낸사람</th><th class="contentth">제목</th><th>보낸날</th></tr>
		<c:forEach items="${list }" var="l">
			<tr>
				<td class="from">${l.mId }</td>
				<td class="content"><a href="${conPath }/mesView.mes?mesNum=${l.mesNum}" class="${l.mesRead }">${l.mesTitle }</a></td>
				<fmt:formatDate value="${l.mesDate }" var="mesDate" pattern="YY-MM-dd HH:mm" />
				<td>${mesDate }</td>
			</tr>
		</c:forEach>
	</table>
	<p>
		<c:if test="${startPage>10 }"><a href="${conPath }/mesReciveList.mes?pageNum=${startPage-1 }">[이전]&nbsp;</a></c:if>
		<c:forEach begin="${startPage }" end="${endPage }" var="i">
			<c:if test="${i==currentPage }">${i }&nbsp;</c:if>
			<c:if test="${i!=currentPage }"><a href="${conPath }/mesReciveList.mes?pageNum=${i }">${i }</a>&nbsp;</c:if>
		</c:forEach>
		<c:if test="${endPage!=pageCnt }"><a href="${conPath }/mesReciveList.mes?pageNum=${endPage+1 }">[다음]</a></c:if>
	</p>
</div>
</body>
</html>