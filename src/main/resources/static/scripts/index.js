$(document).ready(function() {

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
        $(".login-error").text(""); // Clear Possible Previous Errors
        var email = $("#login-email").val();
        var password = $("#login-password").val();
        if(email!==undefined && email!='' && password!==undefined && password!='') {
            var obj = { email: email, password: password };
            $.ajax({
                type: "POST",
                url: "/movies/userExists",
                contentType: 'application/json',
                data: JSON.stringify(obj),
                success: function(result) {
                    if(result) { // Login Succeeded
                        window.location.href = appURL + "/search?email=" + email;
                    } else { // Login Failed
                        $("#login-email").val('');
                        $("#login-password").val('');
                        $(".login-error").text("Invalid Credentials. Try Again.");
                    }
                },
                error: function() {
                    console.log("Cannot Communicate With The Spring Application.");
                }
            });
        } else {
            $(".login-error").text("Values Given Are Not Valid.");
        }
    });

    /**
     * Register Form
     */
    $("#register-form").submit(function () {
        return false;
    });
    $(document).on('click', '#register-button', function() {
        $(".register-error").each(function() { // Clear Possible Previous Errors
            $(this).text("");
        });
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
                    if(result) { // Persist Succeeded
                        window.location.href = appURL + "?created=true";
                    } else {
                        $(".register-general-error").text("User Exists. Try A Different Email.");
                    }
                },
                error: function() {
                    console.log("Cannot Communicate With The Spring Application.");
                }
            });
        } else { // Error Handling
            if(name=='') {
                $(".register-error.name").text("The Name Cannot Be Empty.");
            }
            if(!isEmail(email)) {
                $(".register-error.email").text("Email Is Not Valid.");
            }
            if(password=='' || (password!=confirmPassword)) {
                $(".register-error.password").each(function() {
                    $(this).text("The Passwords Do Not Match.");
                });
            }
        }
    });
});