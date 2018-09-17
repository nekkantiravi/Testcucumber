package com.macys.sdt.projects.Selection.List.steps.website;

/**
 * Created by m657444 on 8/11/17.
 */

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.Cookies;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.framework.utils.rest.utils.RESTOperations;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.assertj.core.api.SoftAssertions;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Logger;



public class ListPageComponents extends StepUtils {


    private static Logger logger = Logger.getLogger(Thread.currentThread().getClass().getName());
    private static String productId = "";
    SoftAssertions softly = new SoftAssertions();
    int itemCount = 0;
    private static String findFirstName = "";
    private static String findLastName = "";


    @And("^I verify the basic components on the page for \"([^\"]*)\" user$")
    public void verify_components(String user_type) throws Throwable {
        if (user_type.equals("SignedIn")) {
            Thread.sleep(10000);
            softly.assertThat(Elements.elementPresent("listpage.SignedIn_pricealert")).as("Alert Button").isEqualTo(true);
            softly.assertThat(Elements.elementPresent("listpage.see_all_lists")).as("see all lists").isEqualTo(true);
            softly.assertThat(Elements.elementPresent("listpage.see_all_lists")).as("see all lists").isEqualTo(true);
            String firstname = TestUsers.getCustomerInformation().getUser().getProfileAddress().getFirstName();
            String usertitletxt = Elements.findElement(Elements.element("listpage.user_name")).getText();
            String userFirstname = usertitletxt.split(" ")[0];
            softly.assertThat(firstname.equals(userFirstname.split("'")[0])).as("signedin user name" + firstname).isEqualTo(true);
            softly.assertAll();
        } else if (user_type.equals("Guest")) {
            Thread.sleep(3000);
            // softly.assertThat(Elements.elementPresent("listpage.btn_signin")).as("Signin button not present").isEqualTo(true);
            softly.assertThat(Elements.elementPresent("listpage.Filter")).as("Flter_optionPresent").isEqualTo(true);
            softly.assertThat(Elements.elementPresent("listpage.Sort_by")).as("Sort_by_optionPresent")
                    .isEqualTo(true);
            softly.assertThat(Elements.elementPresent("listpage.Guest_pricealert")).as("Price Alert Button").isEqualTo(true);
            softly.assertThat(Elements.elementPresent("listpage.see_all_lists")).as("see all lists").isEqualTo(true);
            String usertitletxt = Elements.findElement(Elements.element("listpage.user_name")).getText();
            softly.assertThat(usertitletxt.equals("Guest List")).as("Guest User ").isEqualTo(true);
            logger.info(String.format("Actual Message: " + usertitletxt));
            softly.assertAll();
        } else {
            logger.warning("The usertype didnot match" + user_type);
        }


    }
    @And("^I verify the List promotion components on the page for \"([^\"]*)\" user$")
    public void
    verify_promotion_components(String user_type) throws Throwable {
        if (user_type.equals("SignedIn")) {
            Thread.sleep(10000);
            softly.assertThat(Elements.elementPresent("listpage.promo_badge"));
            softly.assertThat(Elements.elementPresent("listpage.promo_badge")).as("Extra 15% off use SAVEBIG").isEqualTo(true);
            softly.assertAll();
        } else if (user_type.equals("Guest")) {
            Thread.sleep(3000);
            // softly.assertThat(Elements.elementPresent("listpage.btn_signin")).as("Signin button not present").isEqualTo(true);
            softly.assertThat(Elements.elementPresent("listpage.promo_badge")).as("Extra 15% off use SAVEBIG").isEqualTo(true);
            softly.assertAll();
        }
        else
        {
            logger.warning("The usertype didnot match" +user_type );
        }



    }



    @And("^I should see \"([^\"]*)\" products in the responsive list page$")
    public void verify_ItemCount_components(String itemcnt) throws Throwable {
        Thread.sleep(10000);
        int itemcount = Integer.parseInt(itemcnt);
        List<WebElement> Item_container = Elements.findElements(Elements.element("listpage.Item_container"));
        Assert.assertTrue(itemcount == Item_container.size());
        itemCount = Item_container.size();
    }

