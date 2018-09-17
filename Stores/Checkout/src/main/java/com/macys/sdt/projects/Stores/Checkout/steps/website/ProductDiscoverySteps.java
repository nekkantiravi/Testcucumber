package com.macys.sdt.projects.Stores.Checkout.steps.website;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.projects.Stores.Checkout.utils.ProductDiscoveryUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.macys.sdt.framework.interactions.Clicks.click;
import static com.macys.sdt.framework.interactions.Elements.elementShouldBePresent;
import static com.macys.sdt.framework.interactions.Elements.findElement;
import static com.macys.sdt.framework.interactions.TextBoxes.typeTextNEnter;
import static com.macys.sdt.projects.Stores.Checkout.utils.ProductDiscoveryUtils.AddItemtotheBagOnProductDiscovery;


public class ProductDiscoverySteps extends StepUtils {

    private String productQuantity = "";




    @Then("^I can see the landing page$")
    public void i_can_see_the_landing_page() throws Throwable {
        Wait.forPageReady();
        Wait.secondsUntilElementPresent("pdp.landing_message", 15);
      //  Wait.secondsUntilElementPresent("pdp.landing_title", 15);
        //This lading title is being de-prioritized until Matt Johnson and PD makes a decision.
//        elementShouldBePresent("pdp.landing_title");
        elementShouldBePresent("pdp.landing_message");
        elementShouldBePresent("pdp.search_icon");


    }

    @When("^I press the search icon$")
    public void i_press_the_search_icon() throws Throwable {
        elementShouldBePresent("pdp.search_icon");
        click("pdp.search_icon");
    }

    @Then("^I can see the search overlay$")
    public void i_can_see_the_search_overlay() throws Throwable {
        elementShouldBePresent("pdp.search_form");

    }

    @When("^I uncheck this store only$")
    public void i_uncheck_this_store_only() throws Throwable {
        elementShouldBePresent("pdp.instore_checkbox");
        click("pdp.instore_checkbox");
    }

    @When("^I search for \"([^\"]*)\" on product discovery$")
    public void i_search_for_on_product_discovery(String ItemName) throws Throwable {
        Wait.untilElementPresent("pdp.search_form");
        typeTextNEnter("pdp.search_form", ItemName);
        Thread.sleep(2000);
    }


    @Then("^I see the Search results page$")
    public void i_see_the_Search_results_page() throws Throwable {
        Wait.untilElementNotPresent("pdp.oops_popup");
        Wait.untilElementPresent("pdp.search_results");

    }

    @When("^I select the first boot$")
    public void i_select_the_first_boot() throws Throwable {
        Wait.untilElementPresent("pdp.first_boot");
        click("pdp.first_boot");


    }

    @Then("^I can see the product card$")
    public void i_can_see_the_product_card() throws Throwable {
        Wait.untilElementPresent("pdp.product_card");
        elementShouldBePresent("pdp.product_card");

    }

    @Then("^I select size$")
    public void i_select_size() throws Throwable {
        Wait.untilElementPresent("pdp.size_dropdown");
        click("pdp.size_dropdown");
        Wait.untilElementPresent("pdp.boot_size");
        click("pdp.boot_size");
    }

    @Then("^I select color$")
    public void i_select_color() throws Throwable {
        // Write code here that turns the phrase above into concrete actions

    }

    @Then("^I press the Add to bag$")
    public void i_press_the_Add_to_bag() throws Throwable {
        Wait.untilElementPresent("pdp.addtobag_button");
        click("pdp.addtobag_button");
    }

    
    @And("^I close the oops pop-up$")
    public void iCloseTheOopsPopUp() throws Throwable {
        Wait.untilElementPresent("home.overlay_close");
        click("home.overlay_close");
    }


    @When("^I search for \"([^\"]*)\" in home page$")
    public void iSearchForInHomePage(String itemName) throws Throwable {
        //When I press the search icon
        i_press_the_search_icon();
        //Then I can see the search overlay
        i_can_see_the_search_overlay();
        //When I uncheck this store only
        i_uncheck_this_store_only();
        //And I search for "Boots" on product discovery
        i_search_for_on_product_discovery(itemName);

    }

    @When("^I add (\\d+) items by webid to bag via product discovery$")
    public void iAddItemsToBagViaProductDiscovery(int count) throws Throwable {
        ProductDiscoveryUtils.addWebIdToBag(count);
    }

    @When("^I close the product card$")
    public void iCloseTheProductCard() throws Throwable {
        Wait.secondsUntilElementNotPresent("home.add_item_overlay_close_button",5);
        Wait.secondsUntilElementPresent("home.overlay_close",5);
        Clicks.click("home.overlay_close");
    }


    @When("^I remove an item from UAT$")
    public void iRemoveAnItemFromUAT() throws Throwable {
        Elements.elementShouldBePresent("pdp.pd_remove_button");
        Clicks.click("pdp.pd_remove_button");
        Thread.sleep(1000);
    }
    
