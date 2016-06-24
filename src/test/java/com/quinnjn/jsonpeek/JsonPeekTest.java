package com.quinnjn.jsonpeek;

import org.json.JSONException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
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

    @Test
    public void getString_whenTopLevelObject_returnsString() {
        String jsonString = "{\"kString\":\"v\"}";
        String expected = "v";

        String actual = JsonPeek.from(jsonString).getString("kString");
        assertEquals(expected, actual);
    }

    @Test
    public void getString_whenMultiNestedObject_returnsString() {
        String jsonString = "{\"top\":{\"mid\":{\"kString\":\"v\"}}}";
        String expected = "v";

        String actual = JsonPeek.from(jsonString).getString("top.mid.kString");
        assertEquals(expected, actual);
    }

    @Test
    public void getString_whenArray_returnsString() {
        String jsonString = "[\"v\"]";
        String expected = "v";

        String actual = JsonPeek.from(jsonString).getString("[0]");
        assertEquals(expected, actual);
    }

    @Test
    public void getString_whenMultiNestedArray_returnsString() {
        String jsonString = "[[[\"false\",\"v\"]]]";
        String expected = "v";

        String actual = JsonPeek.from(jsonString).getString("[0][0][1]");
        assertEquals(expected, actual);
    }

}