    @And("^I get the response from xapi$")
    public void get_listguid_response() {
        List<WebElement> Item_container = Elements.findElements(Elements.element("listpage.Item_container"));
        Response resp = RESTOperations
                .doGET("http://172.17.6.229:8080/xapi/wishlist/170d003f-4ded-411a-8563-c1462a64e916?expand=items,user");
        System.out.println("Response::" + resp);
        String listguidService = resp.readEntity(String.class);
        System.out.println("Service Response:" + listguidService + "\n");
        JSONObject jsonObj = new JSONObject(listguidService);
        System.out.println("_______:" + jsonObj);

        System.out.println("----wishlistresp" + jsonObj.getJSONObject("WishListResponse").getClass());

        String numberofitems = jsonObj.getJSONObject("WishListResponse").getJSONArray("lists").getJSONObject(0)
                .get("numberOfItems").toString();
        String username = jsonObj.getJSONObject("WishListResponse").getJSONArray("lists").getJSONObject(0).get("name")
                .toString();

        System.out
                .println("testobject" + jsonObj.getJSONObject("WishListResponse").getJSONArray("lists").getJSONObject(0)
                        .getClass());
        System.out.println("----numberofitems:" + " " + numberofitems);
        System.out.println("----username:" + " " + username);

        for (int i = 0; i < Integer.parseInt(numberofitems); i++) {
            int xapi_isaleprice = jsonObj.getJSONObject("WishListResponse").getJSONArray("lists").getJSONObject(0)
                    .getJSONArray("items").getJSONObject(i).getJSONObject("upc")
                    .getJSONObject("price").getInt("salesValue");
            String xapi_saleprice = Integer.toString(xapi_isaleprice);
            String ui_salevalue = Item_container.get(i).findElement(Elements.element("listpage.sale_price")).getText()
                    .toString();
            //need to add logic if sale value is zero
            System.out.println("xapi_saleprice:" + xapi_saleprice);
            System.out.println("ui_salevalue:" + ui_salevalue);


            int xapi_ioriginalprice = jsonObj.getJSONObject("WishListResponse").getJSONArray("lists").getJSONObject(0)
                    .getJSONArray("items").getJSONObject(i).getJSONObject("upc")
                    .getJSONObject("price").getInt("originalPrice");
            String xapi_originalprice = Integer.toString(xapi_ioriginalprice);
            String ui_originalvalue = Item_container.get(i).findElement(Elements.element("listpage.original_price"))
                    .getText().toString();
            //System.out.println("xapiretailvalue:" + xapi_originalprice);

            // asserting the retail price sale price,
            String[] salevalue = ui_salevalue.split("\\$", 0);
            System.out.println("salevalue:" + salevalue[1]);

            softly.assertThat(xapi_saleprice.equals(salevalue[1])).as("Sale Price matched").isEqualTo(true);

            String[] originalprice = ui_originalvalue.split("\\$", 0);
            //System.out.println("retailvalue" + originalprice[1]);
            softly.assertThat(xapi_originalprice.equals(originalprice[1])).as("Retail Price matched").isEqualTo(true);

            //AddtoBagButton,Move,delete Button's
            Assert.assertEquals(Item_container.get(i).findElement(Elements.element("listpage.btn_addtobag"))
                    .isDisplayed(), true);
            Assert.assertEquals(Item_container.get(i).findElement(Elements.element("listpage.btn_move"))
                    .isDisplayed(), true);
            Assert.assertEquals(Item_container.get(i).findElement(Elements.element("listpage.btn_delete"))
                    .isDisplayed(), true);


        }
        softly.assertAll();
    }

