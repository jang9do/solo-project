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
	$(function(){
		$('#sBtn').click(function(){
			var rank = $('#rank').val();
			var value = $('#value').val();
			location.href = '${conPath }/boardSearch.bo?rank='+rank+'&value='+value;
		});
	});
	function sEnter(){
		if(window.event.keyCode == 13){
			var rank = $('#rank').val();
			var value = $('#value').val();
			location.href = '${conPath }/boardSearch.bo?rank='+rank+'&value='+value;
		}
	}
</script>
</head>
<body>
<div id="wrap">
	<c:if test="${not empty bInputResult && bInputResult==1 }"><script>alert('입력성공');</script></c:if>
	<c:if test="${not empty bInputResult && bInputResult==0 }"><script>alert('입력실패');</script></c:if>
	<c:if test="${not empty bModifyResult && bModifyResult==1 }"><script>alert('수정성공');</script></c:if>
	<c:if test="${not empty bModifyResult && bModifyResult==0 }"><script>alert('수정실패');</script></c:if>
	<c:if test="${not empty bDeleteResult && bDeleteResult==1 }"><script>alert('삭제성공');</script></c:if>
	<c:if test="${not empty bDeleteResult && bDeleteResult==0 }"><script>alert('삭제실패');</script></c:if>
	<jsp:include page="../main/header.jsp" />
	<div id="content">
		<table>
			<caption>문의 게시판</caption>
			<tr><th>글 번호</th><th>제목</th><th>글쓴이</th><th>조회수</th><th>작성일</th></tr>
			<c:forEach items="${list }" var="l">
				<tr>
					<td>${l.bNum }</td>
					<td class="bTitle"><a href="${conPath }/boardView.bo?bNum=${l.bNum}">${l.bTitle }</a></td>
					<td>${l.bName }</td>
					<td>${l.bHit }</td>
					<fmt:formatDate value="${l.bDate }" var="bDate" pattern="YY-MM-dd HH:mm" />
					<td class="bDate">${bDate }</td>
				</tr>
			</c:forEach>
		</table>
		<p>
			<c:if test="${startPage>10 }"><a href="${conPath }/boardList.bo?pageNum=${startPage-1 }">[이전]&nbsp;</a></c:if>
			<c:forEach begin="${startPage }" end="${endPage }" var="i">
				<c:if test="${i==currentPage }">${i }&nbsp;</c:if>
				<c:if test="${i!=currentPage }"><a href="${conPath }/boardList.bo?pageNum=${i }">${i }</a>&nbsp;</c:if>
			</c:forEach>
			<c:if test="${endPage!=pageCnt }"><a href="${conPath }/boardList.bo?pageNum=${endPage+1 }">[다음]</a></c:if>
		</p>
		
		<p>
			<select id="rank">
				<option value="1">제목</option>
				<option value="2">내용</option>
				<option value="3">제목+내용</option>
			</select>&nbsp;
			<input type="text" id="value" onkeyup="sEnter();">&nbsp;<button id="sBtn">검색</button>
		</p>
	</div>
	<jsp:include page="../main/footer.jsp" />
</div>
</body>
</html>