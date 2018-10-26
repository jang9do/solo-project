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
</style>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script>
$(document).ready(function(){
	if('${animal.aGender}'=='남'){
		$('#men').attr('checked',true);
	} else{
		$('#woman').attr('checked',true);
	}
});
</script>
</head>
<body>
<div id="wrap">
	<jsp:include page="../main/header.jsp" />
	<div id="content">
		<form action="${conPath }/animalModifyS.ani" enctype="multipart/form-data" method="post">
			<table>
				<tr>
					<td colspan="2"><a href="#"><img src="${conPath }/aniImg/${animal.aPicture }"></a></td>
				</tr>
				<tr>
					<th>등록번호</th>
					<td><input type="Number" value="${animal.aNum }" name="aNum" readonly="readonly"></td>
				</tr>
				<tr>
					<th>이름</th>
					<td><input type="text" value="${animal.aName }" name="aName"></td>
				</tr>
				<tr>
					<c:if test="${empty ma.aBirth }"><c:set var="aBirth" value="등록안됨"/></c:if>
					<c:if test="${not empty ma.aBirth }"><fmt:formatDate pattern="YY-MM-dd" var="aBirth" value="${animal.abirth }"/></c:if>
					<th>생일</th>
					<td>
						<c:if test="${empty ma.aBirth }"><input type="date" name="aBirth" />등록안됨</c:if>
						<c:if test="${not empty ma.aBirth }"><input type="date" name="aBirth" value="${aBirth }" /></c:if>
					</td>
				</tr>
				<tr>
					<th>종류</th>
					<td><input type="text" name="aSpecies" value="${animal.aSpecies }"></td>
				</tr>
				<tr>
					<th>세부 종</th>
					<td><input type="text" name="aSdetail" value="${animal.aSdetail }"></td>
				</tr>
				<tr>
					<th>성별</th>
					<td>남<input type="radio" name="aGender" id="men" value="남">여<input type="radio" name="aGender" id="woman" value="여"></td>
				</tr>
				<tr>
					<th>사진등록</th>
					<td><input type="file" name="aPicture"></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="수정">
					</td>
				</tr>
			</table>
		</form>
	</div>
	<jsp:include page="../main/footer.jsp" />
</div>
</body>
</html>