    @And("^I get the response from xapi for Signed In user$")
    public void get_listguid_response_signedin() {
        List<WebElement> Item_container = Elements.findElements(Elements.element("listpage.Item_container"));
        Response resp = RESTOperations
                .doGET("http://172.17.6.181:8080/xapi/wishlist/e0401e8d-ae96-4300-a44b-e02452f9fbc2?expand=items,user");
        System.out.println("Response::" + resp);
        String listguidService = resp.readEntity(String.class);
        //System.out.println("Service Response:" + listguidService+"\n");
        JSONObject jsonObj = new JSONObject(listguidService);
        System.out.println("_______:" + jsonObj);

        System.out.println("----wishlistresp" + jsonObj.getJSONObject("WishListResponse").getClass());

        String numberofitems = jsonObj.getJSONObject("WishListResponse").getJSONArray("lists").getJSONObject(0)
                .get("numberOfItems").toString();
        String username = jsonObj.getJSONObject("WishListResponse").getJSONArray("lists").getJSONObject(0).get("name")
                .toString();

        System.out
                .println("testobject" + jsonObj.getJSONObject("WishListResponse").getJSONArray("lists").getJSONObject(0)
                        .getClass());
        System.out.println("----numberofitems:" + " " + numberofitems);
        System.out.println("----username:" + " " + username);

        for (int i = 0; i < Integer.parseInt(numberofitems); i++) {
            int xapi_isaleprice = jsonObj.getJSONObject("WishListResponse").getJSONArray("lists").getJSONObject(0)
                    .getJSONArray("items").getJSONObject(i).getJSONObject("upc")
                    .getJSONObject("price").getInt("salesValue");
            String xapi_saleprice = Integer.toString(xapi_isaleprice);
            String ui_salevalue = Item_container.get(i).findElement(Elements.element("listpage.sale_price")).getText()
                    .toString();
            //need to add logic if sale value is zero
            System.out.println("xapi_saleprice:" + xapi_saleprice);
            System.out.println("ui_salevalue:" + ui_salevalue);


            int xapi_ioriginalprice = jsonObj.getJSONObject("WishListResponse").getJSONArray("lists").getJSONObject(0)
                    .getJSONArray("items").getJSONObject(i).getJSONObject("upc")
                    .getJSONObject("price").getInt("originalPrice");
            String xapi_originalprice = Integer.toString(xapi_ioriginalprice);
            String ui_originalvalue = Item_container.get(i).findElement(Elements.element("listpage.original_price"))
                    .getText().toString();
            //System.out.println("xapiretailvalue:" + xapi_originalprice);

            // asserting the retail price sale price,
            String[] salevalue = ui_salevalue.split("\\$", 0);
            System.out.println("salevalue:" + salevalue[1]);

            //softly.assertThat(xapi_saleprice.equals(salevalue[1])).as("Sale Price matched").isEqualTo(true);

            String[] originalprice = ui_originalvalue.split("\\$", 0);
            //System.out.println("retailvalue" + originalprice[1]);
            softly.assertThat(xapi_originalprice.equals(originalprice[1])).as("Retail Price matched").isEqualTo(true);

            //AddtoBagButton,Move,delete Button's
            Assert.assertEquals(Item_container.get(i).findElement(Elements.element("listpage.btn_addtobag"))
                    .isDisplayed(), true);
            Assert.assertEquals(Item_container.get(i).findElement(Elements.element("listpage.btn_move"))
                    .isDisplayed(), true);
            Assert.assertEquals(Item_container.get(i).findElement(Elements.element("listpage.btn_delete"))
                    .isDisplayed(), true);


        }
        softly.assertAll();
    }

    @And("^I click delete button")
    public void delete_item_request() {
        Response resp = RESTOperations
                .doGET("http://xwls-mcom-listre17t.c4d.devops.fds.com/xapi/wishlist/6b759d41-5cce-4bf8-8d58-df2791c23ed9?expand=items,user");
        String listguidService = resp.readEntity(String.class);
        JSONObject jsonObj = new JSONObject(listguidService);
        String itemGuid = jsonObj.getJSONObject("WishListResponse")
                .getJSONArray("lists")
                .getJSONObject(0)
                .getJSONArray("items")
                .getJSONObject(0)
                .getString("guid");

        int numberofitems = jsonObj.getJSONObject("WishListResponse")
                .getJSONArray("lists")
                .getJSONObject(0)
                .getInt("numberOfItems");

        List<WebElement> itemContent = Elements.findElements(Elements.element("listpage.Item_container"));

        Assert.assertEquals(itemContent.get(0).findElement(Elements.element("listpage.btn_delete"))
                .isDisplayed(), true);

        System.out.println(itemContent.get(0).getText());
        Wait.secondsUntilElementPresent("listpage.btn_delete", 10);
        Clicks.click(Elements.element("listpage.btn_delete"));

        Navigate.browserRefresh();

    }

    //all the actions on the list page and the list page popup

