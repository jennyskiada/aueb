const appURL = location.protocol + '//' + location.host;
const apiURL = "http://www.omdbapi.com/";
const apiKey = '6a819847';

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