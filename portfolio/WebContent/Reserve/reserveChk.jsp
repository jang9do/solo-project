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
<style>
	#wrap #content p input[type=submit]{
		background-color: pink;
		color: black;
		border: 1px solid lightgray;
		height: 40px;
		line-height: 40px;
		width: 100px;
	}
</style>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script>
$(function(){
	$('.time').css('color','black')
});

var ins = 0;
$('.time').click(function(){
	$('#timeResult').html($('#timeResult').html() +' 오전'+$(this).attr('class')+'시');
	if($(this).css('color')=='rgb(0, 0, 0)'){
		$('.time').css('color','black');
		$(this).css('color','red');
		ins = 1;
	} else {
		$(this).css('color','black');
		ins = 0;
	}
	if($(this).attr('id').length==1){
		$('#rTime').val('0'+$(this).attr('id')+':00');
	} else{
		$('#rTime').val($(this).attr('id')+':00');
	}
	
});

function sub(){
	if(ins==1){
		return true;
	} else{
		alert('시간을 선택해주세요');
		return false;
	}
}
</script>
</head>
<body>
	<c:set value="0" var="ins"/>
	<c:set value="9" var="rR"/>
	<p>
	<c:forEach items="${reserveR }" var="r">
		<c:if test="${r==0 }"><button id="${rR }" disabled="disabled">${rR }:00~${rR+1 }:00</button></c:if>
		<c:if test="${r==1 }"><button class="time" id="${rR }">${rR }:00~${rR+1 }:00</button></c:if>
		<c:if test="${rR == 11 }"><c:set value="${rR+1 }" var="rR"/></p><p></c:if>
		<c:if test="${rR != 11 }"><c:set value="${rR+1 }" var="rR"/></c:if>
	</c:forEach>
	</p>
	<form action="${conPath }/animalChk.ani" onsubmit="return sub()">
		<p><input type="submit" value="동물 선택"></p>
		<input type="hidden" name="rTime" id="rTime"> 
		<input type="hidden" name="rDate" value="${rDateService }">
	</form>
</body>
</html>