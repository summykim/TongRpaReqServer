<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>통선임 RPA 실행결과</title>
    <link rel="stylesheet" href="/webjars/bootstrap/3.3.4/dist/css/bootstrap.min.css">
    <script src="/webjars/jquery/2.1.3/dist/jquery.min.js"></script>
	<script src="/webjars/bootstrap/3.3.4/dist/js/bootstrap.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/json2html/1.2.0/json2html.min.js"></script>
</head>
<body>
<div class="container" >
  <h3>통선임 RPA 실행결과</h3>
  <p>챗봇을 통한 실행 결과를 볼수 있습니다.</p>           
  <table class="table table-hover">
    <thead>
      <tr>
        <th style="width:20%">항목</th>
        <th>내용</th>
       </tr>
    </thead>
    <tbody>
      <tr>
        <td>JOB아이디</td>
        <td>${result.job_id}</td>

      </tr>
     <tr>
        <td>요청일시</td>
        <td>${result.reg_dtm}</td>

      </tr>
     <tr>
        <td>실행일시</td>
        <td>${result.upd_dtm}</td>

      </tr>
     <tr>
        <td>JOB상태</td>
        <td>
		<c:choose>
		<c:when test="${result.job_status== 'CMP'}">실행완료</c:when>
		<c:when test="${result.job_status== 'ING'}">실행중</c:when>
		<c:when test="${result.job_status== 'REQ'}">요청완료</c:when>
		</c:choose>
 		</td>
      </tr>
     <tr>
        <td>실행결과</td>
        <td>${result.rlt_status}</td>

      </tr>
     <tr>
        <td>실행결과데이터</td>
        <td>${result.rlt_data}</td>

      </tr>
    </tbody>
  </table>
  <button type="button" class="btn btn-primary" onclick="location.reload();">새로고침</button> 
</div>
</body>
</html>
