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
	#wrap table tr td input[type=button]{	
		background-color: #3378C1;
		border: 1px solid blue;
		width: 100%;
		color: white;
	} 
</style>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
</head>
<body>
<div id="wrap">
	<form action="${conPath }/mesReply.mes" method="post">
		<input type="hidden" value="${login.mId }" name="mId">
		<table>
			<tr>
				<th>제목</th>
				<td colspan="3"><input type="text" name="mesTitle" value="[re]"></td>
			</tr>
			<tr>
				<th>보내는 사람</th>
				<td>${login.mName } (${login.mId })</td>
				<th>받는 사람</th>
				<td><input type="text" name="mesFrom" value="${param.mesFrom}" readonly="readonly"></td>
			</tr>
			<tr>
				<th colspan="4">내용</th>
			</tr>
			<tr>
				<td colspan="4"><textarea cols="60" rows="10" required="required" name="mesCon"></textarea></td>
			</tr>
			<tr>
				<td colspan="4"><input type="submit" value="전송"><input type="button" value="뒤로가기" onclick="history.back()"></td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>