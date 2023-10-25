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

<div class="alert alert-info" style="width: 750px;">
	<h1>총 ${totalCount }개의 상품이 입고되었습니다</h1>


<table class="table table-bordered" >
	<caption align="top"><span style="float: right;">
		<button type="button" class="btn btn-outline-info"
		onclick="location.href='ipgoform'">상품추가</button>
	</span></caption>
	
	<c:forEach var="dto" items="${list }">  <!-- controller에서의 insert이후 select할때 담았던 list를 써준것 -->
		<tr>
			<td width="400" rowspan="4" style="padding-left: 50px;">
				<c:if test="${dto.photoname!='no'}">
					<c:forTokens var="pho" items="${dto.photoname }" delims=","
					begin="0" end="0"> <!-- 사진,사진,사진 이런식이니깐 사진 하나를 pho로 정의 //begin과 end를 0으로 하면 여러개 사진 중 대표사진 하나만 나오게 할 수 있다(대표이미지)  -->
						<a href="content?num=${dto.num }"><img alt="" src="../upload/${pho }" 
						 style="width: 150px; height: 150px;"></a> <!-- 사진 여러개인데 대표사진으로 해놔서 누르면 사진 여러개 나오도록 하기(num으로 넘기기) -->
					</c:forTokens>
				</c:if>
				<c:if test="${dto.photoname=='no' }">
					<img alt="" src="../image/noImg.jpg"
						style="width: 150px; height: 150px;">
				</c:if>
			</td>
			<td>
				<b>상품명: ${dto.sangpum }</b>
			</td>
		</tr>
		<tr>
			<td>
				<b>단가: <fmt:formatNumber value="${dto.price }" type="currency"/> </b>
			</td>
		</tr>
		<tr>
			<td>
				<b>입고일: <fmt:formatDate value="${dto.ipgoday }" pattern="yyyy년 MM월 dd일"/> </b>
			</td>
		</tr>
		<tr>
			<td>
				<button type="button" class="btn btn-outline-info"
				onclick="location.href='updateform?num=${dto.num}'">수정</button>
				<button type="button" class="btn btn-outline-danger"
				onclick="location.href='delete?num=${dto.num}'">삭제</button>
			</td>
		</tr>
	</c:forEach>
</table>

</div>
</body>
</html>