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
	</head>

	<body>
		<nav class="navbar navbar-inverse">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false"
						aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
					<a class="navbar-brand" href="javascript:;">Project name</a>
				</div>
				<div id="navbar" class="collapse navbar-collapse">
					<ul class="nav navbar-nav">
						<li class="active"><a href="javascript:;">Home</a></li>
						<li><a href="#about">About</a></li>
						<li><a href="#contact">Contact</a></li>
					</ul>
					<ul id="unlogin" class="nav navbar-nav navbar-right">
						<li><a href="../signup/user.jsp" target="_blank">Register</a></li>
						<li><a href="../signin/user.jsp" target="_blank">Login</a></li>
					</ul>
					<ul id="login" class="nav navbar-nav navbar-right">
						<li class="dropdown">
							<a id="username" class="dropdown-toggle" data-toggle="dropdown"></a>
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


		<div class="container main">
			<div class="row">
				<div class="col-xs-8 col-sm-8 col-md-8 col-md-offset-1 has-info">

					<h4>收获人信息</h4>
				</div>

				<div class="col-xs-8 col-sm-8 col-md-8 col-md-offset-1 has-info">
					<select class="form-control ">
						<option class="default">请选择收货人</option>
					</select>
				</div>
				<div class="col-xs-1 col-sm-1 col-md-1  has-info">
					<button type="button" class="addNewAddr btn btn-success">+</button>
				</div>
				<p id="help-block"></p>
			</div>
			<hr>

			<div class="row">

				<div class="col-xs-12 col-sm-12 col-md-12 col-md-offset-1">
					<h4>支付方式</h4>

					<input type="radio" name="optionsRadios" id="optionsRadios1" value="1" checked>在线支付<br> 
					<input type="radio" name="optionsRadios" id="optionsRadios2" value="0">货到付款

				</div>
				<p id="help-block"></p>
			</div>
			<hr>

			<div class="row">
				<div class="col-xs-8 col-sm-8 col-md-4 col-md-offset-1">
					<h4>备注:</h4>
					<input class="form-control commit" type="text">
				</div>
			</div>
			<hr>
			<div class="row">
				<div class="col-sm-12 col-md-10 col-md-offset-1">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>Product</th>
								<th class="text-center">Quantity</th>
								<th class="text-center">Price</th>
								<th class="text-center">Total</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td>
									<h5>商品费用</h5>
								</td>
								<td class="text-right">
									<h5>
										<strong>￥<span class="foodsTotal">0</span></strong>
									</h5>
								</td>
							</tr>
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td>
									<h5>配送费</h5>
								</td>
								<td class="text-right">
									<h5>
										<strong>￥<span class="deliveryFee">0</span></strong>
									</h5>
								</td>
							</tr>
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td>
									<h3>总费用</h3>
								</td>
								<td class="text-right">
									<h3>
										<strong>￥<span class="total">0</span></strong>
									</h3>
								</td>
							</tr>
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td>
									<a href="seller_fore.jsp?registrationID=" type="button" class="return btn btn-default">
										<span class="glyphicon glyphicon-shopping-cart"></span> 返回继续购物
									</a>
								</td>
								<td>
									<a href="javascript:;"type="button" class="confirm btn btn-success">
									确认下单 <span class="glyphicon glyphicon-play"></span>
								</a>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>


		<hr>
		<footer class="footer" style="text-align: center; margin-top: 50px">
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

	<script
		src="${pageContext.request.contextPath }/style/js/jquery.min.js "></script>
	<script
		src="${pageContext.request.contextPath }/style/bootstrap/js/bootstrap.min.js "></script>
	<script
		src="${pageContext.request.contextPath }/style/js/url.min.js "></script>
	<script
		src="${pageContext.request.contextPath }/style/js/confirmOrderBackground.js"></script>
	<script type="text/javascript ">
		var login = "${sessionScope.userLoginStatus} ";

		if (login.match("true ") != null) {
			$("#unlogin ").hide();
			$("#login ").show();
			$("#username ").html("${sessionScope.username}<b class='caret'></b>");
		} else { 
			$("#login").hide(); 
			$("#unlogin").show(); 
		} 

	</script>
	</body>

	</html>