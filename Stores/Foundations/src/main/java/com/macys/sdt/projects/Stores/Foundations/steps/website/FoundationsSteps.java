package com.macys.sdt.projects.Stores.Foundations.steps.website;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;


public class FoundationsSteps extends StepUtils {

    @Given("^I launch the macy's store domain CSG$")
    public void i_launch_the_macy_s_store_domain_CSG() throws Throwable {
        Navigate.visit("csg");
        shouldBeOnPage("csg");
    }

    @Then("^I should be able to view the validation module text box$")
    public void i_should_be_able_to_view_the_validation_module_text_box() throws Throwable {
        Elements.elementShouldBePresent("csg.validation_test_box");
    }

    @When("^I enter \"([^\"]*)\" in the validation module text box$")
    public void i_enter_in_the_validation_module_text_box(String input) throws Throwable {
        TextBoxes.typeTextbox("csg.validation_test_box", input);;
        //TextBoxes.typeTextNTab("csg.validation_test_box", input);
        Clicks.click("csg.validation_random_click");
        //Thread.sleep(2000);
    }

    @Then("^I should see no error message$")
    public void i_should_see_no_error_message() throws Throwable {
        Assert.assertTrue(!Elements.elementPresent("csg.validation_error_msg"));
    }

    @Then("^I should see \"([^\"]*)\" added to the text box$")
    public void i_should_see_added_to_the_text_box(String inputValue) throws Throwable {
        String input = Elements.findElement("csg.validation_test_box").getAttribute("value");
        Assert.assertEquals(inputValue, input);
    }

    @Then("^The characters are not added to the text box$")
    public void the_characters_are_not_added_to_the_text_box() throws Throwable {
        String input = Elements.findElement("csg.validation_test_box").getAttribute("value");
        Assert.assertEquals("", input);
    }

    @Then("^I should see error message$")
    public void i_should_see_error_message() throws Throwable {
        Elements.elementShouldBePresent("csg.validation_error_msg");
        String errMsg = Elements.findElement("csg.validation_error_msg").getText();
        Assert.assertEquals("This is an inline error message", errMsg);
    }

    @Then("^I should be able to view the Tap Me animation button$")
    public void i_should_be_able_to_view_the_Tap_Me_animation_button() throws Throwable {
        Elements.elementShouldBePresent("csg.validation_animation_button");
        Assert.assertTrue(!Elements.elementPresent("csg.spinner_animation"));
    }

    @When("^I click the Tap Me animation button$")
    public void i_click_the_Tap_Me_animation_button() throws Throwable {
        Clicks.click("csg.validation_animation_button");
        Thread.sleep(2000);
    }

    @Then("^I should see the spinner animation$")
    public void i_should_see_the_spinner_animation() throws Throwable {
        Assert.assertTrue(Elements.elementPresent("csg.spinner_animation"));
    }

