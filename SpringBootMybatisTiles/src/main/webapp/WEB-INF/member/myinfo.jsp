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
	<table class="table table-bordered" style="width: 500px;">
		<tr>
			<td rowspan="6"><img alt="" src="../membersave/${dto.photo }"> </td>
		</tr>
		<tr>
			<td>NAME : ${dto.name }</td>
			<td rowspan="5" colspan="2" align="center" valign="center" style="width: 100px;">
				<input type="button" value="수정" class="btn btn-outline-warning"><br><br>
				<input type="button" value="삭제" class="btn btn-outline-danger">
			</td>
		</tr>
		<tr>
			<td>ID : ${dto.id }</td>
		</tr>
		<tr>
			<td>HP : ${dto.hp }</td>
		</tr>
		<tr>
			<td>ADDR : ${dto.addr }</td>
		</tr>
		<tr>
			<td>EMAIL : ${dto.email }</td>
		</tr>
	</table>
</body>
</html>