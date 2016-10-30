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
	<c:forEach var="news" items="${newsList}" varStatus="status">
	제목 : ${news.newsTitle}<br/>
	내용 : ${news.newsDescription}<br/>
	url : ${news.newsUrl}<br/><br/>
	</c:forEach>
</body>
</html>
