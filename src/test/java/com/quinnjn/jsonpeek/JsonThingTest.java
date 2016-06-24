package com.quinnjn.jsonpeek;

import org.json.JSONException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertNotNull;

public class JsonThingTest {

    @Rule
    public ExpectedException constructorException = ExpectedException.none();

    @Test
    public void from_withObject_returnsJsonThing() {
        assertNotNull(new JsonThing("{}"));
    }

    @Test
    public void from_withArray_returnsJsonThing() {
        assertNotNull(new JsonThing("[]"));
    }

    @Test()
    public void from_throwsExceptionWhenMalformedJson() {
        constructorException.expect(JSONException.class);
        constructorException.expectMessage("Json is neither a JSONObject or JSONArray");
        new JsonThing("nope");
    }
}
