package com.github.timm.cucumber.generate.filter;

import static com.github.timm.cucumber.generate.filter.OfflineParallelExecutionFilter.MODULO;
import static com.github.timm.cucumber.generate.filter.OfflineParallelExecutionFilter.REMAINDER;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class OfflineParallelExecutionFilterTest {

    private Map<String, Integer> config = new HashMap<>();

    @Test
    public void shouldAlwaysBeValidByDefault() {
        IntStream.range(0, 100)
            .forEach(i -> assertTrue(new OfflineParallelExecutionFilter(config).isValid(i)));
    }

    @Test
    public void shouldBeValid() {
        config.put(MODULO, 5);
        config.put(REMAINDER, 2);
        assertTrue(new OfflineParallelExecutionFilter(config).isValid(27));
    }

    @Test
    public void shouldBeInvalid() {
        config.put(MODULO, 7);
        config.put(REMAINDER, 4);
        assertFalse(new OfflineParallelExecutionFilter(config).isValid(27));
    }

}