$(document).ready(function () {
    var pageContent = "全部分类";
    $(".pageNum").html("1");


    getSellerBrief(pageContent, $(".pageNum").html());

    $(".category").click(function () {
        if (pageContent != $(this).html()) {
            pageContent = $(this).html();
            $(".pageNum").html("1");
            getSellerBrief(pageContent, 1);
        } else {
            getSellerBrief(pageContent, $(".pageNum").html());
        }
    })

    $('.root').delegate('.enter', 'click', function () {
        var href = $(this).attr('href');
        if(href.indexOf('?') > 0)
            return;
        var target = href + '?registrationID=' + $(this).parents('.thumbnail').find('.registrationID').html()
        $(this).attr('href', target);
    })

    $(".previous").click(function () {
        if ($(this).hasClass("disabled"))
            return;

        var curPage = parseInt($(".pageNum").html());

        $(".brief").slideToggle();
        $(".brief").remove();

        getSellerBrief(pageContent, curPage - 1)
        $(".pageNum").html(curPage - 1);
    })

    $(".next").click(function () {
        if ($(this).hasClass("disabled"))
            return;

        var curPage = parseInt($(".pageNum").html());

        $(".brief").slideToggle();
        $(".brief").remove();

        getSellerBrief(pageContent, curPage + 1)

        $(".pageNum").html(curPage + 1);
    })

    function getSellerBrief(pageContent, page) {
        $(".brief").remove();

        $.get("../../getSellerBrief.action", {
            pageContent: pageContent,
            page: page
        }, function (briefs) {
            briefs = $.parseJSON(briefs);
            $.each(briefs, function (i, brief) {
                $(".root").append(createSellerBrief());
                var seller = $(".root .brief:last");
                if (brief.store_photo != null && brief.store_photo.length > 0)
                    seller.find(".store_photo").attr("src", brief.store_photo);
                seller.find(".store_name").html(brief.store_name);
                seller.find(".registrationID").html(brief.registrationID);
                seller.find(".soldNum").html(brief.soldNum);
                seller.find(".deliveryFee").html(brief.deliveryFee);

                seller.slideToggle();
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

        var brief = $(".brief");
        var hasNext = (brief.length == 12);
        if (hasNext)
            $(".next").removeClass("disabled");
        else
            $(".next").addClass("disabled");
    }

    function createSellerBrief() {
        var newSellerBrief = "<div class='brief col-sm-6 col-md-3' style='display : none'><div class='thumbnail'><img class='img-responsive ' src='../../style/images/sellers/default.jpg' " +
            "alt='图片离家出走了...'><div class='caption'><h3 class='store_name'></h3><span style='display: none;' class='registrationID'></span>" +
            "<p> 已售<span class='soldNum'></span>单 &ensp;&ensp;&ensp;配送费 ￥<span class='deliveryFee'></span></p> " +
            "<p><a target='_blank' href='seller_fore.jsp' class='enter btn btn-primary form-control' role='button'>进入店铺</a></p></div></div></div>";
        return newSellerBrief;
    }
})