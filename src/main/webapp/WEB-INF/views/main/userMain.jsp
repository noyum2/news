<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<%
	response.setContentType("text/html; charset=utf-8");
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>News Tracer</title>
<link href="resources/css/userMain.css" rel="stylesheet">
</head>
<body style="margin: 0px;">
	<div class="container">
		<div id="top" style="width: 100%">
			<c:import url="/WEB-INF/views/header&footer/header.jsp"></c:import>
		</div>
		<div style="width: 100%">
			<div class="page_left">
				<div>
					<h1 style="color: #368AFF;">
						<span class="glyphicon glyphicon-list"></span> <strong>MENU</strong>
					</h1>
					<div class="panel panel-default">
						<div class="panel-body">
							<div class="main_left_top">
								<div class="panel-group" id="accodion" role="tablist" aria-multiselectable="true">
									<div class="panel panel-primary">
										<div class="panel-heading" role="tab" id="headingOne">
											<h4 class="panel-title">
												<a data-toggle="collapse" data-parent="#accodion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne"> + 내 키워드 보기 </a>
											</h4>
										</div>
										<div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-lebelledby="headingOne">
											<div class="list-group">
												<div id="keywordList">
													<c:forEach items="${keywords}" var="list" varStatus="status">
														<div id="div${status.count}" style="overflow: hidden;">
															<div style="overflow: hidden;">
																<a href="javascript:;" style="width: 90%; float: left;" class="list-group-item" onclick="showNewsFeedByKeyWord('${list.content}')">${list.content}</a> <span style="width: 10%; float: left;"><button class="btn btn-danger list-group-item" style="margin: 0px; width: 100%; height: 100%" onclick="deleteKeyWord('${list.content}','div${status.count}')">
																		<span class="glyphicon glyphicon-remove"></span>
																	</button></span>
															</div>
														</div>
													</c:forEach>
													<input type="hidden" value="${keywords.size()}" id="statusValue">
												</div>
												<a href="javascript:;" class="list-group-item" onclick="showInput()" style="clear: both">+키워드 추가</a>
											</div>
											<div style="overflow: hidden;">
												<form action="/news/mainPage/inputKeyword" method="POST">
													<div id="divKeyWord" style="overflow: hidden;"></div>
													<input type="submit" class="btn btn-primary btn-block" id="submitButton" value="추가하기" style="display: none;">
												</form>
											</div>
											<div id="inputDiv" style="display: none;">
												<div class="input-group">
													<input type="text" class="form-control" id="inputKeyWord" onkeydown="checkEnter()" placeholder="키워드를 입력해주세요"> <span class="input-group-btn">
														<button class="btn btn-default" id="addKeyWord">
															<span class="glyphicon glyphicon-plus"></span>
														</button>
													</span>
												</div>
											</div>
										</div>
									</div>
									<div class="panel panel-primary">
										<div class="panel-heading" role="tab" id="headingTwo">
											<h4 class="panel-title">
												<a class="collapsed" data-toggle="collapse" data-parent="#accodion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo"> + 실시간 추천 검색어</a>
											</h4>
										</div>
										<div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
											<div class="panel-body">
												<c:forEach items="${recommandList}" var="list" varStatus="status">
													<li>${list}</li>
												</c:forEach>
											</div>
										</div>
									</div>
									<div class="panel panel-primary">
										<div class="panel-heading">
											<h4 class="panel-title">
												<a href="#top"><span class="glyphicon glyphicon-triangle-top"></span> 상단으로 돌아가기</a>
											</h4>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="page_right">
				<div>
					<h1 style="color: #368AFF;">
						<span class="glyphicon glyphicon-comment"></span> <strong>CONTENT</strong>
					</h1>
					<div class="panel panel-default">
						<div class="panel-body">
							<div id="newsfeed" class="newsfeed">
								<div class="row">
									<div class="col-md-6 col-md-offset-3">
										<img src="resources/images/newsimage.jpg" class="img-responsive">
									</div>
								</div>
								<h3 style="text-align: center;">
									키워드를 등록하고, 키워드를 클릭하면 뉴스를 받아옵니다.<br /> <br />지금 바로 사용해보세요!
								</h3>
								<br /> <br /> <br />
								<p style='text-align: right;'>made by 윤현도, 김진욱, 김철중</p>
							</div>
							<div id="newsfeed_contents" class="newsfeed_contents"></div>
							<div id="loading" class="sk-fading-circle" style="display: none;">
								<div class="sk-circle1 sk-circle"></div>
								<div class="sk-circle2 sk-circle"></div>
								<div class="sk-circle3 sk-circle"></div>
								<div class="sk-circle4 sk-circle"></div>
								<div class="sk-circle5 sk-circle"></div>
								<div class="sk-circle6 sk-circle"></div>
								<div class="sk-circle7 sk-circle"></div>
								<div class="sk-circle8 sk-circle"></div>
								<div class="sk-circle9 sk-circle"></div>
								<div class="sk-circle10 sk-circle"></div>
								<div class="sk-circle11 sk-circle"></div>
								<div class="sk-circle12 sk-circle"></div>
							</div>
							<br />
							<div id="moreButton" style="display: none;">
								<button id="moreNews" class="btn btn-success btn-block">더 가져오기</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="resources/js/viewjs/userMain.js"></script>
</body>
</html>