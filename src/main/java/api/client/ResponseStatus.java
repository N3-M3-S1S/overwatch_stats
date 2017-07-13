package api.client;

/**
 * Represents OWAPI status responses.
 */
public enum ResponseStatus {
    OK, // Response has no errors.
    API_LIMIT_REACHED, // Too many queries.
    PROFILE_NOT_FOUND, // Profile with given BattleTag is not found. I've not tested the api with an account that has Overwatch but hasn't launched it yet. May be the API will return that error in this case.
    UNEXPECTED_API_ERROR; // Haven't seen this kind of error yet.

    public String getStatusMessage(){
        String statusMessage = "Unknown error.";
        switch (this){
            case OK:
                statusMessage = "OK";
                break;
            case API_LIMIT_REACHED:
                statusMessage = "Api limit reached. Try again later.";
                break;
            case PROFILE_NOT_FOUND:
                statusMessage = "Profile not found. Check the BattleTag.";
                break;
            case UNEXPECTED_API_ERROR:
                statusMessage = "Unexpected API error.";
                break;
        }
        return statusMessage;
    }
}
