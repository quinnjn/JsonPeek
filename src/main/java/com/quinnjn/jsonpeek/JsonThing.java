package com.quinnjn.jsonpeek;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonThing {
    private final JSONObject object;
    private final JSONArray array;

    public JsonThing(String json) {
        JSONArray jsonArray= null;
        JSONObject jsonObject = null;
        try {
            jsonArray = new JSONArray(json);
        } catch (JSONException notAnObject) {
            try {
                jsonObject = new JSONObject(json);
            } catch (JSONException notAnArray) {
                throw new JSONException("Json is neither a JSONObject or JSONArray");
            }
        }
        array = jsonArray;
        object = jsonObject;
    }

    public JsonThing get(String key) {
        return null;
    }
}
