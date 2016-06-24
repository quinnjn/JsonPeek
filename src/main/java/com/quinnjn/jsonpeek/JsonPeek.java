package com.quinnjn.jsonpeek;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonPeek {

    private final JSONObject json;

    public static JsonPeek from(String jsonString) {
        return new JsonPeek(jsonString);
    }

    private JsonPeek(String jsonString){
        this.json = new JSONObject(jsonString);
    }

    public String getString(String key) {
        return (String) getJsonObject(key);
    }

    private Object getJsonObject(String instruction) {
        JSONObject currentObject = json;
        List<String> keys = new ArrayList<String>(Arrays.asList(instruction.split("\\.")));
        String lastKey = keys.remove(keys.size() -1);

        for (String key : keys) {
            currentObject = currentObject.getJSONObject(key);
        }

        return currentObject.get(lastKey);
    }
}
