package com.macys.sdt.projects.Stores.Foundations.steps.website;

import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.projects.Stores.Foundations.utils.GalenCheckLayout;
import cucumber.api.java.en.Then;

import java.util.List;


public class FoundationsUISteps extends StepUtils {

    private GalenCheckLayout test = new GalenCheckLayout();

    @Then("^I should see the grid padding examples on the page as \"([^\"]*)\"$")
    public void iShouldSeeTheGridPaddingExamplesOnThePageAs(String arg0) throws Throwable {
        List<String> tags = null;

        String specPath = "Stores/Foundations/features/lib/sdu_399.gspec";
        test.load(RunConfig.url);
        test.checkLayout(specPath, tags);
    }
}


