<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Dongle:wght@300&family=Gamja+Flower&family=Nanum+Pen+Script&family=Noto+Serif+KR:wght@200&display=swap" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.7.0.js"></script>
<title>Insert title here</title>
</head>
<body>
   <!-- 로그인폼 만들어오기 -->
<div>
<form action="loginprocess" method="post">
   <table>
      <tr>
         <td width="300px">
            <input type="text" placeholder="아이디" name="id" class="form-control" required="required" value="${sessionScope.saveok==null?"":sessionScope.myid }">
         </td>
      </tr>
      <tr>
         <td  width="300px">
            <input type="password" placeholder="비밀번호"  name="pass" class="form-control" required="required">
         </td>
      </tr>
      <tr>
         <td>
            <input type="checkbox" name="cbsave" ${sessionScope.saveok==null?"":"checked" }>아이디저장
            <button class="btn btn-outline-info" type="submit" style="width: 300px;">로그인</button>
         </td>
      </tr>
   </table>
   </form>
</div>
</body>

</html>
