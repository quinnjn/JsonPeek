package com.quinnjn.jsonpeek;

import org.json.JSONException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertNotNull;

public class JsonPeekTest {

    @Rule
    public ExpectedException constructorException = ExpectedException.none();

    @Test
    public void from_withObject_returnsJsonPeek() {
        assertNotNull(JsonPeek.from("{}"));
    }

    @Test
    public void from_withArray_returnsJsonPeek() {
        assertNotNull(JsonPeek.from("[]"));
    }

    @Test()
    public void from_throwsExceptionWhenMalformedJson() {
        constructorException.expect(JSONException.class);
        constructorException.expectMessage("Json is neither a JSONObject or JSONArray");
        JsonPeek.from("nope");
    }
}
