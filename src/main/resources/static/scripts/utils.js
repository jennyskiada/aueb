const appURL = location.protocol + '//' + location.host + '/movies';
const apiURL = "http://www.omdbapi.com/";
const apiKey = '6a819847';

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
 * Construct A List Of Movies In Short Description Given A List Of IDs
 * @param ids List Of IDs
 */
function shortResultsList(ids) {
    var templateFn = _.template(document.getElementById("short-result-template").innerHTML);
    var resultTemplate = document.getElementById("movieresults");
    _.forEach(ids, function(id) { // Execute Template Function
        var url = apiURL + '?apikey=' + apiKey + '&i=' + id;
        fetch(url).then(function(response) {
            return response.json();
        }).then(function(movie){
            var templateHTML = templateFn({
                'poster': movie["Poster"],
                'title' : movie["Title"],
                'plot': movie["Plot"],
                'year': movie["Year"],
                'runtime': movie["Runtime"],
                'imdbid': movie["imdbID"]
            });
            resultTemplate.innerHTML += templateHTML; // Append The Movie
            $("#movieresults").css("display", "flex"); // Show result container and make it flexbox to accommodate cases of images overflowing low text reults
            $("footer").css("top", "10vh"); // Adjust footer's positioning for better visuals
        });
    });
}

/**
 * Enrich The Movie With The Given ID
 * @param id Movie ID
 */
function enrichMovie(id) {
    var templateFn = _.template(document.getElementById("full-result-template").innerHTML);
    var resultTemplate = $(".more[data-value='" + id + "']").parent('div.details');
    var url = apiURL + '?apikey=' + apiKey + '&i=' + id + '&plot=full';
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
            'actors': movie["Actors"],
            'imdbid': movie["imdbID"]
        });
        resultTemplate.html(templateHTML); // Replace The Content
        //$(resultTemplate).css({'margin-left':'1%'}) // hard-fix the replaced content in same position
    });
}

/**
 * On Click Of 'more' Enrich The Movie With More Info
 */
$(document).on('click', 'a.more', function(event) {
    event.preventDefault(); // Deactivate The href Value
    enrichMovie($(this).attr('data-value')); // Enrich The Movie's View
});