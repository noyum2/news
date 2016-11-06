<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Sign Up</title>
</head>
<body>
<form action="/news/signup/signupInput" method="POST">
아이디<input type="text" name="userId"><br>
비밀번호<input type="password" name="userPass"><br>
이름<input type="text" name="userName"><br>
이메일<input type="text" name="userEmail"><br>
전화번호<input type="text" name="userPhone"><br>
성별 <input type="radio" name="gender" value="1">남자 <input type="radio" name="gender" value="0">여자<br>
주소 <input type="text" name="userAddr">
생일<input type="text" name="userBirth">
<input type="submit" value="회원가입">
</form>
</body>
</html>