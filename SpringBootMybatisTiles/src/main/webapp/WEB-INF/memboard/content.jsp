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
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.7.0.js"></script>
<title>Insert title here</title>
<script type="text/javascript">

	$(function(){
		
		list();
		
		//값 넘어가는 지 확인하는 것
		num=$("#num").val();
		loginok="${sessionScope.loginok}";
		myid="${sessionScope.myid}";
		
		//alert(num+","+loginok+","+myid);
		
		//insert하기
		$("#btnansweradd").click(function(){
			
			var content=$("#content").val();
			
			if(content.length==0){
				alert("댓글을 입력해 주세요");
				return;
			}
			
			//게시판에서 댓글 입력했을 때 ajax로 실행하기
			$.ajax({
				
				type:"post",  //다량의 데이터를 넘길 때 post 방식을 씀, 그냥 내가 생각해서 적어주는 것
				dataType:"html",  //json 형식으로 전송하지 않아서
				url:"/mbanswer/ainsert", //controllr.java에서 가상 매핑 주소
				data:{"num":num,"content":content},
				success:function(){
					
					//alert("insert 성공!");
					
					list();
					
					//입력값 초기화
					$("#content").val("");
				}
			});
		});
		
		//댓글의 수정버튼_댓글 수정창 띄우기
		$(document).on("click","i.amod",function(){
			
			idx=$(this).attr("idx");
			alert(idx);
			
			$.ajax({  //$.는 jquery임!
				
				type:"get",
				dataType:"json",
				url:"/mbanswer/adata",
				data:{"idx":idx},
				success:function(res){
					alert(res.content);
					$("#ucontent").val(res.content);
				}
				
			});
			
			$("#mbUpdateModal").modal("show");
		});2.
		
		//모달창에서의 댓글 수정 버튼
		$(document).on("click","#btnupdate",function(){
			
			var content=$("#ucontent").val();
			alert(idx+","+content);
			
			$.ajax({
				
				type:"post",
				dataType:"html",
				url:"/mbanswer/aupdate",
				data:{"idx":idx,"content":content},
				success: function(){  //void라 res로 넘어올 거 없으니깐 빈칸
					list();
					$("#close").trigger("click");
				}
			});
		});
		
		
		
		//댓글 삭제
		$(document).on("click","i.adel",function(){
			
			var idx=$(this).attr("idx");
			alert(idx);
			
			var a=confirm("해당 댓글을 삭제할까요?");
			
			if(a){
				$.ajax({
					
					type:"get",
					dataType:"html",
					url:"/mbanswer/adelete", //controller 매핑주소
					data:{"idx":idx},
					success:function(){  //controller에서 return값 없어서 여기서는 res로 받아오지 않음
						list();
					}
				});
			}
			
		});
		
	});
	
	//사용자함수 정의하기 (댓글 목록)
	function list(){
		
		num=$("#num").val();
		loginok="${sessionScope.loginok}";
		myid="${sessionScope.myid}";
		
		//댓글 작성 숫자 표현 방법
		$.ajax({
			
			type:"get",
			dataType:"json",  //http://localhost:9002/mbanswer/alist?num=6 했을 때 [{},{}] 이런 형태는 json
			url:"/mbanswer/alist",
			data:{"num":num},
			success:function(res){  //여기 data는 내가 controller에서 return한 걸 받아서 처리하는 것
				$("span.acount").text(res.length);  //지금 list를 반환한 걸 받아와서 처리하고 그 길이를 해당 자리에 넣는 걸 한 거네? 즉, 댓글 작성 수 나타남
			
				//댓글 실제로 나타내기 작업
				var s="";
				
				$.each(res,function(i,dto){ //ajax에서 사용하는 반복문 사용법: $.each를 사용한다.
					
					s+="<b>"+dto.name+"</b>: "+dto.content;
					s+="<span class='day'>"+dto.writeday+"</span>";
					
					
					//로그인한 사용자만 해당 댓글 수정,삭제 아이콘 뜨게 하기
					if(loginok!=null&&myid==dto.myid)
					{	
						s+="<i class='bi bi-pencil-square amod' idx='"+dto.idx+"'></i>"
						s+="<i class='bi bi-x-square adel' idx='"+dto.idx+"'></i><br>"  // idx='"+dto.idx+"'를 쓴 이유는 위에서 attr로 받으려고!
					}
				});
				
				$("div.alist").html(s);
			
			}
		});
	}