    @When("^I click on stop button$")
    public void i_click_on_stop_button() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
    }

    @Then("^I should see the animation stop$")
    public void i_should_see_the_animation_stop() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
    }

    @Then("^I should be able to view the accordian controls$")
    public void i_should_be_able_to_view_the_accordian_controls() throws Throwable {
        Elements.elementShouldBePresent("csg.accordian_validation");
        Assert.assertTrue(!Elements.elementPresent("csg.accordian_expanded"));
    }

    @When("^I click the \"(details|reviews|availability)\" accordian menu$")
    public void i_click_the_accordian_menu(String accord_title) throws Throwable {
        switch (accord_title.toLowerCase()) {
            case "details":
                Clicks.click("csg.accordian_details");
                break;
            case "reviews":
                Clicks.click("csg.accordian_reviews");
                break;
            case "availability":
                Clicks.click("csg.accordian_availability");
                break;
        }
    }

    @Then("^I should see all (\\d+) accordian menus open$")
    public void i_should_see_all_accordian_menus_open(int arg1) throws Throwable {
        String details_attrib = Elements.getElementAttribute("csg.accordian_details_expand", "class");
        Assert.assertTrue(details_attrib.contains("expanded"));
        String reviews_attrib = Elements.getElementAttribute("csg.accordian_reviews_expand", "class");
        Assert.assertTrue(reviews_attrib.contains("expanded"));
        String availability_attrib = Elements.getElementAttribute("csg.accordian_availability_expand", "class");
        Assert.assertTrue(availability_attrib.contains("expanded"));
    }

    @Then("^I should be able to view the \"(short modal dialog|long modal dialog|modal dialog with input)\" button$")
    public void i_should_be_able_to_view_the_button(String modal_button) throws Throwable {

        switch (modal_button.toLowerCase()) {
            case "short modal dialog":
                Elements.elementShouldBePresent("csg.short_modal_dialog");
                break;
            case "long modal dialog":
                Elements.elementShouldBePresent("csg.long_modal_dialog");
                break;
            case "modal dialog with input":
                Elements.elementShouldBePresent("csg.modal_dialog_with_input");
                break;
        }
    }

    @When("^I click the \"(short modal dialog|long modal dialog|modal dialog with input)\" button$")
    public void i_click_the_button(String modal_button) throws Throwable {

        switch (modal_button.toLowerCase()) {
            case "short modal dialog":
                Clicks.click("csg.short_modal_dialog");
                break;
            case "long modal dialog":
                Clicks.click("csg.long_modal_dialog");
                break;
            case "modal dialog with input":
                Clicks.click("csg.modal_dialog_with_input");
                break;
        }
    }

    @Then("^I should see the \"(short modal dialog|long modal dialog|modal dialog with input)\"$")
    public void i_should_see_the(String modal_overlay) throws Throwable {

        switch (modal_overlay.toLowerCase()) {
            case "short modal dialog":
                Elements.elementShouldBePresent("csg.modal_overlay_short");
                break;
            case "long modal dialog":
                Elements.elementShouldBePresent("csg.modal_overlay_long");
                break;
            case "modal dialog with input":
                Elements.elementShouldBePresent("csg.modal_overlay_input");
                break;
        }
    }

    @When("^I click the \"([^\"]*)\" in csg screen$")
    public void i_click_the_in_csg_screen(String overlay_button) throws Throwable {

        switch (overlay_button.toLowerCase()) {
            case "x":
                Clicks.click("csg.x_close_container");
                break;
            case "start button":
                Clicks.click("csg.start_continue_button");
                break;
            case "continue button":
                Clicks.click("csg.start_continue_button");
                break;
            case "cancel button":
                Clicks.click("csg.cancel_button");
                break;
        }
    }

    @Then("^the \"(short modal dialog|long modal dialog|modal dialog with input)\" is removed$")
    public void the_is_removed(String modal_overlay) throws Throwable {
        switch (modal_overlay.toLowerCase()) {
            case "short modal dialog":
                Assert.assertTrue(!Elements.elementPresent("csg.modal_overlay_short"));
                break;
            case "long modal dialog":
                Assert.assertTrue(!Elements.elementPresent("csg.modal_overlay_long"));
                break;
            case "modal dialog with input":
                Assert.assertTrue(!Elements.elementPresent("csg.modal_overlay_input"));
                break;
        }
    }

    @Then("^I should be able to view the carousel$")
    public void i_should_be_able_to_view_the_carousel() throws Throwable {
        Elements.elementShouldBePresent("csg.carousel_control");
    }

    @When("^I click on the \"(right|left)\" arrow$")
    public void i_click_on_the_arrow(String arrow_button) throws Throwable {

        switch (arrow_button.toLowerCase()) {
            case "right":
                Clicks.click("csg.carousel_right_arrow");
                break;
            case "left":
                Clicks.click("csg.carousel_left_arrow");
                break;
        }
    }

    @Then("^I should see the carousel scrolls to the \"([^\"]*)\"$")
    public void i_should_see_the_carousel_scrolls_to_the(String scroll_direction) throws Throwable {
        String value = Elements.getElementAttribute("csg.carousel_slide",  "aria-hidden");
        //when scrolling left or right, the value of aria-hidden from the initial image should change
        //from false to true.
        if(value.equals("false")) {
            Assert.fail("Scrolling Failed");
        }
    }

    @Then("^I should be able to view dot under the carousel$")
    public void i_should_be_able_to_view_dot_under_the_carousel() throws Throwable {
        Elements.elementShouldBePresent("csg.carousel_dots");
    }

    @When("^I click a dot under the carousel$")
    public void i_click_a_dot_under_the_carousel() throws Throwable {
        Clicks.click("csg.slide_dot1");
    }

    @Then("^I should see the image has changed$")
    public void i_should_see_the_appropriate_image_is_displayed() throws Throwable {
        Assert.assertTrue(!Elements.elementPresent("csg.carousel_dot_slide"));
//		elementShouldBePresent("csg.carousel_dot_slide");
//		List<WebElement> element = findElements("csg.carousel_dot_slide");
//		System.out.println("Element = " +element);
//		String value = getElementAttribute("csg.carousel_dot_slide",  "img");
//		System.out.println("dot image value = " +value);
    }


//SDU-400
    @Then("^I should be able to view the floating label text box$")
    public void iShouldBeAbleToViewTheFloatingLabelTextBox() throws Throwable {
        Elements.elementShouldBePresent("csg.floatTextBox");
    }

    @When("^I enter \"([^\"]*)\" into the text box$")
    public void iEnterIntoTheTextBox(String arg0) throws Throwable {
        TextBoxes.typeTextbox("csg.floatTextBox","Mike");
    }

    @Then("^I should see the div class change$")
    public void iShouldSeeTheDivClassChange() throws Throwable {
        String input = Elements.findElement("csg.textBox_not_empty").getAttribute("class");
        Assert.assertEquals("textbox field-not-empty", input);
    }
//

}
