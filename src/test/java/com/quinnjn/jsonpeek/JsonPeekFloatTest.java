package com.quinnjn.jsonpeek;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JsonPeekFloatTest {

    @Test
    public void getDouble_whenTopLevelObject_returnsString() {
        String jsonString = "{\"kString\":1.4}";
        double expected = 1.4;

        double actual = JsonPeek.from(jsonString).getDouble("kString");
        assertEquals(expected, actual, 0.0);
    }

    @Test
    public void getDouble_whenMultiNestedObject_returnsString() {
        String jsonString = "{\"top\":{\"mid\":{\"kString\":1.4}}}";
        double expected = 1.4;

        double actual = JsonPeek.from(jsonString).getDouble("top.mid.kString");
        assertEquals(expected, actual, 0.0);
    }

    @Test
    public void getDouble_whenArray_returnsString() {
        String jsonString = "[1.4]";
        double expected = 1.4;

        double actual = JsonPeek.from(jsonString).getDouble("[0]");
        assertEquals(expected, actual, 0.0);
    }

    @Test
    public void getDouble_whenMultiNestedArray_returnsString() {
        String jsonString = "[[[\"false\",2.4]]]";
        double expected = 2.4;

        double actual = JsonPeek.from(jsonString).getDouble("[0][0][1]");
        assertEquals(expected, actual, 0.0);
    }

    @Test
    public void getDouble_whenObjectNestedArray_returnsString() {
        String jsonString = "{\"a\":[\"a\",3.4,\"c\"]}";
        double expected = 3.4;

        double actual = JsonPeek.from(jsonString).getDouble("a[1]");
        assertEquals(expected, actual, 0.0);
    }

    @Test
    public void getDouble_whenRealJson_returnsString() {
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
                "    \"hOffset\": 250.5,\n" +
                "    \"vOffset\": 100,\n" +
                "    \"alignment\": \"center\",\n" +
                "    \"onMouseUp\": \"sun1.opacity = (sun1.opacity / 100) * 90;\"\n" +
                "  }\n" +
                "}}    ";
        double expected = 250.5;

        double actual = JsonPeek.from(jsonString).getDouble("widget.text.hOffset");
        assertEquals(expected, actual, 0.0);
    }
}
