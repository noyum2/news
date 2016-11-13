<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Sign Up</title>
<link href="../resources/css/bootstrap.css" rel="stylesheet">
</head>
<body>
	<c:import url="header&footer/header.jsp"></c:import>
	<div class="container" align="center">
		<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<div class="panel panel-primary" style="margin-top: 30px;">
					<div class="panel-heading">회원 가입</div>
					<div class="panel-body">
						<form class="form-horizontal" action="/news/signup/signupInput" method="post">
							<div class="form-group">
								<label class="col-sm-2 control-label">ID</label>
								<div class="col-sm-9">
									<input type="text" class="form-control" id="userId" name="userId">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">Password</label>
								<div class="col-sm-9">
									<input type="password" class="form-control" id="userPass" name="userPass">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">이름</label>
								<div class="col-sm-9">
									<input type="text" class="form-control" id="userName" name="userName">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">E-mail</label>
								<div class="col-sm-9">
									<input type="email" class="form-control" id="userEmail" name="userEmail">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">전화번호</label>
								<div class="col-sm-9">
									<input type="text" class="form-control" id="userPhone" name="userPhone">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">성별</label>
								<div class="col-sm-4">
									<label class="radio-inline"> <input type="radio" name="gender" value="m"> 남
									</label> <label class="radio-inline"> <input type="radio" name="gender" value="w"> 여
									</label>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">주소</label>
								<div class="col-sm-9">
									<input type="text" class="form-control" id="useraddr" name="userAddr">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">생년월일</label>
								<div class="col-sm-9">
									<input type="text" class="form-control" id="userBirth" name="userBirth">
								</div>
							</div>
							<div class="form-group">
								<button type="submit" class="btn btn-success">제출</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="../resources/js/jquery.js"></script>
	<script src="../resources/js/bootstrap.min.js"></script>
</body>
</html>