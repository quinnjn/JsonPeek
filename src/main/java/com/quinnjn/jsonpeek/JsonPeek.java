package com.quinnjn.jsonpeek;

import org.json.JSONObject;

public class JsonPeek {

    private final JSONObject json;

    public static JsonPeek from(String jsonString) {
        return new JsonPeek(jsonString);
    }

    private JsonPeek(String jsonString){
        this.json = new JSONObject(jsonString);
    }

    public String getString(String key) {
        return json.getString(key);
    }
}
