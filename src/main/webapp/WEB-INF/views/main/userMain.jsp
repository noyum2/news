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
<style>
.main_left_top {
	width: 100%;
	background-color: white;
}

.main_left_bottom {
	width: 100%;
	background-color: white;
}

.inner_main_left_top {
	background-color: #368AFF;
}

.drop_down_keyWord {
	background-color: white;
	display: none;
}

.drop_down_account {
	background-color: white;
	display: none;
}

.page_left {
	float: left;
	width: 30%;
	padding-top: 40px;
	position: fixed;
}

.page_right {
	float: right;
	width: 60%;
	padding-top: 40px;
	max-height: 100px;
}

.newsfeed {
	width: 500px;
	width: 100%;
}

.newsfeed_title {
	background-color: #368AFF;
}

.newsfeed_contents {
	background-color: white;
}
</style>
</head>
<body style="margin: 0px;">
	<div class="container">
		<c:import url="/WEB-INF/views/header&footer/header.jsp"></c:import>
		<div class="page_left">
			<div class="main_left_top">
				<div class="panel-group" id="accodion" role="tablist" aria-multiselectable="true">
					<div class="panel panel-primary">
						<div class="panel-heading" role="tab" id="headingOne">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#accodion" href="#collapseOne" aria-expanded="false" aria-controls="collapseOne"> + 내 키워드 보기 </a>
							</h4>
						</div>
						<div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-lebelledby="headingOne">
							<div class="list-group">
								<c:forEach items="${keywords}" var="list" varStatus="status">
									<div id="div${status.count}" style="clear:both"><div style="width:80%;float:left"><a href="javascript:;" class="list-group-item" onclick="showNewsFeedByKeyWord('${list.content}')">${list.content}</a></div><div><button class = "btn"onclick="deleteKeyWord('${list.content}','div${status.count}')">삭제</button></div></div>
								</c:forEach>
								<a href="javascript:;" class="list-group-item" onclick="showInput()" style="clear:both">+키워드 추가</a>
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
				</div>
				<br />
				<div class="main_left_bottom">
					<div onclick="showAccount()" class="inner_main_left_top">
						<span style="color: white;"> + 내 정보 보기</span>
					</div>
					<div id="drop_down_account" class="drop_down_account">
						<li>윤현도</li>
						<li>윤현도</li>
						<li>윤현도</li>
						<li>윤현도</li>
						<li>윤현도</li>
					</div>
				</div>
			</div>
		</div>
		<div class="page_right">
			<div id="newsfeed" class="newsfeed">
				<div id="newsfeed_contents" class="newsfeed_contents"></div>
			</div>
			<br />
		</div>
	</div>
	<script>
		function showKeyWord() {
			document.getElementById("drop_down_keyWord").style.display = (document
					.getElementById("drop_down_keyWord").style.display == "none") ? "inline"
					: "none";

		}
		function showAccount() {
			document.getElementById("drop_down_account").style.display = (document
					.getElementById("drop_down_account").style.display == "none") ? "inline"
					: "none";

		}
		function showInput() {
			document.getElementById('inputDiv').style.display = (document
					.getElementById('inputDiv').style.display == "none") ? "inline"
					: "none";
		}
		function checkEnter() {
			if (event.keyCode == 13) {
				var value = document.getElementById("inputKeyWord").value;
				$('#divKeyWord')
						.append(
								"<input type='text' class='form-control' style='float:left;width:50%;' name='keyword' readonly value='"
										+ value + "'>");
				document.getElementById("inputKeyWord").value = "";

				if (document.getElementById('submitButton').style.display == "none")
					document.getElementById('submitButton').style.display = "inline";
			}
		}
		
		function deleteKeyWord(keyword,divId) {
			
			var data = {};
			data["keyword"] = keyword;
			$.ajax({
				url : '/news/deleteKeyWord',
				type : 'POST',
				dataType : 'json',
				contentType : 'application/json',
				data : JSON.stringify(data),
				success : function(result) {
					if(result.result=="OK"){
						$('#'+divId).remove();
					}
				},
				error : function(request, status, error) {
					alert("code:" + request.status + "\n" + "error:" + error);
				}
			});
		}
		function showNewsFeedByKeyWord(keyword) {
			var data = {};
			data["keyword"] = keyword;
			$.ajax({
				url : '/news/mainPage/getKeyword',
				type : 'POST',
				dataType : 'json',
				contentType : 'application/json',
				data : JSON.stringify(data),
				success : function(result) {
					$('#newsfeed').empty();
					makeNewsList(result)
				},
				error : function(request, status, error) {
					alert("code:" + request.status + "\n" + "error:" + error);
				}
			});
		}
		function makeNewsList(result) {
			for ( var i in result) {
				$('#newsfeed')
						.append(
								"<div class='panel panel-info'><div class='panel-heading'>"
										+ result[i].newsTitle
										+ "</div><div class='panel-body'><div id='newsDescription"+i+"'>"
										+ result[i].newsDescription
										+ "</div><div id='articleBody"+i+"' style='display: none;'></div><div id='more' style='text-align: right; padding-right: 10px'><a href='javascript:;' onclick='showNewsDiv(\""
										+ result[i].newsUrl + "\"," + i
										+ ")'>더보기</a></div></div></div>");
			}

		}

		function showNewsDiv(url, index) {
			var desDiv = "#newsDescription" + index;
			var newsDiv = "#articleBody" + index;
			getNewsContent(url, newsDiv);
			$(desDiv).hide();
			$(newsDiv).show();
		}
		function getNewsContent(urlstr, targetDiv) {
			var data = {};
			data["urlstr"] = urlstr
			$.ajax({
				type : 'POST',
				url : '/news/mainPage/getContent',
				data : JSON.stringify(data),
				dataType : 'json',
				contentType : "application/json",
				success : function(result) {
					$(targetDiv).html(result.newsContent);
				},
				error : function(request, status, error) {
					alert("code:" + request.status + "\n" + "error:" + error);
				}
			});

		}
		$(document)
				.ready(
						function() {
							$('#collapseOne').collapse({
								'toggle' : false,
								'parent' : '#accodion'
							});

							$('#addKeyWord')
									.click(
											function() {
												var value = document
														.getElementById("inputKeyWord").value;
												$('#divKeyWord')
														.append(
																"<input type='text' class='form-control' style='float:left;width:50%;' name='keyword' readonly value='"
																		+ value
																		+ "'>");
												document
														.getElementById("inputKeyWord").value = "";

												if (document
														.getElementById('submitButton').style.display == "none")
													document
															.getElementById('submitButton').style.display = "inline";
											});

						});
	</script>
</body>
</html>