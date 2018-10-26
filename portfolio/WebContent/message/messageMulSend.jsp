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
<link href="${conPath }/css/messageSend.css" rel="stylesheet">
<style>
</style>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
</head>
<body>
<div id="wrap">
	<p><span>쪽지보내기</span><a href="${conPath }/mesSendView.mes"><- 일반 쪽지보내기</a></p>
	<form action="${conPath }/messageMulSend.mes" method="post">
		<input type="hidden" value="${login.mId }" name="mId">
		<table>
			<tr>
				<th>제목</th>
				<td colspan="3"><input type="text" name="mesTitle" required="required"></td>
			</tr>
			<tr>
				<th>보내는 사람</th>
				<td>${login.mName } (${login.mId })</td>
				<th>받는 사람</th>
				<td><select name="levelN">
    					<option value="2">관리자</option>
    					<option value="3">우수회원</option>
    					<option value="4">일반회원</option>
    					<option value="5" selected="selected">회원전체</option>
					</select></td>
			</tr>
			<tr>
				<th colspan="4">내용</th>
			</tr>
			<tr>
				<td colspan="4"><textarea cols="60" rows="10" required="required" name="mesCon"></textarea></td>
			</tr>
			<tr>
				<td colspan="4"><input type="submit" value="전송"></td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>