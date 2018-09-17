package com.macys.sdt.projects.Selection.List.steps.MEW.mcom;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.StepUtils;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebElement;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.json.JSONObject;
import org.junit.Assert;
import com.macys.sdt.framework.utils.rest.utils.RESTOperations;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.projects.Selection.List.steps.website.GenericUtils;


import java.util.List;
import java.util.logging.Logger;
import javax.ws.rs.core.Response;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.utils.Cookies;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static com.macys.sdt.framework.utils.StepUtils.scrollToLazyLoadElement;

public class WishListPage {
    private static Logger logger = Logger.getLogger(Thread.currentThread().getClass().getName());
    private String productId = "";
    SoftAssertions softly = new SoftAssertions();

    /**
     * Step to add or update FST cookie
     **/
    @And("^I have \"([^\"]*)\" for SEGMENT cookie$")
    public void I_have_segment_value_for_segment_cookie(String segment_val) throws Throwable {
        Wait.forPageReady();
        GenericUtils.setSegmentFSTCookie(segment_val);
    }

    @And("^I verify basic components on Add to Wish List overlay for \"([^\"]*)\" user on PDP using mobile website$")
    public void I_click_add_to_Wish_List_button_on_PDP_using_mobile_website(String user_type) throws Throwable {
        Wait.forPageReady();
        if (user_type.equals("registered")){
//            softly.assertThat(Elements.findElement(Elements.element("listpage.pdp_confimation_header")).getText()).isNotEqualTo("Added to your guest list.");
//            softly.assertThat(Elements.findElement(Elements.element("listpage.pdp_confimation_signin")).getText()).isNotEqualTo("Sign In");
        }else if (user_type.equals("guest")) {
            softly.assertThat(Elements.findElement(Elements.element("listpage.pdp_confimation_header")).getText()).isEqualTo("Added to your guest list.");
            softly.assertThat(Elements.findElement(Elements.element("listpage.pdp_confimation_signin")).getText()).isEqualTo("Sign In");
        }else
        {
            logger.warning("The usertype didnot match" +user_type );
        }
    }

    @And("^I verify the basic components on the list page for \"([^\"]*)\" user in mobile mode$")
    public void verify_components(String user_type) throws Throwable {
        softly.assertThat(Elements.findElement(Elements.element("listpage.see_all_lists")).getText()).isEqualTo("See All Lists");
        softly.assertThat(Elements.findElement(Elements.element("listpage.default_text")).getText()).isEqualTo("(default)");
        softly.assertThat(Elements.findElement(Elements.element("listpage.item_count_text")).getText()).contains(" 1 item");

        if (user_type.equals("registered")) {
            Thread.sleep(10000);
            String firstname = TestUsers.getCustomerInformation().getUser().getProfileAddress().getFirstName();
            String usertitletxt = Elements.findElement(Elements.element("listpage.user_name")).getText();
            String userFirstname = usertitletxt.split(" ")[0];
            softly.assertThat(firstname.equals(userFirstname.split("'")[0])).as("signedin user name"+ firstname).isEqualTo(true);
            softly.assertAll();
        } else if (user_type.equals("guest")) {
            Thread.sleep(3000);
            softly.assertThat(Elements.findElement(Elements.element("listpage.user_name")).getText()).isEqualTo("Guest List");
            softly.assertThat(Elements.findElement(Elements.element("listpage.price_alert_mew")).getText()).isEqualTo("Price Alerts : Off");
            softly.assertThat(Elements.elementPresent("listpage.price_alert_mew")).as("price alert container").isEqualTo(true);
            softly.assertThat(Elements.elementPresent("listpage.guest_signin_panel")).as("guest signin panel").isEqualTo(true);
            softly.assertThat(Elements.elementPresent("listpage.btn_signin")).as("signin button").isEqualTo(true);
            softly.assertThat(Elements.elementPresent("listpage.listView_mew")).as("list layout view").isEqualTo(true);
            softly.assertThat(Elements.elementPresent("listpage.gridView_mew")).as("grid layout view").isEqualTo(true);
            softly.assertThat(Elements.elementPresent("listpage.sort_mew")).as("sort button").isEqualTo(true);
//            softly.assertThat(Elements.elementPresent("listpage.filter_mew")).as("filter button").isEqualTo(true);
            softly.assertAll();
        }
        else
        {
            logger.warning("The usertype didnot match" +user_type );
        }
        softly.assertThat(Elements.elementPresent("listpage.btn_addtobag")).as("add to bag button").isEqualTo(true);
        softly.assertThat(Elements.elementPresent("listpage.btn_delete")).as("delete button").isEqualTo(true);
    }

    @When("^I navigate to PDP with PID \"([^\"]*)\" in (site|iship|registry) mode$")
    public void navigatingToPDPwithPID(String PID, String mode) throws Throwable {
        productId = PID;
        TextBoxes.typeTextNEnter("home.search_field", productId);
        Wait.forPageReady();
        if(!(mode.equalsIgnoreCase("iship")))
            Cookies.deleteAllCookies();
        Navigate.browserRefresh();
        Wait.secondsUntilElementPresent(By.cssSelector(".m-j-product-main-image"),10);
    }

    @When("^I verify \"([^\"]*)\" on the page$")
    public void i_verify_on_the_page(String product_info) throws Throwable {
        Thread.sleep(3000);
        String productNameText = Elements.findElement(Elements.element("listpage.product_name")).getText();
        softly.assertThat(productNameText.equals(product_info)).as("Guest User ").isEqualTo(true);
        softly.assertAll();

    }


    /**
     * Clicks on "Add to Wish List button" on PDP and waits for wish list overlay
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I click Move To List button on BAG using mobile website as a \"([^\"]*)\" user$")
    public void I_click_move_to_List_button_on_BAG_using_mobile_website(String user_type) throws Throwable {
        Wait.forPageReady();
        if (Elements.elementPresent("listpage.move_to_list")){
            Clicks.click("listpage.move_to_list");
        }else{
            Assert.fail("Unable to identify move to list button");
        }
        Thread.sleep(2000);
//        Clicks.click("listpage.continue_shopping");
        if (user_type.equals("signedin")){
            String firstname = TestUsers.getCustomerInformation().getUser().getProfileAddress().getFirstName();
            softly.assertThat(Elements.findElement(Elements.element("listpage.bag_confirm_header")).getText()).isEqualTo("Item added to Guest List");
        }else if (user_type.equals("guest")){
            softly.assertThat(Elements.findElement(Elements.element("listpage.bag_confirm_header")).getText()).isEqualTo("Item added to Guest List");
            softly.assertThat(Elements.findElement(Elements.element("listpage.bag_confirm_message")).getText()).isEqualTo("Item added to Guest List");
        }
        Clicks.click("listpage.bag_confirm_view_list");
    }

    /**
     * Clicks on "Add to Wish List button" on PDP and waits for wish list overlay
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I click Add to Wish List button on BAG overlay using mobile website$")
    public void I_click_add_to_Wish_List_button_on_BAG_overlay_using_mobile_website() throws Throwable {
        Wait.forPageReady();
        Elements.elementInView("listpage.bag_item");
        Clicks.clickWhenPresent("listpage.bag_item");
        Thread.sleep(2000);
        Elements.elementInView("listpage.move_to_list_overlay");
        Clicks.clickWhenPresent("listpage.move_to_list_overlay");

    }

}
