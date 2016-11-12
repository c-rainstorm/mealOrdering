<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>饿了么</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath }/style/css/signup.css">
		<link href="${pageContext.request.contextPath }/style/bootstrap/css/bootstrap.min.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath }/style/css/sticky-footer.css" rel="stylesheet">
		<link rel="icon" href="${pageContext.request.contextPath }/style/images/default/icon.png" />
	</head>

	<body>
		<div class="container">

			<div class="row">
				<div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
					<form role="form" action="${pageContext.request.contextPath }/UserSignup.action" method="post">
					
						<h2>New user? Please Sign Up</h2>
						<hr class="colorgraph">
						<div class="form-group has-info">
							<input type="text" name="username" id="username" class="form-control input-lg" placeholder="User Name" tabindex="1">
							<span class="help-block"></span>
						</div>
						<div class="form-group has-info">
							<input type="text" name="phone" id="phone" class="form-control input-lg" placeholder="Phone Number" tabindex="2">
							<span class="help-block"></span>
						</div>
						<div class="row">
							<div class="col-xs-12 col-sm-6 col-md-6">
								<div class="form-group has-info">
									<input type="password" name="password" id="password" class="form-control input-lg" placeholder="Password" tabindex="3">
									<span class="help-block"></span>
								</div>
							</div>
							<div class="col-xs-12 col-sm-6 col-md-6">
								<div class="form-group has-info">
									<input type="password" name="password_confirmation" id="password_confirmation" class="form-control input-lg" placeholder="Confirm Password"
										tabindex="4">
									<span class="help-block"></span>
								</div>
							</div>
						</div>

						<hr class="colorgraph">
						<div class="row">
							<div class="col-xs-12 col-md-6"><input type="submit" id="submit" value="Register" class="btn btn-primary btn-block btn-lg" tabindex="5"></div>
							<div class="col-xs-12 col-md-6"><a href="${pageContext.request.contextPath }/pages/signin/user.jsp" class="btn btn-success btn-block btn-lg">Login</a></div>
						</div>

					</form>
				</div>
			</div>
		</div>

		<footer class="footer" style="text-align: center">
			<div class="container">
				<p class="text-muted">
					<a href="http://kaidian.ele.me">我要开店</a><span> | </span>
					<a href="contact.html">联系我们</a><span> | </span>
					<a href="agreement.html">服务条款和协议</a><span> | </span>
					<a href="sitemaps.html">站点地图</a><span> | </span>
					<a href="http://jobs.ele.me">加入我们</a>
					<br> 增值电信业务许可证：
					<a class=-link-zero " target="_blank " rel="nofollow " href="http://www.shca.gov.cn ">沪B2-20150033</a><span> | </span>
        <a target="_blank " rel="nofollow " href="http://www.miibeian.gov.cn ">沪ICP备 09007032</a> <span> | </span>
        <a target="_blank " rel="nofollow " href="http://www.sgs.gov.cn/lz/licenseLink.do?method=licenceView&amp;entyId=20120305173227823 ">上海工商行政管理</a>
        <br> Copyright &copy;2008-2015 ele.me, All Rights Reserved.
      </p>
    </div>
  </footer>

  <script src="${pageContext.request.contextPath }/style/js/jquery.min.js "></script>
  <script src="${pageContext.request.contextPath }/style/bootstrap/js/bootstrap.min.js "></script>
  <script src="${pageContext.request.contextPath }/style/js/signup.js "></script>
  <script src="${pageContext.request.contextPath }/style/js/userSignup.js "></script>
</body>
</html>