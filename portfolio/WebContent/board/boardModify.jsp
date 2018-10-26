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
<link type="text/css" rel="stylesheet" href="${conPath }/css/demo.css">
<link type="text/css" rel="stylesheet" href="${conPath }/css/jquery-te-1.4.0.css">
<style>
	.jqte{
		text-align : justify;
		margin: 0;
	}
	.jqte_editor{
		min-height: 500px;
		width: 100%;
	}
	#content table tr td input[type=text]{
		width: 100%;
	}
	#content table tr td input[type=submit], #content table tr td input[type=button]{
		background-color: #3378C1;
		border: 1px solid lightblue;
		width: 100px;
		color: white;
	}
	
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.min.js" charset="utf-8"></script>
<script type="text/javascript" src="${conPath }/js/jquery-te-1.4.0.min.js" charset="utf-8"></script>
<script>
	$(function(){
		$('.jqte-test').jqte();
		
		// settings of status
		var jqteStatus = true;
		$(".status").click(function()
		{
			jqteStatus = jqteStatus ? false : true;
			$('.jqte-test').jqte({"status" : jqteStatus})
		});
	})
</script>
</head>
<body>
<div id="wrap">
	<jsp:include page="../main/header.jsp" />
	<div id="content">
		<form action="${conPath }/boardModify.bo" method="post" enctype="multipart/form-data">
			<input type="hidden" value="${BoardDto.bNum }" name="bNum">
			<table>
				<tr>
					<th>제목</th>
					<td>
						<select name="baNum">
							<c:if test="${BoardDto.baNum eq 1 }">
								<c:if test="${login.levelN==1 || login.levelN==2 }">
									<option value="1" selected="selected">공지사항</option>
									<option value="2">베스트글</option>
								</c:if>
								<option value="3" selected="selected">문의사항</option>
							</c:if>
							<c:if test="${BoardDto.baNum eq 2 }">
								<c:if test="${login.levelN==1 || login.levelN==2 }">
									<option value="1">공지사항</option>
									<option value="2" selected="selected">베스트글</option>
								</c:if>
								<option value="3" selected="selected">문의사항</option>
							</c:if>
							<c:if test="${BoardDto.baNum eq 3 }">
								<c:if test="${login.levelN==1 || login.levelN==2 }">
									<option value="1">공지사항</option>
									<option value="2">베스트글</option>
								</c:if>
								<option value="3" selected="selected">문의사항</option>
							</c:if>
						</select>
					</td>
					<td colspan="2"><input type="text" name="bTitle" required="required" value="${BoardDto.bTitle }"></td>
				<tr>
				<tr>
					<th>작성자</th>
					<td><input type="text" name="bName" readonly="readonly" value='${BoardDto.bName }'></td>
					<th>파일첨부</th>
					<td><input type="file" name="bFile"><br><a href="${conPath }/bFile/${BoardDto.bFile }">${BoardDto.bFile }</a></td>
				</tr>
				<tr>
					<th colspan="4">글 내용</th>
				</tr>
				<tr>
					<td colspan="4"><textarea class="jqte-test" name="bContent">${BoardDto.bContent }</textarea></td>
				</tr>
				<tr>
					<td colspan="4"><input type="submit" value="수정">&nbsp;&nbsp;<input type="button" value="뒤로가기" onclick="history.back()"></td>
				</tr>
			</table>
		</form>
	</div>
	<jsp:include page="../main/footer.jsp" />
</div>
</body>
</html>