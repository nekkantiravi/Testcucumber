package com.macys.sdt.projects.Marketing.UFT.steps.MEW;

import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.shared.actions.MEW.panels.GlobalNav;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;

public class PageNavigation extends StepUtils {


    /**
     * naviaget to plenti sign in page
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I navigate to plenti page using mobile website$")
    public void naviagteToPlentiSignInPage() throws Throwable {
        Navigate.visit(RunConfig.url + "/account/signin?program=usl ");
    }
}
