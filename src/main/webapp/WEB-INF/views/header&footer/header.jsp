<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!--
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<script src="//code.jquery.com/jquery.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
-->
<title>News Tracer</title>
<link href="resources/css/bootstrap.css" rel="stylesheet">
<style>
.head {
	background-color: #368AFF;
	margin: 0px;
	height: 75px;
}

.head_title {
	color: white;
	margin: 0px;
	float: left;
}

.login {
	float: right;
}
</style>
</head>
<body>
	<div class="head" style="padding-left: 20px; padding-right: 20px;">
		<br />
		<h2 class="head_title">NEWS TRACER</h2>
		<c:choose>
			<c:when test="${not empty sessionScope.user}">
				<p>${sessionScope.user.getUserName()}</p>
			</c:when>
			<c:otherwise>
				<div style="float: right;">
					<form class="form-inline">
						<div class="form-group">
							<input type="text" class="form-control" id="id" placeholder="Enter Your ID">
						</div>
						<div class="form-group">
							<input type="password" class="form-control" id="pw" placeholder="Password">
						</div>
						<button id="login" class="btn btn-default">Log in</button>
					</form>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
	<script src="resources/js/jquery.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
	<script>
		$(document).ready(
				function() {

					// 회원가입 버튼
					$('#login').click(
							function() {
								var form = {
									userId : $('#id').val(),
									userPass : $('#pw').val(),
								};
								console.log(JSON.stringify(form));
								$.ajax({
									url : '/news/signin/aa',
									type : 'POST',
									dataType : 'json',
									contentType : 'application/json',
									mimeType : 'application/json',
									data : JSON.stringify(form),
									success : function(result) {
										if (result.resultCode == 200)
											alert(result.resultMessage);
										location.reload();
									},
									error : function(request, status, error) {
										alert("code:" + request.status + "\n"
												+ "error:" + error);
									}
								});
							});
				});
	</script>
</body>
</html>