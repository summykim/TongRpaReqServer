<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>통선임 RPA 실행결과</title>
</head>
<body>
    Hello, Spring Boot App


      ${result.exec_req_id} <br/>
      ${result.job_id} <br/>
      ${result.reg_dtm} <br/>
      ${result.upd_dtm} <br/>
      ${result.rlt_data} <br/>      
      ${result.rlt_status} <br/>     


</body>
</html>
