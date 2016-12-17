$(document).ready(function () {

    var pageContent = "soldNumDown";

    var registrationID = $(".registrationID").html();
    if (registrationID == null || registrationID == ""
        || !registrationID.match(/^\d{15}$/g))
        return;

    $.get('../../GetSellerDetail.action', {
        registrationID: registrationID
    },
        function (seller) {
            seller = $.parseJSON(seller);
            $(".jumbotron .store_name").html(seller.storeName);
            $(".jumbotron .store_description").html(seller.description);
            $(".jumbotron .storeAddress").html(seller.storeAddress);
            $(".jumbotron .phone").html(seller.phone);
            var status = "";
            if (seller.status == 1)
                status = "营业中...";
            else
                status = "已打烊..."

            $(".panel .status").html(status);
            $(".panel .announcement").html(seller.announcement);
            $(".panel .deliveryFee").html(seller.deliveryFee);
        })

    getFoodsBrief(pageContent, 1);

    $(".sortByPrice").click(function () {
        var icon = $(this).find(".glyphicon");
        if (icon.hasClass("glyphicon-arrow-down")) {
            icon.removeClass("glyphicon-arrow-down");
            icon.addClass("glyphicon-arrow-up");
            pageContent = "priceUp";
            getFoodsBrief(pageContent, 1);
        } else if (icon.hasClass("glyphicon-arrow-up")) {
            icon.removeClass("glyphicon-arrow-up");
            icon.addClass("glyphicon-arrow-down");
            pageContent = "priceDown";
            getFoodsBrief(pageContent, 1);
        }
    })

    $(".sortBySoldNum").click(function () {
        var icon = $(this).find(".glyphicon");
        if (icon.hasClass("glyphicon-arrow-down")) {
            icon.removeClass("glyphicon-arrow-down");
            icon.addClass("glyphicon-arrow-up");
            pageContent = "soldNumUp";
            getFoodsBrief(pageContent, 1);
        } else if (icon.hasClass("glyphicon-arrow-up")) {
            icon.removeClass("glyphicon-arrow-up");
            icon.addClass("glyphicon-arrow-down");
            pageContent = "soldNumDown";
            getFoodsBrief(pageContent, 1);
        }
    })

    $(".previous").click(function () {
        if ($(this).hasClass("disabled"))
            return;

        var curPage = parseInt($(".pageNum").html());

        $(".food").remove();

        getFoodsBrief(pageContent, curPage - 1)
        $(".pageNum").html(curPage - 1);
    })

    $(".next").click(function () {
        if ($(this).hasClass("disabled"))
            return;

        var curPage = parseInt($(".pageNum").html());

        $(".food").remove();

        getFoodsBrief(pageContent, curPage + 1)

        $(".pageNum").html(curPage + 1);
    })

    function getFoodsBrief(pageContent, page) {
        $(".food").remove();

        $.get('../../GetFoodsBrief.action', {
            registrationID: registrationID,
            pageContent: pageContent,
            page: page
        }, function (foods) {
            foods = $.parseJSON(foods);
            $.each(foods, function (i, food) {
                $(".root").append(createFoodBrief());
                var newfood = $(".root .food:last");
                newfood.find(".foodID").html(food.foodID);
                newfood.find(".foodName").html(food.name);
                newfood.find(".soldNum").html(food.soldNum);
                newfood.find(".foodPrice").html(food.price);
                if (food.imageAddr != null && food.imageAddr.length > 0)
                    newfood.find("img").attr("src", food.imageAddr);

                newfood.slideToggle();
            })
            setTimeout(checkPage, 500);
        })
    }

    function checkPage() {
        var curPage = $(".pageNum").html();
        if (curPage == "1")
            $(".previous").addClass("disabled");
        else
            $(".previous").removeClass("disabled");

        var food = $(".food");
        var hasNext = (food.length == 12);
        if (hasNext)
            $(".next").removeClass("disabled");
        else
            $(".next").addClass("disabled");
    }


    $(".jumbotron").click(function () {
        $(this).find(".detail").slideToggle();
    })

    function createFoodBrief() {
        var newfood = '<div class="food col-xs-6 col-sm-4 col-md-3" style="display:none;"><div class="thumbnail"><img style="height:110px;width:120px;" src="../../style/images/foods/default.jpg" alt="通用的占位符缩略图"><div class="caption">' +
            '<p class="foodID" style="display:none;"></p><h3 class="foodName"></h3><p>销量：<span class="soldNum"></span></p><p>￥<span class="foodPrice"></span></p>' +
            '<button class="btn btn-success cd-add-to-cart form-control" role="button">加入购物车</button></div></div></div>';
        return newfood;
    }


    //*************************************shopping-card*******************************************************************************

    var cartWrapper = $('.cd-cart-container');
    var productId = 0;
    var cartBody = cartWrapper.find('.body')
    var cartList = cartBody.find('ul').eq(0);
    var cartTotal = cartWrapper.find('.checkout').find('span');
    var cartTrigger = cartWrapper.children('.cd-cart-trigger');
    var cartCount = cartTrigger.children('.count')
    var undo = cartWrapper.find('.undo');
    var undoTimeoutId;


    $.get("../../GetShoppingCars.action", function (shoppingCars) {
        var tshoppingCars = $.grep($.parseJSON(shoppingCars), function (foods, index) {
            return foods[0].foodID.indexOf($(".registrationID").html()) == 0;
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
                    addToCart(tempfood);
                }
            })
        })
    })



    function setShoppingCars(shoppingCars) {
        $.get("../../SetShoppingCars.action", {
            shoppingCars: shoppingCars
        })
    }

    cartWrapper.on('click', function (event) {
        if ($(event.target).is($(this))) toggleCart(true);
    });

    $(".root").delegate(".cd-add-to-cart", "click", function () {
        addToCart($(this).parents(".food"));
    });

    function addToCart(food) {
        var cartIsEmpty = cartWrapper.hasClass('empty');

        //update cart product list
        addProduct(food);

        updateCartCount(cartIsEmpty);
        updateCartTotal(food.find(".price").html(), true);

        quickUpdateCart();

        cartWrapper.removeClass('empty');
    }

    function addProduct(food) {
        productId = productId + 1;

        //////////////////////////////////////////////////////////////////
        //查看删除方式，添加功能---当该食品已在购物车则只更改数量
        //////////////////////////////////////////////////////////////////////
        var len = cartList.find(".product").length;
        for (var i = 0; i < len; ++i) {
            var tempProduct = cartList.find(".product:eq(" + i + ")");

            if (tempProduct.find(".foodID").html() == food.find(".foodID").html()) {
                if (tempProduct.hasClass("delete")) {
                    undoProduct();
                    tempProduct.find(".num").html("1");
                } else {
                    var num = parseInt(tempProduct.find(".num").html());
                    tempProduct.find(".num").html(num + 1);
                }

                return;
            }
        }

        var productAdded = $('<li class="product"><p class="foodID" style="display:none;"></p><div class="product-image"><img class="img-responsive" src="../../style/images/foods/default.jpg" alt="placeholder"></a></div><div class="product-details"><h3 class="foodName"></a></h3></span></span><span class="price"></span><div class="quantity"><span class="btn glyphicon glyphicon-step-backward"></span><span class="num">1</span><span class="btn glyphicon glyphicon-step-forward"></span></div><div class="actions"><a href="#0" class="delete-item btn">Delete</a></div></li>');

        var imageAddr = food.find("img").attr("src");
        if (imageAddr != null && imageAddr != "")
            productAdded.find("img").attr("src", imageAddr);
        productAdded.find(".foodName").html("&ensp;&ensp;" + food.find(".foodName").html());
        productAdded.find(".price").html('￥' + food.find(".foodPrice").html());
        productAdded.find(".foodID").html(food.find(".foodID").html());

        cartList.prepend(productAdded);
    }


    function undoProduct() {
        clearInterval(undoTimeoutId);
        cartList.find('.deleted').addClass('undo-deleted').one('webkitAnimationEnd oanimationend msAnimationEnd animationend', function () {
            $(this).off('webkitAnimationEnd oanimationend msAnimationEnd animationend').removeClass('deleted undo-deleted').removeAttr('style');
            quickUpdateCart();
        });
        undo.removeClass('visible');
    }

    function updateCartCount(emptyCart, quantity) {
        if (typeof quantity === 'undefined') {
            var actual = Number(cartCount.find('li').eq(0).text()) + 1;
            var next = actual + 1;

            if (emptyCart) {
                cartCount.find('li').eq(0).text(actual);
                cartCount.find('li').eq(1).text(next);
            } else {
                cartCount.addClass('update-count');

                setTimeout(function () {
                    cartCount.find('li').eq(0).text(actual);
                }, 150);

                setTimeout(function () {
                    cartCount.removeClass('update-count');
                }, 200);

                setTimeout(function () {
                    cartCount.find('li').eq(1).text(next);
                }, 230);
            }
        } else {
            var actual = Number(cartCount.find('li').eq(0).text()) + quantity;
            var next = actual + 1;

            cartCount.find('li').eq(0).text(actual);
            cartCount.find('li').eq(1).text(next);
        }
    }

    cartTrigger.on('click', function (event) {
        event.preventDefault();
        toggleCart();
    });

    function toggleCart(bool) {
        var cartIsOpen = (typeof bool === 'undefined') ? cartWrapper.hasClass('cart-open') : bool;

        if (cartIsOpen) {
            cartWrapper.removeClass('cart-open');
            //reset undo
            clearInterval(undoTimeoutId);
            undo.removeClass('visible');
            cartList.find('.deleted').remove();

            setTimeout(function () {
                cartBody.scrollTop(0);
                //check if cart empty to hide it
                if (Number(cartCount.find('li').eq(0).text()) == 0) cartWrapper.addClass('empty');
            }, 500);
        } else {
            cartWrapper.addClass('cart-open');
        }
    }

    //delete an item from the cart
    cartList.on('click', '.delete-item', function (event) {
        event.preventDefault();
        removeProduct($(event.target).parents('.product'));
    });

    function removeProduct(product) {
        clearInterval(undoTimeoutId);
        cartList.find('.deleted').remove();

        var topPosition = product.offset().top - cartBody.children('ul').offset().top,
            productQuantity = Number(product.find('.quantity').find('.num').html()),
            productTotPrice = Number(product.find('.price').text().replace('￥', '')) * productQuantity;

        product.css('top', topPosition + 'px').addClass('deleted');

        //update items count + total price
        updateCartTotal(productTotPrice, false);
        updateCartCount(true, -productQuantity);
        undo.addClass('visible');

        //wait 8sec before completely remove the item
        undoTimeoutId = setTimeout(function () {
            undo.removeClass('visible');
            cartList.find('.deleted').remove();
        }, 8000);

        quickUpdateCart();
    }

    function updateCartTotal(price, bool) {
        bool ? cartTotal.text((Number(cartTotal.text()) + Number(price)).toFixed(2)) : cartTotal.text((Number(cartTotal.text()) - Number(price)).toFixed(2));
    }

    function quickUpdateCart() {
        var quantity = 0;
        var price = 0;
        var shoppingCar = [];
        cartList.children('li:not(.deleted)').each(function () {
            var singleQuantity = Number($(this).find('.num').html());
            quantity = quantity + singleQuantity;
            price = price + singleQuantity * Number($(this).find('.price').text().replace('￥', ''));

            var food = new Object();
            food.foodID = $(this).find(".foodID").html();
            food.foodCount = $(this).find(".num").html();
            shoppingCar.push(food);
        });

        $.ajax({
            url: "../../GetShoppingCars.action",
            async: false,//嵌套时最好加上这个，不然容易出问题，我的就是这里出问题了，外层的应该无所谓，内层一定要加
            success: function (shoppingCars) {
                var rest = $.grep($.parseJSON(shoppingCars), function (foods, index) {
                    return foods[0].foodID.indexOf(registrationID) != 0;
                })
                rest.push(shoppingCar);
                $.ajax({
                    url: "../../SetShoppingCars.action",
                    async: false,
                    data: { shoppingCars: JSON.stringify(rest) },
                });
            }
        });

        cartTotal.text(price.toFixed(2));
        cartCount.find('li').eq(0).text(quantity);
        cartCount.find('li').eq(1).text(quantity + 1);

    }

    //reinsert item deleted from the cart
    undo.on('click', 'a', function (event) {
        undoProduct();
    });

    $(".cd-cart-container").delegate(".glyphicon-step-forward", "click", function () {
        var num = $(this).parents(".product").find(".num");
        num.html(parseInt(num.html()) + 1);
        quickUpdateCart();
    })
    $(".cd-cart-container").delegate(".glyphicon-step-backward", "click", function () {
        var num = $(this).parents(".product").find(".num");
        if (num.html() == '1')
            return;
        num.html(parseInt(num.html()) - 1);
        quickUpdateCart();
    })

    $(".checkout").click(function () {
        var href = $(this).attr("href") + $(".registrationID").html();
        $(this).attr("href", href);
    })
})