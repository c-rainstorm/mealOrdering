$(document).ready(function () {
    var pageContent = "all";
    var registrationID = $(".registrationID").html().substring(0, 2);
    var storeStatus;

    $.get("../../getStoreStatus.action", {
        registrationID: registrationID
    }, function (status) {
        storeStatus = parseInt(status);
    })

    showOrderManageTitle();

    $(".orderManage").click(showOrderManageTitle);

    $(".storeManage").click(showStoreManageTitle);

    $(".foodManage").click(showFoodManageTitle);

    function showOrderManageTitle() {
        pageContent = "all";
        $(".title").html("订单简介");
        var orderProcess = $(".orderProcess");
        orderProcess.children("button").hide();
        orderProcess.hide();
        $(".foodTableHead").hide();
        $(".orderTableHead").slideDown();
        $(".orderStatus").slideDown();

        $(".main").children(":not(.page-header)").remove();

        $(".pageNum").html("1");
        getOrderBrief(pageContent, 1);
    }

    function showStoreManageTitle() {
        $(".title").html("店铺管理<button class=\" navbar-right switchButton btn btn-success\"></button>")
        if (storeStatus == 0) {
            $(".switchButton").html("店铺开张");
        } else {
            $(".switchButton").html("店铺打烊");
        }
        $(".orderStatus").slideUp();
        $(".tableHead").slideUp();

        $(".main").children(":not(.page-header)").remove();

        $(".pageNum").html("1");
        showStoreManageForm();
        checkPage();
    }

    function showStoreManageForm() {
        $(".main").append(createSellerInfoForm());
        $.get("../../GetSellerDetail.action", {
            registrationID: registrationID
        }, function (seller) {
            seller = $.parseJSON(seller);
            var sellerInfo = $(".main").find(".sellerInfo");

            sellerInfo.find(".registration_id").val(seller.registrationID);
            sellerInfo.find(".phone").val(seller.phone);
            sellerInfo.find(".store_name").val(seller.storeName);
            sellerInfo.find(".store_address").val(seller.storeAddress);
            sellerInfo.find("option").each(function () {
                if ($(this).html() == seller.category) {
                    $(this).attr("selected", "selected");
                }
            })
            sellerInfo.find(".store_description").val(seller.description);
            sellerInfo.find("announcement").val(seller.announcement);
            sellerInfo.find("delivery_fee").val(seller.deliveryFee);
        })
    }

    function createSellerInfoForm() {
        var newSellerInfo = '<form role="form" class="sellerInfo" action="../../updateSellerInfo.action?category=" method="post"><div class="form-group has-info"><input type="text" name="registration_id" class="registration_id form-control input-lg" placeholder="registrationID" tabindex="1" readonly><p class="help-block"></p></div><div class="form-group has-info"><input type="text" class="form-control input-lg phone" name="phone" placeholder="Phone Number" tabindex="2" readonly><p class="help-block"></p></div><div class="row"><div class="col-xs-12 col-sm-6 col-md-6"><div class="form-group has-info"><input type="password" name="password" class="form-control input-lg password" placeholder="Password" tabindex="3"><p class="help-block"></p></div></div><div class="col-xs-12 col-sm-6 col-md-6"><div class="form-group has-info"><input type="password" class="form-control input-lg password_confirmation" placeholder="Confirm Password"tabindex="4"><p class="help-block"></p></div></div></div><div class="form-group has-info"><input type="text" name="store_name" class="form-control input-lg store_name" placeholder="Store Name" tabindex="5"><p class="help-block"></p></div><div class="form-group has-info"><input type="text" class="form-control input-lg store_address" name="store_address" placeholder="Store Address" tabindex="6"><p class="help-block"></p></div><div class="form-group has-success"><select class="form-control"><option selected="selected">商店超市</option><option>小吃夜宵</option><option>异国料理</option><option>快餐便当</option><option>果蔬生鲜</option><option>特色菜系</option><option>甜品饮品</option><option>鲜花蛋糕</option></select></div><div class="form-group has-info"><textarea class="form-control store_description" name="store_description" placeholder="Store Description..." cols="30" rows="10"></textarea></div><div class="form-group has-info"><input type="text" name="announcement" class="announcement form-control input-lg" placeholder="announcement" tabindex=""><p class="help-block"></p></div><div class="form-group has-info"><input type="text" name="delivery_fee" class="delivery_fee form-control input-lg" placeholder="Delivery Fee(default ￥0)" tabindex=""><p class="help-block"></p></div><hr><div class="row"><div><input type="submit" value="update" class="btn btn-primary btn-block btn-lg" tabindex="7"></div></div></form>';

        return newSellerInfo;
    }

    $(".main").delegate(".password", 'keyup blur change', function () {
        var parent = $(this).parent();

        var password = $(this).val();

        password_confirmation($(".password_confirmation"));

        if (password.match(/^\w+$/g) == null || password.length < 6) {
            addError(parent, "密码只允许由英文字母，数字，下滑线组成，最小长度为6个字符");
            return;
        }

        removeError(parent);
    })

    $(".main").delegate(".password_confirmation", 'keyup blur change', function () {
        password_confirmation($(this));
    })

    function password_confirmation(object) {
        var parent = object.parent();

        var password1 = $(".password").val();
        var password2 = object.val();

        if (password1 != password2) {
            addError(parent, "两次密码不一致!");
            return;
        }

        removeError(parent);
    }

    $(".main").delegate(".store_name", 'keyup blur change', function () {
        var parent = $(this).parent();
        var store_name = $(this).val().replace(/^\s+|\s+$/g, "");
        if (store_name == "") {
            addError(parent, "请输入店铺名！");
            return;
        }

        removeError(parent);
    })

    $(".main").delegate(".store_address", 'keyup blur change', function () {
        var parent = $(this).parent();
        var store_address = $(this).val().replace(/^\s+|\s+$/g, "");
        var help = parent.find(".help-block");
        if (store_address == "") {
            addError(parent, "请输入店铺地址！");
            return;
        }

        removeError(parent);
    })

    $(".main").delegate(".delivery_fee", 'keyup blur change', function () {
        var parent = $(this).parent();
        var fee = $(this).val().replace(/^\s+|\s+$/g, "");
        var help = parent.find(".help-block");
        if (fee.match(/^\d*$/g) == null) {
            addError(parent, "请输入合法数字!");
            return;
        }

        removeError(parent);
    })

    function addError(object, tip) {
        if (object.hasClass("has-info")) {
            object.removeClass("has-info");
            object.addClass("has-error");
            object.find(".help-block").html(tip);
        }
        if (object.hasClass("has-success")) {
            object.removeClass("has-success");
            object.addClass("has-error");
            object.find(".help-block").html(tip);
        }
    }
    function removeError(object) {
        if (object.hasClass("has-error")) {
            object.removeClass("has-error");
            object.addClass("has-success");
            object.find(".help-block").html("");
        }
        if (object.hasClass("has-info")) {
            object.removeClass("has-info");
            object.addClass("has-success");
            object.find(".help-block").html("");
        }
    }

    $(".main").delegate('.form-group input[type="text"], .form-group input[type="password"], .form-group textarea', 'focus', function () {
        removeError($(this).parent());
    });

    $(".main").delegate(".sellerInfo", 'submit', function (even) {
        var flag = true;
        var tip = "请完善您的信息!";
        var len = $(".form-group").length;

        for (var i = 0; i < len; ++i) {
            var fg = $(".form-group:eq(" + i + ")");
            if (flag && fg.hasClass("has-error")) {
                flag = false;
            }

            if (i < len - 3 && fg.find("input").val() == "") {
                addError(fg, tip);
                flag = false;
            }
        }
        if (flag) {
            $(this).attr('action', $(this).attr('action') + $('select').val());
        } else {
            even.preventDefault();
        }

        return flag;
    })

    $(".title").delegate(".switchButton", "click", function () {
        if (storeStatus == 0) {
            turnon();
            storeStatus = 1;
        } else {
            turnoff();
            storeStatus = 0;
        }
    });

    function turnon() {
        $(".switchButton").html("店铺打烊");
        $.get("../../changeStoreStatus.action", {
            registrationID: registrationID
        })
    }

    function turnoff() {
        $(".switchButton").html("店铺开张");
        $.get("../../changeStoreStatus.action", {
            registrationID: registrationID
        })
    }

    function showFoodManageTitle() {
        pageContent = "foods";
        $(".title").html("食品管理<button class=\" navbar-right newFood btn btn-success\">添加新食品</button>")
        $(".orderStatus").slideUp();
        $(".tableHead").slideUp();
        $(".foodTableHead").slideDown();

        $(".main").children(":not(.page-header)").remove();

        $(".pageNum").html("1");
        getFoods(1);
    }

    function getFoods(page) {
        $(".main").children(":not(.page-header)").remove();

        $.get("../../getFoods.action", {
            registrationID: registrationID,
            page: page
        }, function (foods) {
            foods = $.parseJSON(foods);

            $.each(foods, function (i, food) {
                $(".main").append(createFood());

                var curFood = $(".main .food:last");

                var photo = food.imageAddr;
                if (photo != null && photo != "")
                    curFood.find("img").attr("src", photo);
                curFood.find(".foodID").attr("value", food.foodID);
                curFood.find(".foodName").html(food.name);
                curFood.find(".changeFoodName").attr("value", food.name);
                curFood.find(".foodPrice").html(food.price);
                curFood.find(".changeFoodPrice").attr("value", food.price);

                if (food.status == 1) {
                    curFood.find(".star").addClass("glyphicon-star");
                } else {
                    curFood.find(".star").addClass("glyphicon-star-empty");
                }
                curFood.slideDown();
            })
        })
        setTimeout(checkPage, 500);
    }

    function createFood() {
        var food = '<form enctype="multipart/form-data" action="../../modifyFoodInfo.action" method="post" class="row food" style="display:none;"><div class="col-md-3"><img src="../../style/images/foods/default.jpg" alt="food here" class="show img-responsive img-thumbnail" style="height:70px;width:70px;margin-bottom:3px;"><input type="file" class="form-control change changeFoodPhoto" name="image" placeholder="请选择图片..." style="display:none;"></div><div class="col-md-3 has-info"><input type="text" name="foodID" class="foodID" style="display:none;"><h3 class="foodName" style="margin-bottom:25px;">炒面</h3><input type="text" class="form-control change changeFoodName" name="newFoodName" style="display:none;"><span class="help-block"></span></div><div class="col-md-3 has-info"><h3 style="margin-bottom:25px;">￥<span class="foodPrice">7</span></h3><input type="text" class="form-control change changeFoodPrice" name="newFoodPrice" style="display:none;"><span class="help-block"></span></div><div class="col-md-3" style="padding-top:11px;"><span class="modify glyphicon glyphicon-refresh btn btn-success"></span><span class="star glyphicon btn btn-default"></span><span class="remove glyphicon glyphicon-remove btn btn-danger"></span></div></form><hr>';

        return food;
    }

    $(".main").delegate(".newFood", "click", function () {
        $(".main").children(":not(.page-header)").remove();
        $(".foodTableHead").slideUp();
        $(".main").append(showNewFoodForm());

        $(".addNewFood").slideDown();
    })

    function showNewFoodForm() {
        var newFood = '<form class="addNewFood col-sm-8 col-md-5" action="../../addNewFood.action" enctype="multipart/form-data" method="post" style="display: none;"><hr><div class="form-group has-info" style="display: none;"><p>商家注册号</p><input type="text" name="registrationID" class="form-control registrationID" value="' + registrationID + '" placeholder="registrationID" readonly><span class="help-block"></span></div><div class="form-group has-info"><p>预览图片：</p><input type="file" name="foodPhoto" class="form-control foodPhoto" placeholder="请选择图片..."><span class="help-block"></span></div><div class="form-group has-info"><p>食品名称：</p><input type="text" name="foodName" class="form-control foodName" placeholder="food name"><span class="help-block"></span></div><div class="form-group has-info"><p>食品价格</p><input type="text" name="foodPrice" class="form-control foodPrice" placeholder="food Price"><span class="help-block"></span></div><hr><div class="row"><div class="col-xs-12 col-md-4"><input type="button" value="提交" class="submit btn btn-primary btn-block" tabindex="5"></div><div class="col-xs-12 col-md-4"><input type="button" value="清空" class="clear btn btn-success btn-block" tabindex="6"></div></div><div class="row has-info" style="padding: 15px"><span class="help-block"></span></div><hr></form>';
        return newFood;
    }

    $(".main").delegate(".submit", "click", function () {
        var flag = true;
        var tip = "请完善食品信息";
        var newFood = $(this).parents(".addNewFood");
        var foodName = newFood.find(".foodName");
        var foodPrice = newFood.find(".foodPrice");
        if (foodName.parent().hasClass("has-error") || foodPrice.parent().hasClass("has-error")) {
            return;
        }
        if (foodName.val() == null || foodName.val() == "") {
            addError(foodName.parent(), tip);
            flag = false;
        }

        if (foodPrice.val == null || foodPrice.val() == "") {
            addError(foodPrice.parent(), tip);
            flag = false;
        }

        if (flag) {
            newFood.submit();
        }
    })

    $(".main").delegate(".clear", "click", function () {
        var newFood = $(this).parents(".addNewFood");
        var foodName = newFood.find(".foodName");
        var foodPrice = newFood.find(".foodPrice");
        foodName.val("");
        foodPrice.val("");
        removeError(foodName.parent());
        removeError(foodPrice.parent());
    })

    $(".main").delegate(".foodPrice", 'keyup blur change', function () {
        var parent = $(this).parent();
        var fee = $(this).val().replace(/^\s+|\s+$/g, "");
        var help = parent.find(".help-block");
        if (fee.match(/^\d*$/g) == null) {
            addError(parent, "请输入合法数字!");
            return;
        }

        removeError(parent);
    })

    $(".main").delegate(".modify", "click", function () {
        if ($(this).hasClass("glyphicon-refresh")) {
            $(this).removeClass("glyphicon-refresh");
            $(this).addClass("glyphicon-ok");
            $(this).parents(".food").find(".change").slideDown();
        } else {

            updateFoodInfo($(this).parents(".food"));
        }
    })

    $(".main").delegate(':text', 'focus', function () {
        removeError($(this).parent());
    });

    function 　updateFoodInfo(food) {
        var flag = true;
        var tip = "请完善食品信息";
        $.each(food.find(":text"), function (i, input) {
            if ($(this).val() == null || $(this).val() == "" || $(this).parent().hasClass("has-error")) {
                addError($(this).parent(), tip);
                flag = false;
            }
        })
        if (flag) {
            food.submit();
            $(this).removeClass("glyphicon-ok");
            $(this).addClass("glyphicon-refresh");
        }
    }

    $(".main").delegate(".star", "click", function () {
        if ($(this).hasClass("glyphicon-star-empty")) {
            $(this).removeClass("glyphicon-star-empty");
            $(this).addClass("glyphicon-star");
        } else {
            $(this).removeClass("glyphicon-star");
            $(this).addClass("glyphicon-star-empty");
        }
        $.get("../../changeFoodStatus.action", {
            foodID: $(this).parents(".food").find(".foodID").val()
        })
    })

    $(".main").delegate(".remove", "click", function () {
        $(this).parents(".food").slideUp();
        $.get("../../deleteFood.action", {
            foodID: $(this).parents(".food").find(".foodID").val()
        })
    })

    $(".all").click(function () {
        pageContent = "all";
        $(".pageNum").html("1");
        $(".orderProcess").slideUp();
        $(".orderProcess").children("button").slideUp();
        getOrderBrief(pageContent, 1);
    })

    $(".notAccept").click(function () {
        getNotAccept();
    })

    function getNotAccept() {
        pageContent = "notAccept";
        $(".pageNum").html("1");
        $(".orderProcess").slideDown();
        $(".orderProcess").children("button").slideUp();
        $(".acceptAll").slideDown();
        getOrderBrief(pageContent, 1);
    }

    $(".notSend").click(function () {
        getNotSend();
    })

    function getNotSend() {
        pageContent = "notSend";
        $(".pageNum").html("1");
        $(".orderProcess").slideDown();
        $(".orderProcess").children("button").slideUp();
        $(".sendAll").slideDown();
        getOrderBrief(pageContent, 1);
    }

    $(".notArrive").click(function () {
        getNotArrive();
    })

    function getNotArrive() {
        pageContent = "notArrive";
        $(".pageNum").html("1");
        $(".orderProcess").slideDown();
        $(".orderProcess").children("button").slideUp();
        $(".arriveAll").slideDown();
        getOrderBrief(pageContent, 1);
    }

    $(".unfinished").click(function () {
        getUnfinished();
    })

    function getUnfinished() {
        pageContent = "unfinished";
        $(".pageNum").html("1");
        $(".orderProcess").slideUp();
        $(".orderProcess").children("button").slideUp();
        getOrderBrief(pageContent, 1);
    }

    $(".finished").click(function () {
        getFinished();
    })

    function getFinished() {
        pageContent = "finished";
        $(".pageNum").html("1");
        $(".orderProcess").slideUp();
        $(".orderProcess").children("button").slideUp();
        getOrderBrief(pageContent, 1);
    }

    $(".acceptAll").click(function () {
        $.get("../../updateOrderStatus.action", {
            curStatus: "0",
            orderID: registrationID + "%"
        }, function (result) {
            if (result.match("true")) {
                getNotSend();
            } else {
                alert("操作失败！请稍后再试...")
            }
        })
    })

    $(".sendAll").click(function () {
        $.get("../../updateOrderStatus.action", {
            curStatus: "1",
            orderID: registrationID + "%"
        }, function (result) {
            if (result.match("true")) {
                getNotArrive();
            } else {
                alert("操作失败！请稍后再试...")
            }
        })
    })


    $(".arriveAll").click(function () {
        $.get("../../updateOrderStatus.action", {
            curStatus: "2",
            orderID: registrationID + "%"
        }, function (result) {
            if (result.match("true")) {
                getUnfinished();
            } else {
                alert("操作失败！请稍后再试...")
            }
        })
    })

    $(".main").delegate(".finishOrder", "click", function () {
        $.get("../../updateOrderStatus.action", {
            curStatus: "-1",
            orderID: $(this).parents(".summary").find(".orderID").html()
        }, function (result) {
            if (result.match("true")) {
                getOrderBrief(pageContent, $(".pageNum").html());
            } else {
                alert("操作失败！请稍后再试...")
            }
        })
    })

    $(".main").delegate(".showDetail", "click", function () {
        var len = $(".summary").length;

        for (var i = 0; i < len; ++i) {
            var order = $(".summary:eq(" + i + ")");
            var brief = order.find(".brief");
            var detail = order.find(".detail");
            //已经获取订单并显示出来
            if (detail.length > 0 && !detail.get(0).style.display.match("none")) {
                detail.slideToggle();
                brief.slideToggle();
                break;
            }
        }

        var summary = $(this).parent().parent().parent();

        var brief = summary.find(".brief");
        var detail = summary.find(".detail");
        if (detail.length == 0) {
            summary.append(createOrderDetail());
            var orderID = summary.find(".brief .orderID").html();
            $.get("../../GetOrderDetail.action", { orderID: orderID.toString() }, function (order) {
                addDataToDetail(summary.find(".detail"), $.parseJSON(order));
            })
        }
        summary.find(".detail").slideToggle();
        brief.slideToggle();
    })

    function addDataToDetail(detail, order) {
        var statusCode = order.status;
        var status = "";
        var operate = "";
        if (statusCode == 0) {
            status = "商家未接单";
            operate = "接单"
        } else if (statusCode == 1) {
            status = "商家未发货";
            operate = "发货";
        } else if (statusCode == 2) {
            status = "订单已在路上...";
            operate = "已送达"
        } else if (statusCode == 3) {
            status = "订单已送达";
        } else if (statusCode == 4) {
            status = "订单已完成";
        }
        var finishTime = order.finishTime;
        if (statusCode != 4) {
            finishTime = "订单尚未完成";
            if (statusCode != 3) {
                detail.find(".finishOrder").html(operate);
                detail.find(".finishOrder").show();
            }
        }
        var payway = "在线支付";
        if (order.payway == 0)
            payway = "货到付款";

        detail.find(".status").html(status);
        detail.find(".orderID").html(order.orderID);
        detail.find(".orderTime").html(order.orderTime);
        detail.find(".payway").html(payway);
        detail.find(".receiverAddr").html(order.receiverAddr);
        detail.find(".receiverName").html(order.receiverName + " " + order.receiverPhone);
        detail.find(".finishTime").html(finishTime);
        detail.find(".commit").html(order.commit);

        $.each(order.foods, function (i, item) {
            var image = "../../style/images/foods/default.jpg";
            if (item.food.imageAddr != null && item.food.imageAddr != "")
                image = item.food.imageAddr;


            var food = "<div class=\"row\"><div class=\"col-xs-3 col-sm-3 col-md-3\">" +
                "<img style='height:100px; width:100px;' src=\"" + image + "\" alt=\"该图片离家出走了..\" class=\"img-thumbnail img-responsive\"></div><div class=\"col-xs-6 col-sm-6 col-md-6\">" +
                "<p><strong><span class=\"foodName\">" + item.food.name + "</span></strong></p><p>售价： ￥<span class=\"price\">" + item.food.price +
                "</span> x<span class=\"soldNum\">" + item.soldNum + "</span></p>" +
                "</div><div class=\"col-xs-3 col-sm-3 col-md-3\"><p>￥<span class=\"subTotal\">" + (item.soldNum * item.food.price) + "</span></p></div></div><hr>"

            detail.find(".foods").append(food);
        })

        detail.find(".orderTotal").html(order.total - order.deliveryFee);
        detail.find(".deliveryFee").html(order.deliveryFee);
        detail.find(".realPay").html(order.total);
    }

    function getOrderBrief(pageContent, page) {
        $(".summary").remove();

        $.get("../../GetSellerOrderBrief.action", {
            registrationID: registrationID,
            pageContent: pageContent,
            page: page
        },
            function (briefs) {
                briefs = $.parseJSON(briefs);

                $.each(briefs, function (i, item) {
                    var newBrief = createOrderBrief();
                    $(".main").append(newBrief);

                    var brief = $(".main .summary:last");
                    var statusCode = item.status;
                    var status = "";
                    if (statusCode == 0)
                        status = "未接单";
                    else if (statusCode == 1)
                        status = "未发货";
                    else if (statusCode == 2)
                        status = "未送达";
                    else if (statusCode == 3)
                        status = "未完成";
                    else if (statusCode == 4)
                        status = "已完成";
                    brief.find(".orderStatus").html(status);
                    brief.find(".orderID").html(item.orderID);

                    brief.slideDown();
                })
            })

        setTimeout(checkPage, 500);
    }

    function createOrderBrief() {
        var newBrief = "<div class=\"summary\" style=\"display: none;\"><div class=\"row brief\"><div class=\"col-xs-4 col-sm-4 col-md-4\"><h4 class=\"orderID\"></h4>" +
            "</div><div class=\"col-xs-4 col-sm-4 col-md-4\"><h4 class=\"orderStatus\"></h4></div><div class=\"col-xs-4 col-sm-4 col-md-4\">" +
            "<a href=\"javascript:;\" class=\"showDetail form-control btn btn-success\" style=\"margin: 3px 12px;\">查看详情</a></div></div></div>";

        return newBrief;
    }

    function createOrderDetail() {
        var newDetail = "<div class=\"row detail\" style=\"display: none;\"><h3>订单详情</h3><hr><p><strong>订单状态：" +
            "<span class=\"status\"></span></strong></p><br><p>订单号码：<span class=\"orderID\"></span></p>" +
            "<p>下单日期：<span class=\"orderTime\"></span></p><p>付款方式：<span class=\"payway\"></span></p><br>" +
            "<p>收货地址：<span class=\"receiverAddr\"></p><p>收货人名：<span class=\"receiverName\"></span></p>" +
            "<p>收货日期：<span class=\"finishTime\"></span></p><br><p>买家备注：<span class=\"commit\"></span></p><hr><div class=\"row\"><div class=\"col-xs-3 col-sm-3 col-md-3\">" +
            "<p>食品</p></div><div class=\"col-xs-6 col-sm-6 col-md-6\"><p>购买信息</p></div><div class=\"col-xs-3 col-sm-3 col-md-3\">" +
            "<p>小计</p></div></div><hr><div class=\"foods\"></div>" +
            "<div class=\"row\"><div class=\"col-xs-3 col-sm-3 col-md-3\"></div><div class=\"col-xs-6 col-sm-6 col-md-6\"></div>" +
            "<div class=\"col-xs-3 col-sm-3 col-md-3\"><p>订单总额： ￥<span class=\"orderTotal\"></span></p><p>配送费用： ￥<span class=\"deliveryFee\"></span></p>" +
            "<p><strong>总 金 额：￥<span class=\"realPay\"></span></strong></p></div></div><div class=\"row\">" +
            "<div class=\"col-xs-10 col-sm-10 col-md-10 col-xs-offset-1 col-sm-offset-1 col-md-offset-1\"><a href=\"javascript:;\"" +
            "class=\"finishOrder form-control btn btn-success\" style=\"display: none;\">完成订单</a></div></div></div>";
        return newDetail;
    }

    $(".previous").click(function () {
        if ($(this).hasClass("disabled"))
            return;

        var curPage = parseInt($(".pageNum").html());
        if (pageContent != "foods") {
            $(".brief").remove();
            getOrderBrief(pageContent, curPage - 1)
        } else {
            $(".food").remove();
            getFoods(curPage - 1);
        }
        $(".pageNum").html(curPage - 1);
    })

    $(".next").click(function () {
        if ($(this).hasClass("disabled"))
            return;

        var curPage = parseInt($(".pageNum").html());
        if (pageContent != "foods") {
            $(".brief").remove();
            getOrderBrief(pageContent, curPage + 1)
            setTimeout(checkPage, 500);
        } else {
            $(".food").remove();
            getFoods(curPage + 1);
            setTimeout(checkPage, 500);
        }
        $(".pageNum").html(curPage + 1);
    })

    function checkPage() {
        var curPage = $(".pageNum").html();
        if (curPage == "1")
            $(".previous").addClass("disabled");
        else
            $(".previous").removeClass("disabled");

        var hasNext = true;
        if (pageContent == "foods") {
            hasNext = ($(".food").length == 10);
        } else {
            hasNext = ($(".brief").length == 10);
        }
        if (hasNext)
            $(".next").removeClass("disabled");
        else
            $(".next").addClass("disabled");
    }
})