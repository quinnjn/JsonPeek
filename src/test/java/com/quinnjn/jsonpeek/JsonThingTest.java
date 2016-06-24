package com.quinnjn.jsonpeek;

import org.json.JSONException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class JsonThingTest {

    @Rule
    public ExpectedException constructorException = ExpectedException.none();

    @Test
    public void from_withObject_returnsJsonThing() {
        assertNotNull(JsonThing.from("{}"));
    }

    @Test
    public void from_withArray_returnsJsonThing() {
        assertNotNull(JsonThing.from("[]"));
    }

    @Test()
    public void from_throwsExceptionWhenMalformedJson() {
        constructorException.expect(JSONException.class);
        constructorException.expectMessage("Json is neither a JSONObject or JSONArray");
        JsonThing.from(null);
    }

    @Test()
    public void get_whenObjectNestedObject_returnsThing() {
        JsonThing expected = JsonThing.from("{\"b\":\"c\"}");
        JsonThing actual = JsonThing.from("{\"a\":{\"b\":\"c\"}}").get("a");
        assertEquals(expected.toString(), actual.toString());
    }

    @Test()
    public void get_whenArrayNestedArray_returnsThing() {
        JsonThing expected = JsonThing.from("[\"a\"]");
        JsonThing actual = JsonThing.from("[[\"a\"]]").get(0);
        assertEquals(expected.toString(), actual.toString());
    }

    @Test()
    public void get_whenObjectNestedArray_returnsThing() {
        JsonThing expected = JsonThing.from("[\"b\"]");
        JsonThing actual = JsonThing.from("{\"a\":[\"b\"]}").get("a");
        assertEquals(expected.toString(), actual.toString());
    }

    @Test()
    public void get_whenArrayNestedObject_returnsThing() {
        JsonThing expected = JsonThing.from("{\"a\":\"b\"}");
        JsonThing actual = JsonThing.from("[{\"a\":\"b\"}]").get(0);
        assertEquals(expected.toString(), actual.toString());
    }
}
