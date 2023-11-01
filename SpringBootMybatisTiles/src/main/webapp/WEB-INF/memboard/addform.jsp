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
<div style="margin: 50px 100px;">
	<form action="insert" method="post" enctype="multipart/form-data">
		<table class="table table-bordered" style="width: 500px;">
			<caption align="top"><b>회원전용 글쓰기</b></caption>
			<tr>
				<th>제목</th>
				<td>
					<input type="text" name="subject" class="form-control"
					required="required" autofocus="autofocus">
				</td>
			</tr>
			
			<tr>
				<th>업로드</th>
				<td>
					<input type="file" name="upload" class="form-control"> <!-- dto에서 올릴 이름 정한 것 대로 넣어줘야 함, 그래서 name이 upload가 됨  -->
				</td>
			</tr>
			
			<tr>
				<td colspan="2">
					<textarea style="width: 400px; height: 150px;"
					class="form-control" required="required" name="content"></textarea>
				</td>
			</tr>
			
			<tr>
				<td colspan="2" align="centet">
					<button type="submit" class="btn btn-outline-info">등록</button>
					<button type="button" class="btn btn-outline-info"
					onclick="location.href='list'">목록</button>
				</td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>