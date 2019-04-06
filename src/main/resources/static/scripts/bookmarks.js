$(document).ready(function() {

    /**
     * Call The API To Get The Movies
     */
    if(getUrlParameter('email')!='') {
        $.ajax({
            type: "GET",
            url: "/movies/userBookmarks?email=" + getUrlParameter('email'),
            contentType: 'application/json',
            success: function(result) {

                if(result.bookmarks.length>0) {






                }



            },
            error: function() {
                //TODO Ajax Failed
            }
        });
    } else {
        //TODO Redirect To An Error Page
    }

});