    @Then("^I should see a message that no items were found$")
    public void iShouldSeeAMessageThatNoItemsWereFound() throws Throwable {
        String message;
        Wait.untilElementPresent("pdp.no_product_message");
        message = findElement("pdp.no_product_message").getText();
        String expectedMessage = "We didn't find any products for";
        Assert.assertTrue("Expected message was not found",message.contains(expectedMessage));

    }

    @Then("^I am returned to product discovery$")
    public void iAmReturnedToProductDiscovery() throws Throwable {
        shouldBeOnPage("home");
        Elements.elementShouldBePresent("home.verify_page");
    }

    @Then("^I can see Phase one Checkout empty bag view$")
    public void iCanSeePhaseOneCheckoutEmptyBagView() throws Throwable {
            Wait.forPageReady();
            Thread.sleep(5000);

            if (!Elements.elementPresent("bag_screen.emptyBag")) {
                Assert.fail("Empty bag element not displayed");
            }
            if (!Elements.getText("bag_screen.emptyBag").contains("Bag is empty\nScan or search for items")) {
                Assert.fail("Empty bag message");

            }
    }

    @Given("^I launch the offline landing page$")
    public void iLaunchTheOfflineLandingPage() throws Throwable {
        Navigate.visit("home");
        elementShouldBePresent("pdp.landing_title");
        elementShouldBePresent("pdp.landing_message");


    }

    @When("^I click the About Icon$")
    public void iClickTheAboutIcon() throws Throwable {
        Elements.elementShouldBePresent("pdp.about_button");
        Clicks.click("pdp.about_button");
    }

    @Then("^I am directed to the Base Configuration Screen$")
    public void iAmDirectedToTheBaseConfigurationScreen() throws Throwable {
        Elements.elementShouldBePresent("pdp.refresh_button");
    }

    @And("^I should be able to view the configuration values of uPOS$")
    public void iShouldBeAbleToViewTheConfigurationValuesOfUPOS() throws Throwable {
        Elements.elementShouldBePresent("pdp.store_number");
        Elements.elementShouldBePresent("pdp.register_number");
        Elements.elementShouldBePresent("pdp.machine_name");
        Elements.elementShouldBePresent("pdp.device_ip_address");
        Elements.elementShouldBePresent("pdp.device_gateway");
        Elements.elementShouldBePresent("pdp.device_netmask");
        Elements.elementShouldBePresent("pdp.isp_ip_address");
        Elements.elementShouldBePresent("pdp.isp_hostname");
        Elements.elementShouldBePresent("pdp.ssp_ip_address");
        Elements.elementShouldBePresent("pdp.ssp_hostname");
        Elements.elementShouldBePresent("pdp.hal_version");


    }

    @Then("^I verify quantity drop down box$")
    public void I_verify_Quantity_drop_down_box() throws Throwable {

        Wait.secondsUntilElementPresent("pdp.select_max_quantity", 40);
        Assert.assertEquals("Select Max Quantity dropdown", Elements.element("pdp.select_max_quantity"));
    }

    @When("^I select random value from the quantity list$")
    public void I_select_random_value_from_the_Quantity_list() throws Throwable {
        productQuantity = DropDowns.selectRandomValue("pdp.select_max_quantity");
    }

    @Then("^I verify product quantity gets updated$")
    public void I_verify_product_Quantity_gets_updated() throws Throwable {
        String pageQuantity = DropDowns.getSelectedValue(Elements.element("pdp.select_max_quantity"));
        Assert.assertEquals(productQuantity, pageQuantity);
    }

    @When("^I remove all item from the UAT bag$")
    public void i_remove_all_item_from_the_UAT_bag() throws Throwable {

        Wait.untilElementPresent("pdp.pd_remove_button");

        try {
            List<WebElement> items = Elements.findElements("pdp.pd_remove_button");
            int totalsize = items.size();
            while (items.size() > 0) {
                Clicks.click(items.get(0));
                Thread.sleep(2000);
//Updates the decremented  values
                --totalsize;
                if (totalsize > 0)
//Just to avoid getting an Exception
                    items = Elements.findElements("pdp.pd_remove_button");
            }
        } catch (Exception e) {
            e.printStackTrace();
//Prints the Exception message
        }
    }

    @Then("^I can view the PDP Header$")
    public void i_can_view_the_PDP_Header() throws Throwable {
        Elements.elementShouldBePresent("home.hamburger_menu");
        Elements.elementShouldBePresent("pdp.search_icon");
        Elements.elementShouldBePresent("home.page_title");
        Elements.elementShouldBePresent("home.bag_icon");

    }

    @When("^I press the customer icon$")
    public void i_press_the_customer_icon() throws Throwable {
        Wait.untilElementPresent("pdp.customer_menu");
        Clicks.click("pdp.customer_menu");
    }

    @Then("^I can see the customer bag$")
    public void i_can_see_the_customer_bag() throws Throwable {
        Elements.elementShouldBePresent("pdp.customer_dropdown");
        Elements.elementShouldBePresent("pdp.customerbag_name");
        Elements.elementShouldBePresent("pdp.customerbag_icon");

    }

