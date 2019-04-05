$(document).ready(function() {

    var url = location.protocol + '//' + location.host;

    /* Login Form */
    $("#login-form").submit(function () {
        return false;
    });
    $(document).on('click', '#login-button', function() {
        $("#login-error").text(""); // Clear Possible Previous Errors
        var email = $("#login-email").val();
        var password = $("#login-password").val();
        if(email!='' && password!='') {
            var obj = { email: email, password: password };
            $.ajax({
                type: "POST",
                url: "/movies/userExists",
                contentType: 'application/json',
                data: JSON.stringify(obj),
                success: function(result) {
                    if(result) { // Login Succeeded
                        window.location.href = url + "/movies";
                    } else { // Login Failed
                        $("#login-email").val('');
                        $("#login-password").val('');
                        $("#login-error").text("Invalid Credentials. Try Again.");
                    }
                },
                error: function() {
                    //TODO Ajax Failed
                }
            });
        } else {
            //TODO Not Valid
        }
    });

    /* Register Form*/
    $("#register-form").submit(function () {
        return false;
    });
    $(document).on('click', '#register-button', function() {
        $("#register-error").text(""); // Clear Possible Previous Errors
        var name = $("#register-name").val();
        var email = $("#register-email").val();
        var password = $("#register-password").val();
        var confirmPassword = $("#register-confirm-password").val();
        if(name!='' && email!='' && password!='' && password==confirmPassword) {
            var obj = { name: name, email: email, password: password };
            $.ajax({
                type: "POST",
                url: "/movies/insertUser",
                contentType: 'application/json',
                data: JSON.stringify(obj),
                success: function(result) {
                    console.log("User Successfully Created.");
                    window.location.href = url + "/movies/list?email=" + email;
                },
                error: function() {
                    //TODO Ajax Failed
                }
            });
        } else {
            //TODO Not Valid
        }
    });


});