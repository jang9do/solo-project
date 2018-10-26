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
	#content table tr th{
		width: 180px;
	}
</style>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
</head>
<body>
<div id="wrap">
	<jsp:include page="../main/header.jsp" />
	<div id="content">
		<form action="${conPath }/memberGetPw.mem">
			 <table>
			 	<caption>비밀번호 찾기</caption>
			 	<tr>
			 		<th>아이디</th>
			 		<td><input type="text" name="mId"></td>
			 	</tr>
			 	<tr>
			 		<th>전화번호</th>
			 		<td><input type="text" name="mPhone"></td>
			 	</tr>
			 	<tr>
			 		<th>비밀번호를 받으실 메일</th>
			 		<td><input type="email" name="mEmail"></td>
			 	</tr>
			 	<tr>
			 		<td colspan="2"><input type="submit" value="찾기">&nbsp;&nbsp;<input type="button" onclick="history.back()" value="뒤로가기"></td>
			 	</tr>
			 </table>
		</form>
	</div>
	<jsp:include page="../main/footer.jsp" />
</div>
</body>
</html>