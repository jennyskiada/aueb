$(document).ready(function() {

    $("#searchicon img").click(function() { // Just add some nice effect to hide the Magnifying Glass and show the search bar on Click!!
        $("#searchicon").hide();
        $("#searchmovie").fadeIn(750);
    });

    $("#movie-form").submit(function() {
        return false;
	});




	// Detect End Of Typing And When Done Search The API
    var typingTimer;
    $("#movie").keyup(function() { // On keyup Start The Countdown
        clearTimeout(typingTimer);
        if ($("#movie").val()) {
            typingTimer = setTimeout(doneTyping, 1000);
        }
    });
    function doneTyping () {
        document.getElementById("movieresults").innerHTML = ""; // clear previous results
        fetchMovie(false); // false = 'Short Description'
    }






    var imdbtt
    $(document).on('click', 'a.more', function(event) {
        event.preventDefault(); // Deactivate The href Value
        imdbtt = $(this).data('value');
        fetchMovie(true); // true = 'Full Description'
    });

	/**
	 * Fetch Short Or Full Movie From The API
	 */
    function fetchMovie(full) {
        if(full){
            fullResult(movie)
        } else {
            var url = constructUrl(full);
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
    }

    //TODO
    function fullResult(movie) {
        var fullResultTemplate = document.getElementById("full-result-template").innerHTML;
		// Create Template Function
        var templateFn = _.template(fullResultTemplate);
        var resultTemplate = document.querySelector("[data-value='"+imdbtt+"']").parentElement; // find the div that has the matchind imdb id value that the user clicked by getting the data value element of the a.more and get its parent element
        var url = apiURL + '?apikey=' + apiKey + '&i=' + imdbtt + '&plot=full';
        fetch(url).then(function(response) {
            return response.json();
        }).then(function(movie){
            var templateHTML = templateFn({ // Execute Template Function
                'title' : movie["Title"],
                'plot': movie["Plot"],
                'poster': movie["Poster"],
                'year': movie["Year"],
                'awards': movie["Awards"],
                'runtime': movie["Runtime"],
                'genre': movie["Genre"],
                'rating': movie["imdbRating"],
                'rotten': _.get(movie, "Ratings[1].Value"),
                'metacritic': movie["Metascore"],
                'director': movie["Director"],
                'actors': movie["Actors"]
		    });
            resultTemplate.innerHTML = templateHTML;
            $(resultTemplate).css({'margin-left':'1%'}) //hard-fix the replaced content in same position
        });
    }

	/**
	 * Display An Error Message
	 */
    function errorResult(error) {
        if(error=='not-found') {
            $("#movieresults").html("We didn't find your movie, perhaps another idea for tonight's screening?");
        } else {
            $("#movieresults").html('An Error Occurred. Try Again Later.');
        }
    }







    /**
     * Handle The Header's Values
     */
    if(getUrlParameter('email')!='') {
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
    } else {
        //TODO Redirect To An Error Page
    }

    /**
     * Bookmark A Movie On Click Of 'save' Button
     */
    $(document).on('click', 'a.save', function(event) {
        event.preventDefault(); // Deactivate The href Value
        var obj = { email: getUrlParameter('email'), movieId: $(this).data('value') };
        $.ajax({
            type: "POST",
            url: "/movies/insertBookmark",
            contentType: 'application/json',
            data: JSON.stringify(obj),
            success: function(result) {
                if(result) { // Bookmark Saved
                    //TODO Message
                    console.log("Saved");
                    //$("<span>Bookmark Saved.</span>").insertAfter($(this));
                } else {
                    //TODO Message
                    console.log("Already Exists");
                    //$("<span>Bookmark Already Exists.</span>").insertAfter($(this));
                }
            },
            error: function() {
                //TODO Ajax Failed
            }
        });
    });


});