    @And("^I click on the \"([^\"]*)\" button$")
    public void click_on_list_page_action_button(String action_button) {
        switch (action_button) {
            case "Keep brwosing":
                Wait.secondsUntilElementPresent("listpage.btn_keep_browsing", 10);
                Clicks.click(Elements.element("listpage.btn_keep_browsing"));
                break;
            case "AddToBag":
                try {
                    Wait.forPageReady();
                    softly.assertThat(Elements.elementPresent("listpage.btn_addtobag")).as("add_to_bag is not present").isEqualTo(true);
                    Navigate.execJavascript("window.scrollTo(0, document.body.scrollHeight)");
                    Wait.secondsUntilElementPresent("listpage.btn_addtobag", 15);
                    Clicks.click(Elements.element("listpage.btn_addtobag"));
                } catch (Exception e) {
                    logger.warning(String.format("Couldn't perform ADDTOBAG action as the button was not displayed"));
                    e.printStackTrace();

                }
                Wait.secondsUntilElementPresent("listpanels.popup_atb", 20);
                String atb_popup_msg = Elements.findElement("listpanels.popup_atb").getText();
                logger.info("Abbtobag Popup is displayed:" + atb_popup_msg);
                softly.assertAll();
                Assert.assertTrue("ERROR - ENV: The Atb POP-UP message is not the expected message:", atb_popup_msg.equals("This item was added to Your Bag"));
                break;
            case "Move":
                try {
                    Wait.forPageReady();
                    softly.assertThat(Elements.elementPresent("listpage.btn_move")).as("move_link is not present").isEqualTo(true);
                    Navigate.execJavascript("window.scrollTo(0, document.body.scrollHeight)");
                    Wait.secondsUntilElementPresent("listpage.btn_move", 15);
                    Clicks.click(Elements.element("listpage.btn_move"));
                } catch (Exception e) {
                    logger.warning(String.format("Couldn't perform MOVE action as the button was not displayed"));
                    e.printStackTrace();
                }
                Wait.secondsUntilElementPresent("listpage.btn_move", 20);
                // ---- Save for when move list overlay is in UI ----
                //softly.assertThat(Elements.elementPresent("listpanels.btn_move")).as("move_link is not present").isEqualTo(true);
                //String move_popup_msg = Elements.findElement("listpanels.popup_move").getText();
                //logger.info("Move popup is displayed:"+move_popup_msg);
                //softly.assertAll();
                //Assert.assertTrue("ERROR - ENV: The Move POP-UP message is not the expected message:", move_popup_msg.equals("This item have been moved"));
                break;

            case "viewcheckout":
                try {
                    Wait.secondsUntilElementPresent("listpanels.btn_viewcheckout", 30);
                    Clicks.click(Elements.element("listpanels.btn_viewcheckout"));
                } catch (Exception e) {
                    logger.warning(String.format("Couldn't click on viewcheckout button as the button is not displayed"));
                    e.printStackTrace();
                }
                break;
            case "yourbag":
                try {
                    Wait.secondsUntilElementPresent("listpanels.lnk_yourbag", 30);
                    Clicks.click(Elements.element("listpanels.lnk_yourbag"));
                } catch (Exception e) {
                    logger.warning(String.format("Couldn't click on link yourbag button as the button is not displayed"));
                    e.printStackTrace();
                }
                break;
            case "continue":
                try {
                    Wait.secondsUntilElementPresent("listpanels.btn_backtolist", 30);
                    Clicks.click(Elements.element("listpanels.btn_backtolist"));
                } catch (Exception e) {
                    logger.warning(String.format("Couldn't click on back to list button as the button is not displayed"));
                    e.printStackTrace();
                }
                break;
            case "Delete":
                try {
                    Wait.forPageReady();
                    softly.assertThat(Elements.elementPresent("listpage.btn_delete")).as("delete is not present").isEqualTo(true);
                    Navigate.execJavascript("window.scrollTo(0, document.body.scrollHeight)");
                    Wait.secondsUntilElementPresent("listpage.btn_delete", 30);
                    Clicks.click(Elements.element("listpage.btn_delete"));
                } catch (Exception e) {
                    logger.warning(String.format("Couldn't click on Delete button button as the button is not displayed"));
                    e.printStackTrace();
                }
                break;
            case "SeeALlList":
                try {
                    Wait.forPageReady();
                    Wait.secondsUntilElementPresent("listpage.see_all_lists", 30);
                    Clicks.click(Elements.element("listpage.see_all_lists"));

                } catch (Exception e) {
                    logger.warning(String.format("See All List -Link is not found "));
                    e.printStackTrace();

                }
                break;
            case "settings":
                try {
                    Wait.forPageReady();
                    Wait.secondsUntilElementPresent("listpage.settings_link", 30);
                    Clicks.click(Elements.element("listpage.settings_link"));
                } catch (Exception e) {
                    logger.warning(String.format("Settings link is not found"));
                    e.printStackTrace();
                }
                break;
        }
    }

