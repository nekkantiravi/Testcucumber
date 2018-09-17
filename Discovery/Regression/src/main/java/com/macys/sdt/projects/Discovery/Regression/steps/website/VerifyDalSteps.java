package com.macys.sdt.projects.Discovery.Regression.steps.website;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalTime;

/**
 * Created by yh03402 on 5/31/2017.
 */
public class VerifyDalSteps extends StepUtils {
    private String debugValue = LocalTime.now().toString().replace(":", "").replace(".", "");
    private static final Logger log = LoggerFactory.getLogger(VerifyDalSteps.class);

    @Then("^I verify that page is served from \"([^\"]*)\" server$")
    public void iVerifyThatPageIsServedFromServer(String siteName) throws Throwable {
        Wait.isPageLoaded();
        if (!onPage("registry_home")) {
            try {
                log.info("Checking Environment Details");
                String siteNameDisplayed = WebDriverManager.getWebDriver().getPageSource().split("<site>")[1].split("</site>")[0];
                log.info("Page served from: " + siteNameDisplayed + " data center");
                if (!(WebDriverManager.getWebDriver().getPageSource().contains(siteName) || WebDriverManager.getCurrentUrl().contains("debug"))) {
                    String value = WebDriverManager.getCurrentUrl().contains("?") ? "&debug=" : "?&debug=";
                    Navigate.visit(WebDriverManager.getCurrentUrl() + value + debugValue);
                }
                Assert.assertTrue("ERROR - APP: Page is not served from " + siteName + " data center",
                        siteNameDisplayed.contains(siteName));
            } catch (IndexOutOfBoundsException exe) {
                log.info("ERROR: Environment detail unavailable" + exe);
                throw new Exception("Environment details unavailable");
            }
        } else {
            log.info("On Registry Home Page");
            log.info("Skipping environment details step");
        }
    }

    @When("^I select quick view of random product for mode$")
    public void i_select_quick_view_of_random_product_for_mode() throws Throwable {

        WebElement thumbnail = Elements.getRandomElement("search_result.product_thumbnail_wrapper");
        Clicks.hoverForSelection(thumbnail.findElement(By.xpath("//*[contains(@class,'productThumbnail')]")));
        if (Elements.elementPresent("search_result.product_thumbnail_quickview"))
            Clicks.click("search_result.product_thumbnail_quickview");

    }

}


