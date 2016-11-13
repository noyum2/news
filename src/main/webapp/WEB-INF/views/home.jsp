<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Home</title>
<link href="resources/css/bootstrap.css" rel="stylesheet">
</head>
<body>
	<div class="container" align="center" style="padding-bottom: 20px;">
		<div class="row">
			<c:import url="../views/header&footer/header.jsp"/>
		</div>
		<div class="row">
			<img src="resources/images/index.png" width="100%" height="60%" />
		</div>
		<div class="row" style="text-align: center;">
			<h2>"News Tracer"는 사용자 키워드 기반으로 관심있는 뉴스를 보여줍니다.</h2>
			<hr />
		</div>
		<div class="row">
			<h2>지금 회원으로 등록하고 관심있는 뉴스를 받아보세요!</h2>
			<div class="col-md-6 col-md-offset-3">
				<button type="button" class="btn-lg btn-success btn-block" id="signup" style="height: 100px;">회원 가입</button>
			</div>
		</div>
	</div>
	<script src="resources/js/jquery.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
	<script type="text/javascript">
	$(document).ready(
			function(){
				$('#signup').click(function(){
					location.href='/news/signup/';
				})
			}
			)
	</script>
</body>
</html>
