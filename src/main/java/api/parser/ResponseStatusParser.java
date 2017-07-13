package api.parser;

import api.client.ResponseStatus;
import org.json.JSONObject;
import static api.client.ResponseStatus.*;

public class ResponseStatusParser implements JSONParser<ResponseStatus> {

    /**
     * If given json contains a key with name "error" - parses an error code and returns {@link ResponseStatus} represents the error code.
     * Otherwise returns {@link ResponseStatus} OK.
     */
    @Override
    public ResponseStatus parse(JSONObject json) {
        if(json.has("error")){ //if there's an error key in the response.
            int errorCode = json.getInt("error"); //get an error code.
            switch (errorCode){
                case 404: return PROFILE_NOT_FOUND;
                case 429: return API_LIMIT_REACHED;
                case 500: return UNEXPECTED_API_ERROR;
                default: return UNEXPECTED_API_ERROR;
            }
        }
        return ResponseStatus.OK;
    }
}
