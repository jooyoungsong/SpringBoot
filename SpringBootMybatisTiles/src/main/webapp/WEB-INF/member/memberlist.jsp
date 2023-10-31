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
<script type="text/javascript">
	$(function(){
		$("#allcheck").click(function(){
			var chk=$(this).is(":checked");
			console.log(chk);
			
			$(".del").prop("checked",chk);
			
		});
		
		$("#btnmemberdel").click(function(){
			var cnt=$(".del:checked").length;
			//alert(cnt);
			
			if(cnt==0){
				alert("먼저 선택할 사람을 선택해주세요");
				return;
			}
			
			$(".del:checked").each(function(i,element){
				
				var num=$(this).attr("num");
				console.log(num);
				
				//선택한 체크 삭제
				$.ajax({
					
					type:"get",
					url:"delete",
					dataType:"html",
					data:{"num":num},
					success:function(){
						
						alert("삭제되었습니다");
						location.reload();
					}
				});
			});
			
		});
	});
</script>
</head>
<body>

<h3 class="alert alert-info"style="width: 1200px;">&nbsp;${totalCount }명의 회원이 있습니다.</h3>

<button type="button" class="btn btn-outline-info"
onclick="location.href='form'">Registarion</button>

	<table class="table table-hover" style="width: 1000px;">
			<tr>
				<th>No</th>
				<th>Photo</th>
				<th>Name</th>
				<th>Id</th>
				<th>Hp</th>
				<th>Addr</th>
				<th>Email</th>
				<th>Since</th>
				<th>
					<input type="checkbox" id="allcheck">Resign
				</th>
			</tr>
<%-- 		<c:forEach var="dto" items="${list }" varStatus="i">
			<tr align="center" valign="middle" class="content">
				<td>${i.count }</td>
				<td>
					<a href="detail?num=${dto.num }">
					<img alt="" src="../membersave/${dto.photo }" style="width: 100px; height: 100px;">
					</a>
				</td>
				<td>${dto.name }</td>
				<td>${dto.id }</td>
				<td>${dto.hp }</td>
				<td>${dto.addr }</td>
				<td>${dto.email }</td>
				<td>
					<fmt:formatDate value="${dto.gaipday }" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<input type="checkbox" num=${dto.num } class="del">
					<button type="button" class="btn btn-outline-danger"
					onclick="location.href='delete?num=${dto.num}'">강퇴</button>
				</td>
			</tr>
		</c:forEach> --%>	
		<c:forEach var="dto" items="${list }" varStatus="i">
			<tr align="center" valign="middle" class="content">
				<td>${i.count }</td>
				<td>${dto.name }</td>
				<td>${dto.id }</td>
				<td>
					<a href="myinfo?id=${dto.id }"><img alt="" src="../membersave/${dto.photo }" style="width: 100px; height: 100px;"></a>
				</td>
				<td>${dto.hp }</td>
				<td>${dto.addr }</td>
				<td>${dto.email }</td>
				<td>
					<fmt:formatDate value="${dto.gaipday }" pattern="yyyy-MM-dd HH:mm"/> 
				</td>
				
				<td>
					<input type="checkbox" num=${dto.num } class="del">
				</td>
			</tr>
		</c:forEach>					
	</table>
	
	<button type="button" class="btn btn-danger" id="btnmemberdel">delete</button>
</body>
</html>