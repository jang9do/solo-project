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
	#wrap #content table{
		margin-bottom: 50px;
		border: 1px solid black;
	}
	#wrap #content table tr td{
		border-right: 1px solid black;
		width: 200px;
	} 
	#wrap #content table tr td button, #wrap #content table tr td input[type=submit]{
		background-color: lightblue;
		border: 1px solid black;
		font-weight: bold;
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
				<th>진료번호</th>
				<td>${ReserveDto.rNum }</td>
			</tr>
			<tr>
				<th>진료동물</th>
				<td>(등록번호 ${ReserveDto.aNum})&nbsp;${ReserveDto.aName }</td>
			</tr>
			<tr>
				<th>예약일</th>
				<td>${ReserveDto.rDate }</td>
			</tr>
			<tr>
				<th>예약내용</th>
				<td>${ReserveDto.rDetail }</td>
			</tr>
			<tr>
				<c:if test="${login.levelN==1 || login.levelN==2 }">
					<td colspan="2">
						<button onclick="history.back()">뒤로가기</button>
						<button onclick="location.href='${conPath }/reserveDeleteMaster.res?rNum=${ReserveDto.rNum }'">예약취소</button>
					</td>
				</c:if>
				<c:if test="${login.levelN==3 || login.levelN==4 }">
					<td colspan="2">
						<button onclick="history.back()">뒤로가기</button>
						<button onclick="location.href='${conPath }/reserveDeleteCustom.res?rNum=${ReserveDto.rNum }'">예약취소</button>
					</td>
				</c:if>
			</tr>
		</table>
		<c:if test="${login.levelN==1 || login.levelN==2 }">
		<form action="${conPath }/reserveFinish.res" method="post">
			<table>
				<tr>
					<td colspan="2">진료 결과</td>
				</tr>
				<tr>
					<td><textarea cols="60" rows="15" name="tResult" required="required"></textarea></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="검진완료"></td>
				</tr>
			</table>
			<input type="hidden" value="${ReserveDto.rNum }" name="rNum">
		</form>
		</c:if>
	</div>
	<jsp:include page="../main/footer.jsp" />
</div>
</body>
</html>