$(document).ready(function() {

    /* Login Form */
    $("#login-form").submit(function () {
        return false;
    });
    $(document).on('click', '#login-button', function() {
        var email = $("#login-email").val();
        var password = $("#login-password").val();
        if(email!='' && password!='') {
            var obj = { email: email, password: password };



            $.ajax({
                type: "POST",
                url: "/movies/userExists",
                dataType: 'json',
                data: JSON.stringify(obj),
                success: function(data) {

                    console.log("XXX");
                },
                error: function() {
                    console.log("YYY");
                }
            });

        }


    });


    /* Register Form*/
    $("#register-form").submit(function () {
        return false;
    });



});