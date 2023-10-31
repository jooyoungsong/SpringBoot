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
	#btnlogin{
		margin-left: 800px;
	}
</style>
<script type="text/javascript">
	$(function(){
		
		$("#btnloginok").click(function(){
			
			
			//아이디 비번 읽기
			var id=$("#loginid").val();
			var pass=$("#loginpass").val();
			
			alert(id+","+pass);
			
			var root='${root}';
			console.log("root"+root);
			
			$.ajax({
				
				type:"get",
				url:root+"/member/login",
				dataType:"json",
				data:{"id":id,"pass":pass},
				success:function(res){
					if(res.result=='fail'){
						alert("아이디나 비번이 맞지 않습니다")
					}else{
						location.reload();  //새로고침_다시 그 페이지로 돌아감
					}
				}
			})
		});
	})
</script>
</head>
<body>
<c:set var="root" value="<%=request.getContextPath() %>"/>

<!-- 로그인 안하면 기본사진, 로그인하면 자기사진으로 변경하기 -->
<c:if test="${sessionScope.loginok==null }">
	<a href="${root }/"><img alt="" src="${root }/image/title1.png"> </a>
	<!-- 어디에서든 사진 누르면 메인(root)로 가도록 설정 -->
</c:if>

<c:if test="${sessionScope.loginok!=null }">
	<a href="${root }/"><img alt="" src="${root }/membersave/${sessionScope.loginphoto }.png"> </a>
	<!-- 어디에서든 사진 누르면 메인(root)로 가도록 설정 -->
</c:if>


<c:if test="${sessionScope.loginok==null }">
	<br><br><button type="button" class="btn btn-outline-success"
	onclick="location.href='${root}/login/main'" style="float: right;">Login</button>
</c:if>

<c:if test="${sessionScope.loginok!=null }">
	<br><br><b>${sessionScope.myid }님이 로그인 중...</b>
	<button type="button" class="btn btn-outline-danger"
	onclick="location.href='${root}/login/logoutprocess'" style="float: right;">Logout</button>
</c:if>
	
	<!-- ajax로그인 --> 
<div class="container">
		
		<c:if test="${sessionScope.loginok==null }">
		<button type="button" class="btn btn-primary btnlogin" data-bs-toggle="modal" data-bs-target="#myModal">AjaxLogin</button>
		</c:if>
		
		<c:if test="${sessionScope.loginok!=null }">
		<button type="button" class="btn btn-primary btnlogin" data-bs-toggle="modal" data-bs-target="#myModal">AjaxLogout</button>
		</c:if>

		<!-- The Modal -->
		<div class="modal" id="myModal">
			<div class="modal-dialog">
				<div class="modal-content">

					<!-- Modal Header -->
					<div class="modal-header">
						<h4 class="modal-title">Login</h4>
						<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
					</div>

					<!-- Modal body -->
					<div class="modal-body">
						<div class="member_login_input">
							<input type="text" id="loginid" placeholder="아이디"
								class="form-control">
						</div>

						<div class="member_login_input">
							<input type="password" id="loginpass" placeholder="비밀번호"
								class="form-control">
						</div>

						<div class="member_login_input">
							<input type="checkbox" name="cbsave">아이디 저장
						</div>

					</div>

					<!-- Modal footer -->
					<div class="modal-footer">
						<button type="button" class="btn btn-success"
							data-bs-dismiss="modal" id="btnloginok">Login</button>
						<button type="button" class="btn btn-danger"
							data-bs-dismiss="modal">Close</button>
					</div>

				</div>
			</div>
		</div>

</div>
</body>
</html>