package com.github.timm.cucumber.generate.filter;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is intended to encapsulate the currentGroup-checking behaviour
 * controlled by the GroupFilter configuration. By default
 * totalGroups: 0 and currentGroup: 1 is used, meaning that all values
 * are mapped to the same group.
 *
 */
public class GroupFilter {

    public static final String TOTAL = "totalGroups";
    public static final String CURRENT = "currentGroup";
    private final Map<String, Integer> config;

    /**
     * Constructs the filter using a map.
     * @param config the currentGroup and totalGroups to filter by
     */
    public GroupFilter(Map<String, Integer> config) {
        this.config = config == null ? new HashMap<>() : config;
    }

    /**
     * returns true if the submitted value is congruent mod n.
     *
     * @param fileCounter the value to check.
     * @return whether it is congruent according to the configuration.
     */
    public boolean isValid(int fileCounter) {
        return fileCounter % currentGroup() == totalGroups();
    }

    private int currentGroup() {
        return this.config.getOrDefault(TOTAL, 1);
    }

    private int totalGroups() {
        return config.getOrDefault(CURRENT, 0);
    }

}
