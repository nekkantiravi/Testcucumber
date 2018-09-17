package com.macys.sdt.projects.Discovery.Regression.steps.MEW.mcom;


import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import cucumber.api.java.en.And;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Admin on 8/14/2017.
 */
public class Homepage {

    private static final Logger log = LoggerFactory.getLogger(SortAndFilterBy.class);

    @And("^I should see global navigation panel$")
    public void Global_navigation_panel() throws Throwable {
        Assert.assertTrue("Global navigation penal is not visible...",Elements.findElement("home.nav_menu_list").isDisplayed());
        log.info("Global navigation panel is visible");
    }

    @And("^I again tab on the global navigation$")
    public void again_tab_on_the_global_navigation() throws Throwable {
        Clicks.click("main_left_nav.global_nav_button");
    }

    @And("^I should not see global navigation panel$")
    public void I_should_not_see_global_navigation_panel() throws Throwable {
        Assert.assertTrue("Global navigation penal is still visible...",!Elements.isElementInView(Elements.findElement("home.nav_menu_list")));
        log.info("Global navigation panel is visible");
    }
}