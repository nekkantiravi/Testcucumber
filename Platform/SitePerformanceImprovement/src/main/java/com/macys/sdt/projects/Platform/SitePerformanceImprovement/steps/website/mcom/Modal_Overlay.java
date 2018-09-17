package com.macys.sdt.projects.Platform.SitePerformanceImprovement.steps.website.mcom;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.projects.Platform.SitePerformanceImprovement.utils.config.RcHelper;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.logging.Logger;


/**
 * Created by yc04026 on 12/13/2017.
 */
public class Modal_Overlay extends StepUtils {

    private static Logger log = Logger.getLogger(Thread.currentThread().getClass().getName());
    private static int screenWidth = 0;
    private static int screenHeight = 0;
    SoftAssertions softly = new SoftAssertions();
    WebDriver driver = WebDriverManager.getWebDriver();
    JavascriptExecutor jse = (JavascriptExecutor) driver;
    Actions actions = new Actions(driver);


    @Given("^I visit the polaris app page$")
    public void I_visit_the_polaris_app_page() {

        String url = "http://11.142.10.33:8081/csg/components#profile_email";
        WebDriverManager.getWebDriver().get(url);
        Utils.threadSleep(2000, null);
        Navigate.scrollPage(0, -250);


    }

    @Then("^I should see component \"([^\"]*)\" displayed$")
    public void iShouldSeeOverlaydisplayed(String result) throws Throwable {
        Wait.forPageReady();
        String result_displayed = result.toLowerCase();
        switch (result_displayed) {
            case "modal overlay":
                Assert.assertTrue("Overlay is not displayed", Elements.elementPresent("CSG_components.overlay"));
                Thread.sleep(2000);
                break;
            case "drawer":
                Assert.assertTrue("Drawer is not displayed", Elements.findElement("CSG_components.drawer").getAttribute("tabindex").equals("0"));
                Thread.sleep(2000);
                break;
            case "loader indicator":
                Assert.assertTrue("loader indicator is not displayed", Elements.elementPresent("CSG_components.loading_indicator_image"));
                Thread.sleep(2000);
                break;
        }
    }

    @Given("^I should not see \"([^\"]*)\" once I click on close button$")
    public void iShouldSeeModelOverlayNotDisplayed(String component) throws Throwable {
        String component_type = component.toLowerCase();
        switch (component_type) {
            case "modal overlay":
                Clicks.click("CSG_components.overlay_close");
                Thread.sleep(2000);
                Assert.assertTrue("Overlay is still displaying", !Elements.anyPresent("CSG_components.overlay"));
                break;
            case "drawer":
                Clicks.click("CSG_components.drawer_close");
                Thread.sleep(2000);
                Assert.assertTrue("Drawer is still displaying", Elements.findElement("CSG_components.drawer").getAttribute("tabindex").equals("-1"));
                break;
        }
    }

    @Given("^I should not see Loading Indicator once I click on \"([^\"]*)\" button$")
    public void i_should_see_loading_indicator_not_displayed(String button) throws Throwable {
        String button_type = button.toLowerCase();
        switch (button_type) {
            case "remove loader":
                Clicks.click("CSG_components.loading_indicator_remove");
                Assert.assertTrue("Drawer is still displaying", !Elements.anyPresent("CSG_components.loading_indicator_image"));
                break;
            case "hide loader":
                Clicks.click("CSG_components.loading_indicator_hide");
                Utils.threadSleep(2000, null);
                Assert.assertTrue("Drawer is still displaying", Elements.findElement("CSG_components.loader_container").getAttribute("aria-busy").equalsIgnoreCase("false"));
                break;
        }

    }

