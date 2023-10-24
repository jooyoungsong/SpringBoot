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

	.allpage{
		background-color: black;
		color: white;
	}
	.upPage{
		display: flex;
		margin-top: 50px;
	}
	
	.cname{
		text-align: center;
		margin-top: 50px;
	}

</style>
</head>
<body class="allpage">
<div class="cname">
	<h2>JOOYOUNG CINEMA</h2>
</div>
<div class="upPage">
	<div style="margin-left: 80px; margin-right:80px; float: left">무비차트</div>
	<div>상영예정작</div>
	<button type="button" class="btn btn-success btn-sm" style="float: right; margin-left: 1500px;"
	onclick="location.href='writeform'">영화등록</button>
</div>
<br>
<hr>
<div>
	<div class="container" style="margin-top: 80px;">
		<c:forEach var="a" items="${list }">
			<a href="detail?num=${a.num }"><img alt="" src="../moviephoto/${a.poster }" style="width: 300px; height:400px; margin-left: 20px;"></a>
		</c:forEach>
	</div>
</div>
</body>
</html>