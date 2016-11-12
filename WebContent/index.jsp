<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>饿了么</title>
<link href="style/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="style/css/cover.css" rel="stylesheet">
<link rel="icon" href="style/images/default/icon.png" />
</head>

<body>

	<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">ELE</a>
		</div>
		<div id="navbar" class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<li class="active"><a href="#">Home</a></li>
				<li><a href="#about">About</a></li>
				<li><a href="#contact">Contact</a></li>
				
			</ul>
			<ul id="unlogin" class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown">Register<strong class="caret"></strong></a>
					<ul class="dropdown-menu">
						<li><a href="pages/signup/user.jsp">User</a></li>
						<li class="divider"></li>
						<li><a href="pages/signup/seller.jsp">Seller</a></li>
					</ul></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown">Login<strong class="caret"></strong></a>
					<ul class="dropdown-menu">
						<li><a href="pages/signin/user.jsp">User</a></li>
						<li class="divider"></li>
						<li><a href="pages/signin/seller.jsp">Seller</a></li>
						<li class="divider"></li>
						<li><a href="pages/signin/admin.jsp">Admin</a></li>
					</ul></li>
			</ul>
			<ul id="login" class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"> John Smith <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="#"> Profile</a></li>
						<li><a href="#"> Inbox</a></li>
						<li><a href="#"> Settings</a></li>
						<li class="divider"></li>
						<li><a href="#"> Log Out</a></li>
					</ul></li>
			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
	</nav>

	<div class="site-wrapper">
		<div class="site-wrapper-inner">
			<div class="cover-container">
				<div class="inner cover">
					<h1 class="cover-heading">Eat whatever you want</h1>
					<p class="lead">Cover is a one-page template for building
						simple and beautiful home pages. Download, edit the text, and add
						your own fullscreen background photo to make it your own.</p>
					<p class="lead">
						<a href="pages/profiles/home.jsp" class="btn btn-lg btn-default">Ordering
							right now!</a>
					</p>
				</div>
			</div>
		</div>
	</div>

	<footer class="footer" style="text-align: center">
	<div class="container" style="padding: 20px 0;">
		<p class="text-muted">
			<a href="http://kaidian.ele.me">我要开店</a><span> | </span> <a
				href="contact.html">联系我们</a><span> | </span> <a
				href="agreement.html">服务条款和协议</a><span> | </span> <a
				href="sitemaps.html">站点地图</a><span> | </span> <a
				href="http://jobs.ele.me">加入我们</a> <br> 增值电信业务许可证： <a
				class=-link-zero " target="_blank " rel="nofollow "
				href="http://www.shca.gov.cn ">沪B2-20150033</a><span> | </span> <a
				target="_blank " rel="nofollow " href="http://www.miibeian.gov.cn ">沪ICP备
				09007032</a> <span> | </span> <a target="_blank " rel="nofollow "
				href="http://www.sgs.gov.cn/lz/licenseLink.do?method=licenceView&amp;entyId=20120305173227823 ">上海工商行政管理</a>
			<br> Copyright &copy;2008-2015 ele.me, All Rights Reserved.
		</p>
	</div>
	</footer>

	<script src="style/js/jquery.min.js "></script>
	<script src="style/bootstrap/js/bootstrap.min.js "></script>
	<script src="style/js/public.js "></script>
	<script type="text/javascript">
		var login = "".concat(${sessionScope.phone});
		
		if (login == "") {
			$("#login").hide();
			$("#unlogin").show();
		} else {
			$("#unlogin").hide();
			$("#login").show();
		}
	</script>
</body>

</html>