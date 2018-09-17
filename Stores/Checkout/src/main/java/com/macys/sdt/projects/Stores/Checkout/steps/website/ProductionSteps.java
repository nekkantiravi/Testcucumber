package com.macys.sdt.projects.Stores.Checkout.steps.website;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.projects.Stores.Checkout.utils.ProductionUtils;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;

import static com.macys.sdt.projects.Stores.Checkout.steps.website.CheckoutSteps.firstTime;
import static com.macys.sdt.projects.Stores.Checkout.utils.CheckoutUtils.CancelTrans;
import static com.macys.sdt.projects.Stores.Checkout.utils.CheckoutUtils.refreshPage;
import static com.macys.sdt.projects.Stores.Checkout.utils.ProductionUtils.VerifyProdbadpin;


public class ProductionSteps {
    @Given("^I verify the Prod Stores \"([^\"]*)\"$")
    public void iVerifyTheProdStores(String StoreNum) throws Throwable {
        Navigate.visit("pdp");
        Wait.forPageReady();
        if (firstTime) {
            System.out.println("I entered the first time LOGIC");
            ProductionUtils produtil = new ProductionUtils();
            produtil.GetandSetProdPDJWT(StoreNum);
            Navigate.visit("pdp");
            firstTime = false;
        }
        if (Elements.elementPresent("home.error_overlay")) {
            Thread.sleep(500);
            refreshPage();
            Thread.sleep(500);
            System.out.println("The 3rd Refresh was needed... For Some reason we saw an adiitional error on startup");
        }
        if (Elements.elementPresent("home.last_trans_abandoned_error")) {
            Clicks.click("home.overlay_close");
            Thread.sleep(500);
            CancelTrans();
            Thread.sleep(500);
            Assert.fail("The previous transaction failed after a successful tender but prior to signature being complete");
        }

        if (Elements.elementPresent("bag_screen.emptyBag")) {
            ProductDiscoverySteps productDiscoveryStep = new ProductDiscoverySteps();
            productDiscoveryStep.iClickOnHamburgerIcon();
            productDiscoveryStep.iClickTheProductDiscoveryHomeMenuOption();

        }


        if (!Elements.findElement("home.bag_action").getText().isEmpty()) {
            System.out.println("THERE WAS SOMETHING IN THE BAG");
            ProductDiscoverySteps productDiscoveryStep = new ProductDiscoverySteps();
            productDiscoveryStep.iClickOnHamburgerIcon();
            CheckoutSteps check = new CheckoutSteps();
            check.iSeeTheCancelTransactionButton();
            check.iClickOnCancelTransationButton();
            Thread.sleep(500);
            Assert.fail("At the start of the transaction there was already an item within the bag");
        }

//
//
//                Wait.secondsUntilElementPresent("bag_screen.emptyBag", 20);
//                if (!Elements.elementPresent("bag_screen.emptyBag")) {
//                    Wait.secondsUntilElementPresent("home.progressBar_bag", 15);
//                    Clicks.click("home.progressBar_bag");
//                    Thread.sleep(500);
//                }

        if (Elements.elementPresent("bag_screen.item_details")) {
            CancelTrans();
            Thread.sleep(500);
            Assert.fail("The previous transaction was not canceled or completed. Please fix");
        }

        Elements.elementShouldBePresent("pdp.verify_page");

    }


    @Given("^I visit the production stores with a bad pin \"([^\"]*)\"$")
    public void iVeriyTheProductionStoresWithABadPin(String Store) throws Throwable {
        Navigate.visit("pdp");
        Wait.forPageReady();
        VerifyProdbadpin(Store);

    }

    @Then("^I can see Sales Trans is up and running$")
    public void iCanSeeTheJWTErrorMessage() throws Throwable {
        Wait.forPageReady();
        Thread.sleep(3000);
        Wait.secondsUntilElementPresent("bag_screen.emptyBag", 10);
        if (Elements.elementPresent("bag_screen.emptyBag")) {
            Elements.elementShouldBePresent("bag_screen.emptyBag");
            System.out.println("This store was in the empty bag");
        }
        if (Elements.elementPresent("home.error_overlay")) {
            Elements.elementShouldBePresent("home.error_overlay");
            String JWTerror = Elements.findElement("bag_screen.confirmation_overlay_header").getText();
            Assert.assertEquals("An error occured when starting the application", JWTerror);
            System.out.println("This store saw the JWT Error");

        }

        if (!(Elements.elementPresent("bag_screen.emptyBag") || Elements.elementPresent("home.error_overlay"))) {
            Wait.secondsUntilElementPresent("bag_screen.emptyBag", 35);
            if (Elements.elementPresent("pdp.bag_error")) {
                if (Elements.findElement("pdp.bag_error").getText().contains("503")) {
                    Assert.fail("When Going to the bag there was a 503 Service Error Message displaying within the bag");
                }

            } else {
                Wait.secondsUntilElementPresent("bag_screen.emptyBag", 30);
                if (!(Elements.elementPresent("bag_screen.emptyBag") || Elements.elementPresent("home.error_overlay"))) {

                    Assert.fail("Sales Trans on this store took longer than 1 minute to respond once the bag icon was clicked. Please investigate.");

                }
            }

        }
    }
}

