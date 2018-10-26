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
	#wrap #content table tr td input[type=button], #wrap #content table tr td input[type=submit]{
		background-color: lightblue;
		border: 1px solid black;
		font-weight: bold;
	}
</style>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script>
	var ins=0;
	
	function chk(){
		if(ins==1){
			return true;
		} else{
			alert('동물을 선택해주세요');
			return false;
		}
	}
	
	$(function(){
		$("input:radio[name=aNum]").click(function(){
			ins=1;
		});
	})
</script>
</head>
<body>
<div id="wrap">
	<jsp:include page="../main/header.jsp" />
	<div id="content">
	<form action="${conPath }/Reserve.res" method="post" onsubmit="return chk()">
		<input type="hidden" name="rDate" value='${param.rDate} ${param.rTime }'>
		<input type="hidden" name="aName">
		<table>
		<tr>
			<th colspan="2">진료 내용</th>
		</tr>
		<tr>
			<td><textarea rows="15" cols="50" name="rDetail" required="required"></textarea></td>
		</tr>
		<tr>
			<td><input type="submit" value="예약">&nbsp;&nbsp;<input type="button" value="뒤로가기" onclick="history.back()"></td>
		</tr>
		</table>
		<c:forEach items="${memberAnimal }" var="ma">
			<table>
				<tr><td colspan="2"><input type="radio" name="aNum" value="${ma.aNum }"></td></tr>
				<tr>
					<td colspan="2"><a href="#"><img src="${conPath }/aniImg/${ma.aPicture }"></a></td>
				</tr>
				<tr>
					<td>등록번호</td>
					<td>${ma.aNum }</td>
				</tr>
				<tr>
					<td>이름</td>
					<td>${ma.aName }</td>
				</tr>
				<tr>
					<td>생일</td>
					<td><c:if test="${empty ma.aBirth }">등록안됨</c:if>${ma.aBirth }</td>
				</tr>
				<tr>
					<td>종류</td>
					<td>${ma.aSpecies } (${ma.aSdetail })</td>
				</tr>
				<tr>
					<td>성별</td>
					<td>${ma.aGender }</td>
				</tr>
			</table>
		</c:forEach>
	</form>
	</div>
	<jsp:include page="../main/footer.jsp" />
</div>
</body>
</html>