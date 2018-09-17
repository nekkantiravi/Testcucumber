package com.macys.sdt.projects.Discovery.Regression.steps.website;

import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.rest.utils.RESTOperations;
import com.macys.sdt.shared.actions.website.mcom.pages.home.Home;
import com.macys.sdt.shared.actions.website.mcom.pages.shop_and_browse.CategorySplash;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by YH03402 on 5/21/2017.
 */
public class TopNavFlyoutSteps extends StepUtils {

    CategorySplash categorySplash = new CategorySplash();

    private static final Logger logger = LoggerFactory.getLogger(TopNavFlyoutSteps.class);

/*
* Navigate to cat Splash page
* */

    @When("^I navigate to category splash page$")
    public void i_navigate_to_category_splash_page() throws Throwable {
        Home home=new Home();
        home.selectRandomCategory();
    }

    @And("^I validate all special character links in all flyouts$")
    public void iValidateAllSpecialCharacterLinksInAllFlyouts() {
        List<WebElement> allLinks = Elements.findElements("category_menu.all_links");
        String linkHref = null;
        for(WebElement link : allLinks) {
            linkHref = link.getAttribute("href");
            Pattern specialCharacters = Pattern.compile("[%&$]");
            Matcher m = specialCharacters.matcher(link.getText());
            if (m.find()) {
                if (!linkHref.contains("http")) {
                    linkHref = RunConfig.url + linkHref;
                }
                //To avoid URISyntax exception, replacing 100% to 100%25
                if(link.getText().contains("%") && !linkHref.contains("%25")) {
                linkHref = linkHref.replace("%","%25");
                }
                int statusCode = RESTOperations.doGET(linkHref, null).getStatus();
                logger.info("Special character Link::" + linkHref + ":: Response Code ::" + statusCode);
                Assert.assertTrue("Special character Link::" + linkHref + " linkText::" + link.getText() + " returned " + statusCode + " on GET call",
                        statusCode == 200 || statusCode == 302 || statusCode == 301);
            }
        }
    }
}
