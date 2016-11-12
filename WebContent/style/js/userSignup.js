$(document).ready(function () {
    $("input:not(:submit)").val("");

    $("#username").on('keyup blur change', function () {
        var parent = $(this).parent();
        var name = $(this).val().replace(/^\s+|\s+$/g, "");

        var help = parent.find('.help-block');
        if (name.match(/^[\u4e00-\u9fa5_a-zA-Z0-9]+$/g) == null) {
            addError(parent, "用户名只允许由汉字，英文字母，数字，下滑线组成，且不能为空");
            return;
        }

        $.post("../../CheckUsername.action", { name: name.toString() }, function (have) {
            if (have.match("true")) {
                addError(parent, "该用户名已注册");
                return;
            }
        })

        removeError(parent, help);
    })

    $("#phone").on('keyup blur change', function () {
        var parent = $(this).parent();
        var phone = $(this).val().replace(/^\s+|\s+$/g, "");
        var help = parent.find(".help-block");
        if (phone.match(/^\d{11}$/g) == null) {
            addError(parent, "请输入11位手机号码！");
            return;
        }

        $.post("../../CheckPhone.action", { phone: phone.toString() }, function (have) {
            if (have.match("true")) {
                addError(parent, "该手机号已注册！");
                return;
            }
        })

        removeError(parent);
    })

    $("#password").on('keyup blur change', function () {
        var parent = $(this).parent();
        var help = parent.find(".help-block");

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
        var help = parent.find(".help-block");

        var password1 = $("#password").val();
        var password2 = object.val();

        if (password1 != password2) {
            addError(parent,"两次密码不一致!");
            return;
        }

        removeError(parent);

    }




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

    $("form").on('submit', function () {
        var flag = true;
        var tip = "请完善您的信息!";
        var len = $(".form-group").length;

        for (var i = 0; i < len; ++i) {
            var fg = $(".form-group:eq(" + i + ")");
            if (flag && fg.hasClass("has-error")) {
                flag = false;
            }
            if (fg.find("input").val() == "") {
                addError(fg, tip);
                flag = false;
            }
        }

        return flag;
    })
})