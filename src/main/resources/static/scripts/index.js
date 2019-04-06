$(document).ready(function() {

    var url = location.protocol + '//' + location.host;

    /**
     * Validate The Given Email
     * @param email Email Value
     * @returns {boolean}
     */
    function isEmail(email) {
        var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
        return regex.test(email);
    }

    /**
     * Function To Get URL Parameter [ https://stackoverflow.com/a/21903119 ]
     * @param p Parameter Name
     * @returns Parameter's Value
     */
    var getUrlParameter = function getUrlParameter(p) {
        var sPageURL = window.location.search.substring(1), sURLVariables = sPageURL.split('&'), sParameterName, i;
        for (i = 0; i < sURLVariables.length; i++) {
            sParameterName = sURLVariables[i].split('=');
            if (sParameterName[0] === p) {
                return sParameterName[1] === undefined ? true : decodeURIComponent(sParameterName[1]);
            }
        }
    };

    /**
     * Toggle The Forms Visibility Base On The 'register' Parameter
     */
    if(getUrlParameter('register')=='true') {
        $("#login-container").addClass('hidden');
        $("#register-container").removeClass('hidden');
    } else {
        $("#login-container").removeClass('hidden');
        $("#register-container").addClass('hidden');
    }

    /**
     * Show The Creation Success Message
     */
    if(getUrlParameter('created')=='true') {
        $("#message-container").text("User Successfully Created.");
    }

    /**
     * Login Form
     */
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
                        window.location.href = url + "/movies/list?email=" + email;
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
            $("#login-error").text("Values Given Are Not Valid.");
        }
    });

    /**
     * Register Form
     */
    $("#register-form").submit(function () {
        return false;
    });
    $(document).on('click', '#register-button', function() {
        $("#register-error").text(""); // Clear Possible Previous Errors
        var name = $("#register-name").val();
        var email = $("#register-email").val();
        var password = $("#register-password").val();
        var confirmPassword = $("#register-confirm-password").val();
        if(name!='' && isEmail(email) && password!='' && password==confirmPassword) {
            var obj = { name: name, email: email, password: password };
            $.ajax({
                type: "POST",
                url: "/movies/insertUser",
                contentType: 'application/json',
                data: JSON.stringify(obj),
                success: function(result) {
                    window.location.href = url + "?created=true";
                },
                error: function() {
                    //TODO Ajax Failed
                }
            });
        } else {
            $("#register-error").text("Values Given Are Not Valid.");
        }
    });
});