    @Then("^I should see \"([^\"]*)\" component$")
    public void i_should_see_CSG_component(String component) {
        String componentType = component.toLowerCase();
        Wait.forPageReady();
        switch (componentType) {
            case "modal overlay":
                Wait.untilElementPresent("CSG_components.modal_overlay");
                new Actions(driver).moveToElement(Elements.findElement("CSG_components.modal_overlay")).perform();
                Utils.threadSleep(2000, null);
                Assert.assertTrue("Modal Overlay component is not displayed", Elements.elementPresent("CSG_components.modal_overlay"));
                break;
            case "Accordion":
                Wait.untilElementPresent("CSG_components.accordion_component");
                new Actions(driver).moveToElement(Elements.findElement("CSG_components.accordion_component")).perform();
                Utils.threadSleep(2000, null);
                Assert.assertTrue("Accordion component is not displayed", Elements.elementPresent("CSG_components.accordion_component"));
                break;
            case "tabs":
                Wait.untilElementPresent("CSG_components.tabs_component");
                new Actions(driver).moveToElement(Elements.findElement("CSG_components.tabs_component")).perform();
                Utils.threadSleep(2000, null);
                Assert.assertTrue("Tabs component is not displaying", Elements.elementPresent("CSG_components.tabs_component"));
                break;
            case "loading indicator":
                Wait.untilElementPresent("CSG_components.loading_indicator_component");
                new Actions(driver).moveToElement(Elements.findElement("CSG_components.loading_indicator_component")).perform();
                Utils.threadSleep(2000, null);
                Assert.assertTrue("Loading indicator component is not displaying", Elements.elementPresent("CSG_components.loading_indicator_component"));
                break;
            case "form validation":
                Wait.untilElementPresent("CSG_components.form_validation_component");
                new Actions(driver).moveToElement(Elements.findElement("CSG_components.form_validation_component")).perform();
                Utils.threadSleep(2000, null);
                Assert.assertTrue("Forms validation component is not displaying", Elements.elementPresent("CSG_components.form_validation_component"));
                Clicks.click("CSG_components.form_cancel_btn");
                break;
        }
    }

    @When("^I click on \"([^\"]*)\" button$")
    public void i_click_on_button(String button) {
        String buttonType = button.toLowerCase();
        switch (buttonType) {

            case "open modal overlay":
                Clicks.click("CSG_components.overlay_btn");
                break;
            case "show drawer":
                Clicks.click("CSG_components.drawer_show_btn");
                break;
            case "add loader":
                Clicks.click("CSG_components.loading_indicator_add");
                Utils.threadSleep(2000, null);
                break;
            case "cancel":
                Clicks.click("CSG_components.form_cancel_btn");
                Utils.threadSleep(2000, null);
                break;
        }
    }

    @When("^I validate \"([^\"]*)\" selected and body text \"([^\"]*)\" is displaying correctly$")
    public void i_validate_selected_tab(String tab, String message)throws Throwable{
        /*List<WebElement> tabList = Elements.findElements("CSG_components.tabs");
        for(WebElement ele: tabList){
            Clicks.click(ele.findElement(By.className("tab")));
            Utils.threadSleep(2000, null);
           Boolean selectedTab =  Elements.findElement("CSG_components.selected_tab_text").getText().equalsIgnoreCase(message);
            Assert.assertTrue("Text is not displaying for selected tab1", selectedTab);
        }*/
        String selectedTab = tab.toLowerCase();
        switch (selectedTab) {

            case "tab1":

                Clicks.click("CSG_components.tab1");
                Thread.sleep(1000);
                softly.assertThat(Elements.elementPresent("CSG_components.tab1")).as("hello1").isEqualTo(true);
                String bodyText_tab1 = Elements.findElement("CSG_components.selected_tab_text").getText();
                Assert.assertTrue("Text is not displaying for selected tab1", bodyText_tab1.equalsIgnoreCase(message));
                break;

            case "tab2":
                Clicks.click("CSG_components.tab2");
                Thread.sleep(1000);
                softly.assertThat(Elements.elementPresent("CSG_components.tab2")).as("hello2").isEqualTo(true);
                String bodyText_tab2 = Elements.findElement("CSG_components.selected_tab_text").getText();
                Assert.assertTrue("Text is not displaying for selected tab2" + bodyText_tab2, bodyText_tab2.equalsIgnoreCase(message));
                break;
        }
    }

    @And("^I should be able to scroll the page$")
    public void i_should_able_scroll_page() throws Throwable {
        Navigate.scrollPage(0, -250);
        Navigate.scrollPage(250, 0);
    }

