package com.macys.sdt.projects.Selection.PROS.steps.website.mcom;

import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.projects.Selection.PDP.steps.website.mcom.StepDefinitionsPDP;
import com.macys.sdt.projects.Selection.PROS.page.PdpPage;
import com.macys.sdt.projects.Selection.PROS.util.ProsStep;
import com.macys.sdt.shared.steps.website.PageNavigation;
import com.macys.sdt.shared.utils.*;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;

public class PdpSteps extends ProsStep {

    private static final Logger logger = LoggerFactory.getLogger(PdpSteps.class);
    private static SoftAssertions softly = new SoftAssertions();

    private void navigatePdp(String user, String panel, String _mode, Product.Type _prodType) throws Throwable {

        String mode = StringUtils.trimToEmpty(_mode).toLowerCase();
        RunConfig runConfig = RunConfig.getInstance();

        if (Product.Type.Member == _prodType && runConfig.hasPid(Product.Type.Member)) {

            PageNavigation.NavigateMode navMode = mode.equalsIgnoreCase(PageNavigation.NavigateMode.Registry.toString())?
                PageNavigation.NavigateMode.Registry: PageNavigation.NavigateMode.Site;

            PdpPage pdpPage = new PdpPage(runConfig.getMemberPid());

            pdpPage.navigate(navMode);

        } else if (Product.Type.Master == _prodType && runConfig.hasPid(Product.Type.Master)) {

            PageNavigation.NavigateMode navMode = mode.equalsIgnoreCase(PageNavigation.NavigateMode.Registry.toString())?
                PageNavigation.NavigateMode.Registry: PageNavigation.NavigateMode.Site;

            PdpPage pdpPage = new PdpPage(runConfig.getMasterPid());

            pdpPage.navigate(navMode);
        } else {
            switch (mode) {
                case "site":
                    CommonUtils.navigateToRandomProduct("orderable and available", "master_product");
                    break;
                case "registry":
                    CommonUtils.navigateToRandomProduct("orderable and available and registrable", "master_product");
                    break;
                case "iship":
                    CommonUtils.navigateToRandomProduct("orderable and available and iship_eligible", "master_product");
                    break;
            }
        }
    }

    @When("^I select a \"([^\"]*)\" product and navigate to PDP$")
    public void I_select_the_product_and_navigate_to_the_PDP(String _prodType) throws Throwable {

        Product.Type prodType = Product.Type.valueOf(StringUtils.capitalize(StringUtils.trimToEmpty(_prodType)));

        if(RunConfig.getInstance().hasPid(prodType)) {
            navigatePdp(null, null, testContext.getCurrentMode().toString(), prodType);
        }
        else {
            new StepDefinitionsPDP().I_select_eligible_nonEligible_products_and_navigate_to_PDP(prodType.toString(), "PROS");
        }
    }

    @When("^I (pros user) navigate PDP page that has recommendations in \"([^\"]*)\" panel in \"([^\"]*)\" mode$")
    public void i_navigate_PDP_page_that_has_recommendations_in_panel_in_mode(String user, String panel, String mode) throws Throwable {
        navigatePdp(user, panel, mode, Product.Type.Member);
    }

    @Then("^I should see recommendation products in vertical panel$")
    public void i_should_see_recommendation_products_in_vertical_panel() throws Throwable {
        Assert.assertFalse("Product list is Empty",
            Elements.findElements("pdp.vertical_recommendations").isEmpty());
    }

    @Then("^I should be navigated to the corresponding PDP page from recommendations section$")
    public void i_should_be_navigated_to_the_corresponding_PDP_page() throws Throwable {

        List<WebElement> recommendations = Elements.findElements("pdp.recommendation_products");
        Random number= new Random();
        int randomNumber=number.nextInt(3);
        String webIdRecommdendation = null;
        if(bloomingdales()) {
            webIdRecommdendation = recommendations.get(randomNumber).getAttribute("data-webid");
        }
        else{
            webIdRecommdendation = recommendations.get(randomNumber).getAttribute("id");
        }
        logger.info("web id of product in recommendation section::" +webIdRecommdendation);
        recommendations.get(randomNumber).click();
        shouldBeOnPage("product_display");
        logger.info(WebDriverManager.getCurrentUrl());
        if(bloomingdales()) {
            Elements.findElement("pdp.show_more").click();
        }
        Wait.forPageReady();
        shouldBeOnPage("product_display");
        String webdIdOnPDP = Elements.getText("pdp.web_id");
        logger.info("web id of opened product::" +webdIdOnPDP);
        logger.info("web id::"+webdIdOnPDP.split(" ")[2]);

        Assert.assertTrue("ERROR: Recommended product id not matched",
            webIdRecommdendation.equalsIgnoreCase(webdIdOnPDP.split(" ")[2]) );
    }
}