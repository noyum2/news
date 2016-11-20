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

function deleteKeyWord(keyword, divId) {

	var data = {};
	data["keyword"] = keyword;
	$.ajax({
		url : '/news/deleteKeyWord',
		type : 'POST',
		dataType : 'json',
		contentType : 'application/json',
		data : JSON.stringify(data),
		success : function(result) {
			if (result.result == "OK") {
				$('#' + divId).remove();
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
			makeNewsList(result)
		},
		beforeSend : function() {
			$('#newsfeed').empty();
			$('#loading').show();
		},
		complete : function() {
			$('#loading').hide();
			$('#moreButton').show();
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
								+ "</div><div class='panel-body'><div id='newsDescription"
								+ i
								+ "'>"
								+ result[i].newsDescription
								+ "</div><div id='articleBody"
								+ i
								+ "' style='display: none;'></div><div id='seeMore"
								+ i
								+ "' style='text-align: right; padding-right: 10px'><a href='javascript:;' onclick='showNewsDiv(\""
								+ result[i].newsUrl
								+ "\","
								+ i
								+ ")'>더보기 <span class='glyphicon glyphicon-menu-down'></span></a></div><div id='close"
								+ i
								+ "' style='text-align: right; padding-right:10px; display: none;'><a href='javascript:;' onclick='closeNewsDiv("
								+ i
								+ ")'>접기 <span class='glyphicon glyphicon-menu-up'></span></a></div></div></div>");
	}

}

function showNewsDiv(url, index) {
	var desDiv = "#newsDescription" + index;
	var newsDiv = "#articleBody" + index;
	var seeDiv = "#seeMore" + index;
	var closeDiv = "#close" + index;
	getNewsContent(url, newsDiv);
	$(desDiv).hide();
	$(newsDiv).show();
	$(seeDiv).hide();
	$(closeDiv).show();
}

function closeNewsDiv(index) {
	var desDiv = "#newsDescription" + index;
	var newsDiv = "#articleBody" + index;
	var seeDiv = "#seeMore" + index;
	var closeDiv = "#close" + index;
	$(desDiv).show();
	$(newsDiv).hide();
	$(seeDiv).show();
	$(closeDiv).hide();
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
																+ value + "'>");
										document.getElementById("inputKeyWord").value = "";

										if (document
												.getElementById('submitButton').style.display == "none")
											document
													.getElementById('submitButton').style.display = "inline";
									});

					$('#moreNews').click(
							function() {
								$.ajax({
									url : '/news/mainPage/getMoreNews',
									type : 'POST',
									dataType : 'json',
									contentType : 'application/json',
									success : function(result) {
										makeNewsList(result)
									},
									beforeSend : function() {
										$('#moreButton').hide();
										$('#loading').show();
									},
									complete : function() {
										$('#loading').hide();
										$('#moreButton').show();
									},
									error : function(request, status, error) {
										alert("code:" + request.status + "\n"
												+ "error:" + error);
									}
								});
							});

				});