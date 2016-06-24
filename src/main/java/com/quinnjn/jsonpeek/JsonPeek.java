package com.quinnjn.jsonpeek;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonPeek {

    private final JsonThing json;

    public static JsonPeek from(String jsonString) {
        return new JsonPeek(jsonString);
    }

    private JsonPeek(String jsonString){
        json = JsonThing.from(jsonString);
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
        instruction = instruction.replaceAll("\\]\\[", ".").replaceAll("\\[", "").replaceAll("\\]", "");
        return getJsonObject(instruction);
    }

    private Object getJsonObject(String instruction) {
        JsonThing currentThing = json;

        List<String> keys = new ArrayList<String>(Arrays.asList(instruction.split("\\.")));
        String lastKey = keys.remove(keys.size() -1);

        for (String key : keys) {
            int keyInt = optInt(key, -1);

            if (keyInt >= 0) {
                currentThing = currentThing.get(keyInt);
            } else {
                currentThing = currentThing.get(key);
            }
        }

        int keyInt = optInt(lastKey, -1);
        if (keyInt >= 0) {
            return currentThing.value(keyInt);
        } else {
            return currentThing.value(lastKey);
        }
    }

    private int optInt(String s, int defaultVal) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException notAnInt) {
            return defaultVal;
        }
    }
}
