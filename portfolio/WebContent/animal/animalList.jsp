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
	#wrap #content table{
		margin-bottom: 50px;
	}
	#wrap #content table tr td{
		width: 200px;
	}
	#wrap #content table tr td img{
		width: 200px;
		height: 200px;
	}
	#wrap #content table tr td input[type=button]{
		background-color: lightblue;
		border: 1px solid black;
		font-weight: bold;
	}
</style>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
</head>
<body>
<div id="wrap">
	<c:if test="${not empty AnimalDelete && AnimalDelete==1}"><script>alert('삭제성공');</script></c:if>
	<c:if test="${not empty AnimalDelete && AnimalDelete==0}"><script>alert('삭제실패');</script></c:if>
	<c:if test="${not empty animalResult && animalResult==1}"><script>alert('수정성공');</script></c:if>
	<c:if test="${not empty animalResult && animalResult==0}"><script>alert('수정실패');</script></c:if>
	<c:if test="${not empty inputAnimal && inputAnimal==1}"><script>alert('등록성공');</script></c:if>
	<c:if test="${not empty inputAnimal && inputAnimal==0}"><script>alert('등록실패');</script></c:if>
	<jsp:include page="../main/header.jsp" />
	<div id="content">
		<c:forEach items="${memberAnimal }" var="ma">
			<div>
				<table>
					<tr>
						<td colspan="2"><a href="#"><img src="${conPath }/aniImg/${ma.aPicture }"></a></td>
					</tr>
					<tr>
						<th>등록번호</th>
						<td>${ma.aNum }</td>
					</tr>
					<tr>
						<th>이름</th>
						<td>${ma.aName }</td>
					</tr>
					<tr>
						<th>주인</th>
						<td><a href="${conPath}/MemberView.mem?mId=${ma.mId }">${ma.mId }</a></td>
					</tr>
					<tr>
						<th>생일</th>
						<c:if test="${not empty ma.aBirth }"><fmt:formatDate value="${ma.aBirth }" pattern="YYYY-MM-dd" var="aBirth"/></c:if>
						<td><c:if test="${empty ma.aBirth }">등록안됨</c:if>${aBirth }</td>
					</tr>
					<tr>
						<th>종류</th>
						<td>${ma.aSpecies } (${ma.aSdetail })</td>
					</tr>
					<tr>
						<th>성별</th>
						<td>${ma.aGender }</td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="button" value="수정" onclick="location.href='${conPath }/animalModifyView.ani?aNum=${ma.aNum }'">&nbsp;&nbsp;
							<input type="button" value="삭제" onclick="location.href='${conPath }/animalDelete.ani?aNum=${ma.aNum }'">
						</td>
					</tr>
				</table>
			</div>
		</c:forEach>
	</div>
	<jsp:include page="../main/footer.jsp" />
</div>
</body>
</html>