    @Then("^I should verify the information on the popup$")
    public void addtobag_popup_information() {
        softly.assertThat(Elements.elementPresent("listpanels.btn_viewcheckout")).as("view Checkout button is present").isEqualTo(true);
        softly.assertThat(Elements.elementPresent("listpanels.btn_backtolist")).as("back to list button is not present").isEqualTo(true);
        softly.assertAll();
    }


    @Then("I verify user landed on shoppingbag page$")
    public void verify_user_landed_shopping_bag_pge() {
        try {

            Wait.forPageReady();
            Wait.secondsUntilElementPresent("shoppingbag_list.shopppingbag_id", 30);
            softly.assertThat(Elements.elementPresent("shoppingbag_list.shopppingbag_id")).as("shopping bag page not loadedt").isEqualTo(true);

        } catch (Exception e) {
            logger.warning(String.format("the shopping page is not loaded"));
            e.printStackTrace();

        }
    }

    @Then("^I verify user landed on list page$")
    public void verify_user_landed_on_listpage() {
        Assert.assertTrue(WebDriverManager.getWebDriver().getCurrentUrl().contains("/wishlist/mylist?"));

    }

    @And("^I verify the unavailable message for the product on the list page$")
    public void verify_unavailable_product() {
        softly.assertThat(Elements.elementPresent("listpage.unavailable_message")).as("Unavailable product message").isEqualTo(true);
    }

    @Then("^I check the box to make my list \"([^\"]*)\"$")
    public void i_check_the_box_to_make_my_list(String action) throws Throwable {
        switch (action) {
            case "searchable":
                Wait.forPageReady();
                Wait.secondsUntilElementPresent("manage_panel.check_searchable", 15);
                softly.assertThat(Elements.elementPresent("manage_panel.check_searchable")).as("Searchable checkbox").isEqualTo(true);
                Clicks.click(Elements.element("manage_panel.check_searchable"));
                findFirstName = TestUsers.getCustomerInformation().getUser().getProfileAddress().getFirstName();
                findLastName = TestUsers.getCustomerInformation().getUser().getProfileAddress().getLastName();
                break;
            case "default":
                Wait.forPageReady();
                Wait.secondsUntilElementPresent("manage_panel.check_default", 15);
                softly.assertThat(Elements.elementPresent("manage_panel.check_default")).as("Default checkbox").isEqualTo(true);
                Clicks.click(Elements.element("manage_panel.check_default"));
                break;
            case "have price alerts":
                Wait.forPageReady();
                Wait.secondsUntilElementPresent("manage_panel.check_alerts", 15);
                softly.assertThat(Elements.elementPresent("manage_panel.check_alerts")).as("Price alerts checkbox").isEqualTo(true);
                Clicks.click(Elements.element("manage_panel.check_alerts"));
                break;
        }
    }

    @And("^I click on \"([^\"]*)\" on manage list overlay$")
    public void iClickOnOnSettingsOverlay(String btn) throws Throwable {
        switch (btn) {
            case "save":
                Clicks.click(Elements.element("manage_panel.settings_save"));
                break;
            case "cancel":
                Clicks.click(Elements.element("manage_panel.settings_cancel"));
                break;
            case "close":
                Clicks.click(Elements.element("manage_panel.settings_close"));
        }
        Wait.forPageReady();
        Wait.secondsUntilElementNotPresent("listpage.settings_overlay", 15);
        softly.assertThat(Elements.elementPresent("listpage.settings_overlay")).isEqualTo(false);
        softly.assertAll();
    }

    @And("^I verify lists manage settings on responsive list page")
    public void iVerifySettingsOverlayOnListPage() throws Throwable {
        softly.assertThat(Elements.elementPresent("manage_panel.settings_cancel")).as("manage cancel button").isEqualTo(true);
        softly.assertThat(Elements.elementPresent("manage_panel.settings_save")).as("manage save button").isEqualTo(true);
        softly.assertAll();
    }

