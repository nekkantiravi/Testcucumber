package com.macys.sdt.projects.Discovery.Regression.steps.website;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.rest.utils.RESTOperations;
import com.macys.sdt.projects.Discovery.Regression.actions.website.SourceInspector;
import com.macys.sdt.shared.actions.website.mcom.pages.home.Home;
import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by YC05165 on 16/06/17.
 */
public class PreviewCategory extends StepUtils {
    private static final Logger logger = LoggerFactory.getLogger(PreviewHome.class);

@Then("^I should see footer elements section$")
    public void i_should_see_footer_elements_section() throws Throwable{
    Wait.secondsUntilElementPresent("footer.footer_section",30);
    Assert.assertTrue("footer section is not displayed : ",
            Elements.elementPresent("footer.footer_section"));
}

    @When("^I navigate to all category pages and I verify footer section is displayed$")
    public void i_navigate_to_all_category_pages_and_I_verify_footer_section_is_dislayed(DataTable dataTable) throws Throwable {
        List<String> categories = dataTable.asList(String.class);
        for (String cat : categories) {
            logger.info("opening category::" + cat);
            Wait.forPageReady();
            new Home().selectMainCategory(cat);
                Wait.forPageReady();
                String Currentpageurl = WebDriverManager.getWebDriver().getCurrentUrl();
                logger.info("Current page url is: " + Currentpageurl);
                Assert.assertTrue("footer section is not displayed : ",
                        Elements.elementPresent("footer.footer_menu_section"));
                    logger.info("There is no footer section at cat splash page");
            }

    }

    @Then("^I verify the response code for cat browse page$")
    public boolean i_verify_the_response_code_for_cat_browse_page() throws Throwable {
        String src = WebDriverManager.getCurrentUrl();
        logger.info("SRC is ::" + src);
        int responseCode = RESTOperations.doGET(src, null).getStatus();
        if (responseCode != 200) {
            logger.info("Got Invalid Response Code:" + responseCode);
            return false;
        }
        logger.info("Got Valid responseCode: " + responseCode);
        return true;
    }

    @Then("^I verify the pop up links for \"([^\"]*)\" Page$")
    public void i_verify_the_pop_up_links_for_page(String pageType) throws Throwable {
        Wait.forPageReady();
        int issue=0;
        int noOfPopUps = Elements.findElements("preview_category."+pageType.toLowerCase()+"_pop_up_links").size();
        logger.info("Total number of Pop Ups in the page: " + noOfPopUps);
        for (int i = 0; i < noOfPopUps; i++) {
            try {
                String parentWindow = WebDriverManager.getWebDriver().getWindowHandle();
                Clicks.click(Elements.findElements("preview_category."+pageType.toLowerCase()+"_pop_up_links").get(i));
                Thread.sleep(4000);
                Set<String> handles = WebDriverManager.getWebDriver().getWindowHandles();
                for (String windowHandle : handles) {
                    if (!windowHandle.equals(parentWindow)) {
                        WebDriverManager.getWebDriver().switchTo().window(windowHandle);//switch to child window
                        String url = WebDriverManager.getCurrentUrl();
                        if(WebDriverManager.getWebDriver().getTitle().equalsIgnoreCase("") || !Wait.secondsUntilElementPresent("preview_category.pop_up_heading",20)){
                            logger.info("Pop Up "+url+" is blank");
                            issue++;
                        }
                        else {
                            logger.info(Elements.findElement("preview_category.pop_up_heading").getText());
                        }
                        WebDriverManager.getWebDriver().close(); //closing child window
                        WebDriverManager.getWebDriver().switchTo().window(parentWindow); //cntrl to parent window
                    }
                }
                Wait.forPageReady();
            }
            catch(Exception ee)
            {
                logger.info("Pop Up "+WebDriverManager.getCurrentUrl()+" has issues"+ee.getMessage());
                issue++;
            }
        }
        Assert.assertFalse("Page has "+issue+" pop up issues ",issue>0);
    }

    @Then("^I verify no pages under \"(.*)\" are blank$")
    public void i_verify_no_pages_under_are_blank(String fob) throws Throwable {
        SourceInspector inspector=new SourceInspector();
        String error_string=null;
       // String fob;
        //fob=macys()?fob_mcom :fob_bcom;
        logger.info("FOB Name::"+fob);
        error_string=inspector.validate_category_by_name(fob);
        Assert.assertTrue("Test failed Reasons ::\n"+error_string,error_string==null);
    }
}
