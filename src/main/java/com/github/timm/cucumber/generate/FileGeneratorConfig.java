package com.github.timm.cucumber.generate;

import org.apache.maven.plugin.logging.Log;

import java.io.File;
import java.util.Map;

interface FileGeneratorConfig {

    Log getLog();

    String getEncoding();

    File getCucumberOutputDir();

    boolean useTestNG();

    String getCustomVmTemplate();

    String getPackageName();

    File getProjectBasedir();

    Map<String, Integer> getGroupFilter();
}