    @Then("^I can see the header suspend button$")
    public void i_can_see_the_header_suspend_button() throws Throwable {
        Elements.elementShouldBePresent("pdp.header_suspend");
        Elements.elementShouldBePresent("pdp.header_cancel");
    }
    @When("^I press the Bag in the Header$")
    public void i_press_the_Customer_Menu_in_the_Header() throws Throwable {
        Elements.elementShouldBePresent("home.bag_icon");
        Clicks.click("home.bag_icon");
    }

    @Then("^I can see the Customer Bag Dropdown$")
    public void i_can_see_the_Customer_Bag_Dropdown() throws Throwable {
        Elements.elementShouldBePresent("pdp.customer_dropdown");
        Elements.elementShouldBePresent("pdp.customerbag_name");
        Elements.elementShouldBePresent("pdp.customerbag_icon");
    }

    @And("^I can see the Customer Name Title$")
    public void i_can_see_the_Customer_Name_Title() throws Throwable {
        Elements.elementShouldBePresent("pdp.customerbag_name");

    }

    @When("^I press the Customer Name$")
    public void i_press_the_Customer_Name() throws Throwable {
        Elements.elementShouldBePresent("pdp.customerbag_name");
        Clicks.click("pdp.customerbag_name");
    }

    @Then("^I can see the bag header title$")
    public void i_can_see_the_bag_header_title() throws Throwable {
        Elements.elementShouldBePresent("home.page_title");
        Elements.elementShouldBePresent("pdp.bag_subtitle");

        String bag_name = Elements.findElement("home.page_title").getText();
        Assert.assertEquals("Bag", bag_name);

        String cusbag_name = Elements.findElement("pdp.bag_subtitle").getText();
        Assert.assertEquals("Customer 1", cusbag_name);
    }


    @Then("^I see the redirection to PDP$")
    public void iSeeTheRedirectionToPDP() throws Throwable {
        boolean hasPathToAdd = url().contains("20714001940");
        Assert.assertTrue("Clicking Product card took me to " + url(), hasPathToAdd);
        Wait.secondsUntilElementPresent("pdp.oops_popup", 10);

    }

    @And("^I click on Hamburger icon$")
    public void iClickOnHamburgerIcon() throws Throwable {
        Thread.sleep(500);
        if (Elements.elementPresent("bag_screen.toast_body")){
            Thread.sleep(3000);
            if(Elements.elementPresent("bag_screen.toast_message")){
              Clicks.click("bag_screen.toast_close_button");
              Thread.sleep(2000);
            }
        }
        if(Elements.elementPresent("bag_screen.spinner")){
            Wait.secondsUntilElementNotPresent("bag_screen.spinner", 10);

        }
        if (Elements.elementPresent("tendering.authorize_overlay")){
            Wait.secondsUntilElementNotPresent("tendering.authorize_overlay", 20);

            if(Elements.elementPresent("tendering.authorize_overlay")){
                Assert.fail("The Authorization overlay was there way too long.");
                
            }


        }

        Wait.secondsUntilElementNotPresent("bag_screen.toast_close", 10);
        Elements.elementShouldBePresent("home.hamburger_menu");
        Elements.elementInView("home.hamburger_menu");
        Clicks.click("home.hamburger_menu");
    }

    @Then("^I See the About icon$")
    public void iSeeTheAboutIcon() throws Throwable {
        Elements.elementShouldBePresent("pdp.about_button");
        String aboutButton = Elements.findElement("pdp.about_button").getText();
        Assert.assertEquals("About", aboutButton);

        if (Elements.elementPresent("pdp.about_button")) {
            int no_of_items = Elements.findElements("pdp.about_button").size();
            Assert.assertEquals(1, no_of_items);

        }
    }

    @When("^I select a Small in Orange$")
    public void iSelectAMediumInRed() throws Throwable {
       Wait.forPageReady();
       Clicks.click("pdp.small");

       Thread.sleep(1000);
       Clicks.click("pdp.orange");

    }

    @Then("^I can view the UAT suspend button$")
    public void iCanViewTheUATSuspendButton() throws Throwable {
        Wait.untilElementPresent("pdp.uat_suspend_button");
        Elements.elementShouldBePresent("pdp.uat_suspend_button");
    }

    @When("^I press the UAT suspend button$")
    public void iPressTheUATSuspendButton() throws Throwable {
      Clicks.click("pdp.uat_suspend_button");
    }

    @When("^I navigate back to the bag$")
    public void iNavigateBackToTheBag() throws Throwable {
        Navigate.visit("home");

    }

    @When("^I add \"([^\"]*)\" into PD for a \"([^\"]*)\" sale$")
    public void iAddIntoPDForASale(String UPC, String TransType) throws Throwable {
        AddItemtotheBagOnProductDiscovery(UPC, TransType);




    }

    @And("^I Click the Product Discovery Home Menu Option$")
    public void iClickTheProductDiscoveryHomeMenuOption() throws Throwable {
        Wait.secondsUntilElementPresent("pdp.pdp_home", 10);
        Elements.elementShouldBePresent("pdp.pdp_home");
        Clicks.click("pdp.pdp_home");
        Thread.sleep(500);
        i_can_see_the_landing_page();
    }
}
