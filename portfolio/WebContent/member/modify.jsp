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
	#wrap #content table caption{
		text-align: center;
	}
	#wrap #content table th{
		width: 120px;
	}
</style>
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script>
	$(document).ready(function(){
		if('${login.mGender}'=='남자'){
			$('#men').attr('checked',true);
		} else{
			$('#woman').attr('checked',true);
		}
	});
	function pwchk(){
		if($('#mPwchk').val()!='${login.mPw}'){
			alert('비밀번호를 확인해주세요');
			return false;
		} else{
			return true;
		}
	}
</script>
</head>
<body>
<fmt:formatDate value="${login.mBirth }" pattern="YYYY-MM-dd" var="mBirth"/>
<c:if test="${not empty modiMsg }">
	<script>
		alert('${modiMsg }')
	</script>
</c:if>
<div id="wrap">
	<jsp:include page="../main/header.jsp" />
	<div id="content">
	<form action="${conPath }/modify.mem" method="post" onsubmit="return pwchk()" name="frm">
		<table>
			<caption>정보 수정</caption>
			<tr>
				<th colspan="2">[ * 필수]</th>
			</tr>
			<tr>
				<th>* 아이디</th>
				<td><input type="text" name="mId" id="mId" value="${login.mId }" readonly="readonly"></td>
			</tr>
			<tr>
				<th>* 이름</th>
				<td><input type="text" name="mName" value="${login.mName }" readonly="readonly"></td>
			</tr>
			<tr>
				<th>* 비밀번호 확인</th>
				<td><input type="password" name="mPwchk" required="required" id="mPwchk"></td>
			</tr>
			<tr>
				<th>비밀번호 수정</th>
				<td><input type="password" name="mPw"></td>
			</tr>
			<tr>
				<th>* 생년월일</th>
				<td><input type="date" name="mBirth" value="${mBirth }" required="required"></td>
			</tr>
			<tr>
				<th>* 성별</th>
				<td>남<input type="radio" name="mGender" value="남자" id="men">여<input type="radio" name="mGender" value="여자" id="woman"></td>
			</tr>
			<tr>
				<th>* 휴대전화</th>
				<td><input type="text" name="mPhone" required="required" value="${login.mPhone }"></td>
			</tr>
			<tr>
				<th>전화</th>
				<td><input type="text" name="mTel" value="${login.mTel }"></td>
			</tr>
			<tr>
				<th>* 주소</th>
				<td><input type="text" name="mAddress" required="required" value="${login.mAddress }"></td>
			</tr>
			<tr>
				<th>상세 주소</th>
				<td><input type="text" name="mAdetail" value="${login.mAdetail }"></td>	
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="수정">&nbsp;&nbsp;
					<input type="button" value="돌아가기" onclick="history.back()">&nbsp;&nbsp;
					<input type="button" value="회원탈퇴" onclick="location.href='${conPath }/MemberDelete.mem?mId=${login.mId }'">
				</td>
			</tr>
		</table>
	</form>
</div>
	<jsp:include page="../main/footer.jsp" />
</div>
</body>
</html>