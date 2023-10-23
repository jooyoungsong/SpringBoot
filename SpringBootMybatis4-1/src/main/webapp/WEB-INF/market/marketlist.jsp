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
<c:if test="${totalCount==0 }">
	<div class="alert alert-info">
		<b>저장된 상품정보가 없습니다</b>
	</div>
</c:if>
<c:if test="${totalCount>0 }">
	<div class="alert alert-info">
		<b>총${totalCount }개의 저장된 상품정보가 있습니다</b>
	</div>
</c:if>
<div style="margin: 50px 100px;">
<button type="button" class="btn btn-info"
onclick="location.href='writeform'">글쓰기</button>
</div>

<table class="table table-border" style="width: 1000px;">
	<tr>
		<th style="width: 100px;">번호</th>
		<th style="width: 250px;">상품명</th>
		<th style="width: 200px;">가격</th>
		<th style="width: 150px;">입고일자</th>
		<th style="width: 150px;">편집</th>
	</tr>
	<c:forEach var="dto" items="${list }" varStatus="i"> <!--controller에서 list를 보내서 marketlist에서 items로 받고, 그걸 var="dto"로 정의-->
		<tr>
			<td>${i.count }</td>
			<td>
			<img alt="" src="../save/${dto.photoname }" style="width: 100px;">
			${dto.sang }
			</td>
			<td><fmt:formatNumber value="${dto.price }" type="currency"/></td>
			<td><fmt:formatDate value="${dto.ipgoday }" pattern="yyyy-MM-dd"/></td>
			<td>
			<input type="button" class="btn btn-info btn-sm" value="수정"
			onclick="location.href='uform?num=${dto.num}'">
			<input type="button" class="btn btn-danger btn-sm" value="삭제"
			onclick="location.href='delete?num=${dto.num}'">
			</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>