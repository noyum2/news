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
<script src="/resources/js/jquery.js" type="text/javascript"></script>
<style>
.newsContents{
border:1px blue solid;
border-radius: 5px;
background-color: white;
}
</style>
</head>
<body style="background-color: gray;">
	<c:forEach var="news" items="${newsList}" varStatus="status">
		<div class="newsContents">
			제목 : ${news.newsTitle}<br/>
			<hr>
			내용 : ${news.newsDescription}<br/>
			url : <a href="${news.newsUrl}">${news.newsUrl}</a><br/><br/>
		</div>
		<br>
	</c:forEach>
</body>
</html>
