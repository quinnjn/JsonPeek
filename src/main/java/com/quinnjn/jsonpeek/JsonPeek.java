package com.quinnjn.jsonpeek;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonPeek {

    private final JSONArray jsonArray;
    private final JSONObject jsonObject;

    public static JsonPeek from(String jsonString) {
        return new JsonPeek(jsonString);
    }

    private JsonPeek(String jsonString){
        JSONArray array = null;
        JSONObject object = null;
        try {
            array = new JSONArray(jsonString);
        } catch (JSONException notAnObject) {
            try {
                object = new JSONObject(jsonString);
            } catch (JSONException notAnArray) {
                throw new JSONException("Json is neither a JSONObject or JSONArray");
            }
        }
        jsonArray = array;
        jsonObject = object;
    }

    public String getString(String key) {
        return (String) getProperty(key);
    }

    /**
     * Takes a json dot notation string and divides it up into each layer to dive.
     * Examples of how this should work.
     * - {"a":{"b":{"c":"result"}}}. want(result). notation(a.b.c). array(a, b, c)
     * - [[["a","b"]]]. want(b). notation([0][0][1]). array(0,0,1)
     * - {"a":["b","c"]}. want(c). notation(a[1]). array(a,1)
     */
    private Object getProperty(String instruction) {
        return getJsonObject(instruction);
    }

    private Object getProperty(List<String> keys) {
        return null;
    }

    private Object getJsonObject(String instruction) {
        JSONObject currentObject = jsonObject;
        List<String> keys = new ArrayList<String>(Arrays.asList(instruction.split("\\.")));
        String lastKey = keys.remove(keys.size() -1);

        for (String key : keys) {
            currentObject = currentObject.getJSONObject(key);
        }

        return currentObject.get(lastKey);
    }
}
