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


    <div class="container" style="padding-bottom: 88px">

      <div class="row">
        <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
          <form role="form" action="${pageContext.request.contextPath }/SellerSignup.action?category=" method="post">
            <h2>New Seller? Please Sign Up</h2>
            <hr class="colorgraph">

            <div class="form-group has-info">
              <input type="text" name="registration_id" id="registration_id" class="form-control input-lg" placeholder="registrationID"
                tabindex="1">
              <p class="help-block"></p>
            </div>


            <div class="form-group has-info">
              <input type="text" name="phone" id="phone" class="form-control input-lg" placeholder="Phone Number" tabindex="2">
              <p class="help-block"></p>
            </div>

            <div class="row">
              <div class="col-xs-12 col-sm-6 col-md-6">
                <div class="form-group has-info">
                  <input type="password" name="password" id="password" class="form-control input-lg" placeholder="Password" tabindex="3">
                  <p class="help-block"></p>
                </div>
              </div>
              <div class="col-xs-12 col-sm-6 col-md-6">
                <div class="form-group has-info">
                  <input type="password" name="password_confirmation" id="password_confirmation" class="form-control input-lg" placeholder="Confirm Password"
                    tabindex="4">
                  <p class="help-block"></p>
                </div>
              </div>
            </div>

            <div class="form-group has-info">
              <input type="text" name="store_name" id="store_name" class="form-control input-lg" placeholder="Store Name" tabindex="5">
              <p class="help-block"></p>
            </div>

            <div class="form-group has-info">
              <input type="text" name="store_address" id="store_address" class="form-control input-lg" placeholder="Store Address" tabindex="6">
              <p class="help-block"></p>
            </div>

            <div class="form-group has-success">
              <select name="agent_id" id="category" class="form-control">
                  <option selected="selected">商店超市</option>
                  <option>小吃夜宵</option>
                  <option>异国料理</option>
                  <option>快餐便当</option>
                  <option>果蔬生鲜</option>
                  <option>特色菜系</option>
                  <option>甜品饮品</option>
                  <option>鲜花蛋糕</option>
              </select>
            </div>

            <div class="form-group has-info">
              <textarea class="form-control" name="store_description" id="store_description" placeholder="Store Description..." cols="30"
                rows="10"></textarea>
            </div>

            <div class="form-group has-info">
              <input type="text" name="delivery_fee" id="delivery_fee" class="form-control input-lg" placeholder="Delivery Fee(default ￥0)" tabindex="">
              <p class="help-block"></p>
            </div>


            <hr class="colorgraph">
            <div class="row">
              <div class="col-xs-12 col-md-6"><input type="submit" value="Register" class="btn btn-primary btn-block btn-lg" tabindex="7"></div>
              <div class="col-xs-12 col-md-6"><a href="#" class="btn btn-success btn-block btn-lg">Login</a></div>
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
  <script src="${pageContext.request.contextPath }/style/js/sellerSignup.js "></script>
  <script src="${pageContext.request.contextPath }/style/js/signup.js "></script>
</body>
</html>