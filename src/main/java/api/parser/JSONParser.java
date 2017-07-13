package api.parser;

import org.json.JSONObject;


public interface JSONParser<T> {
    T parse(JSONObject json);
}
