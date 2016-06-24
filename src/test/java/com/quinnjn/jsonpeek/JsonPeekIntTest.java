package com.quinnjn.jsonpeek;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JsonPeekIntTest {

    @Test
    public void getInt_whenTopLevelObject_returnsString() {
        String jsonString = "{\"kString\":1}";
        int expected = 1;

        int actual = JsonPeek.from(jsonString).getInt("kString");
        assertEquals(expected, actual);
    }

    @Test
    public void getInt_whenMultiNestedObject_returnsString() {
        String jsonString = "{\"top\":{\"mid\":{\"kString\":1}}}";
        int expected = 1;

        int actual = JsonPeek.from(jsonString).getInt("top.mid.kString");
        assertEquals(expected, actual);
    }

    @Test
    public void getInt_whenArray_returnsString() {
        String jsonString = "[1]";
        int expected = 1;

        int actual = JsonPeek.from(jsonString).getInt("[0]");
        assertEquals(expected, actual);
    }

    @Test
    public void getInt_whenMultiNestedArray_returnsString() {
        String jsonString = "[[[\"false\",2]]]";
        int expected = 2;

        int actual = JsonPeek.from(jsonString).getInt("[0][0][1]");
        assertEquals(expected, actual);
    }

    @Test
    public void getInt_whenObjectNestedArray_returnsString() {
        String jsonString = "{\"a\":[\"a\",3,\"c\"]}";
        int expected = 3;

        int actual = JsonPeek.from(jsonString).getInt("a[1]");
        assertEquals(expected, actual);
    }

    @Test
    public void getInt_whenRealJson_returnsString() {
        String jsonString = "{\"widget\": {\n" +
                "  \"debug\": \"on\",\n" +
                "  \"window\": {\n" +
                "    \"title\": \"Sample Konfabulator Widget\",\n" +
                "    \"name\": \"main_window\",\n" +
                "    \"width\": 500,\n" +
                "    \"height\": 500\n" +
                "  },\n" +
                "  \"image\": {\n" +
                "    \"src\": \"Images/Sun.png\",\n" +
                "    \"name\": \"sun1\",\n" +
                "    \"hOffset\": 250,\n" +
                "    \"vOffset\": 250,\n" +
                "    \"alignment\": \"center\"\n" +
                "  },\n" +
                "  \"text\": {\n" +
                "    \"data\": \"Click Here\",\n" +
                "    \"size\": 36,\n" +
                "    \"style\": \"bold\",\n" +
                "    \"name\": \"text1\",\n" +
                "    \"hOffset\": 250,\n" +
                "    \"vOffset\": 100,\n" +
                "    \"alignment\": \"center\",\n" +
                "    \"onMouseUp\": \"sun1.opacity = (sun1.opacity / 100) * 90;\"\n" +
                "  }\n" +
                "}}    ";
        int expected = 250;

        int actual = JsonPeek.from(jsonString).getInt("widget.text.hOffset");
        assertEquals(expected, actual);
    }

}
