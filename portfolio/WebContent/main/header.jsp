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
<link href="${conPath }/css/header.css" rel="stylesheet">
<script>
	$(document).ready(function(){
		$.ajax({
			url : '${conPath }/mesNoReadChk.mes',
			type : 'get',
			datatype : 'html',
			success : function(data, status){
				$('#newM').html(data);
				$('#newM').css("color", "red");
			}
		});
		$('.menu').hover(function(){
			$('header').css('background-color', '#adbbd1');
			$('header').css('width','450px');
		},function(){
			$('header').css('background-color', 'transparent');
			$('header').css('width','290px');
		});
		
		$('.MessageView').click(function(){
			window.open('${conPath }/mesReciveList.mes', '', 'width=400, height=300, left=200');
		});
		$('.MessageSend').click(function(){
			window.open('${conPath }/mesSendView.mes', '', 'width=470, height=350, left=200');
		})
	});
</script>
</head>
<body>
	<c:if test="${not empty Msg }">
		<script>
			alert('${Msg }');
		</script>
	</c:if>
<header>
	<div id="headW">
		<a href="${conPath }/main.mem"><img src="${conPath }/img/logo.jpg" alt="logo"></a>
		<c:if test="${empty login }">
			<div id='login'>
				<form action="${conPath }/login.mem" method="post">
					<table>
						<tr>
							<th>아이디</th>
							<td><input type="text" name="mId" size="10" required="required"></td>
						</tr>
						<tr>
							<th>비밀번호</th>
							<td><input type="password" name="mPw" size="10" required="required"></td>
						</tr>
						<tr>
							<td colspan="2"><input type="submit" value="로그인"></td>
						</tr>
					</table>
					<table>
						<tr>
							<td><a href="${conPath }/joinView.mem">회원가입</a></td>
							<td><a href="${conPath }/memberGetView.mem">ID/PW 찾기</a></td>
						</tr>
					</table>
				</form>
			</div>
			<ul>
				<li><a href="#">병원소개</a></li>
				<li><a href="#">오시는길</a></li>
				<li class="menu"><a href="#">커뮤니티</a>
					<ul class="menu2">
						<li><a href="${conPath }/boardInputView.bo">글 작성&nbsp;&nbsp;</a></li>
						<li><a href="${conPath }/boardGongji.bo">공지사항&nbsp;&nbsp;</a></li>
						<li><a href="${conPath }/boardBest.bo">자주하는 질문&nbsp;&nbsp;</a></li>
						<li><a href="${conPath }/boardList.bo">문의게시판&nbsp;&nbsp;</a></li>
					</ul>
				</li>
			</ul>
		</c:if>
		
		
		<c:if test="${login.levelN==1}">
			<div id='login'>
				<table>
					<tr>
						<td id="mName" colspan="2">${login.mName } 님 환영합니다</td>
					</tr>
					<tr>
						<td>회원등급</td>
						<td style="color:gold;">마스터</td>
					</tr>
					<tr>
						<td colspan="2"><a href="${conPath }/logout.mem">로그아웃</a></td>
					</tr>
					<tr>
						<td colspan="2" id="newM"></td>
					</tr>
				</table>
				<table>
					<tr>
						<td><a href="#" class="MessageView">메세지 보기</a></td>
						<td><a href="#" class="MessageSend">메세지 작성</a></td>
					</tr>
				</table>
			</div>
		<ul>
			<li class="menu"><a href="#">마스터 모드</a>
				<ul class="menu2">
					<li><a href="${conPath }/MemberAdminView.mem">관리자 등록&nbsp;&nbsp;</a></li>
					<li><a href="${conPath }/MemberList.mem">회원목록&nbsp;&nbsp;</a></li>
					<li><a href="${conPath }/MemberSearch.mem">회원검색&nbsp;&nbsp;</a></li>
					<li><a href="${conPath }/AnimalSearchView.ani">동물검색&nbsp;&nbsp;</a></li>
				</ul>
			</li>
			<li class="menu"><a href="#">개인정보</a>
				<ul class="menu2">
					<li><a href="${conPath }/MemberView.mem">개인정보 확인&nbsp;&nbsp;</a></li>
					<li><a href="${conPath }/modifyView.mem">개인정보 수정&nbsp;&nbsp;</a></li>
				</ul>
			</li>
			<li class="menu"><a href="#">진료</a>
				<ul class="menu2">
					<li><a href="${conPath }/ReserveMList.res">예약/진료확인&nbsp;&nbsp;</a></li>
					<li><a href="${conPath }/treatSearchView.tre">진료기록&nbsp;&nbsp;</a></li>
				</ul>
			</li>
			<li><a href="#">병원소개</a></li>
			<li><a href="#">오시는길</a></li>
			<li class="menu"><a href="#">커뮤니티</a>
				<ul class="menu2">
					<li><a href="${conPath }/boardInputView.bo">글 작성&nbsp;&nbsp;</a></li>
					<li><a href="${conPath }/boardGongji.bo">공지사항&nbsp;&nbsp;</a></li>
					<li><a href="${conPath }/boardBest.bo">자주하는 질문&nbsp;&nbsp;</a></li>
					<li><a href="${conPath }/boardList.bo">문의게시판&nbsp;&nbsp;</a></li>
				</ul>
			</li>
		</ul>
		</c:if>
		
		<c:if test="${login.levelN==2}">
			<div id='login'>
				<table>
					<tr>
						<td id="mName" colspan="2">${login.mName } 님 환영합니다</td>
					</tr>
					<tr>
						<td>회원등급</td>
						<td style="color:red;">관리자</td>
					</tr>
					<tr>
						<td colspan="2"><a href="${conPath }/logout.mem">로그아웃</a></td>
					</tr>
					<tr>
						<td colspan="2" id="newM"></td>
					</tr>
					<tr>
						<td><a href="#" class="MessageView">메세지 보기</a></td>
						<td><a href="#" class="MessageSend">메세지 작성</a></td>
					</tr>
				</table>
			</div>
		<ul>
			<li class="menu"><a href="#">관리자 모드</a>
				<ul class="menu2">
					<li><a href="${conPath }/MemberList.mem">회원목록&nbsp;&nbsp;</a></li>
					<li><a href="${conPath }/MemberSearch.mem">회원검색&nbsp;&nbsp;</a></li>
					<li><a href="${conPath }/AnimalSearchView.ani">동물검색&nbsp;&nbsp;</a></li>
				</ul>
			</li>
			<li class="menu"><a href="#">개인정보</a>
				<ul class="menu2">
					<li><a href="${conPath }/MemberView.mem">개인정보 확인&nbsp;&nbsp;</a></li>
					<li><a href="${conPath }/modifyView.mem">개인정보 수정&nbsp;&nbsp;</a></li>
				</ul>
			</li>
			<li class="menu"><a href="#">진료</a>
				<ul class="menu2">
					<li><a href="${conPath }/ReserveMList.res">예약/진료확인&nbsp;&nbsp;</a></li>
					<li><a href="${conPath }/treatSearchView.tre">진료기록&nbsp;&nbsp;</a></li>
				</ul>
			</li>
			<li><a href="#">병원소개</a></li>
			<li><a href="#">오시는길</a></li>
			<li class="menu"><a href="#">커뮤니티</a>
				<ul class="menu2">
					<li><a href="${conPath }/boardInputView.bo">글 작성&nbsp;&nbsp;</a></li>
					<li><a href="${conPath }/boardGongji.bo">공지사항&nbsp;&nbsp;</a></li>
					<li><a href="${conPath }/boardBest.bo">자주하는 질문&nbsp;&nbsp;</a></li>
					<li><a href="${conPath }/boardList.bo">문의게시판&nbsp;&nbsp;</a></li>
				</ul>
			</li>
		</ul>
		</c:if>
		
		
		<c:if test="${login.levelN==3 || login.levelN==4}">
			<div id='login'>
				<table>
					<tr>
						<td id="mName" colspan="2">${login.mName } 님 환영합니다</td>
					</tr>
					<tr>
						<td>회원등급</td>
						<c:if test="${login.levelN==3 }"><td style="color:blue;">우수회원</td></c:if>
						<c:if test="${login.levelN==4 }"><td style="color:gray;">일반회원</td></c:if>
					</tr>
					<tr>
						<td colspan="2"><a href="${conPath }/logout.mem">로그아웃</a></td>
					</tr>
					<tr>
						<td colspan="2" id="newM"></td>
					</tr>
					<tr>
						<td colspan="2"><a href="#" class="MessageView">메세지 보기</a></td>
					</tr>
				</table>
			</div>
		<ul>
			<li class="menu"><a href="#">개인정보</a>
				<ul class="menu2">
					<li><a href="${conPath }/MemberView.mem">개인정보 확인&nbsp;&nbsp;</a></li>
					<li><a href="${conPath }/modifyView.mem">개인정보 수정&nbsp;&nbsp;</a></li>
				</ul>
			</li>
			<li class="menu"><a href="${conPath }/animalList.ani">반려동물</a>
				<ul class="menu2">
					<li><a href="${conPath }/AnimalInputView.ani">동물 등록&nbsp;&nbsp;</a></li>
					<li><a href="${conPath }/animalList.ani">동물 목록/관리&nbsp;&nbsp;</a></li>
				</ul>
			</li>
			<li class="menu"><a href="#">진료예약</a>
				<ul class="menu2">
					<li><a href="${conPath }/ReserveView.res">예약하기&nbsp;&nbsp;</a></li>
					<li><a href="${conPath }/reserveCustiomList.res">예약확인&nbsp;&nbsp;</a></li>
				</ul>
			</li>
			<li><a href="#">병원소개</a></li>
			<li><a href="#">오시는길</a></li>
			<li class="menu"><a href="#">커뮤니티</a>
				<ul class="menu2">
					<li><a href="${conPath }/boardInputView.bo">글 작성&nbsp;&nbsp;</a></li>
					<li><a href="${conPath }/boardGongji.bo">공지사항&nbsp;&nbsp;</a></li>
					<li><a href="${conPath }/boardBest.bo">자주하는 질문&nbsp;&nbsp;</a></li>
					<li><a href="${conPath }/boardList.bo">문의게시판&nbsp;&nbsp;</a></li>
				</ul>
			</li>
		</ul>
		</c:if>
		<div id="call">
			<p>전화번호</p>
			<img src="${conPath }/img/call.png"><span>010-1111-2222</span>
		</div>
	</div>
</header>
</body>
</html>