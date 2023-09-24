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
			<h1>투 두 리스트 로그인</h1>
			<form action = "/" method="post">
				<fieldset>
					<p>아이디</p>
					<input type="text" name="loginId" placeholder="ID" autocomplete='off'>
					<p>패스워드</p>
					<input type="password" name="loginPw" placeholder="PW" autocomplete='off'>

					<button>로그인</button>
					<a href= "/signup">회원가입</a>
				</fieldset>
			</form>
		</main>
	</body>
</html>