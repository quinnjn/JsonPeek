package com.quinnjn.jsonpeek;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonThing {
    final JSONObject object;
    final JSONArray array;

    public static JsonThing from(String json) {
        JSONArray jsonArray= null;
        JSONObject jsonObject = null;
        if (json != null){
            try {
                jsonArray = new JSONArray(json);
            } catch (JSONException notAnObject) {

            }
            try {
                jsonObject = new JSONObject(json);
            } catch (JSONException notAnArray) {
            }
        }
        return new JsonThing(jsonArray, jsonObject);
    }

    public JsonThing(JSONArray array, JSONObject object) {
        this.array = array;
        this.object = object;

        if (this.array == null && this.object == null) {
            throw new JSONException("Json is neither a JSONObject or JSONArray");
        }
    }

    public JsonThing get(int key){
        return JsonThing.from(array.get(key).toString());
    }

    public JsonThing get(String key) {
        return JsonThing.from(object.get(key).toString());
    }

    public Object value(int key) {
        return array.get(key);
    }

    public Object value(String key) {
       return object.get(key);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof JsonThing)) {
            return false;
        }

        JsonThing other = (JsonThing)obj;
        boolean result = true;
        if (object != null) {
            result &= object.equals(other.object);
        }
        if (array != null) {
            result &= array.equals(other.array);
        }
        return result;
    }

    @Override
    public String toString() {
        return String.format("object: %s array: %s", object, array);
    }
}
