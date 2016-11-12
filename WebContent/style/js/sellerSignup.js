$(document).ready(function () {
    $("input:not(:submit)").val("");
    $("textarea").val("");

    $("#registration_id").on('keyup blur change', function () {
        var parent = $(this).parent();
        var registrationID = $(this).val().replace(/^\s+|\s+$/g, "");

        if (registrationID.match(/^\d{15}$/g) == null) {
            addError(parent, "请填写15位注册号！");
            return;
        }

        $.get("../../CheckRegistrationID.action", { registrationID: registrationID.toString() }, function (have) {
            if (have.match("true")) {
                addError(parent, "该资格证已注册！");
                return;
            }
        })

        removeError(parent);
    })

    $("#phone").on('keyup blur change', function () {
        var parent = $(this).parent();
        var phone = $(this).val().replace(/^\s+|\s+$/g, "");

        if (phone.match(/^\d{11}$/g) == null) {
            addError(parent, "请输入11位手机号码！");
            return;
        }

        $.get("../../CheckSellerPhone.action", { phone: phone.toString() }, function (have) {
            if (have.match("true")) {
                addError(parent, "该手机号已注册！");
                return;
            }
        })

        removeError(parent);
    })

    $("#password").on('keyup blur change', function () {
        var parent = $(this).parent();

        var password = $(this).val();

        password_confirmation($("#password_confirmation"));

        if (password.match(/^\w+$/g) == null || password.length < 6) {
            addError(parent, "密码只允许由英文字母，数字，下滑线组成，最小长度为6个字符");
            return;
        }

        removeError(parent);
    })

    $("#password_confirmation").on('keyup blur change', function () {
        password_confirmation($(this));
    })

    function password_confirmation(object) {
        var parent = object.parent();

        var password1 = $("#password").val();
        var password2 = object.val();

        if (password1 != password2) {
            addError(parent, "两次密码不一致!");
            return;
        }

        removeError(parent);
    }

    $("#store_name").on('keyup blur change', function () {
        var parent = $(this).parent();
        var store_name = $(this).val().replace(/^\s+|\s+$/g, "");
        if (store_name == "") {
            addError(parent, "请输入店铺名！");
            return;
        }

        removeError(parent);
    })

    $("#store_address").on('keyup blur change', function () {
        var parent = $(this).parent();
        var store_address = $(this).val().replace(/^\s+|\s+$/g, "");
        var help = parent.find(".help-block");
        if (store_address == "") {
            addError(parent, "请输入店铺地址！");
            return;
        }

        removeError(parent);
    })

    $("#delivery_fee").on('keyup blur change', function () {
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

    $('.form-group input[type="text"], .form-group input[type="password"], .form-group textarea').on('focus', function () {
        removeError($(this).parent());
    });

    $("form").on('submit', function () {
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
        if(flag){
            $(this).attr('action',$(this).attr('action')+$('#category').val());
        }

        return flag;
    })
})