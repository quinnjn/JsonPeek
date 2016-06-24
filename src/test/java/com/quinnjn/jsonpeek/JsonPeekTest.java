package com.quinnjn.jsonpeek;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class JsonPeekTest {

    @Test
    public void sanityCheck() {
        JsonPeek jsonPeek = new JsonPeek();
        assertNotNull(jsonPeek);
    }
}
