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
		<link href="${pageContext.request.contextPath }/style/css/add_to_card_reset.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath }/style/css/add_to_card_style.css" rel="stylesheet">
		<link rel="icon" href="${pageContext.request.contextPath }/style/images/default/icon.png" />
	</head>

	<body>
		<p class="registrationID"></p>
		<nav class="navbar navbar-inverse navbar-fixed-top">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false"
						aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
					<a class="navbar-brand" href="javascript:;">Ele</a>
				</div>
				<div id="navbar" class="collapse navbar-collapse">
					<ul class="nav navbar-nav">
						<li class="active"><a href="javascript:;">Home</a></li>
						<li><a href="#about">About</a></li>
						<li><a href="#contact">Contact</a></li>
					</ul>
					<ul id="unlogin" class="nav navbar-nav navbar-right">
						<li><a href="javascript:;">Sign in</a></li>
						<li><a href="javascript:;">Sign up</a></li>
					</ul>
					<ul id="login" class="nav navbar-nav navbar-right">
						<li class="dropdown"><a id="username" href="javascript:;" class="dropdown-toggle" data-toggle="dropdown"></a>
							<ul class="dropdown-menu">
								<li><a href="javascript:;"> Profile</a></li>
								<li><a href="javascript:;"> Inbox</a></li>
								<li><a href="javascript:;"> Settings</a></li>
								<li class="divider"></li>
								<li><a href="../../UserLogout.action"> Log Out</a></li>
							</ul>
						</li>
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
		</nav>

		<div class="jumbotron">
			<div class="container">
				<h1 class="store_name"></h1>
				<p class="store_description"></p>
				<div class="detail" style="display : none;">
					<p>店铺地址：<span class="storeAddress"></span></p>
					<p>店主联系方式：<span class="phone"></span></p>
				</div>
			</div>
		</div>

		<div class="container" style="padding-bottom: 88px">
			<div class="row clearfix">
				<div class="col-sm-offset-8 col-md-offset-8">
					<div class="btn-toolbar" role="toolbar">
						<div class="btn-group">
							<button type="button" class="sortByPrice btn btn-default">价格<span class="glyphicon glyphicon-arrow-down"></span></button>
							<button type="button" class="sortBySoldNum btn btn-default">销量<span class="glyphicon glyphicon-arrow-down"></span></button>
						</div>
					</div>
				</div>
			</div>
			<hr>
			<div class="row clearfix">
				<div class="col-sm-10 col-md-10 column">
					<div class="container-fluid">
						<div class="root" class="row" style="padding: 20px">
							
							
						</div>
					</div>
					<ul class="pager">
						<li class="previous"><a href="javascript:;">Previous</a></li>
						<li class="pageNum">1</li>
						<li class="next"><a href="javascript:;">Next</a></li>
					</ul>
				</div>

				<div class="col-sm-10 col-md-2 column">
					<div class="panel panel-info">
						<div class="panel-heading">
							<h3 class="panel-title">店铺状态</h3>
						</div>
						<div class="panel-body"><span class="status"></span></div>
					</div>
					<div class="panel panel-info">
						<div class="panel-heading">
							<h3 class="panel-title">商家公告</h3>
						</div>
						<div class="panel-body announcement"></div>
					</div>
					<div class="panel panel-info">
						<div class="panel-heading">
							<h3 class="panel-title">配送费</h3>
						</div>
						<div class="panel-body">￥<span class="deliveryFee"></span></div>
					</div>
					<hr>
				</div>
			</div>
		</div>

		<footer class="footer" style="text-align: center ">
			<div class="container">
				<p class="text-muted">
					<a href="http://kaidian.ele.me">我要开店</a><span> | </span> <a href="contact.html">联系我们</a><span> | </span> <a href="agreement.html">服务条款和协议</a><span> | </span>					<a href="sitemaps.html">站点地图</a><span> | </span> <a href="http://jobs.ele.me">加入我们</a> <br> 增值电信业务许可证：
					<a class=-link-zero " target="_blank " rel="nofollow "
				href="http://www.shca.gov.cn ">沪B2-20150033</a><span> | </span> <a
				target="_blank " rel="nofollow " href="http://www.miibeian.gov.cn ">沪ICP备
				09007032</a> <span> | </span> <a target="_blank " rel="nofollow "
				href="http://www.sgs.gov.cn/lz/licenseLink.do?method=licenceView&amp;entyId=20120305173227823 ">上海工商行政管理</a>
			<br> Copyright &copy;2008-2015 ele.me, All Rights Reserved.
		</p>
	</div>
	</footer>

	<div class="cd-cart-container empty">
		<a href="#0 " class="cd-cart-trigger "> Cart
			<ul class="count">
				<li>0</li>
				<li>0</li>
			</ul>
		</a>
		<div class="cd-cart ">
			<div class="wrapper ">
				<header>
				<h2>Cart</h2>
				<span class="undo ">Item removed. <a href="#0 ">Undo</a></span> 
				</header>
				<div class="body ">
					<ul>
					</ul>
				</div>
				<footer class="footer "> <a href="confirm_order.jsp?registrationID=" class="checkout btn "><em>Checkout
						- ￥<span>0</span>
				</em></a> </footer>
			</div>
		</div>
	</div>

	<script
		src="${pageContext.request.contextPath }/style/js/jquery.min.js "></script>
	<script
		src="${pageContext.request.contextPath }/style/bootstrap/js/bootstrap.min.js "></script>
	<script
		src="${pageContext.request.contextPath }/style/js/sellerForeBackground.js "></script>
	<script type="text/javascript ">
		var login = "${sessionScope.userLoginStatus} ";

		if (login.match("true ") != null) {
			$("#unlogin ").hide();
			$("#login ").show();
			$("#username ").html("${sessionScope.username}<b class='caret'></b>"); } 
		else { $("#login").hide(); $("#unlogin").show(); } 
		$(".registrationID").html("${sessionScope.userRegistrationID}");
	</script>
	</body>

	</html>