$(document).ready(function() {

    var email = getUrlParameter('email');

    $("#searchicon img").click(function() { // Just add some nice effect to hide the Magnifying Glass and show the search bar on Click!!
        $("#searchicon").hide();
        $("#searchmovie").fadeIn(750);
    });

    $("#movie-form").submit(function() {
        return false;
	});

    /**
     * Detect End Of Typing And When Done Search The API
     */
    var typingTimer;
    $("#movie").keyup(function() { // On keyup Start The Countdown
        clearTimeout(typingTimer);
        if ($("#movie").val()) {
            typingTimer = setTimeout(doneTyping, 1000);
        }
    });
    function doneTyping () {
        document.getElementById("movieresults").innerHTML = ""; // clear previous results
        searchMovies();
    }

    /**
     * Search For Movies
     */
    function searchMovies() {
        var url = apiURL + '?apikey=' + apiKey + '&s=' + $("#movie").val();
        fetch(url).then(function(response) {
            return response.json();
        }).then(function(movies){
            if(movies["Response"]=="False") {
                errorResult('not-found');
                return;
            }
            // Based On The Returned Results Construct A List Of IDs
            var ids = [];
            _.forEach(movies.Search, function(movie) {
                ids.push(movie.imdbID);
            });
            shortResultsList(ids);
         }).catch(function(error) {
            errorResult('exception');
        });
    }

    /**
     * Handle The Header's Values
     */
    if(email!== undefined && email!='') {
        $.ajax({
            type: "GET",
            url: "/movies/userBookmarks?email=" + email,
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
                console.log("Cannot Communicate With The Spring Application.");
            }
        });
    } else { // If No Email Is Given Redirect To Login Page
        window.location.href = appURL;
    }

    /**
     * Bookmark A Movie On Click Of 'save' Button
     */
    $(document).on('click', 'a.save', function(event) {
        event.preventDefault(); // Deactivate The href Value
        var obj = { email: email, movieId: $(this).data('value') };
        $.ajax({
            type: "POST",
            url: "/movies/insertBookmark",
            contentType: 'application/json',
            data: JSON.stringify(obj),
            success: function(result) {
                var message;
                if(result) { // Bookmark Saved
                    message = "Bookmark Saved.";
                } else {
                    message = "Bookmark Already Exists.";
                }
                // Append A Message Informing About The Result And Remove It After 2 Seconds
                var container = $("a[data-value=" + obj.movieId +"]").parent('div');
                container.append("<span class='save-result'>" + message + "</span>")
                setTimeout(function() {
                    container.find($('.save-result')).remove();
                }, 2000);
            },
            error: function() {
                console.log("Cannot Communicate With The Spring Application.");
            }
        });
    });
});