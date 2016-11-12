$(document).ready(function () {
    var username = $("#username").html();
    username = username.substring(0, username.indexOf("<"));
    var pageContent = "orders";
    $(".pageNum").html("1");

    showOrderPageOf(1);
    setTimeout(checkOrderPage, 500);

    $(".orderManage").click(function () {
        if (pageContent != "orders") {
            pageContent = "orders";
            $(".userInfo ,.newReceiver, .receiverInfo").remove();
            $(".title").html("订单简介");
            $(".titleHead , .pager").show();
            $(".pageNum").html("1");

            showOrderPageOf(1);
            setTimeout(checkOrderPage, 500);
        }
    })

    $(".userInfoManage").click(function () {
        if (pageContent != "userInfo") {
            pageContent = "userInfo";
            $(".title").html("个人信息");
            $(".titleHead , .pager").hide();
            $(".summary , .newReceiver, .receiverInfo").remove();

            $(".main").append(createUserInfoForm());

            $.get("../../GetUserInfo.action", { username: username }, function (userInfo) {
                userInfo = $.parseJSON(userInfo);
                $(".username").val(userInfo.username);
                $(".phone").val(userInfo.phone);
            })
        }
    })

    $(".receiverManage").click(function () {
        if (pageContent != "receiverInfo") {
            pageContent = "receiverInfo";
            $(".title").html("收获地址管理<button class=\" navbar-right addNewAddr btn btn-success\">添加新地址</button>");
            $(".pageNum").html("1");
            $(".pager").show();
            checkReceiverInfoPage();
            $(".titleHead").hide();
            $(".summary , .userInfo ,.newReceiver").remove();

            showReceiverInfoPageOf(1);
            setTimeout(checkReceiverInfoPage, 500);
        }
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

    $(".main").delegate(".finishOrder", "click", function () {
        var summary = $(this).parents(".summary");
        var brief = summary.find(".brief");
        var detail = summary.find(".detail");

        detail.slideToggle();
        detail.remove();

        $.get("../../FinishOrder.action", { orderID: brief.find(".orderID").html() }, function (result) {
            if (result.match("true"))
                brief.find(".orderStatus").html("订单已完成");
            else
                alert("操作失败！请稍后再试...")
        })
        brief.slideToggle();
    })

    $(".previous").click(function () {
        if ($(this).hasClass("disabled"))
            return;

        var curPage = parseInt($(".pageNum").html());
        if (pageContent == "orders") {
            $(".summary").remove();
            showOrderPageOf(curPage - 1);
            setTimeout(checkOrderPage, 500);
        } else {
            $(".receiverInfo").remove();
            showReceiverInfoPageOf(curPage - 1);
            setTimeout(checkReceiverInfoPage, 500);
        }
        $(".pageNum").html(curPage - 1);
    })

    $(".next").click(function () {
        if ($(this).hasClass("disabled"))
            return;

        var curPage = parseInt($(".pageNum").html());
        if (pageContent == "orders") {
            $(".summary").remove();
            showOrderPageOf(curPage + 1);
            setTimeout(checkOrderPage, 500);
        } else {
            $(".receiverInfo").remove();
            showReceiverInfoPageOf(curPage + 1);
            setTimeout(checkReceiverInfoPage, 500);
        }
        $(".pageNum").html(curPage + 1);
    })

    $(".main").delegate(".setDefault", "click", function () {
        var receiverInfo = $(this).parents(".receiverInfo");
        var owner = receiverInfo.find(".owner").val();
        var receiverPhone = receiverInfo.find(".receiverPhone").val();

        $.get("../../ReceiverSetDefault.action", {
            owner: owner,
            receiverPhone: receiverPhone
        }, function (result) {
            if (result.match("true")) {
                $(".receiverInfo").remove();
                showReceiverInfoPageOf(parseInt($(".pageNum").html()));
                setTimeout(checkReceiverInfoPage, 500);
            }
            else
                alert("操作失败！请稍后再试...")
        })
    })

    $(".main").delegate(".update", "click", function () {
        var receiverInfo = $(this).parents(".receiverInfo");
        var owner = receiverInfo.find(".owner").val();
        var receiverAddr = receiverInfo.find(".receiverAddr").val();
        var receiverName = receiverInfo.find(".receiverName").val();
        var receiverPhone = receiverInfo.find(".receiverPhone").val();

        $.get("../../UpdateReceiverInfo.action", {
            owner: owner,
            receiverAddr: receiverAddr,
            receiverName: receiverName,
            receiverPhone: receiverPhone
        }, function (result) {
            if (result.match("true")) {
                $(".receiverInfo").remove();
                showReceiverInfoPageOf(parseInt($(".pageNum").html()));
                setTimeout(checkReceiverInfoPage, 500);
            }
            else
                alert("操作失败！请稍后再试...")
        })
    })

    $(".main").delegate(".delete", "click", function () {
        var receiverInfo = $(this).parents(".receiverInfo");
        var owner = receiverInfo.find(".owner").val();
        var receiverPhone = receiverInfo.find(".receiverPhone").val();

        $.get("../../RemoveReceiver.action", {
            owner: owner,
            receiverPhone: receiverPhone
        }, function (result) {
            if (result.match("true")) {
                $(".receiverInfo").remove();
                showReceiverInfoPageOf(parseInt($(".pageNum").html()));
                setTimeout(checkReceiverInfoPage, 500);
            }
            else
                alert("操作失败！请稍后再试...")
        })
    })


    $(".main").delegate(".addNewAddr", "click", function () {
        if ($(".main .newReceiver").length > 0)
            return;

        $(".receiverInfo").slideToggle();
        $(".receiverInfo").remove();
        $(".pageNum").html("1");
        $(".previous, .next").addClass("disabled");

        $(".main").append(createNewReceiver());
        $(".newReceiver").slideToggle();
        $(".newReceiver .username").val(username);
    })

    $(".main").delegate(".newReceiver .receiverPhone", 'keyup blur change', function () {
        var parent = $(this).parent();
        var phone = $(this).val().replace(/^\s+|\s+$/g, "");
        var help = parent.find(".help-block");
        if (phone.match(/^\d{11}$/g) == null) {
            addError(parent, "请输入11位手机号码！");
            return;
        }

        $.post("../../CheckReceiverPhone.action", { username: username, phone: phone }, function (have) {
            if (have.match("true")) {
                addError(parent, "该手机号已注册！");
                return;
            }
        })

        removeError(parent);
    })

    $(".main").delegate(".addNewReceiver", "click", function () {
        var tip = "请完善您的信息!";

        var emptyInput = $(".newReceiver :text").filter(function () {
            return !this.value;
        });

        if (emptyInput.length > 0 || $('.newReceiver .form-group').hasClass("has-error")) {
            addError(emptyInput.parent(), tip);
            return;
        }

        var receiver = $(".newReceiver");
        var receiverAddr = receiver.find(".receiverAddr").val();
        var receiverName = receiver.find(".receiverName").val();
        var receiverPhone = receiver.find(".receiverPhone").val();

        $.get("../../AddNewReceiver.action", {
            username: username,
            receiverAddr: receiverAddr,
            receiverName: receiverName,
            receiverPhone: receiverPhone
        }, function (result) {
            if (result.match("true")) {
                var help1 = $(".newReceiver .help-block:last");
                var help2 = $(".newReceiver .help-block").last();
                addError($(".newReceiver .help-block").last().parent(), "添加成功！2s后跳转到第1页...");
                setTimeout(function () {
                    $(".newReceiver").remove();
                    showReceiverInfoPageOf(1);
                    setTimeout(checkReceiverInfoPage, 500);
                }, 2000);
            }
            else
                alert("操作失败！请稍后再试...")
        })
    })
    function checkOrderPage() {
        var curPage = $(".pageNum").html();
        if (curPage == "1")
            $(".previous").addClass("disabled");
        else
            $(".previous").removeClass("disabled");

        var summary = $(".summary");
        var hasNext = (summary.length == 10);
        if (hasNext)
            $(".next").removeClass("disabled");
        else
            $(".next").addClass("disabled");
    }

    function checkReceiverInfoPage() {
        var curPage = $(".pageNum").html();
        if (curPage == "1")
            $(".previous").addClass("disabled");
        else
            $(".previous").removeClass("disabled");

        var receiverInfo = $(".receiverInfo");
        var hasNext = (receiverInfo.length == 2);
        if (hasNext)
            $(".next").removeClass("disabled");
        else
            $(".next").addClass("disabled");
    }

    function showOrderPageOf(pageNum) {
        $.get("../../GetUserOrderBrief.action", {
            username: username,
            page: pageNum
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
                        status = "商家未接单";
                    else if (statusCode == 1)
                        status = "商家未发货";
                    else if (statusCode == 2)
                        status = "订单已在路上...";
                    else if (statusCode == 3)
                        status = "订单已送达";
                    else if (statusCode == 4)
                        status = "订单已完成";
                    brief.find(".orderStatus").html(status);
                    brief.find(".orderID").html(item.orderID);

                    brief.slideToggle();
                })
            })
    }

    function showReceiverInfoPageOf(pageNum) {
        $.get("../../GetReceiverInfo.action", {
            username: username,
            page: pageNum
        },
            function (infos) {
                infos = $.parseJSON(infos);

                $.each(infos, function (i, item) {
                    var newinfo = createReceiverInfo();
                    $(".main").append(newinfo);

                    var info = $(".main .receiverInfo:last");

                    info.find(".owner").val(item.owner);
                    info.find(".receiverName").val(item.receiverName);
                    info.find(".receiverPhone").val(item.receiverPhone);
                    info.find(".receiverAddr").val(item.receiverAddr);
                    if (item.isDefault == 1)
                        info.find(".setDefault").attr("disabled", "disabled");
                })
            })
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

    function createUserInfoForm() {
        var newUserInfoForm = "<form role=\"form\" class=\"userInfo\" action=\"../../UpdateUserPassword.action\" method=\"post\">" +
            "<hr class=\"colorgraph\"><div class=\"form-group has-info\"><input type=\"text\" name=\"username\" class=\"username form-control input-lg\" placeholder=\"User Name\" tabindex=\"1\" readOnly>" +
            "</div><div class=\"form-group has-info\"><input type=\"text\" name=\"phone\" class=\"phone form-control input-lg\" placeholder=\"Phone Number\" tabindex=\"2\" readOnly>" +
            "</div><div class=\"row\"><div class=\"col-xs-12 col-sm-6 col-md-6\"><div class=\"form-group has-info\">" +
            "<input type=\"password\" name=\"password\" class=\"password form-control input-lg\" placeholder=\"Password\" tabindex=\"3\"><span class=\"help-block\"></span></div></div>" +
            "<div class=\"col-xs-12 col-sm-6 col-md-6\"><div class=\"form-group has-info\"><input type=\"password\" name=\"password_confirmation\" class=\"password_confirmation form-control input-lg\" placeholder=\"Confirm Password\"tabindex=\"4\">" +
            "<span class=\"help-block\"></span></div></div></div><hr><div class=\"row\">" +
            "<div class=\"col-xs-12 col-md-6\"><input type=\"submit\" id=\"submit\" value=\"Update\" class=\"btn btn-primary btn-block btn-lg\" tabindex=\"5\"></div>" +
            "<div class=\"col-xs-12 col-md-6\"><a href=\"javascript:;\" class=\"btn btn-success btn-block btn-lg clear\" tabindex=\"6\">Clear</a></div></div></form>";
        return newUserInfoForm;
    }

    function createReceiverInfo() {
        var newReceiver = "<div class=\"receiverInfo col-sm-8 col-md-5\"><hr><div class=\"form-group has-info\" style=\"display: none;\"><p>当前用户手机号</p>" +
            "<input type=\"text\" name=\"owner\" class=\"form-control owner\" placeholder=\"owner\" tabindex=\"1\" readonly><span class=\"help-block\">" +
            "</span></div><div class=\"form-group has-info\"><p>收货人名称：</p><input type=\"text\" name=\"receiverName\" class=\"form-control receiverName\"" +
            "placeholder=\"Receiver Name\" tabindex=\"2\"><span class=\"help-block\"></span></div><div class=\"form-group has-info\"><p>收货人联系方式：</p>" +
            "<input type=\"text\" name=\"receiverPhone\" class=\"form-control receiverPhone\" placeholder=\"Receiver Phone\" tabindex=\"3\" readonly>" +
            "<span class=\"help-block\"></span></div><div class=\"form-group has-info\"><p>收货人地址</p><input type=\"text\" name=\"receiverAddr\"" +
            "class=\"form-control receiverAddr\" placeholder=\"Receiver Address\" tabindex=\"4\"><span class=\"help-block\"></span></div><hr><div class=\"row\">" +
            "<div class=\"col-xs-12 col-md-4\"><input type=\"button\" value=\"设为默认\" class=\"setDefault btn btn-success btn-block\" tabindex=\"5\"></div>" +
            "<div class=\"col-xs-12 col-md-4\"><input type=\"button\" value=\"更新地址\" class=\"update btn btn-warning btn-block\" tabindex=\"6\"></div>" +
            "<div class=\"col-xs-12 col-md-4\"><input type=\"button\" value=\"删除地址\" class=\"delete btn btn-danger btn-block\" tabindex=\"7\"></div></div><hr></div>";
        return newReceiver;
    }

    function createNewReceiver() {
        var newReceiver = "<div class=\"newReceiver col-sm-8 col-md-5\" style=\"display: none;\"><hr><div class=\"form-group has-info\" style=\"display: none;\"><p>当前用户手机号</p>" +
            "<input type=\"text\" name=\"username\" class=\"form-control username\" placeholder=\"username\" readonly><span class=\"help-block\">" +
            "</span></div><div class=\"form-group has-info\"><p>收货人名称：</p><input type=\"text\" name=\"receiverName\" class=\"form-control receiverName\"" +
            "placeholder=\"Receiver Name\"><span class=\"help-block\"></span></div><div class=\"form-group has-info\"><p>收货人联系方式：</p>" +
            "<input type=\"text\" name=\"receiverPhone\" class=\"form-control receiverPhone\" placeholder=\"Receiver Phone\">" +
            "<span class=\"help-block\"></span></div><div class=\"form-group has-info\"><p>收货人地址</p><input type=\"text\" name=\"receiverAddr\"" +
            "class=\"form-control receiverAddr\" placeholder=\"Receiver Address\"><span class=\"help-block\"></span></div><hr><div class=\"row\">" +
            "<div class=\"col-xs-12 col-md-4\"><input type=\"button\" value=\"提交\" class=\"addNewReceiver btn btn-primary btn-block\" tabindex=\"5\"></div>" +
            "<div class=\"col-xs-12 col-md-4\"><input type=\"button\" value=\"清空\" class=\"clear btn btn-success btn-block\" tabindex=\"6\"></div></div><div class=\"row has-info\" style=\"padding: 15px\"><span class=\"help-block\"></span></div><hr></div>";
        return newReceiver;
    }

    function addDataToDetail(detail, order) {
        var statusCode = order.status;
        var status = "";
        if (statusCode == 0)
            status = "商家未接单";
        else if (statusCode == 1)
            status = "商家未发货";
        else if (statusCode == 2)
            status = "订单已在路上...";
        else if (statusCode == 3)
            status = "订单已送达";
        else if (statusCode == 4)
            status = "订单已完成";

        var finishTime = order.finishTime;
        if (statusCode != 4) {
            finishTime = "订单尚未完成";
            detail.find(".finishOrder").show();
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
            if(item.food.imageAddr != null && item.food.imageAddr != "")
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

    $(".main").delegate(".password", 'keyup blur change', function () {
        var parent = $(this).parent();
        var help = parent.find(".help-block");

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
        var help = parent.find(".help-block");

        var password1 = $(".password").val();
        var password2 = object.val();

        if (password1 != password2) {
            addError(parent, "两次密码不一致!");
            return;
        }

        removeError(parent);
    }

    $(".main").delegate(".clear", "click", function () {
        $('input[type="password"]').val("");
        removeError($('input[type="password"]').parent());

        var newReceiverInfo = $(".newReceiver :text:not('.username')");
        newReceiverInfo.val("");
        removeError(newReceiverInfo.parent());
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

    $(".main").delegate('input[type="password"]', 'focus', function () {
        removeError($(this).parent());
    });

    $(".main").delegate("form", 'submit', function (e) {
        var tip = "请完善您的信息!";

        if ($(".password").val() == ""
            || $('input[type="password"]').parent().hasClass("has-error")) {
            e.preventDefault();
            addError($(".password").parent(), tip);
            addError($(".password_confirmation").parent(), tip);
        }
    })
})