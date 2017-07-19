package com.github.timm.cucumber.generate.filter;

import com.github.timm.cucumber.generate.ScenarioAndLocation;
import gherkin.ast.Examples;
import gherkin.ast.Feature;
import gherkin.ast.ScenarioDefinition;
import gherkin.ast.ScenarioOutline;
import gherkin.ast.TableRow;
import gherkin.ast.Tag;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This class is intended to encapsulate the modulo-checking behaviour
 * controlled by the OfflineParallelExecutionFilter configuration. By default
 * a remainder of 0 and a modulo of 1 is used, meaning that all values are
 * congruent.
 *
 */
public class OfflineParallelExecutionFilter {

    public static final String MODULO = "modulo";
    public static final String REMAINDER = "remainder";
    private final Map<String, Integer> config;

    /**
     * Constructs the filter using a map.
     * @param config the modulo and remainder to filter by
     */
    public OfflineParallelExecutionFilter(Map<String, Integer> config) {
        this.config = config == null ? new HashMap<>() : config;
    }

    /**
     * returns true if the submitted value is congruent mod n.
     *
     * @param fileCounter the value to check.
     * @return whether it is congruent according to the configuration.
     */
    public boolean isValid(int fileCounter) {
        return fileCounter % modulo() == remainder();
    }

    private int modulo() {
        return this.config.getOrDefault(MODULO, 1);
    }

    private int remainder() {
        return config.getOrDefault(REMAINDER, 0);
    }

}
