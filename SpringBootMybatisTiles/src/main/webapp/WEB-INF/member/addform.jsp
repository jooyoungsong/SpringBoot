<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Dongle:wght@300&family=Gaegu:wght@300&family=Nanum+Pen+Script&family=Sunflower:wght@300&display=swap"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.7.0.js"></script>
<title>Insert title here</title>

<style type="text/css">
	#showimg{
		border: 1px solid gray;
		width: 130px;
		height: 160px;
		margin-top: 20px;
	}
</style>

<script type="text/javascript">
	$(function(){
		$("#btnphoto").click(function(){
			$("#myphoto").trigger("click");  //trigger가 강제로 이벤트 발생시키도록 하는 것
		});
		
		//사진 미리보기 기능 넣기
		$("#myphoto").change(function(){
			 
			if($(this)[0].files[0]){
				var reader=new FileReader();
				reader.onload=function(e){
					$("#showimg").attr("src",e.target.result);
				}
				
				reader.readAsDataURL($(this)[0].files[0]);
			}
		});
		
		//아이디 입력시 아이디 체크
        $("#btnidcheck").click(function(){
        	
        	//alert($("#id").val()); 아이디 입력한 게 뜨는 지 확인용
        	
        	$.ajax({
        		
        		type:"get",
        		dataType:"json",
        		url:"idcheck", //ajax에서는 중간경로 빼고 쓰는 것
        		data:{"id":$("#id").val()},
        		success:function(res){
        			
        			if(res.count==0){
        				$("span.idsuccess").text("ok");
        			}else{
        				$("span.idsuccess").text("fail");
        			}
        		}
        	});
        });
		
		//자동으로 옆으로 넘어가게 하기
		$("#pass").keyup(function(){
			
			 if (this.value.length >= 4) {

		     $(this).next().focus();
			
			}
		});
        //2번째 비밀번호 입력하자마자 span태그에 나타내기
		$("#pass2").keyup(function(){
			var p1=$("#pass").val();
			var p2=$(this).val();
			
			if(p1==p2){
				$("span.passsuccess").text("ok");
			}else{
				$("span.passsuccess").text("");
			}
			
		});
		
        //유효성 체크 후에도 값이 넘어가기 때문에, 유효성 검사 실패하면 값 못 넘어가게 해줘야 해
		function check(){
        	
        	//사진
        	if($("#myphoto").val()==''){
        		alert("사진을 넣어주세요");
        		return false;
        	}
        	
        	//id
        	if($("span.idsuccess").text()!='ok'){
        		alert('아이디 중복체크를 해주세요');
        		return false;
        	}
        	
        	//pass
        	if($("span.passsuccess").text()!='ok'){
        		alert('비밀번호가 서로 다릅니다');
        		return false;    
        		//onsubmit="return check()"이 form태그에 있어야지만 return이 true일때만 전송하고 false일 때는 전송하지 않는 것!
        	}
        	
        }
		
	});
	
	/* 사진 미리보기 기능 다른 버전
    $("#myphoto").on("change", function(event) {

        var file = event.target.files[0];

        var reader = new FileReader();
        reader.onload = function(e) {

            $("#showimg").attr("src", e.target.result);
        }

        reader.readAsDataURL(file);
    }); */
</script>	
        
</head>
<body>
	<div style="margin: 100px 100px;">
		<form action="insert" method="post" enctype="multipart/form-data" onsubmit="return check()">
			<table class="table table-bordered" style="width: 600px;">
				<caption align="top"><b>회원가입</b></caption>
					<tr>
						<td rowspan="5" align="center" style="width: 250px;">
								<input type="file" id="myphoto" name="myphoto" style="display: none;">
								<button type="button" id="btnphoto" class="btn btn-secondary">사진선택</button>
								<br>
								<img id="showimg">
						</td>
						<td>
							<div class="d-inline-flex">
								<input type="text" placeholder="아이디입력" name="id" id="id" class="form-control"
								style="width: 120px;"> &nbsp;
								<button type="button" class="btn btn-danger"
								 id="btnidcheck">중복체크</button> &nbsp;
								<span class="idsuccess" style="width: 60px; color: green;"></span>
							</div>
						</td>
					</tr>
					
					<tr>
						<td>
							<div class="d-inline-flex">
								<input type="password" style="width: 120px;" class="form-control"
								name="pass" id="pass" placeholder="숫자4자리" maxlength="4" required="required">
								<input type="password" style="width: 120px;" class="form-control"
								id="pass2" placeholder="숫자4자리" maxlength="4" required="required">
								<span class="passsuccess" style="width: 60px; color: green;"></span>
							</div>
						</td>
					</tr>
					
					<tr>
						<td>
							<div class="d-inline-flex">
								<input type="text" name="name" class="form-control" placeholder="이름" required="required"
								style="width: 150px;">
								<input type="text" name="hp" class="form-control" placeholder="전화번호" required="required"
								style="width: 200px;">
							</div>
						</td>
					</tr>
					
					<tr>
						<td>
								<input type="text" name="email" class="form-control" placeholder="이메일" required="required"
								style="width: 250px;">
						</td>
					</tr>
					
					<tr>
						<td>
								<input type="text" name="addr" class="form-control" placeholder="주소입력" required="required"
								style="width: 300px;">
						</td>
					</tr>
					
					<tr>
						<td colspan="2" align="center">
								<button type="submit" class="btn btn-info" style="width: 120px;">회원가입</button>
								<button type="submit" class="btn btn-info" style="width: 120px;"
								onclick="location.href=''">로그인</button>
						</td>
					</tr>
			</table>
		</form>
	</div>

</body>
</html>