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
	#wrap #content table tr td input[type=submit], #wrap #content table tr td input[type=button]{
		background-color: lightblue;
		border: 1px solid black;
		width:80px;
		height: 40px;
		line-height: 40px;
		margin: 10px;
		font-weight: bold;
	}
</style>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
</head>
<body>
<div id="wrap">
	<jsp:include page="../main/header.jsp" />
	<div id="content">
		<form action="${conPath }/AnimalInput.ani" enctype="multipart/form-data" method="post">
			<table>
				<caption>동물 등록</caption>
				<tr>
					<th>* 이름</th>
					<td><input type="text" name="aName" required="required"></td>
				</tr>
				<tr>
					<th>생일</th>
					<td>
						<input type="date" name="aBirth" />
					</td>
				</tr>
				<tr>
					<th>* 종류</th>
					<td><input type="text" name="aSpecies" required="required"></td>
				</tr>
				<tr>
					<th>세부 종</th>
					<td><input type="text" name="aSdetail"></td>
				</tr>
				<tr>
					<th>* 성별</th>
					<td>남<input type="radio" name="aGender" value="남" checked="checked">여<input type="radio" name="aGender" value="여"></td>
				</tr>
				<tr>
					<th>사진등록</th>
					<td><input type="file" name="aPicture"></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="등록">&nbsp;&nbsp;<input type="button" value="뒤로가기" onclick="history.back()">
					</td>
				</tr>
			</table>
		</form>
	</div>
	<jsp:include page="../main/footer.jsp" />
</div>
</body>
</html>