</script>
</head>
<body>
	<div style="margin: 50px 150px;">
		<table class="table table-bordered" style="width: 600px;">
			<tr>
				<td>
					<h4>
						<b>${dto.subject }</b>
						<span style="font-size: 14pt; color: gray; float: right;">
							<fmt:formatDate value="${dto.writeday }" pattern="yyyy-MM-dd HH:mm"/>
						</span>
					</h4>
					<span>작성자: ${dto.name }(${dto.myid })</span>
					
					<c:if test="${dto.uploadfile!='no' }">
						<span style="float: right;">
							<a href="download?clip=${dto.uploadfile }">
								<i class="bi bi-download"></i>&nbsp;&nbsp;
								<b>${dto.uploadfile }</b>
							</a>
							<!-- clip은 downloadcontroller에서 만든 걸로 한 것!!-->
						</span>
					</c:if>
				</td>
			</tr>
			
			<tr>
				<td>
					<c:if test="${bupload==true }">  <!-- 컨트롤러 content부분에 bupload생성한 이후 -->
						<img alt="" src="../savefile/${dto.uploadfile }" style="width: 200px;">
					</c:if>
					<br><br>
					<pre><!-- 엔터 먹는 pre 태그 -->
						${dto.content }
					</pre>
					<br>
					<b>조회: ${dto.readcount }</b> &nbsp;&nbsp;&nbsp;
					<b>댓글: <span class="acount"></span></b>  <!-- 댓글 개수를 표현  -->
				</td>
			</tr>
			
			 <!-- 댓글(ajax방식) -->
        	 <tr>
           		 <td>
               		<div class="alist"></div>  <!-- 댓글 쓴 걸 볼 수 있는 곳 -->
               
              		<input type="hidden" id="num" value="${dto.num }">  <!-- 게시판에 대한 num을 숨겨라, 이거 mbanswer테이블의 시퀀스는 idx고,num은 게시판과 fk를 준 것! -->
               
              		<c:if test="${sessionScope.loginok!=null }">
                	<div class="aform">
                   		<div class="d-inline-flex"> <!-- 댓글 창 옆에 바로 버튼이 옆으로 붙도록 설정하는 것 -->
                        	<input type="text" class="form-control" style="width: 500px;" placeholder="댓글을 입력하세요"
                       		 id="content">&nbsp;
                        	<button type="button" class="btn btn-info" id="btnansweradd">등록</button>
                     	</div>
                  	</div>
               		</c:if>
            	</td>
         	</tr>
			
			<!-- 버튼들 추가하기 -->
			<tr>
				<td align="right">
				
				<c:if test="${sessionScope.loginok!=null }">
					<button type="button" class="btn btn-outline-info"
					onclick="location.href='form'">글쓰기</button>
				</c:if>
				
				<c:if test="${sessionScope.loginok!=null and sessionScope.myid==dto.myid}">
					<button type="button" class="btn btn-outline-info"
					onclick="location.href='updateform?num=${dto.num}&currentPage=${currentPage }'">수정</button>
				</c:if>
				
				<c:if test="${sessionScope.loginok!=null and sessionScope.myid==dto.myid}">	
					<button type="button" class="btn btn-outline-info"
					onclick="location.href='delete?num=${dto.num}&currentPage=${currentPage }'">삭제</button>
				</c:if>
				
					<button type="button" class="btn btn-outline-info"
					onclick="location.href='list?currentPage=${currentPage}'">목록</button>					
				</td>
			</tr>
		</table>
	</div>
	
	<!-- The Modal //W3S bootstrap5 modal 첫번째 들어가서 복붙 -->
<div class="modal" id="mbUpdateModal">
  <div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">댓글 수정</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>

      <!-- Modal body -->
      <div class="modal-body">
        <input type="text" id="ucontent" class="form-control" name="content">
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
      	<button type="button" class="btn btn-success" id="btnupdate">수정</button>
        <button type="button" class="btn btn-danger" id="close" data-bs-dismiss="modal">Close</button>
      </div>

    </div>
  </div>
</div>

</body>
</html>