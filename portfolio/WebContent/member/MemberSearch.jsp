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
	#content table{
		margin-bottom: 40px;
	}
	#content table tr td{
		white-space: nowrap; 
	}
	#wrap #content p input[type=submit]{
		background-color: lightblue;
		border: 1px solid black;
		font-weight: bold;
		line-height: 30px;
		height: 30px;
	}
</style>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script>
$(function(){
	$('button').css('border', '1px solid black');
	$('button').css('font-weight', 'bold');
	$('button').css('background-color', 'lightblue');
});
</script>
</head>
<body>
<div id="wrap">
	<jsp:include page="../main/header.jsp" />
	<div id="content">
		<form action="${conPath }/MemberSearch.mem">
			<p>아이디 <input type="text" name="mId">&nbsp;&nbsp;&nbsp;이름 <input type="text" name="mName">&nbsp;&nbsp;<input type="submit" value="검색"></p>
		</form>
		<table>
			<tr>
				<th>아이디</th><th>이름</th><th>계급</th><th>휴대전화</th><th>주소</th><th>등급업</th>
			</tr>
			<c:forEach items="${MemberSearch }" var="l">
			<tr>
				<td><a href="${conPath }/MemberView.mem?mId=${l.mId}">${l.mId }</a></td>
				<td>${l.mName }</td>
				<td>${l.lName }</td>
				<td>${l.mPhone }</td>
				<td>${l.mAddress }</td>
				<td><c:if test="${l.levelN==4 }"><button onclick="location.href='${conPath}/MemberRank.mem?levelN=${l.levelN }&mId=${l.mId }'">등급 업</button></c:if></td>
			</tr>
			</c:forEach>
		</table>
	</div>
	<jsp:include page="../main/footer.jsp" />
</div>
</body>
</html>