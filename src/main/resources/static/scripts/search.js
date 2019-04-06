$(document).ready(function() {

    $("#searchicon img").click(function() { // Just add some nice effect to hide the Magnifying Glass and show the search bar on Click!!
        $("#searchicon").hide();
        $("#searchmovie").fadeIn(750);
    });

    $("#movie-form").submit(function() {
        return false;
	});

	// Detect End Of Typing
    var typingTimer;
	// On keyup Start The Countdown
    $("#movie").keyup(function() {
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
        var mid = $(this)
        imdbtt = mid.data('value')
        fetchMovie(true); // true = 'Full Description'
    });

	/**
	 * Fetch Short Or Full Movie From The API
	 */
    const apiKey = '6a819847'
    const apiURL = "http://www.omdbapi.com/"

    function fetchMovie(full) {
        if(full){
            fullResult(movie)
        } else {
            var url = constructUrl(full);
            fetch(url).then(function(response) {
                return response.json();
            }).then(function(movieData){
                console.log(movieData)
                if(!results){ // only apply the first time a movie is searched for
                    var results = true;
                    $("#movieresults").css("display", "flex"); // Show result container and make it flexbox to accommodate cases of images overflowing low text reults
                    $("footer").css("top", "10vh"); // Adjust footer's positioning for better visuals
                }
                if(movieData["Response"]=="False") {
                    errorResult('not-found');
                    return;
                }
                // Display The Full Results Template
                shortResult(movieData);
             }).catch(function(error) {
                errorResult('exception');
            });
        }
    }

	/**
	 * Construct The API's URL (For Full Or Short Plot)
	 */
    function constructUrl(full) {
        if(!full){
            var url = apiURL + '?apikey=' + apiKey + '&s=' + $("#movie").val();
            return url;
        }
    }

	/**
	 * Short Results Template
	 */
    function shortResult(movie) {
        var shortResultTemplate = document.getElementById("short-result-template").innerHTML;

		// Create Template Function
        var templateFn = _.template(shortResultTemplate);
        var resultTemplate = document.getElementById("movieresults");

		// Execute Template Function
        _.forEach(movie.Search, function(movieitem,key) {
            url = apiURL + '?apikey=' + apiKey + '&i=' + movieitem.imdbID;
            console.log(url,key)

            fetch(url).then(function(response) {
                return response.json();
            }).then(function(damovieData){
                console.log(damovieData)
                var templateHTML = templateFn({
                    'title' : damovieData["Title"],
                    'plot': damovieData["Plot"],
                    'poster': damovieData["Poster"],
                    'year': damovieData["Year"],
                    'runtime': damovieData["Runtime"],
                    'genre': damovieData["Genre"],
                    'rating': damovieData["imdbRating"],
                    'rotten': _.get(damovieData, "Ratings[1].Value"),
                    'metacritic': damovieData["Metascore"],
                    'imdbid': damovieData["imdbID"]
                });
                resultTemplate.innerHTML += templateHTML;
            });
        });
    }

	/**
	 * Short Results Template
	 */
    function fullResult(damovieData) {
        var fullResultTemplate = document.getElementById("full-result-template").innerHTML;

		// Create Template Function
        var templateFn = _.template(fullResultTemplate);
        var resultTemplate = document.querySelector("[data-value='"+imdbtt+"']").parentElement // find the div that has the matchind imdb id value that the user clicked by getting the data value element of the a.more and get its parent element
        url = apiURL + '?apikey=' + apiKey + '&i=' + imdbtt + '&plot=full';

        fetch(url).then(function(response) {
            return response.json();
        }).then(function(movie){

		    // Execute Template Function
            var templateHTML = templateFn({
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








});