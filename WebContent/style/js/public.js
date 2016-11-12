$(document).ready(function () {

    // checklogin();


    function checklogin() {
        var login ;//= "${sessionScope.phone}";

        if (login === undefined) {
            $("#login").hide();
            $("#unlogin").show();
        } else{
            $("#unlogin").hide();
            $("#login").show();
        }
    }
})