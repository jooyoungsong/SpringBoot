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
<style type="text/css">
	.infoimg{
			width : 350px;
			height: 350px;
			border-radius: 36px 36px 0px 0px;
		}
	.wrapinfoimg{
			border: 10px solid #bc8f8f;
			width: 370px;
			height: 660px;
			border-radius: 50px;
	}
	
	#welcome{
		text-align: center;
		font-size: 1.5em;
	}
	
</style>
</head>
<body>
<c:set var="root" value="<%=request.getContextPath() %>"/>
	<div class="wrapinfoimg">
	<img alt="" src="${root }/image/infoimg1.png" class="infoimg">
		<div id="welcome">
			<b>Welcome to Rozley</b>
		</div>
		<div id="binfo" style="width: 300px; margin-left: 15px;">
			<b>COMPANY.</b><h5>이스트앤드</h5>
			<b>TEL.</b><h5>070-4124-4999</h5>
			<b>ADDR.</b><h5>서울특별시 성동구 뚝섬로1길 63 영창디지털타워 지하 2층 201호</h5>
			<b>BANK ACCOUNT.</b><h5>우리 1005-703-333686</h5>
		</div>
			<h5 style="text-align: center; font-weight: bold; color: gray;">copyright ⓒ rozley all rights reserved</h5>
	</div>
</body>
</html>