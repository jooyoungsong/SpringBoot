<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Dongle:wght@300&family=Gaegu:wght@300&family=Nanum+Pen+Script&family=Sunflower:wght@300&display=swap" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.7.0.js"></script>
<title>Insert title here</title>
<style type="text/css">
	.detailAllpage{
		background-color: black;
	}
</style>
</head>
<body class="detailAllpage">
	<div class="container">
	<div style="color: white; margin-top:100px; margin-left: 280px; font-size: 3em;">MOVIE INFO</div>
	<div style="border: 2px solid white; width: 800px; height: 500px; border-radius: 30px; margin-top: 70px; margin-left: 280px; display: flex">
		<img alt="" src="../moviephoto/${dto.poster}" style="width: 300px; height:450px; margin-left: 20px; margin-top: 25px; border:2px solid white;">
		<pre style="display: inline-block; color: white; padding:100px 0px 30px;"><b>
			영화명: ${dto.title } <br>
			감독 : ${dto.director } <br>
			개봉일 : ${dto.opendate }
		</b></pre>
	</div>
	<div style="text-align: center; margin-left: 100px; margin-top: 20px;">
		<button type="button" class="btn btn-warning" onclick="location.href='updateform?num=${dto.num}'">수정</button>
		<button type="button" class="btn btn-danger" onclick="location.href='detail?num=${dto.num}'">삭제</button>
		<button type="button" class="btn btn-warning" onclick="location.href='list'">목록</button>
		<button type="button" class="btn btn-warning" onclick="location.href='insert'">글쓰기</button>
	</div>
	</div>
</body>
</html>