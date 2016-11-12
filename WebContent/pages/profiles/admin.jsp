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
		<link href="${pageContext.request.contextPath }/style/css/dashboard.css" rel="stylesheet">
		<link rel="icon" href="${pageContext.request.contextPath }/style/images/default/icon.png" />
	</head>

	<body>
		<nav class="navbar navbar-inverse navbar-fixed-top">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false"
						aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
					<a class="navbar-brand" href="#">ELE</a>
				</div>
				<div id="navbar" class="navbar-collapse collapse">
					<ul class="nav navbar-nav navbar-right">
						<ul id="login" class="nav navbar-nav">
							<li class="dropdown">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown"> John Smith <b class="caret"></b></a>
								<ul class="dropdown-menu">
									<li><a href="#"> Profile</a></li>
									<li><a href="#"> Inbox</a></li>
									<li><a href="#"> Settings</a></li>
									<li class="divider"></li>
									<li><a href="../../AdminLogout.action"> Log Out</a></li>
								</ul>
							</li>
						</ul>
						<li><a href="#">About</a></li>
						<li><a href="#">Contaxt</a></li>
						<li><a href="#">Help</a></li>
					</ul>
					<form class="navbar-form navbar-right">
						<input type="text" class="form-control" placeholder="Search...">
					</form>
				</div>
			</div>
		</nav>

		<div class="container-fluid">
			<div class="container" style="padding-bottom: 35px">
				<div class="row">
					<div class="col-sm-3 col-md-2 sidebar">
						<ul class="nav nav-sidebar">
							<li class="active"><a href="#">Overview <span
								class="sr-only">(current)</span></a></li>
							<li><a href="#">Reports</a></li>
							<li><a href="#">Analytics</a></li>
							<li><a href="#">Export</a></li>
						</ul>
						<ul class="nav nav-sidebar">
							<li><a href="">Nav item</a></li>
							<li><a href="">Nav item again</a></li>
							<li><a href="">One more nav</a></li>
							<li><a href="">Another nav item</a></li>
							<li><a href="">More navigation</a></li>
						</ul>
						<ul class="nav nav-sidebar">
							<li><a href="">Nav item again</a></li>
							<li><a href="">One more nav</a></li>
							<li><a href="">Another nav item</a></li>
						</ul>
					</div>


					<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2">
						<div class="row main">
							<h1 class="page-header">Dashboard</h1>
							<div class="row placeholders">
								<div class="col-xs-6 col-sm-3 placeholder">
									<img src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" width="200" height="200" class="img-responsive"
										alt="Generic placeholder thumbnail">
									<h4>Label</h4>
									<span class="text-muted">Something else</span>
								</div>
								<div class="col-xs-6 col-sm-3 placeholder">
									<img src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" width="200" height="200" class="img-responsive"
										alt="Generic placeholder thumbnail">
									<h4>Label</h4>
									<span class="text-muted">Something else</span>
								</div>
								<div class="col-xs-6 col-sm-3 placeholder">
									<img src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" width="200" height="200" class="img-responsive"
										alt="Generic placeholder thumbnail">
									<h4>Label</h4>
									<span class="text-muted">Something else</span>
								</div>
								<div class="col-xs-6 col-sm-3 placeholder">
									<img src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" width="200" height="200" class="img-responsive"
										alt="Generic placeholder thumbnail">
									<h4>Label</h4>
									<span class="text-muted">Something else</span>
								</div>
							</div>

							<h2 class="sub-header">Section title</h2>
							<h1 class="page-header">Dashboard</h1>
							<div class="row placeholders">
								<div class="col-xs-6 col-sm-3 placeholder">
									<img src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" width="200" height="200" class="img-responsive"
										alt="Generic placeholder thumbnail">
									<h4>Label</h4>
									<span class="text-muted">Something else</span>
								</div>
								<div class="col-xs-6 col-sm-3 placeholder">
									<img src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" width="200" height="200" class="img-responsive"
										alt="Generic placeholder thumbnail">
									<h4>Label</h4>
									<span class="text-muted">Something else</span>
								</div>
								<div class="col-xs-6 col-sm-3 placeholder">
									<img src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" width="200" height="200" class="img-responsive"
										alt="Generic placeholder thumbnail">
									<h4>Label</h4>
									<span class="text-muted">Something else</span>
								</div>
								<div class="col-xs-6 col-sm-3 placeholder">
									<img src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" width="200" height="200" class="img-responsive"
										alt="Generic placeholder thumbnail">
									<h4>Label</h4>
									<span class="text-muted">Something else</span>
								</div>
							</div>
							<h1 class="page-header">Dashboard</h1>
							<div class="row placeholders">
								<div class="col-xs-6 col-sm-3 placeholder">
									<img src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" width="200" height="200" class="img-responsive"
										alt="Generic placeholder thumbnail">
									<h4>Label</h4>
									<span class="text-muted">Something else</span>
								</div>
								<div class="col-xs-6 col-sm-3 placeholder">
									<img src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" width="200" height="200" class="img-responsive"
										alt="Generic placeholder thumbnail">
									<h4>Label</h4>
									<span class="text-muted">Something else</span>
								</div>
								<div class="col-xs-6 col-sm-3 placeholder">
									<img src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" width="200" height="200" class="img-responsive"
										alt="Generic placeholder thumbnail">
									<h4>Label</h4>
									<span class="text-muted">Something else</span>
								</div>
								<div class="col-xs-6 col-sm-3 placeholder">
									<img src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" width="200" height="200" class="img-responsive"
										alt="Generic placeholder thumbnail">
									<h4>Label</h4>
									<span class="text-muted">Something else</span>
								</div>
							</div>
							<ul class="pager">
								<li><a href="#">Previous</a></li>
								<li>1</li>
								<li><a href="#">Next</a></li>
							</ul>
							<hr>
						</div>

					</div>
				</div>
			</div>
			<footer class="footer" style="text-align: center; padding-left: 233px; left : 0px">
				<div class="container">
					<p class="text-muted">
						<a href="http://kaidian.ele.me">我要开店</a><span> | </span> <a href="contact.html">联系我们</a><span> | </span> <a href="agreement.html">服务条款和协议</a><span> | </span>						<a href="sitemaps.html">站点地图</a><span> | </span> <a href="http://jobs.ele.me">加入我们</a> <br> 增值电信业务许可证： <a class=-link-zero
							" target="_blank " rel="nofollow "
					href="http://www.shca.gov.cn ">沪B2-20150033</a><span> | </span> <a
					target="_blank " rel="nofollow " href="http://www.miibeian.gov.cn ">沪ICP备
					09007032</a> <span> | </span> <a target="_blank " rel="nofollow "
					href="http://www.sgs.gov.cn/lz/licenseLink.do?method=licenceView&amp;entyId=20120305173227823 ">上海工商行政管理</a>
				<br> Copyright &copy;2008-2015 ele.me, All Rights Reserved.
			</p>
		</div>
		</footer>
	</div>





	<script
		src="${pageContext.request.contextPath }/style/js/jquery.min.js "></script>
	<script
		src="${pageContext.request.contextPath }/style/bootstrap/js/bootstrap.min.js "></script>
</body>
</html>