    @And("^I verify the list name as \"([^\"]*)\" on list page$")
    public void verify_list_name(String listName) {

        try {
            Wait.forPageReady();
            Wait.secondsUntilElementPresent("listpage.user_name", 15);
            switch (listName) {
                case "default":
                    String firstname = TestUsers.getCustomerInformation().getUser().getProfileAddress().getFirstName();
                    String usertitletxt = Elements.findElement(Elements.element("listpage.user_name")).getText();
                    String userFirstname = usertitletxt.split(" ")[0];
                    softly.assertThat(firstname.equals(userFirstname.split("'")[0])).as("signedin user name" + firstname).isEqualTo(true);
                    break;
                default:
                    usertitletxt = Elements.findElement(Elements.element("listpage.user_name")).getText();
                    softly.assertThat(usertitletxt.equals(listName)).as("List name").isEqualTo(true);
            }
        } catch (Exception e) {
            logger.warning(String.format("List name is incorrect"));
            e.printStackTrace();
        }
        softly.assertAll();
    }


    @And("^I verify that my list is subscribed for price alerts$")
    public void iVerifyThatMyListIsSubscribedForPriceAlerts() throws Throwable {
        try {
            Wait.forPageReady();
            Wait.secondsUntilElementPresent("listpage.alerts_valid", 15);
            softly.assertThat(Elements.elementPresent("listpage.alerts_valid")).as("Price alert subscription").isEqualTo(true);
        } catch (Exception e) {
            logger.warning(String.format("Subscription message is not visible"));
            e.printStackTrace();
        }
    }

    @And("^I move my item from bag to list$")
    public void i_move_item_from_bag_to_my_list() throws Throwable {
        try {
            Clicks.click(Elements.findElements("shopping_bag.line_items").get(0).findElement(Elements.element("shopping_bag.wish_list")));
            Wait.untilElementPresent("shopping_bag.wishlist_msg");
            Assert.assertTrue("Added to wishlist message is displaying", Elements.elementPresent("shopping_bag.wishlist_msg"));
            Navigate.browserRefresh();
            logger.info("Item moved to wish list");
        } catch (Exception e) {
            Assert.fail("Error while removing given item from bag!!");
            e.printStackTrace();
        }
    }

    @And("^I filter the list as \"([^\"]*)\"")
    public void i_filter_list_as(String filterBy) throws Throwable {
        try {
            Wait.forPageReady();
            Wait.untilElementPresent("listpage.Filter");
            DropDowns.selectByText("listpage.Filter", filterBy);

        } catch (Exception e) {
            Assert.fail("Error when filtering list page");
            e.printStackTrace();
        }
    }

    @Then("^I should see the list filtered as \"([^\"]*)\"")
    public void i_see_the_list_filtered(String filterBy) throws Throwable {
        try {
            Wait.forPageReady();
            Wait.untilElementPresent("listpage.Filter");
            Assert.assertTrue("List page is filtered correctly", filterBy.equals(DropDowns.getSelectedValue(Elements.element("listpage.Filter"))));
        } catch (Exception e) {
            Assert.fail("Error when filtering list page incorrectly");
            e.printStackTrace();
        }
    }

    @And("^I set cookie for SSC to see responsive experience$")
    public void I_set_ssc_cookie() throws Throwable {
        Wait.forPageReady();
        Cookies.addCookie("SSC", "64-21");
        Cookies.addCookie("SignedIn", "1");
        Cookies.printAllCookieData("SSC");
        Navigate.browserRefresh();
    }

    @And("^I set cookie for SSC to see responsive experience for MEW$")
    public void I_set_ssc_cookie_MEW() throws Throwable {
        Wait.forPageReady();
        Cookies.addCookie("SSC", "81-21");
        Cookies.addCookie("SignedIn", "1");
        Cookies.printAllCookieData("SSC");
        Navigate.browserRefresh();
    }

    @Then("^I should see move overlay on responsive list page$")
    public void I_should_see_move_overlay() throws Throwable {
        try {
            Wait.forPageReady();
            Wait.untilElementPresent("listpage.move_overlay");
            Assert.assertTrue("Move overlay is displaying", Elements.elementPresent("listpage.move_overlay"));
            logger.info("Move overlay is displaying");
        } catch (Exception e) {
            Assert.fail("Error while moving product to another list");
            e.printStackTrace();
        }
    }