    @And("^I resize the window to \"([^\"]*)\" type$")
    public void i_resize_the_window(String device) throws Throwable {

        log.info(String.format("Original dimension (width, height):  " + WebDriverManager.getWebDriver().manage().window().getSize()));
        Dimension dimensions = WebDriverManager.getWebDriver().manage().window().getSize();
        WebElement html = WebDriverManager.getWebDriver().findElement(By.tagName("html"));
        int originalWidth = Integer.parseInt(html.getAttribute("clientWidth"));
        int originalHeight = Integer.parseInt(html.getAttribute("clientHeight"));
        switch (device) {
            case ("desktop"): {
                screenWidth = 1400;
                screenHeight = originalHeight;
                break;
            }
            case ("mobile"): {
                screenWidth = 400;
                screenHeight = 700;
                WebDriverManager.getWebDriver().manage().window().setSize(new Dimension(
                        (dimensions.width - originalWidth) + screenWidth,
                        (dimensions.height - originalHeight) + screenHeight
                ));

                break;
            }
            case ("tablet"): {
                screenWidth = 870;
                screenHeight = 530;
                WebDriverManager.getWebDriver().manage().window().setSize(new Dimension(
                        (dimensions.width - originalWidth) + screenWidth,
                        (dimensions.height - originalHeight) + screenHeight
                ));

                break;
            }
        }

        html = WebDriverManager.getWebDriver().findElement(By.tagName("html"));
        Assert.assertTrue(Integer.parseInt(html.getAttribute("clientWidth")) <= screenWidth);
        Assert.assertTrue(Integer.parseInt(html.getAttribute("clientHeight")) <= screenHeight);
        log.info(String.format("Dimensions modified from width=" + originalWidth + " & height=" + originalHeight + " to width=" +
                Integer.parseInt(html.getAttribute("clientWidth")) + " & height=" + Integer.parseInt(html.getAttribute("clientHeight"))));
        Thread.sleep(2000);
    }

    @And("^I should not able to scroll the page$")
    public void i_should_not_scroll_page() {


        //Check If horizontal scroll Is present or not.
        // Boolean b1 = (Boolean) jse.executeScript("return document.documentElement.scrollWidth>document.documentElement.clientWidth;");
        //Check If vertical scroll Is present or not.
        Boolean b2 = (Boolean) jse.executeScript("return document.documentElement.scrollHeight>document.documentElement.clientHeight;");
        Assert.assertTrue("Page is scrollable", b2 == true);
    }

    @And("^I should see one row is \"([^\"]*)\"$")
    public void i_verify_default_accordions(String status) {
        if (status.equalsIgnoreCase("collapsed")) {
            Assert.assertTrue("first accordion is not collapsed", Elements.anyPresent("CSG_components.accordion_collapse"));
        } else {
            Assert.assertTrue("second accordion is not collapsed", Elements.anyPresent("CSG_components.accordian_expanded"));
        }
    }

    @When("^I click on accordion \"([^\"]*)\" button$")
    public void i_click_accordion(String accordion_type) {
        if (accordion_type.equalsIgnoreCase("plus")) {
            Boolean row_expanded = Elements.anyPresent("CSG_components.accordian_expanded");
            if (row_expanded) {
                Clicks.click("CSG_components.accordian_expanded");
            }

            Clicks.click("CSG_components.accordion_collapse");
        } else {
            Boolean row_expanded = Elements.anyPresent("CSG_components.accordian_expanded");
            if (row_expanded) {
                Clicks.click("CSG_components.accordian_expanded");
            }
        }
    }

    @Then("^I \"([^\"]*)\" see body text present$")
    public void i_should_see_body_text_present(String displayed) {
        if (displayed.equalsIgnoreCase("should")) {
            Assert.assertTrue("row is not expanded", Elements.findElement("CSG_components.accordian_body").getText().equalsIgnoreCase("This is the body conetnt"));
        } else
            Assert.assertTrue("row is not collapsed", !Elements.findElement("CSG_components.accordian_body").getText().equalsIgnoreCase("This is the body conetnt"));
    }

    @And("^I click into \"([^\"]*)\" field and focus should be in the field$")
    public void field_validation(String field) throws Throwable {

        String field_name = field.toLowerCase();
        switch (field_name) {
            case "username":
                WebElement userName = Elements.findElement("CSG_components.field_username");
                Clicks.click(userName);
                actions.moveToElement(userName);
                actions.click();
                actions.sendKeys("");
                actions.build().perform();
                Thread.sleep(2000);
                break;
            case "email address":
                WebElement email = Elements.findElement("CSG_components.field_email");
                Clicks.click(email);
                actions.moveToElement(email);
                actions.click();
                actions.sendKeys("");
                actions.build().perform();
                Thread.sleep(2000);
                Clicks.click("CSG_components.field_password");
                break;
            case "password":
                WebElement password = Elements.findElement("CSG_components.field_password");
                actions.moveToElement(password);
                actions.click();
                actions.sendKeys("");
                actions.build().perform();
                Clicks.click("CSG_components.field_conPassword");
                Thread.sleep(2000);
                break;
            case "confirm password":
                WebElement con_password = Elements.findElement("CSG_components.field_conPassword");
                actions.moveToElement(con_password);
                actions.click();
                actions.sendKeys("");
                actions.build().perform();
                Thread.sleep(2000);
                break;

        }
    }

