$(document).ready(function() {

    var email = getUrlParameter('email');

    /**
     * Call The API To Get The Movies
     */
    if(email!== undefined && email!='') {
        $.ajax({
            type: "GET",
            url: "/movies/userBookmarks?email=" + email,
            contentType: 'application/json',
            success: function(result) {
                // Handle The Header Values
                $("#greetings .user-name").text(result.name);
                $("#greetings a").attr('href', '/movies/search?email=' + result.email);
                $("#greetings").removeClass("hidden"); // Show The Header
                // Fetch Bookmarks Via REST Call To API
                if(result.bookmarks.length>0) {
                    shortResultsList(result.bookmarks);
                } else { // If No Bookmarks Exist Redirect To Search Page
                    window.location.href = appURL + "/search?email=" + email;
                }
            },
            error: function() {
                //TODO Ajax Failed
            }
        });
    } else { // If No Email Is Given Redirect To Login Page
        window.location.href = appURL;
    }

});