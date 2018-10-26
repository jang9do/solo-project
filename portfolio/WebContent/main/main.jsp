<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${conPath }/css/reset.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
<link href="${conPath }/css/main.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
<script>
    $(document).ready(function(){
		$('.slider').bxSlider({
			auto: true,
			speed: 1000,
			pause: 4000,
			mode:'fade',
			autoControls: false,
			pager:false
		});
	});
  </script>
</head>
<body>
<div id="wrap">
	<jsp:include page="../main/header.jsp" />
	<div id="content">
		<div class="slider">
		    <div><img src="${conPath }/img/bx01.jpg" /></div>
		    <div><img src="${conPath }/img/bx02.jpg" /></div>
		    <div><img src="${conPath }/img/bx03.jpg" /></div>
		</div>
		<div id="fiveBoard">
			<div id="gongji">
				<table>
					<caption>공지사항</caption>
				<c:forEach items="${t5list }" var="t">
					<tr><td><a href="${conPath }/boardView.bo?bNum=${t.bNum}">&nbsp;&nbsp;&nbsp;&nbsp;▶&nbsp;${t.bTitle }</a></td></tr>
				</c:forEach>
				</table>
			</div>
			<div id="best">
				<table>
					<caption>Best 질문</caption>
				<c:forEach items="${b5list }" var="b">
					<tr><td><a href="${conPath }/boardView.bo?bNum=${b.bNum}">&nbsp;&nbsp;&nbsp;&nbsp;▶&nbsp;${b.bTitle }</a></td></tr>
				</c:forEach>
				</table>
			</div>
			<div>
				<img src="${conPath }/img/heart.png" id="heart"/>
				<span>심장 사상충 약!<br><br>꼭 접종!</span>
			</div>
			<div>
				<img src="${conPath }/img/born.png" id="born" />
				<p>올바른 간식 문화란?</p>
			</div>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp" />
</div>
</body>
</html>