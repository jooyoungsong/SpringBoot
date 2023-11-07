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
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
<script src="https://code.jquery.com/jquery-3.7.0.js"></script>
<title>Insert title here</title>
<style type="text/css">
div.likes{cursor: pointer;}
</style>
<script type="text/javascript">
$(function(){
	
	$("div.likes").click(function(){
		
		//좋아요 엄지 누르면 색상 변경되게 하는 기능 작업
		var thumb=$(this).find("i").attr("class");
		//alert(thumb);
		
		if(thumb=='bi bi-hand-thumbs-up'){
			$(this).find("i").attr("class","bi bi-hand-thumbs-up-fill").css("color","black");
			
		}else{
			$(this).find("i").attr("class","bi bi-hand-thumbs-up").css("color","black");
		}
		
		var num=${dto.num};
		//alert(num);  //num뜨면 ajax 진행해도 된다
		
		//좋아요 증가 기능
		$.ajax({
			
			type:"get",
			url:"likes",
			dataType:"json",
			data:{"num":num},
			success:function(res){
				$("div.likes").find("b").text(res.likes);  //b태그 찾아서 res로 넘겨받은 likes를 출력하면 좋아요 누르면 증가됨
			}
		});
	});
});
</script>
</head>
<body>

<div style="width: 600px; margin: 50px 100px;">
<table class="table table-bordered">
	<tr>
		<td>
			<h2>${dto.subject }</h2>
			<b>${dto.name }(${dto.id })</b><br>
			<span style="color: gray; font-size: 0.8em;">
				<fmt:formatDate value="${dto.writeday }" pattern="yyyy-MM-dd HH:mm"/>
				&nbsp;&nbsp;&nbsp;&nbsp;
				조회:${dto.readcount }
			</span>
		</td>
	</tr>
	<tr>
		<td>
			<pre>
				${dto.content }
			</pre><br><br>
			<c:if test="${dto.photo!=null }">
				<c:forEach var="photo" items="${dto.photo }">
					<img alt="" src="../rephoto/${photo }" width="100" class="img-thumbnail">
				</c:forEach>
			</c:if>
			<br><br>
			<div class="likes">
				<i class="bi bi-hand-thumbs-up"></i>&nbsp;&nbsp;
				좋아요 <b>${dto.likes }</b>
			</div>
		</td>
	</tr>
	
	<tr>
		<td>
			<button type="button" class="btn btn-outline-success"
			onclick="location.href='form?num=${dto.num}&regroup=${dto.regroup }&restep=${dto.restep }&relevel=${dto.relevel }&currentPage=${currentPage }'">답글</button>
			
			<c:if test="${sessionScope.loginok!=null && sessionScope.myid==dto.id}">
			<button type="button" class="btn btn-outline-success"
			onclick="location.href=''">수정</button>
			</c:if>
			
			<c:if test="${sessionScope.loginok!=null && sessionScope.myid==dto.id}">
			<button type="button" class="btn btn-outline-success"
			onclick="location.href='list?currentPage=${currentPage}'">목록</button>
			</c:if>

			<c:if test="${sessionScope.loginok!=null }">		
			<button type="button" class="btn btn-outline-success"
			onclick="location.href=''">글쓰기</button>
			</c:if>	
		</td>
	</tr>
</table>
</div>

</body>
</html>