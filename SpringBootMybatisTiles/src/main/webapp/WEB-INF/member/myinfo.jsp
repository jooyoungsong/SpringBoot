<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<link href="https://fonts.googleapis.com/css2?family=Dongle:wght@300&family=Gaegu:wght@300&family=Nanum+Pen+Script&family=Sunflower:wght@300&display=swap" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.7.0.js"></script>
<title>Insert title here</title>
<style type="text/css">
	#pho{
		width: 300px;
		height: 300px;	
	}
</style>

<script type="text/javascript">
	$(function(){
		$("#btnnewphoto").click(function(){
			$("#newphoto").trigger("click");
		});
		
		$("#newphoto").change(function(){
			var num = $(this).attr("num");
			console.log(num);
			
			var form = new FormData();
			form.append("photo", $(this)[0].files[0]); //선택한 1개만 추가
			form.append("num", num);
			
			console.dir(form);
			
			$.ajax({
				type:"post",
				dataType:"html",  //키,값으로 이루어진 형태는(map포함) json 사용하고, 나머지는 html 사용
				url:"updatephoto",
				processData:false,
				contentType:false,
				data:form,
				success:function(){
					location.reload();
				}
			});
		});
		
		$("#loginupdate").click(function(){
			
			var num=$("#loginupdate").attr("num");
			alert(num);
			var name=$("#name").val();
			var hp=$("#hp").val();
			var addr=$("#addr").val();
			var email=$("#email").val();
			
			//var data = "name="+name+",hp="+name; 이런식으로 작성하고 data:{"data":data}라고 하면 된다
			
			$.ajax({
				
				type: "post",  //post =>  여러개면 post라고 생각하고 사용하면 된다.
				dataType:"html",
				url:"updatemember",
				data:{"name":name,"hp":hp,"addr":addr,"email":email,"num":num},
				success:function(){
					alert("회원정보가 수정되었습니다");
					location.reload();
				}
				
			});
			
		});
		
	});
</script>
</head>
<body>
	<table class="table table-bordered" style="width: 800px;">
		<c:if test="${sessionScope.loginok!=null and sessionScope.myid==dto.id }">
		<tr>
			<td rowspan="6" align="center">
				<img alt="" src="../membersave/${dto.photo }" id="pho">
				<br>
				<input type="file" style="display: none;" num="${dto.num }" id="newphoto" > <!-- 사진수정시 호출  -->
				<button type="button" class="btn btn-outline-secondary" id="btnnewphoto">사진수정</button>
			</td>
			<td>NAME : ${dto.name }</td>
			<td rowspan="5" colspan="2" align="center" valign="middle" style="width: 100px;">
				<button type="button" class="btn btn-outline-warning updatebtn" data-bs-toggle="modal" data-bs-target="#myModal1" num=${dto.num }>수정</button>
				<br><br>
				<input type="button" value="삭제" class="btn btn-outline-danger btndelete" num=${dto.num }>
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
		</c:if>
	</table>
	
	<!-- The Modal -->
		<div class="modal" id="myModal1">
			<div class="modal-dialog">
				<div class="modal-content">

					<!-- Modal Header -->
					<div class="modal-header">
						<h4 class="modal-title">정보수정</h4>
						<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
					</div>

					<!-- Modal body -->
					<div class="modal-body">
						<div class="member_login_input">
							<input type="text" id="name" placeholder="이름" value="${dto.name }" 
								class="form-control">
						</div>

						<div class="member_login_input">
							<input type="text" id="hp" placeholder="핸드폰번호" value="${dto.hp }"
								class="form-control">
						</div>
						
						<div class="member_login_input">
							<input type="text" id="addr" placeholder="주소" value="${dto.addr }"
								class="form-control">
						</div>
						
						<div class="member_login_input">
							<input type="text" id="email" placeholder="이메일"  value="${dto.email }"
								class="form-control">
						</div>


					</div>

					<!-- Modal footer -->
					<div class="modal-footer">
						<button type="button" class="btn btn-success"
							data-bs-dismiss="modal" id="loginupdate" num="${dto.num }">update</button>
						<button type="button" class="btn btn-danger"
							data-bs-dismiss="modal">Close</button>
					</div>

				</div>
			</div>
		</div>
</body>
</html>