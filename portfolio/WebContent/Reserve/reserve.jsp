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
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	$( function() {
		$.ajax({
			url : '${conPath }/ReserveChk.res',
			type : 'get',
			datatype : 'html',
			success : function(data, status){
				$('#rButton').html(data);
			}
		});
		$( "#datepicker" ).datepicker(
    		{	dateFormat : 'yy-mm-dd',
    			monthNames : ['1월','2월','3월','4월','5월','6월','7월','8월',
    				'9월','10월','11월','12월'],
    			showMonthAfterYear : true,
    			yearSuffix : '년',
    			showOtherMonths : true,
    			minDate : +1,
    			maxDate : +30,
    			dayNamesMin : ['일','월','화','수','목','금','토'],
    			onSelect: function(dateText, inst){
    				$.ajax({
    					url : '${conPath }/ReserveChk.res',
    					type : 'get',
    					data : 'rDate='+dateText,
    					datatype : 'html',
    					success : function(data, status){
    						$('#rButton').html(data);
    					}
    				});
    			}
    		}
		);// datePicker
	});
</script>
<style>
	#content #datepicker table{
		margin: 0;
	}
	#content #datepicker table tr td{
		background-color: white;
		padding: 0;
		margin: 0;
		height: 60px;
	}
	#content #datepicker table tr {
		background-color: white;
		padding: 0;
		margin: 0;
		height: 60px;
	}
	#content #datepicker table tr td a, #content table tr td span{
		padding: 0;
		margin: 0;
		line-height: 60px;
		text-align: center;
	}
	#content #datepicker{
		margin: 0 auto;
		width: 900px; 
	}
	#content #datepicker a{
		font-weight: bold;
		color: black;
	}
	.ui-datepicker{
		width: 600px;
		margin: 30px auto;
	}
	#content h1{
		text-align: center;
		font-size: 2em;
		font-weight: bold;
	}
	#content p{
		text-align: center;
	}
	#content p button{
		margin: 10px 10px;
	}
</style>
</head>
<body>
<div id="wrap">
	<c:if test="${not empty rResult && rResult==1 }">
		<script>
			alert('예약성공');
		</script>
	</c:if>
	<c:if test="${not empty rResult && rResult==0 }">
		<script>
			alert('예약실패');
		</script>
	</c:if>
	<jsp:include page="../main/header.jsp" />
	<div id="content">
		<h1>날짜 선택</h1>
		<div id="datepicker"></div>
		<h1>시간 선택</h1>
		<div id="rButton"></div>
	</div>
	<jsp:include page="../main/footer.jsp" />
</div>
</body>
</html>