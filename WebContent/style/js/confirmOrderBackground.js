$(document).ready(function () {
    var registrationID = url("?registrationID");
    if (registrationID == null || registrationID == ""
        || !registrationID.match(/^\d{2}$/g))
        return;

    var loginStatus = true;
    var username = $("#username").html();
    if (username == null || username == "") {
        loginStatus = false;
    }

    if (loginStatus == true) {
        username = username.substring(0, username.indexOf("<"))
        getReceivers(username);


        function getReceivers(username) {
            $.get("../../GetReceiverInfo.action", {
                username: username
            }, function (receivers) {
                receivers = $.parseJSON(receivers);
                for (var i = 0; i < receivers.length; ++i) {
                    $("select").append(createReceiverOption(receivers[i]));
                }
            })
        }

        function createReceiverOption(receiver) {
            return '<option>' + receiver.receiverName + "&ensp;&ensp;" +
                receiver.receiverPhone + "&ensp;&ensp;" + receiver.receiverAddr + '</option>';
        }

        $(".main").delegate(".addNewAddr", "click", function () {
            if ($(".newReceiver").length > 0)
                return;
            var parent = $(this).parent().parent();

            parent.append(createNewReceiver());

            var newReceiver = parent.find(".newReceiver");
            newReceiver.find(".username").val(username);
            newReceiver.slideDown();
        })

        $(".main").delegate(".update", "click", function () {
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
                    addError($(".newReceiver .help-block").last().parent(), "添加成功！");
                    setTimeout(function () {
                        $(".newReceiver").slideUp();
                        setTimeout(function () {
                            $(".newReceiver").remove();
                        }, 500);
                    }, 2000);

                    $("option:not('.default')").remove();
                    getReceivers(username);
                }
                else
                    alert("操作失败！请稍后再试...")
            })
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

        $(".main").delegate(".clear", "click", function () {
            var newReceiverInfo = $(".newReceiver :text:not('.username')")

            newReceiverInfo.val("");
            removeError(newReceiverInfo.parent());
        })

        $(".main").delegate(".cancel", "click", function () {
            var parent = $(this).parents(".newReceiver");

            parent.slideUp();

            setTimeout(function () {
                parent.remove();
            }, 500);
        })

        $(".confirm").click(function () {
            if ($("tbody .food").length == 0) {
                return;
            }
            if ($("select").val() == "请选择收货人") {
                alert("请选择收货人");
                return;
            }
            $.get("../../SubmitOrder.action", {
                receiver: $("select").val(),
                payway: $(":radio").val(),
                username: username,
                registrationID: registrationID,
                commit: $(".commit").val()
            }, function (result) {
                if (result.match("false")) {
                    alert("下单失败!请稍后再试...");
                } else {
                    $.ajax({
                        url: "../../GetShoppingCars.action",
                        async: false,
                        success: function (shoppingCars) {
                            var rest = $.grep($.parseJSON(shoppingCars), function (foods, index) {
                                return foods[0].foodID.indexOf(registrationID) != 0;
                            })
                            $.ajax({
                                url: "../../SetShoppingCars.action",
                                async: false,
                                data: { shoppingCars: JSON.stringify(rest)},
                            });
                        }
                    });
                    
                    window.location.replace("http://localhost:8080/mealOrdering/pages/profiles/user.jsp");

                }
            })

        })

        function createNewReceiver() {
            var newReceiver = "<div class=\"newReceiver col-sm-8 col-md-5 col-md-offset-1\" style=\"display: none;\"><hr><div class=\"form-group has-info\" style=\"display: none;\"><p>当前用户用户名</p>" +
                "<input type=\"text\" name=\"username\" class=\"form-control username\" placeholder=\"username\" readonly><span class=\"help-block\">" +
                "</span></div><div class=\"form-group has-info\"><p>收货人名称：</p><input type=\"text\" name=\"receiverName\" class=\"form-control receiverName\"" +
                "placeholder=\"Receiver Name\"><span class=\"help-block\"></span></div><div class=\"form-group has-info\"><p>收货人联系方式：</p>" +
                "<input type=\"text\" name=\"receiverPhone\" class=\"form-control receiverPhone\" placeholder=\"Receiver Phone\">" +
                "<span class=\"help-block\"></span></div><div class=\"form-group has-info\"><p>收货人地址</p><input type=\"text\" name=\"receiverAddr\"" +
                "class=\"form-control receiverAddr\" placeholder=\"Receiver Address\"><span class=\"help-block\"></span></div><hr><div class=\"row\">" +
                "<div class=\"col-xs-12 col-md-4\"><input type=\"button\" value=\"提交\" class=\"update btn btn-success btn-block\" tabindex=\"5\"></div>" +
                "<div class=\"col-xs-12 col-md-4\"><input type=\"button\" value=\"清空\" class=\"clear btn btn-warning btn-block\" tabindex=\"6\"></div>" +
                "<div class=\"col-xs-12 col-md-4\"><input type=\"button\" value=\"取消\" class=\"cancel btn btn-primary btn-block\" tabindex=\"5\"></div>" +
                "</div><div class=\"row has-info\" style=\"padding: 15px\"><span class=\"help-block\"></span></div><hr></div>";
            return newReceiver;
        }

        $.get("../../GetShoppingCars.action", function (shoppingCars) {
            var tshoppingCars = $.grep($.parseJSON(shoppingCars), function (foods, index) {
                return foods[0].foodID.indexOf(registrationID) == 0;
            })

            $.each(tshoppingCars[0], function (i, food) {
                var tempfood = $('<div/>').html(createFoodBrief()).contents();
                $.get("../../GetFoodBrief.action", {
                    foodID: food.foodID
                }, function (brief) {
                    brief = $.parseJSON(brief);

                    tempfood.find("img").attr("src", brief.imageAddr);
                    tempfood.find(".foodID").html(brief.foodID);
                    tempfood.find(".foodName").html(brief.name);
                    tempfood.find(".foodPrice").html(brief.price);

                    var count = parseInt(food.foodCount);
                    while (count-- != 0) {
                        addFood(tempfood);
                    }
                })
            })
            setTimeout(updateShoppingCars, 500);
        })

        function addFood(food) {

            for (var i = 0; i < $(".food").length; ++i) {
                var temp = $(".food:eq(" + i + ")");

                if (temp.find(".foodID").html() == food.find(".foodID").html()) {
                    var num = parseInt(temp.find(".num").html());
                    temp.find(".num").html(num + 1);
                    return;
                }
            }

            var foodAdded = $('<div/>').html(createFood()).contents();

            var imageAddr = food.find("img").attr("src");
            if (imageAddr != null && imageAddr != "")
                foodAdded.find("img").attr("src", imageAddr);
            foodAdded.find(".foodName").html("&ensp;&ensp;" + food.find(".foodName").html());
            foodAdded.find(".foodPrice").html('￥' + food.find(".foodPrice").html());
            foodAdded.find(".foodID").html(food.find(".foodID").html());

            $("tbody").prepend(foodAdded);

        }

        function createFoodBrief() {
            var newfood = '<div class="food col-xs-6 col-sm-4 col-md-3" style="display:none;"><div class="thumbnail"><img src="" alt="通用的占位符缩略图"><div class="caption">' +
                '<p class="foodID" style="display:none;"></p><h3 class="foodName"></h3><p>销量：<span class="soldNum"></span></p><p>￥<span class="foodPrice"></span></p>' +
                '<button class="btn btn-success cd-add-to-cart form-control" role="button">加入购物车</button></div></div></div>';
            return newfood;
        }

        function createFood() {
            var newFood = '<tr class="food"></span><td class="col-sm-7 col-md-6"><div class="media"><a class="thumbnail pull-left" href="javascript:;"><img class="media-object" src="../../style/images/foods/default.jpg" style="width: 72px; height: 72px;"></a><div class="media-body"><h4 class="media-heading">&ensp;&ensp;<span class="foodName">黄焖鸡米饭</span><span class="foodID" style="display:none;"></h4><p class="media-body"></p></div></div></td><td class="col-sm-2 col-md-2 text-center"><span class="btn glyphicon glyphicon-step-backward"></span><span class="num">1</span><span class="btn glyphicon glyphicon-step-forward"></span></td><td class="col-sm-1 col-md-1 text-center"><strong><span class="foodPrice"></span></strong></td><td class="col-sm-1 col-md-1 text-center"><strong><span class="subtotal"></span></strong></td><td class="col-sm-1 col-md-1"><button type="button" class="remove btn btn-danger"><span class="glyphicon glyphicon-remove"></span> Remove</button></td></tr>';
            return newFood;
        }

        function updateTotals(price, bool) {
            var total = $(".total");
            bool ? total.text((Number(total.text()) + Number(price)).toFixed(2)) : total.text((Number(total.text()) - Number(price)).toFixed(2));
            var foodTotal = $(".foodTotal");
            bool ? foodTotal.text((Number(foodTotal.text()) + Number(price)).toFixed(2)) : foodTotal.text((Number(foodTotal.text()) - Number(price)).toFixed(2));
        }

        function updateShoppingCars() {
            var price = 0;
            var shoppingCar = [];
            $("tbody").find('.food').each(function () {
                var singleQuantity = Number($(this).find('.num').html());
                var subtotal = singleQuantity * Number($(this).find('.foodPrice').text().replace("￥", ""));
                price = price + subtotal;

                $(this).find(".subtotal").html(subtotal.toFixed(2));

                var food = new Object();
                food.foodID = $(this).find(".foodID").html();
                food.foodCount = $(this).find(".num").html();
                shoppingCar.push(food);
            });

            $(".foodsTotal").html(price);
            $(".total").html(price + parseFloat($(".deliveryFee").html()));

            $.get("../../GetShoppingCars.action", function (shoppingCars) {
                //保存其余购物车信息
                var rest = $.grep($.parseJSON(shoppingCars), function (foods, index) {
                    return foods[0].foodID.indexOf(registrationID) != 0;
                })

                if (shoppingCar.length > 0)
                    rest.push(shoppingCar);

                setShoppingCars(JSON.stringify(rest));
            })
        }

        function setShoppingCars(shoppingCars) {
            $.get("../../SetShoppingCars.action", {
                shoppingCars: shoppingCars
            })
        }

        $("tbody").delegate(".glyphicon-step-forward", "click", function () {
            var num = $(this).parents(".food").find(".num");
            num.html(parseInt(num.html()) + 1);
            updateShoppingCars();
        })
        $("tbody").delegate(".glyphicon-step-backward", "click", function () {
            var num = $(this).parents(".food").find(".num");
            if (num.html() == '1')
                return;
            num.html(parseInt(num.html()) - 1);
            updateShoppingCars();
        })

        $("tbody").delegate(".remove", "click", function () {
            var food = $(this).parents(".food");
            food.remove();

            updateShoppingCars();
        })

        $(".main").delegate('input', 'focus', function () {
            removeError($(this).parent());
        });

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
    } else {
        alert("请登录后刷新页面！");
    }



    $(".return").click(function () {
        var href = $(this).attr("href") + registrationID;
        $(this).attr("href", href);
    })
})