<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>수정</title>
		
		<link rel="stylesheet" href ="/resources/css/main.css">
	</head>
	<body>
		<main>
			<h1>To do 수정하기</h1>
			<!-- 현재 수정할 값 들가져와야 함.  -->
			<form action = "/update" method="post" class = "container">
				<p>제목</p>
				<input type="text" name="updateTitle"autocomplete='off' class = "title" value = "${todo.todoTitle}">

				 <p>메모</p>
				 <textarea name="updateMemo" autocomplete='off' class = "memo"> ${todo.todoMemo}</textarea>
					<!--  
						<input type="text" name="updateMemo" placeholder="메모" autocomplete='off' id = "updateMemo">
					-->
				<input type="hidden" name="updateNo"autocomplete='off' value = "${todo.todoNo}">
	
				<button  class = "btn" id = "updateBtn">수정하기</button>
			</form>
		</main>
	</body>