    @Then("^I \"([^\"]*)\" see \"([^\"]*)\" validation message \"([^\"]*)\" displaying$")
    public void i_validate_error_message(String field_type, String actual_error_message, String error_displaying) {
        switch (field_type) {
            case "email":
                if(error_displaying.equalsIgnoreCase("should")) {
                    Assert.assertTrue("Error message is not correct", Elements.findElement("CSG_components.email_error_message").getText().equalsIgnoreCase(actual_error_message));
                }
                else{
                    Assert.assertTrue("Error message should not be displaying", !Elements.anyPresent("CSG_components.email_error_message"));
                }
                break;
            case "password":
                if(error_displaying.equalsIgnoreCase("should")) {
                Assert.assertTrue("Error message is not correct", Elements.findElement("CSG_components.password_error_message").getText().equalsIgnoreCase(actual_error_message));
                }
                else{
                    Assert.assertTrue("Error message should not be displaying", !Elements.anyPresent("CSG_components.email_error_message"));
                }
                break;
        }
    }

    @And("^I \"([^\"]*)\" see a \"([^\"]*)\" icon displayed in the field$")
    public void i_validated_visual_icon(String icon_present, String visual_type) {
        if (visual_type.equalsIgnoreCase("error")) {
            if (icon_present.equalsIgnoreCase("should"))
                Assert.assertTrue("Warning icon is not present", Elements.anyPresent("CSG_components.warning_icon"));
            else
                Assert.assertTrue("Warning icon is still present", !Elements.anyPresent("CSG_components.warning_icon"));
        } else {
            if (icon_present.equalsIgnoreCase("should")) {
                Assert.assertTrue("validation icon is not present", Elements.anyPresent("CSG_components.validation_icon"));
            }
            else {
                Assert.assertTrue("Validation icon is still present", !Elements.anyPresent("CSG_components.validation_icon"));
            }
        }
    }

    @And("^I enter special character in \"([^\"]*)\" form text field$")
    public void i_enter_special_character(String input) {
        String input_type = input.toLowerCase();
        switch (input_type) {
            case "username":
                WebElement userName = Elements.findElement("CSG_components.field_username");
                Clicks.click(userName);
                actions.moveToElement(userName);
                actions.click();
                actions.sendKeys("^&&^^%%$#$#23445%^^");
                actions.build().perform();
                break;
            case "email":
                WebElement email = Elements.findElement("CSG_components.field_email");
                Clicks.click(email);
                actions.moveToElement(email);
                actions.click();
                actions.sendKeys("^&&^^%%$#$#23445%^^");
                actions.build().perform();
                Clicks.click("CSG_components.field_password");
                break;
            case "password":
                WebElement password = Elements.findElement("CSG_components.field_password");
                actions.moveToElement(password);
                actions.click();
                actions.sendKeys("^&&^^%%$#$#23445%^^");
                actions.build().perform();
                Clicks.click("CSG_components.field_conPassword");
                break;
            case "confirm password":
                WebElement con_password = Elements.findElement("CSG_components.field_conPassword");
                actions.moveToElement(con_password);
                actions.click();
                actions.sendKeys("^&&^^%%$#$#23445%^^");
                actions.build().perform();
                break;
        }
    }

    @And("^I enter valid input in \"([^\"]*)\" form text field$")
    public void i_enter_valid_input(String input) {
        String input_type = input.toLowerCase();
        switch (input_type) {
            case "username":
                WebElement userName = Elements.findElement("CSG_components.field_username");
                Clicks.click(userName);
                actions.moveToElement(userName);
                actions.click();
                actions.sendKeys("qe");
                actions.build().perform();
                break;
            case "email":
                WebElement email = Elements.findElement("CSG_components.field_email");
                Clicks.click(email);
                actions.moveToElement(email);
                actions.click();
                actions.sendKeys("qe@macys.com");
                actions.build().perform();
                Clicks.click("CSG_components.field_password");
                break;
            case "password":
                WebElement password = Elements.findElement("CSG_components.field_password");
                actions.moveToElement(password);
                actions.click();
                actions.sendKeys("macys123");
                actions.build().perform();
                Clicks.click("CSG_components.field_conPassword");
                break;
            case "confirm password":
                WebElement con_password = Elements.findElement("CSG_components.field_conPassword");
                actions.moveToElement(con_password);
                actions.click();
                actions.sendKeys("macys123");
                actions.build().perform();
                break;
        }
    }

}
