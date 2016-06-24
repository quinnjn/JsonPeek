package com.quinnjn.jsonpeek;

import org.json.JSONException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class JsonPeekTest {

    @Test
    public void from_returnsJsonPeek() {
        assertNotNull(JsonPeek.from("{}"));
    }

    @Test(expected = JSONException.class)
    public void from_throwsExceptionWhenMalformedJson() {
        JsonPeek.from("nope");
    }

    @Test
    public void getString_whenTopLevel_returnsString() {
        String jsonString = "{\"kString\":\"v\"}";
        String expected = "v";

        String actual = JsonPeek.from(jsonString).getString("kString");
        assertEquals(expected, actual);
    }

    @Test
    public void getString_whenMultiNested_returnsString() {
        String jsonString = "{\"top\":{\"mid\":{\"kString\":\"v\"}}}";
        String expected = "v";

        String actual = JsonPeek.from(jsonString).getString("top.mid.kString");
        assertEquals(expected, actual);
    }
}
