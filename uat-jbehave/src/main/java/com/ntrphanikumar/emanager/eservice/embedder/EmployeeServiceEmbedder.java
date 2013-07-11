package com.ntrphanikumar.emanager.eservice.embedder;

import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;
import static org.jbehave.core.reporters.Format.CONSOLE;
import static org.jbehave.core.reporters.Format.HTML;
import static org.jbehave.core.reporters.Format.TXT;
import static org.jbehave.core.reporters.Format.XML;

import java.text.SimpleDateFormat;
import java.util.List;

import org.jbehave.core.InjectableEmbedder;
import org.jbehave.core.annotations.Configure;
import org.jbehave.core.annotations.UsingEmbedder;
import org.jbehave.core.annotations.spring.UsingSpring;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.embedder.StoryControls;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.spring.SpringAnnotatedEmbedderRunner;
import org.jbehave.core.parsers.RegexPrefixCapturingPatternParser;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.ParameterConverters.DateConverter;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.ntrphanikumar.emanager.eservice.embedder.EmployeeServiceEmbedder.MyDateConverter;
import com.ntrphanikumar.emanager.eservice.embedder.EmployeeServiceEmbedder.MyRegexPrefixCapturingPatternParser;
import com.ntrphanikumar.emanager.eservice.embedder.EmployeeServiceEmbedder.MyReportBuilder;
import com.ntrphanikumar.emanager.eservice.embedder.EmployeeServiceEmbedder.MyStoryControls;
import com.ntrphanikumar.emanager.eservice.embedder.EmployeeServiceEmbedder.MyStoryLoader;

@RunWith(SpringAnnotatedEmbedderRunner.class)
@Configure(storyControls = MyStoryControls.class, storyLoader = MyStoryLoader.class, parameterConverters = { MyDateConverter.class }, storyReporterBuilder = MyReportBuilder.class, stepPatternParser = MyRegexPrefixCapturingPatternParser.class)
@UsingEmbedder(embedder = Embedder.class, generateViewAfterStories = true, ignoreFailureInStories = true, ignoreFailureInView = true, verboseFailures = true, storyTimeoutInSecs = 100, threads = 1, metaFilters = "-skip")
@UsingSpring(resources = { "spring/restclientservices.xml", "eservice/spring/steps.xml" })
public class EmployeeServiceEmbedder extends InjectableEmbedder {
    @Test
    public void run() {
        List<String> storyPaths = new StoryFinder().findPaths(
                codeLocationFromClass(this.getClass()), "eservice/stories/*.story", "");
        injectedEmbedder().runStoriesAsPaths(storyPaths);
    }

    public static class MyStoryControls extends StoryControls {
        public MyStoryControls() {
            doDryRun(false);
            doSkipScenariosAfterFailure(false);
        }
    }

    public static class MyStoryLoader extends LoadFromClasspath {
        public MyStoryLoader() {
            super(EmployeeServiceEmbedder.class.getClassLoader());
        }
    }

    public static class MyRegexPrefixCapturingPatternParser extends
            RegexPrefixCapturingPatternParser {
        public MyRegexPrefixCapturingPatternParser() {
            super("%");
        }
    }

    public static class MyDateConverter extends DateConverter {
        public MyDateConverter() {
            super(new SimpleDateFormat("dd-MM-yyyy"));
        }
    }

    public static class MyReportBuilder extends StoryReporterBuilder {
        public MyReportBuilder() {
            this.withFormats(CONSOLE, TXT, HTML, XML).withDefaultFormats();
        }
    }
}