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
</head>
<body>
<div style="margin: 50px 50px; width: 800px;">
<div>JOOYOUNG CINEMA </div>
<form action="insert" method="post" enctype="multipart/form-data">
<table class="table table-bordered">
	<tr>
		<th>영화명</th>
		<td>
			<input type="text" name="title" class="form-control" required="required" style="width: 500px;">
		</td>
	</tr>
	<tr>
		<th>포스터</th>
		<td>
			<input type="file" name="photo" class="form-control" style="width: 500px;">
		</td>
	</tr>
	<tr>
		<th>감독</th>
		<td>
			<input type="text" name="director" class="form-control" required="required" style="width: 500px;">
		</td>
	</tr>
	<tr>
		<th>개봉일</th>
		<td>
			<input type="date" name="opendate" class="form-control" required="required" style="width: 500px;">
		</td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<input type="submit" class="btn btn-outline-success" value="등록">
			<input type="button" class="btn btn-outline-success" value="목록"
			onclick="location.href='list'">
		</td>
	</tr>
</table>
</form>
</div>
</body>
</html>