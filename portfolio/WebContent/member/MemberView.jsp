<%@page import="com.tj.dto.MemberDto"%>
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
		width: 300px;
	}
	#content table caption{
		text-align: center;
	}
</style>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
</head>
<body>
<div id="wrap">
	<jsp:include page="../main/header.jsp" />
	<div id="content">
		<table>
			<caption>회원정보</caption>
			<tr>
				<th>아이디</th>
				<td>${MemberView.mId }</td>
			</tr>
			<tr>
				<th>이름</th>
				<td>${MemberView.mName }</td>
			</tr>
			<tr>
				<th>생년월일</th>
				<fmt:formatDate value="${MemberView.mBirth }" pattern="YYYY-MM-dd" var="mBirth"/>
				<td>${mBirth }</td>
			</tr>
			<tr>
				<th>성별</th>
				<td>${MemberView.mGender }</td>
			</tr>
			<tr>
				<th>휴대전화</th>
				<td>${MemberView.mPhone }</td>
			</tr>
			<tr>
				<th>전화</th>
				<td>${MemberView.mTel }</td>
			</tr>
			<tr>
				<th>주소</th>
				<td>${MemberView.mAddress }</td>
			</tr>
			<tr>
				<th>상세 주소</th>
				<td>${MemberView.mAdetail }</td>	
			</tr>
			<tr>
				<td colspan="2">
					<input type="button" value="돌아가기" onclick="history.back()">&nbsp;&nbsp;
					<input type="button" value="회원수정" onclick="location.href='${conPath }/modifyView.mem'">
				</td>
			</tr>
		</table>
	</div>
	<jsp:include page="../main/footer.jsp" />
</div>
</body>
</html>