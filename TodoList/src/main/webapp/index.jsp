<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>YES-TodoList</title>
		
		<link rel="stylesheet" href="/resources/css/main.css">
	</head>
	<body>
		<main>
			<c:choose>
				<c:when test = "${empty sessionScope.loginMember }">
					<h1>투 두 리스트 로그인</h1>
					<form action = "/login" method="post">
						<fieldset>
							<p>아이디</p>
							<input type="text" name="loginId" placeholder="ID" autocomplete='off'>
							<p>패스워드</p>
							<input type="password" name="loginPw" placeholder="PW" autocomplete='off'>
		
							<button>로그인</button>
							<a href= "/signup">회원가입</a> 
						</fieldset>
					</form>
				</c:when>
				
				<c:otherwise>
					<h1>${sessionScope.loginMember.memberNickName}의 투두 리스트 </h1>
					<c:forEach var = "todolist" items = "${sessionScope.todoList}">
						<table>
							<tr>
							<!-- 왜 정렬이 안되는거지?????????????????????????????????????? -->
								<td>${todolist.todoTitle}</td>
								<td>${todolist.todoMemo}</td>
								<td>${todolist.todoDate}</td>
								<td class = "oneListBtn">
									<span>
										<a href = "/update" id = "update">수정</a>
									</span>
	
									<span>
										<a href = "/delete" id = "delete">삭제</a>
									</span>
								</td>
							</tr>
						</table>
					</c:forEach>
					
					<div class = "todoUserBtn">
						<a href = "/insert" id = "insert">등록하기</a>
						<a href = "/logout" id = "logout">로그아웃</a>
					</div>
					
				</c:otherwise>
				
			</c:choose>
		</main>
	</body>
</html>