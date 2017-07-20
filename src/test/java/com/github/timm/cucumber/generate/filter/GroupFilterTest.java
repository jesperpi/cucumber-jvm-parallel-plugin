package com.github.timm.cucumber.generate.filter;

import static com.github.timm.cucumber.generate.filter.GroupFilter.TOTAL;
import static com.github.timm.cucumber.generate.filter.GroupFilter.CURRENT;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class GroupFilterTest {

    private Map<String, Integer> config = new HashMap<>();

    @Test
    public void shouldAlwaysBeValidByDefault() {
        IntStream.range(0, 100)
            .forEach(i -> assertTrue(new GroupFilter(config).isValid(i)));
    }

    @Test
    public void shouldBeValid() {
        config.put(TOTAL, 5);
        config.put(CURRENT, 2);
        assertTrue(new GroupFilter(config).isValid(27));
    }

    @Test
    public void shouldBeInvalid() {
        config.put(TOTAL, 7);
        config.put(CURRENT, 4);
        assertFalse(new GroupFilter(config).isValid(27));
    }

}