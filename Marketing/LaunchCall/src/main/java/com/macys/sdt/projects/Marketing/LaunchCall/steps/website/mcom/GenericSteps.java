package com.macys.sdt.projects.Marketing.LaunchCall.steps.website.mcom;

import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.model.CreditCard;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.rest.utils.RESTOperations;
import com.macys.sdt.projects.Marketing.LaunchCall.utils.mcom.GenericUtils;
import com.macys.sdt.projects.Marketing.OCWallet.actions.website.mcom.pages.Wallet;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.macys.sdt.shared.actions.website.mcom.pages.my_account.MyWallet.addCard;


public class GenericSteps extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(GenericSteps.class);

    @And("^I verify all links in homepage return valid response$")
    public void iVerifyLinksInHomePage() throws Throwable {

        List<WebElement> elems = null;
        String[] tags = {"a", "area"};

        for (String tag : tags) {
            switch (tag) {
                case "a":
                    elems = Elements.findElements(By.tagName("a"));
                    GenericUtils.validateLinks(elems);
                    break;
                case "area":
                    elems = Elements.findElements(By.tagName("area"));
                    GenericUtils.validateLinks(elems);
                    break;
            }
        }

        if (!(GenericUtils.failedLinks == null)) {
            logger.info("<<<<<<<<<<<<<<<<<<LIST OF FAILED LINKS>>>>>>>>>>>>>>>>>>");
            for (Map.Entry link : GenericUtils.failedLinks.entrySet()) {
                logger.info(link.getKey() + " " + link.getValue());
            }
        }
    }

    @And("^I verify FOBS are displayed and return a (\\d+) OK$")
    public void iVerifyFOBSAreDisplayedAndReturnAOK(int httpCode, DataTable table) throws Throwable {

        String url = null;
        String FOBName = null;
        for (Map<String, String> row : table.asMaps(String.class, String.class)) {

            switch (row.get("FOBS")) {
                case "BED & BATH":
                    url = Elements.findElement("home.bedbath_fob").getAttribute("href");
                    FOBName = Elements.findElement("home.bedbath_fob").getText();
                    break;
                case "WOMEN":
                    url = Elements.findElement("home.women_fob").getAttribute("href");
                    FOBName = Elements.findElement("home.women_fob").getText();
                    break;
                case "SHOES":
                    url = Elements.findElement("home.shoes_fob").getAttribute("href");
                    FOBName = Elements.findElement("home.shoes_fob").getText();
                    break;
                case "HANDBAGS":
                    url = Elements.findElement("home.handbags_fob").getAttribute("href");
                    FOBName = Elements.findElement("home.handbags_fob").getText();
                    break;
                case "JEWELRY":
                    url = Elements.findElement("home.jewelry_fob").getAttribute("href");
                    FOBName = Elements.findElement("home.jewelry_fob").getText();
                    break;
                case "BEAUTY":
                    url = Elements.findElement("home.beauty_fob").getAttribute("href");
                    FOBName = Elements.findElement("home.beauty_fob").getText();
                    break;
                case "MEN":
                    url = Elements.findElement("home.men_fob").getAttribute("href");
                    FOBName = Elements.findElement("home.men_fob").getText();
                    break;
                case "KIDS":
                    url = Elements.findElement("home.kids_fob").getAttribute("href");
                    FOBName = Elements.findElement("home.kids_fob").getText();
                    break;
                case "HOME":
                    url = Elements.findElement("home.home_fob").getAttribute("href");
                    FOBName = Elements.findElement("home.home_fob").getText();
                    break;
                case "JUNIORS":
                    url = Elements.findElement("home.juniors_fob").getAttribute("href");
                    FOBName = Elements.findElement("home.juniors_fob").getText();
                    break;
                case "WATCHES":
                    url = Elements.findElement("home.watches_fob").getAttribute("href");
                    FOBName = Elements.findElement("home.watches_fob").getText();
                    break;
                case "ACTIVE":
                    url = Elements.findElement("home.active_fob").getAttribute("href");
                    FOBName = Elements.findElement("home.active_fob").getText();
                    break;
                case "BRANDS":
                    url = Elements.findElement("home.brands_fob").getAttribute("href");
                    FOBName = Elements.findElement("home.brands_fob").getText();
                    break;
                case "STARTER IDEAS":
                    url = Elements.findElement("registry_home.starter_ideas_fob").getAttribute("href");
                    FOBName = Elements.findElement("registry_home.starter_ideas_fob").getText();
                    break;
                case "WEDDING SHOP":
                    url = Elements.findElement("registry_home.wedding_shop_fob").getAttribute("href");
                    FOBName = Elements.findElement("registry_home.wedding_shop_fob").getText();
                    break;
                case "WEDDING REGISTRY":
                    url = Elements.findElement("registry_home.wedding_registry_fob").getAttribute("href");
                    FOBName = Elements.findElement("registry_home.wedding_registry_fob").getText();
                    break;
                case "DINING":
                    url = Elements.findElement("registry_home.dining_fob").getAttribute("href");
                    FOBName = Elements.findElement("registry_home.dining_fob").getText();
                    break;
                case "KITCHEN":
                    url = Elements.findElement("registry_home.kitchen_fob").getAttribute("href");
                    FOBName = Elements.findElement("registry_home.kitchen_fob").getText();
                    break;
                case "LUGGAGE":
                    url = Elements.findElement("registry_home.luggage_fob").getAttribute("href");
                    FOBName = Elements.findElement("registry_home.luggage_fob").getText();
                    break;
                case "CLEANING & ORGANIZING":
                    url = Elements.findElement("registry_home.cleaning_fob").getAttribute("href");
                    FOBName = Elements.findElement("registry_home.cleaning_fob").getText();
                    break;
                case "HOME DECOR":
                    url = Elements.findElement("registry_home.home_decor_fob").getAttribute("href");
                    FOBName = Elements.findElement("registry_home.home_decor_fob").getText();
                    break;
            }
            Map<String, String> headers = new HashMap<>();

            Response response = RESTOperations.doGET(url, headers);
            int statusCode = response.getStatus();
            Assert.assertTrue(FOBName + " FOB returned " + statusCode + " on get call",
                    statusCode == httpCode || statusCode == 302 || statusCode == 301);
        }
    }

    @Then("^I verify TOPNAV elements are displayed and return a (\\d+) OK$")
    public void iVerifyTOPNAVElementsAreDisplayedAndReturnAOK(int httpCode, DataTable table) throws Throwable {

        String url = null;
        String TopNavName = null;
        for (Map<String, String> row : table.asMaps(String.class, String.class)) {

            switch (row.get("TOPNAV")) {
                case "sign in":
                    url = Elements.findElement("home.goto_sign_in_link").getAttribute("href");
                    TopNavName = Elements.findElement("home.goto_sign_in_link").getText();
                    break;
                case "my account":
                    url = Elements.findElement("home.goto_guest_my_account").getAttribute("href");
                    TopNavName = Elements.findElement("home.goto_guest_my_account").getText();
                    break;
                case "stores":
                    url = Elements.findElement("home.stores_header").getAttribute("href");
                    TopNavName = Elements.findElement("home.stores_header").getText();
                    break;
                case "deals":
                    url = Elements.findElement("home.deals_header").getAttribute("href");
                    TopNavName = Elements.findElement("home.deals_header").getText();
                    break;
                case "lists":
                    url = Elements.findElement("home.lists_header").getAttribute("href");
                    TopNavName = Elements.findElement("home.lists_header").getText();
                    break;
                case "gifts":
                    Assert.assertTrue("Gifts top nav could not be found",
                            Elements.elementPresent("home.gifts_header"));
                    break;
                case "wedding registry":
                    url = Elements.findElement("home.goto_wedding_registry").getAttribute("href");
                    TopNavName = Elements.findElement("home.goto_wedding_registry").getText();
                    break;
                case "shipping to":
                    url = Elements.findElement("home.shipping_to_header").getAttribute("href");
                    TopNavName = Elements.findElement("home.shipping_to_header").getText();
                    break;
                case "IN flag":
                    url = Elements.findElement("home.shipping_to_header").getAttribute("href");
                    Assert.assertTrue("Flag image could not be found",
                            Elements.elementPresent("home.flag_image"));
            }
            Map<String, String> headers = new HashMap<>();

            if (!TopNavName.equals("GIFTS")) {
                if (!url.contains("http")) {
                    url = RunConfig.url + url;
                }
                Response response = RESTOperations.doGET(url, headers);
                int statusCode = response.getStatus();
                Assert.assertTrue(TopNavName + " Top NAV did not return 200 or 302 on GET call",
                        statusCode == httpCode || statusCode == 302);
            }
        }
    }

    @Then("^I validate all links return valid response$")
    public void iValidateAllTheSubNavLinksReturnValidResponse() throws Throwable {

        List<WebElement> allLinks = Elements.findElements(By.tagName("a"));
        String[] elems = {"Wallet", "Star Rewards", "Plenti", "FAQs", "about security", "about privacy",
                             "about credit card privacy", "deals & promotions"};
        By[] bys = new By[elems.length];
        for (int i = 0; i < elems.length; i++) {
            bys[i] = By.linkText(elems[i]);
            GenericUtils.validateResponse(Elements.findElement(bys[i]));
        }
    }
}
