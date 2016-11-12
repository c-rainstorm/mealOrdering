<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>饿了么</title>
		<link href="${pageContext.request.contextPath }/style/bootstrap/css/bootstrap.min.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath }/style/css/sticky-footer.css" rel="stylesheet">
		<link rel="icon" href="${pageContext.request.contextPath }/style/images/default/icon.png" />
		<link href="${pageContext.request.contextPath }/style/css/carousel.css" rel="stylesheet">
	</head>

	<body>

		<nav class="navbar navbar-inverse navbar-fixed-top">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false"
						aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
					<a class="navbar-brand" href="../../index.jsp">ELE</a>
				</div>
				<div id="navbar" class="collapse navbar-collapse">
					<ul class="nav navbar-nav">
						<li class="active"><a href="javascript:;">Home</a></li>
						<li><a href="#about">About</a></li>
						<li><a href="#contact">Contact</a></li>
					</ul>

					<ul id="unlogin" class="nav navbar-nav navbar-right">
						<li><a href="../signup/user.jsp">Register</a></li>
						<li><a href="../signin/user.jsp">Login</a></li>
					</ul>
					<ul id="login" class="nav navbar-nav navbar-right">
						<li class="dropdown">
							<a href="javascript:;" id="username" class="dropdown-toggle" data-toggle="dropdown"></a>

							<ul class="dropdown-menu">
								<li><a href="user.jsp">Profile</a>
								</li>
								<li class="divider"></li>
								<li><a href="../../UserLogout.action" id="logout"> Log Out</a></li>
							</ul>
						</li>
					</ul>
				</div>

				<!--/.nav-collapse -->
			</div>
		</nav>

		<div id="myCarousel" class="carousel slide" data-ride="carousel">
			<!-- Indicators -->
			<ol class="carousel-indicators">
				<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
				<li data-target="#myCarousel" data-slide-to="1"></li>
				<li data-target="#myCarousel" data-slide-to="2"></li>
			</ol>
			<div class="carousel-inner" role="listbox">
				<div class="item active">
					<img class="first-slide" src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" alt="First slide">
					<div class="container">
						<div class="carousel-caption">
							<h1>Example headline.</h1>
							<p>
								Note: If you're viewing this page via a
								<code>file://</code> URL, the "next" and "previous" Glyphicon buttons on the left and right might not load/display
								properly due to web browser security rules.
							</p>
							<p>
								<a class="btn btn-lg btn-primary" href="javascript:;" role="button">Sign
								up today</a>
							</p>
						</div>
					</div>
				</div>
				<div class="item">
					<img class="second-slide" src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" alt="Second slide">
					<div class="container">
						<div class="carousel-caption">
							<h1>Another example headline.</h1>
							<p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam
								id dolor id nibh ultricies vehicula ut id elit.</p>
							<p>
								<a class="btn btn-lg btn-primary" href="javascript:;" role="button">Learn
								more</a>
							</p>
						</div>
					</div>
				</div>
				<div class="item">
					<img class="third-slide" src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" alt="Third slide">
					<div class="container">
						<div class="carousel-caption">
							<h1>One more for good measure.</h1>
							<p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam
								id dolor id nibh ultricies vehicula ut id elit.</p>
							<p>
								<a class="btn btn-lg btn-primary" href="javascript:;" role="button">Browse
								gallery</a>
							</p>
						</div>
					</div>
				</div>
			</div>
			<a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev"> <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span> <span class="sr-only">Previous</span>
			</a>
			<a class="right carousel-control" href="#myCarousel" role="button" data-slide="next"> <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
				<span class="sr-only">Next</span>
			</a>
		</div>
		<!-- /.carousel -->


		<div class="bun-group-wrap" style="text-align: center">
			<div class="btn-group">
				<button type="button" class="btn btn-primary category">全部分类</button>
				<button type="button" class="btn btn-primary category">商店超市</button>
				<button type="button" class="btn btn-primary category">小吃宵夜</button>
				<button type="button" class="btn btn-primary category">异国料理</button>
				<button type="button" class="btn btn-primary category">快餐便当</button>
				<button type="button" class="btn btn-primary category">果蔬生鲜</button>
				<button type="button" class="btn btn-primary category">特色菜系</button>
				<button type="button" class="btn btn-primary category">甜品饮品</button>
				<button type="button" class="btn btn-primary category">鲜花蛋糕</button>
			</div>
		</div>
		<hr>
		<div class="container">
			<div class="row root" style="padding: 20px">

			</div>
			<ul class="pager">
					<li class="previous"><a href="javascript:;">Previous</a></li>
					<li class="pageNum">1</li>
					<li class="next"><a href="javascript:;">Next</a></li>
				</ul>
				<hr>
		</div>
		<footer class="footer" style="text-align: center">
			<div class="container">
				<p class="text-muted">
					<a href="http://kaidian.ele.me">我要开店</a><span> | </span> <a href="contact.html">联系我们</a><span> | </span> <a href="agreement.html">服务条款和协议</a><span> | </span>					<a href="sitemaps.html">站点地图</a><span> | </span> <a href="http://jobs.ele.me">加入我们</a> <br> 增值电信业务许可证： <a class="-link-zero"
						target="_blank " rel="nofollow " href="http://www.shca.gov.cn ">沪B2-20150033</a><span> | </span> <a target="_blank "
						rel="nofollow " href="http://www.miibeian.gov.cn ">沪ICP备
				09007032</a> <span> | </span> <a target="_blank " rel="nofollow " href="http://www.sgs.gov.cn/lz/licenseLink.do?method=licenceView&amp;entyId=20120305173227823 ">上海工商行政管理</a>
					<br> Copyright &copy;2008-2015 ele.me, All Rights Reserved.
				</p>
			</div>
		</footer>
		<script src="${pageContext.request.contextPath }/style/js/jquery.min.js "></script>
		<script src="${pageContext.request.contextPath }/style/bootstrap/js/bootstrap.min.js "></script>
		<script src="${pageContext.request.contextPath }/style/js/homeBackground.js "></script>
		<script type="text/javascript">
		var login = "${sessionScope.userLoginStatus}";

		if (login.match("true") != null) {
			$("#unlogin").hide();
			$("#login").show();
			$("#username").html("${sessionScope.username}<b class='caret'></b>");
		} else {
			$("#login").hide();
			$("#unlogin").show();
		}
	</script>
	</body>

	</html>