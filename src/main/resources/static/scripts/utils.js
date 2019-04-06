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
     * If The Page Has The Greetings Header Fill The Needed Values
     */
    if($("#greetings").length && getUrlParameter('email')!='') {
        $.ajax({
            type: "GET",
            url: "/movies/userBookmarks?email=" + getUrlParameter('email'),
            contentType: 'application/json',
            success: function(result) {
                console.log(result);
                $("#greetings .user-name").text(result.name);
                if(result.bookmarks.length>0) {
                    $("#greetings .user-bookmarks a").attr('href', '/movies/bookmarks?email=' + result.email);
                    $("#greetings .user-bookmarks").removeClass('hidden');
                }
                $("#greetings").removeClass("hidden"); // Show The Header
            },
            error: function() {
                //TODO Ajax Failed
            }
        });
    }




});