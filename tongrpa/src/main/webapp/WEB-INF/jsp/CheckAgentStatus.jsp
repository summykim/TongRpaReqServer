<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>통선임 RPA Agent상태보기 </title>
    <link rel="stylesheet" href="/webjars/bootstrap/3.3.4/dist/css/bootstrap.min.css">
    <script src="/webjars/jquery/2.1.3/dist/jquery.min.js"></script>
	<script src="/webjars/bootstrap/3.3.4/dist/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container" >
  <h3>통선임 RPA Agent상태</h3>
  <p>Agent 들의 상태를 표시합니다.</p>           
  <table class="table table-hover">
    <thead>

      <tr>
        <th>AgentID</th>
        <th>Agent명</th>
        <th>Agent상태</th>
       </tr>
  
    </thead>
    <tbody>
     <c:forEach items="${result}" var="info">
      <tr>
        <td>${info.agent_id}</td>
        <td>${info.agent_nm}</td>
        <td>${info.agent_status} </td>
      </tr>
     </c:forEach>  
    </tbody>
  </table>
  <table class="table table-hover">
    <thead>

      <tr>
        <th>날짜</th>
        <th>AgentID</th>
        <th>성공</th>
        <th>실패</th>
        <th>지연처리</th>        
       </tr>
  
    </thead>
    <tbody>
     <c:forEach items="${resultStat}" var="info">
      <tr>
        <td>${info.UPD_DT}</td>
        <td>${info.agent_id}</td>
        <td>${info.success_cnt}</td>
        <td>${info.fail_cnt}</td>
        <td>${info.delay_cnt}</td>
      </tr>
     </c:forEach>  
    </tbody>
  </table>
  <button type="button" class="btn btn-primary" onclick="location.reload();">새로고침</button> 
</div>
</body>
</html>
