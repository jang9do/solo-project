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
	#content table{
		margin-bottom: 50px;
	}
	#content table tr td{
		width: 150px;
	}
	#content table tr #bContent{
		text-align:left;
		margin: 10px;
		height: 400px;
	}
	#content table:last-child td {
		width: 200px;
	}
	#content table:last-child .bTitle {
		width: 200px;
	}
	#content table:last-child td:first-child{
		width: 90px;
	}
	#content table:last-child td #abContent{
		width: 100%;
	}
	#content table:last-child td:nth-child(3){
		width: 200px;
	}
	#content table:last-child td:last-child {
		width: 60px;
	}
}
</style>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
</head>
<body>
<c:if test="${not empty addBoardDeleteResult && addBoardDeleteResult==1 }"><script>alert('삭제성공');</script></c:if>
<c:if test="${not empty addBoardDeleteResult && addBoardDeleteResult==0 }"><script>alert('삭제실패');</script></c:if>
<c:if test="${not empty addBoardResult && addBoardResult==1 }"><script>alert('입력성공');</script></c:if>
<c:if test="${not empty addBoardResult && addBoardResult==0 }"><script>alert('입력실패');</script></c:if>	
<div id="wrap">
	<jsp:include page="../main/header.jsp" />
	<div id="content">
		<table>
			<tr>
				<th>제목</th>
				<td>
					<c:if test="${BoardDto.baNum==1}">공지사항[${BoardDto.bNum }번]</c:if>
					<c:if test="${BoardDto.baNum==2}">[BEST]</c:if>
					<c:if test="${BoardDto.baNum==3}">[문의게시판]</c:if>
				</td>
				<td colspan="2" class="bTitle">${BoardDto.bTitle }</td>
			<tr>
			<tr>
				<th>작성자</th>
				<td>${BoardDto.bName } (${BoardDto.mId })</td>
				<th>파일첨부</th>
				<td><a href="${conPath}/bFile/${BoardDto.bFile }">${BoardDto.bFile }</a></td>
			</tr>
			<tr>
				<th colspan="4">글 내용</th>
			</tr>
			<tr>
				<td colspan="4" id="bContent">${BoardDto.bContent }</td>
			</tr>
			<tr>
				<td colspan="4">
					<c:if test="${not empty login }"><input type="button" value="답글 작성" onclick="location.href='${conPath}/boardReplyView.bo?bNum=${BoardDto.bNum }'">&nbsp;&nbsp;</c:if>
					<c:if test="${(login.levelN==1 || login.levelN==2) && BoardDto.baNum==3 }"><input type="button" value="BEST 등록" onclick="location.href='${conPath}/boardBestUp.bo?bNum=${BoardDto.bNum }'">&nbsp;&nbsp;</c:if>
					<c:if test="${login.levelN==1 || login.levelN==2 || ((login.mId==BoardDto.mId) && not empty login)}"><input type="button" value="수정" onclick="location.href='${conPath}/boardModifyView.bo?bNum=${BoardDto.bNum }'">&nbsp;&nbsp;</c:if>
					<c:if test="${login.levelN==1 || login.levelN==2 || ((login.mId==BoardDto.mId) && not empty login)}"><input type="button" value="삭제"  onclick="location.href='${conPath}/boardDelete.bo?bNum=${BoardDto.bNum }'">&nbsp;&nbsp;</c:if>
					<input type="button" value="뒤로가기" onclick="history.back()">
				</td>
			</tr>
		</table>
		
		<form action="${conPath}/addBoardInsert.bo" method="post">
			<input type="hidden" value="${BoardDto.bNum }" name="bNum">
			<table>
				<c:if test="${not empty login }">
					<tr>
						<th>${login.mId }</th>
						<td colspan="2"><textarea name="abContent" id="abContent"></textarea></td>
						<td><input type="submit" value="입력"></td>
					</tr>
				</c:if>
				<c:forEach items="${addBoardList }" var="ab">
					<tr>
						<td>${ab.mId }</td>
						<td class="bTitle">${ab.abContent }</td>
						<fmt:formatDate value="${ab.abDate }" var="abDate" pattern="YY-MM-dd" />
						<td>${abDate }</td>
						<td>
							<c:if test="${ab.mId eq login.mId }"><input type="button" value="삭제" id="addDelete" onclick="location.href='${conPath}/addBoardDelete.bo?abNum=${ab.abNum }&bNum=${ab.bNum }'"></c:if>
						</td>
					</tr>
				</c:forEach>
			</table>
		</form>
	</div>
	<jsp:include page="../main/footer.jsp" />
</div>
</body>
</html>