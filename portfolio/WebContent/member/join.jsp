<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${conPath }/css/reset.css" rel="stylesheet">
<link href="${conPath }/css/content.css" rel="stylesheet">
<style>
	#wrap #content table caption{
		text-align: center;
	}
	#wrap #content table th{
		width: 120px;
	}
	#wrap #content table td{
		width: 300px;
	}
	#wrap #content table td input[type=text], #wrap #content table td input[type=password]{
		width: 100%;
	}
</style>
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script>
$(document).ready(function(){
	$('#mId').keyup(function(){
		$.ajax({
			url : '${conPath }/idChk.mem',
			type : 'get',
			data : 'mId='+$('#mId').val(),
			datatype : 'html',
			success : function(data, status){
				$('#ic').html(data);
			}
		});
	});
	$('#mPwchk').keyup(function(){
		if($('#mPw').val()==$('#mPwchk').val() && ($('#mPw').val()).length>=8){
			$('#pc').html('사용할 수 있는 비밀번호입니다');
			$('#pc').css('color', 'blue');
		} else{
			$('#pc').html('사용할 수 없는 비밀번호입니다');
			$('#pc').css('color', 'red');
		}
	});
});

function Chk(){
	if($('#mPw').val()==$('#mPwchk').val() && ($('#mPw').val()).length>=8){
		return true;
	} else{
		alert('비밀번호 확인');
		return false;
	}
}
</script>
</head>
<body>
<div id="wrap">
<c:if test="${not empty login }">
	<jsp:forward page="../main/main.jsp"/>
</c:if>
<c:if test="${not empty joinMsg }">
	<script>
		alert('${joinMsg }')
	</script>
</c:if>
<jsp:include page="../main/header.jsp" />
<div id="content">
	<form action="${conPath }/join.mem" method="post" onsubmit="return Chk()" name="frm">
		<table>
			<caption>회원가입</caption>
			<tr>
				<th colspan="2">[ * 필수]</th>
			</tr>
			<tr>
				<th>* 아이디<br>(8자 이상)</th>
				<td><input type="text" name="mId" id="mId" required="required"><br><span id='ic'></span></td>
			</tr>
			<tr>
				<th>* 이름</th>
				<td><input type="text" name="mName" required="required"></td>
			</tr>
			<tr>
				<th>* 비밀번호<br>(8자 이상)</th>
				<td><input type="password" name="mPw" id="mPw" required="required"></td>
			</tr>
			<tr>
				<th>* 비밀번호 확인</th>
				<td><input type="password" name="mPwchk" id="mPwchk" required="required"><br><span id="pc"></span></td>
			</tr>
			<tr>
				<th>* 생년월일</th>
				<td><input type="date" name="mBirth" required="required"></td>
			</tr>
			<tr>
				<th>* 성별</th>
				<td>남<input type="radio" name="mGender" value="남자" checked="checked" required="required">여<input type="radio" name="mGender" value="여자" required="required"></td>
			</tr>
			<tr>
				<th>* 휴대전화</th>
				<td><input type="text" name="mPhone" required="required"></td>
			</tr>
			<tr>
				<th>전화</th>
				<td><input type="text" name="mTel"></td>
			</tr>
			<tr>
				<th>* 주소</th>
				<td><input type="text" name="mAddress" required="required"></td>
			</tr>
			<tr>
				<th>상세 주소</th>
				<td><input type="text" name="mAdetail"></td>	
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="가입">&nbsp;&nbsp;<input type="button" value="돌아가기" onclick="history.back()"></td>
			</tr>
		</table>
	</form>
</div>
<jsp:include page="../main/footer.jsp" />
</div>
</body>
</html>