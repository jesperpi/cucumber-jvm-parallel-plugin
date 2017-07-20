package com.github.timm.cucumber.generate;

import static com.github.timm.cucumber.generate.Plugin.createBuildInPlugin;
import static com.github.timm.cucumber.generate.filter.GroupFilter.TOTAL;
import static com.github.timm.cucumber.generate.filter.GroupFilter.CURRENT;
import static java.util.Collections.singletonList;
import static org.fest.assertions.Assertions.assertThat;

import com.github.timm.cucumber.generate.name.ClassNamingScheme;
import com.github.timm.cucumber.generate.name.ClassNamingSchemeFactory;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.util.Collections;

public class OfflineGroupFilteringTest {

    private CucumberITGeneratorByScenario classUnderTest;
    private TestFileGeneratorConfig config;
    private File outputDirectory;

    private void setup(int total, int current) throws Exception {
        config = new TestFileGeneratorConfig()
                        .setFeaturesDirectory(
                                        new File("src/it/junit/issue_43-outline_runner/src/test/resources/features/"))
                        .setCucumberOutputDir(this.getClass());

        config.filterMap.put(TOTAL, total);
        config.filterMap.put(CURRENT, current);

        final OverriddenCucumberOptionsParameters overriddenParameters =
                new OverriddenCucumberOptionsParameters()
                        .setTags(Collections.emptyList())
                        .setGlue(singletonList("foo"))
                        .setStrict(true)
                        .setPlugins(singletonList(createBuildInPlugin("json")))
                        .setMonochrome(false);

        final ClassNamingScheme classNamingScheme =
                        new ClassNamingSchemeFactory(new InstanceCounter()).create("simple", null);

        classUnderTest = new CucumberITGeneratorByScenario(config, overriddenParameters,
                        classNamingScheme);

        outputDirectory = config.getCucumberOutputDir();
        outputDirectory.mkdirs();
        FileUtils.cleanDirectory(outputDirectory);
    }

    @Test
    public void ShouldCreateAllFiles() throws Exception {

        setup(1, 0);

        final String featureFile =
                        "src/it/junit/issue_43-outline_runner/src/test/resources/features/feature2.feature";
        final int expectedGeneratedFiles = 4;

        classUnderTest.generateCucumberITFiles(outputDirectory, singletonList(new File(featureFile)));

        assertThat(outputDirectory.listFiles()).hasSize(expectedGeneratedFiles);

    }

    @Test
    public void ShouldOnlyCreateEvenFiles() throws Exception {

        setup(2, 0);

        final String featureFile =
            "src/it/junit/issue_43-outline_runner/src/test/resources/features/feature2.feature";
        final int expectedGeneratedFiles = 2;

        classUnderTest.generateCucumberITFiles(outputDirectory, singletonList(new File(featureFile)));

        assertThat(outputDirectory.listFiles()).hasSize(expectedGeneratedFiles);

    }

}