    @When("^I create a list as \"([^\"]*)\" on the move overlay$")
    public void I_create_list_on_move_overlay(String listName) throws Throwable {
        try {
            Wait.forPageReady();
            Wait.untilElementPresent("move_panel.create_new_list");
            Clicks.click(Elements.element("move_panel.create_new_list"));
            Wait.untilElementPresent("move_panel.list_name_text");
            TextBoxes.typeTextbox("move_panel.list_name_text", listName);
            Clicks.click(Elements.element("move_panel.list_create"));
            logger.info("Created new list from move overlay");
        } catch (Exception e) {
            Assert.fail("Error when creating new list to move product");
            e.printStackTrace();
        }
    }

    @Then("^I should see the list name as \"([^\"]*)\" on responsive list page$")
    public void I_should_see_list_name(String listName) throws Throwable {
        try {
            Wait.forPageReady();
            Wait.untilElementPresent("listpage.user_name");
            String usertitletxt = Elements.findElement(Elements.element("listpage.user_name")).getText();
            String firstname = TestUsers.getCustomerInformation().getUser().getProfileAddress().getFirstName();
            String userFirstname = usertitletxt.split(" ")[0];
            switch (listName) {
                case "default name":
                    Assert.assertTrue(firstname.equals(userFirstname.split("'")[0]));
                    break;
                default:
                    Assert.assertTrue(listName.equals(usertitletxt));
            }
        } catch (Exception e) {
            Assert.fail("Error when list name has not changed");
            e.printStackTrace();
        }
    }

    @Then("^I move my product to default list on move overlay$")
    public void I_move_product_to_default_list() throws Throwable {
        try {
            Wait.forPageReady();
            Wait.untilElementPresent("move_panel.default_list");
            Clicks.click(Elements.element("move_panel.default_list"));
            logger.info("Moved product to default list");
        } catch (Exception e) {
            Assert.fail("Error when moving product to default list");
            e.printStackTrace();
        }
    }

    @Then("^I should see move confirmation message and navigate to other list$")
    public void I_should_see_move_confirmation_message() throws Throwable {
        try {
            Wait.forPageReady();
            Wait.untilElementPresent("listpage.move_confirmation");
            Clicks.click(Elements.element("listpage.move_confirmation"));
            logger.info("Confirmation move message is displaying");
        } catch (Exception e) {
            Assert.fail("Error when displaying move confirmation message");
            e.printStackTrace();
        }
    }

    @And("^I click on Find a List$")
    public void i_click_on_find_a_list() throws Throwable {
        Wait.secondsUntilElementPresent("listhome.find_a_list", 30);
        Clicks.click(Elements.element("listhome.find_a_list"));
    }

    @When("^I enter a first and last name to Find a List$")
    public void i_enter_first_and_last_name() throws Throwable {
        try {
            Wait.forPageReady();
            Wait.untilElementPresent("find_list.first_name_box");
            Wait.untilElementPresent("find_list.last_name_box");
            TextBoxes.typeTextbox("find_list.first_name_box", findFirstName);
            TextBoxes.typeTextbox("find_list.last_name_box", findLastName);
            Clicks.click(Elements.element("find_list.find_search"));
            logger.info("Input first and last name to find a list");
        } catch (Exception e) {
            Assert.fail("Error when inputting first and last name");
            e.printStackTrace();
        }
    }

    @When("^I click on view list from Find a List results$")
    public void i_click_on_view_list_from_results() throws Throwable {
        try {
            Wait.forPageReady();
            Wait.untilElementPresent("find_list.view_list_result");
            Clicks.click(Elements.element("find_list.view_list_result"));
            logger.info("View list button is displaying from results");
        } catch (Exception e) {
            Assert.fail("Error when displaying find a list results");
            e.printStackTrace();
        }
    }

    @Then("^I should see my friend's list in the responsive list page$")
    public void i_should_see_my_friends_list() throws Throwable {
        try {
            Wait.forPageReady();
            Wait.untilElementPresent("listpage.friends_list_message");
            Assert.assertFalse("Product move link is not displayed", Elements.elementPresent("listpage.btn_move"));
            Assert.assertFalse("Product delete link is not displayed", Elements.elementPresent("listpage.btn_delete"));
            logger.info("Friend's list is displayed");
        } catch (Exception e) {
            Assert.fail("Error when displaying Friend's list");
            e.printStackTrace();
        }
    }
}

