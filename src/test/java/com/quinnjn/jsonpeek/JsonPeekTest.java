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

    @Test
    public void getString_whenObjectNestedArray_returnsString() {
        String jsonString = "{\"a\":[\"a\",\"b\",\"c\"]}";
        String expected = "b";

        String actual = JsonPeek.from(jsonString).getString("a[1]");
        assertEquals(expected, actual);
    }

    @Test
    public void getString_whenRealJson_returnsString() {
        String jsonString = "{\"menu\": {\n" +
                "  \"header\": \"SVG Viewer\",\n" +
                "  \"items\": [\n" +
                "    {\"id\": \"Open\"},\n" +
                "    {\"id\": \"OpenNew\", \"label\": \"Open New\"},\n" +
                "    null,\n" +
                "    {\"id\": \"ZoomIn\", \"label\": \"Zoom In\"},\n" +
                "    {\"id\": \"ZoomOut\", \"label\": \"Zoom Out\"},\n" +
                "    {\"id\": \"OriginalView\", \"label\": \"Original View\"},\n" +
                "    null,\n" +
                "    {\"id\": \"Quality\"},\n" +
                "    {\"id\": \"Pause\"},\n" +
                "    {\"id\": \"Mute\"},\n" +
                "    null,\n" +
                "    {\"id\": \"Find\", \"label\": \"Find...\"},\n" +
                "    {\"id\": \"FindAgain\", \"label\": \"Find Again\"},\n" +
                "    {\"id\": \"Copy\"},\n" +
                "    {\"id\": \"CopyAgain\", \"label\": \"Copy Again\"},\n" +
                "    {\"id\": \"CopySVG\", \"label\": \"Copy SVG\"},\n" +
                "    {\"id\": \"ViewSVG\", \"label\": \"View SVG\"},\n" +
                "    {\"id\": \"ViewSource\", \"label\": \"View Source\"},\n" +
                "    {\"id\": \"SaveAs\", \"label\": \"Save As\"},\n" +
                "    null,\n" +
                "    {\"id\": \"Help\"},\n" +
                "    {\"id\": \"About\", \"label\": \"About Adobe CVG Viewer...\"}\n" +
                "  ]\n" +
                "}}";
        String expected = "Copy Again";

        String actual = JsonPeek.from(jsonString).getString("menu.items[14].label");
        assertEquals(expected, actual);
    }

}
