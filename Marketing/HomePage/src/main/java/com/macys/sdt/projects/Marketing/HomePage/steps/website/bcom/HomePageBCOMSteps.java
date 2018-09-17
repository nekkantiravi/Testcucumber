package com.macys.sdt.projects.Marketing.HomePage.steps.website.bcom;


import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.Cookies;
import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.framework.utils.rest.utils.RESTOperations;
import com.macys.sdt.projects.Discovery.Regression.steps.website.Header;
import com.macys.sdt.shared.actions.website.mcom.pages.home.Home;
import com.macys.sdt.shared.steps.website.PageNavigation;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.shared.steps.website.HeaderAndFooter;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import javax.ws.rs.core.Response;
import java.net.URLDecoder;
import java.util.*;
import java.util.stream.Collectors;

import static com.macys.sdt.framework.interactions.Clicks.click;
import static com.macys.sdt.framework.interactions.Navigate.switchWindow;

public class HomePageBCOMSteps extends StepUtils{
    HeaderAndFooter headerAndFooter  = new HeaderAndFooter();
    Home home                        = new Home();
    PageNavigation pageNavigation    = new PageNavigation();
    Header header                    = new Header();
    static String urlBuffer          = null;

    @When("^I navigate to category page$")
    public void iNavigateToCategoryPage(List<String> brands, List<String> pageNames) throws Throwable {
        Wait.forPageReady();
        String category= null;

        if (macys()) {
            category=pageNames.get(brands.indexOf("MCOM"));
        }
        else if (bloomingdales()) {
            category=pageNames.get(brands.indexOf("BCOM"));
        }

        if (category == null){
            throw new Exception("invalid input parameter");
        }
        home.selectMainCategory(category);
    }


    @When("^I navigate to category page from flyout menu$")
    public void iNavigateToCategoryPageFromFlyoutMenu(List<String> brands, List<String>pageNames) throws Throwable {
        Wait.forPageReady();
        String category= null;

        if (macys()) {
            category=pageNames.get(brands.indexOf("MCOM"));
        }
        else if (bloomingdales()) {
            category=pageNames.get(brands.indexOf("BCOM"));
        }

        if (category == null){
            throw new Exception("invalid input parameter");
        }

        //open flyout
        pageNavigation.I_mouse_over_category_from_top_navigation(category);
        // select category
        home.selectMainCategory(category);
    }



    @Then("^I should see list of brands$")
    public void iShouldSeeListOfBrands() throws Throwable {
        List<WebElement> brands = Elements.findElements("designer_static.brands");

        Assert.assertTrue("brands not displayed", brands.size() > 0);
    }


    @And("^I click on facet$")
    public void iClickOnFacet() throws Throwable {
        try {
            List<WebElement> facets = Elements.findElements("search_result.facet_select");
            WebElement randomFacet = facets.get(new Random().nextInt(facets.size()));
            click(randomFacet);
            List<WebElement> subfacet = Elements.findElement("search_result.sub_facets").findElements(By.className("display"));
            WebElement randomSubFacet = subfacet.get(new Random().nextInt(subfacet.size()));
            click(randomSubFacet);

        } catch (Exception e) {
            Assert.fail("Error in selecting a facet");
        }
    }


    @And("^I mouse over on the below \"([^\"]*)\" fob's$")
    public void iMouseOverOnTheBelowFobS(String category) throws Throwable {
        //open flyout
        pageNavigation.I_mouse_over_category_from_top_navigation(category);
    }


    @When("^I mouse over on \"([^\"]*)\" category from top navigation$")
    public void iMouseOverOnCategoryFromTopNavigation(String category) throws Throwable {
        //open flyout
        pageNavigation.I_mouse_over_category_from_top_navigation(category);
    }



    @And("^I should not be able to see \"([^\"]*)\" cookie$")
    public void iShouldNotBeAbleToSeeCookie(String cookieName) throws Throwable {
        Assert.assertTrue(Cookies.getCookieValue(cookieName).isEmpty());
    }

    @And("^I verify FOBS are displayed and return (\\d+) OK$")
    public void iVerifyAreDisplayedAndReturnAOK(int code, DataTable fobTable) throws Throwable {

        List<String> fobs = fobTable.asList(String.class);
        for (String fob : fobs) {
          //  code = fob.equals("THE REGISTRY") && (Elements.findElement("home.iship_flag_img")
                 //   .getAttribute("src").contains("US")) ? 302 : 200;
            headerAndFooter.iHoverOverOnTheBelowFobS(fob);
            if ((!fob.equals("THE REGISTRY"))  || (Elements.findElement("home.iship_flag_img")
                    .getAttribute("src").contains("US"))) {
                if (!(fob.equals("SALE")  && WebDriverManager.getCurrentUrl().contains("registry")))
                headerAndFooter.iVerifyThatFlyoutMenuIsDisplayed();
            }
            String fobName = "home.";

            switch (fob) {
                case "JEWELRY & ACCESSORIES":
                    fobName = fobName + "jewelry";
                    break;
                case "WHAT'S NEW":
                    fobName = fobName + "whatsnew";
                    break;
                case "DINING & ENTERTAINING":
                    fobName = fobName + "dining";
                    break;
                case "BED & BATH":
                    fobName = fobName + "bedbath";
                    break;
                case "HOME DECOR":
                    fobName = fobName + "homedecor";
                    break;
                case "HOME CARE & TECH":
                    fobName = fobName + "homecaretech";
                    break;
                case "GETTING STARTED":
                    fobName = fobName + "gettingstarted";
                    break;
                case "THE REGISTRY":
                    fobName = fobName + "theregistry";
                    break;
                case "DESIGNERS":
                    fobName = fobName + "designers";
                    break;
                case "WOMEN":
                    fobName = fobName + "women";
                    break;
                case "SHOES":
                    fobName = fobName + "shoes";
                    break;
                case "HANDBAGS":
                    fobName = fobName + "handbags";
                    break;
                case "BEAUTY":
                    fobName = fobName + "beauty";
                    break;
                case "MEN":
                    fobName = fobName + "men";
                    break;
                case "KIDS":
                    fobName = fobName + "kids";
                    break;
                case "HOME":
                    fobName = fobName + "home";
                    break;
                case "GIFTS":
                    fobName = fobName + "gifts";
                    break;
                case "SALE":
                    fobName = fobName + "sale";
                    break;
                case "BRANDS":
                    fobName = fobName + "brands";
                    break;
                case "KITCHEN":
                    fobName = fobName + "kitchen";
                    break;
                case "LUGGAGE":
                    fobName = fobName + "luggage";
                    break;
                default:
                    fobName = fobName + fob;
            }

            fobName = fobName + "_fob";

            // if registry menu
           // if (home.getExpectedMainCategories("registry").contains(fob)) {
             //   fobName = "registry_" + fobName;
           // }

            String link = Elements.findElement(fobName).getAttribute("href");
            Response response = RESTOperations.doGET(link, null);
            Assert.assertTrue(fob + " did not return " + code + " , got " + response.getStatus() + " instead",
                    response.getStatus() == code || response.getStatus() == 302);
        }
    }

    @Then("^I verify INFO and EXCLUSIONS link is not displayed$")
    public void iVerifyINFOAndEXCLUSIONSLinkIsNotDisplayed() throws Throwable {

        if (macys()) {
            Assert.assertFalse(Elements.elementPresent("home.nav_banner"));
        } else {
            Assert.assertFalse(Elements.elementPresent("home.nav_banner"));
        }
    }

    @And("^I verify Info and Exclusions Popup is rendered with content$")
    public void iVerifyInfoAndExclusionsPopupIsRenderedWithContent() throws Throwable {
        Utils.threadSleep(5000, null);
        switchWindow(1);
        Assert.assertTrue("CLOSE WINDOW button is not displayed ",Elements.elementPresent("info_exclusions.close_window"));
        Assert.assertTrue("LEARN MORE link is not displayed", Elements.elementPresent("info_exclusions.learn_more"));
        Assert.assertTrue("FREE SHIPPING is not displayed",Elements.elementPresent("info_exclusions.free_shipping"));
        Assert.assertTrue("FREE RETURNS is not displayed", Elements.elementPresent("info_exclusion.free_return"));
        Assert.assertTrue("BUY ONLINE, PICK UP IN STORE is not displayed", Elements.elementPresent("info_exclusion.bops"));

        //close window
        click("info_exclusion.close_window");
    }

    @When("^I click on the \"([^\"]*)\" link$")
    public void iClickOnTheLink(String link) throws Throwable {
        header.i_click_on_link(link);
    }

    @Then("^I verify Seasonal Action is displayed$")
    public void iVerifySeasonalActionIsDisplayed() throws Throwable {
        header.i_verify_seasonal_action_wrapper("is");
    }

    @Then("^I verify that it is closed$")
    public void iVerifyThatItIsClosed() throws Throwable {
        header.i_verify_that_Seasonal_Action_wrapper_is_closed();
    }

    @Then("^I verify I land on the Home Page$")
    public void iVerifyILandOnTheHomePage() throws Throwable {
        pageNavigation.I_should_see_the_page("home");
    }

    @And("^I verify the Seasonal Image link is reachable$")
    public void iVerifyTheSeasonalImageLinkIsReachable() throws Throwable {
        // verify seasonal link is displayed
        header.i_verify_seasonal_action_wrapper("is");

        // get image url
        String image_url = Elements.findElement(By.id("seasonalActionOff")).getAttribute("src");

        // verify image reachable
        Assert.assertTrue("Seasonal Image link is not reachable",
                RESTOperations.doGET(image_url, null).getStatus() == 200);
    }

    @Then("^I verify logo is displayed and returns (\\d+) OK$")
    public void iVerifyLogoIsDisplayedAndReturnsOK(int code) throws Throwable {

        // verify logo displayed
        WebElement logoElement = Elements.findElement("home.verify_page");
        Assert.assertNotNull("logo not displayed", logoElement);

        // get logo url
        String logo_url = logoElement.getAttribute("src");

        // verify logo returns code
        Assert.assertTrue("logo not returns " + code,
                RESTOperations.doGET(logo_url, null).getStatus() == 200);


    }


/* Duplicated steps - both available in shared steps
    @Then("^I verify that Page Title should have between (\\d+) - (\\d+) characters$")
    public void iVerifyThatPageTitleShouldHaveBetweenCharacters(int low, int high) throws Throwable {
        String pageTitle = WebDriverManager.getWebDriver().getTitle();
        Assert.assertTrue("page title size should be less than or equal to " + high , pageTitle.length() <=high);
        Assert.assertTrue("page title size should be greater than or equal to " + low, pageTitle.length() >= low);
    }

    @Then("^I verify that Meta Description should have between (\\d+) - (\\d+) characters$")
    public void iVerifyThatMetaDescriptionShouldHaveBetweenCharacters(int low, int high) throws Throwable {
        String metaDescription = Elements.findElement("header.meta_description").getAttribute("content");
        Assert.assertTrue("page title size should be less than or equal to " + high , metaDescription.length() <=high);
        Assert.assertTrue("page title size should be greater than or equal to " + low, metaDescription.length() >= low);
    }
*/

  /*  @Then("^I \"([^\"]*)\" see canonical tag pointing to the corresponding URL added in the header section$")
    public void iSeeCanonicalTagPointingToTheCorrespondingURLAddedInTheHeaderSection(String arg0) throws Throwable {

        if (arg0.equals("should")) {
            String canonicalURL = URLDecoder.decode(Elements.findElement("header.canonical_tag").getAttribute("href"), "UTF-8");
            String pageURL = URLDecoder.decode(WebDriverManager.getCurrentUrl(), "UTF-8");

            Assert.assertTrue("canonical tag not point to corresponding URL", canonicalURL.contains(pageURL) || pageURL.contains(canonicalURL));
        }
        else {
            throw new Exception("ERROR: " + arg0 + " not supported");
        }
    }

    @Then("^I \"([^\"]*)\" see the <link rel=\"([^\"]*)\" media=\"([^\"]*)\" href=\"([^\"]*)\"> tag pointing to the corresponding URL added in the header section$")
    public void iSeeLinkRelMediaHrefTagPointingToTheCorrespondingURLAddedInTheHeaderSection(String arg0, String arg1, String arg2, String arg3) throws Throwable {
        if (arg0.equals("should") && arg1.equals("alternate")) {
            String alternateMedia = Elements.findElement("header.alternate_tag").getAttribute("media");

            String pageURL = URLDecoder.decode(WebDriverManager.getCurrentUrl(), "UTF-8");
            String alternateURL = URLDecoder.decode(Elements.findElement("header.alternate_tag").getAttribute("href"), "UTF-8");

            Assert.assertTrue("alternate media error", arg2.equals(alternateMedia));
            Assert.assertTrue("page URL not matched alternate tag url",
                    alternateURL.substring(alternateURL.indexOf('.'),alternateURL.length()).equals(pageURL.substring(pageURL.indexOf('.'),pageURL.length())));

        }
        else {
            throw new Exception("ERROR: " + arg0 + " and " + arg1 + " not supported");
        }

    }*/

    @Then("^I verify page url matches alternate tag url$")
    public void iVerifyPageUrlMatchesAlternateTagUrl() throws Throwable {
        String alternateURL = URLDecoder.decode(Elements.findElement("header.alternate_tag").getAttribute("href"), "UTF-8");
        String pageURL      = URLDecoder.decode(WebDriverManager.getCurrentUrl(), "UTF-8");

        Assert.assertTrue("page URL not matched alternate tag url",
                alternateURL.substring(alternateURL.indexOf('.'),alternateURL.length()).equals(pageURL.substring(pageURL.indexOf('.'),pageURL.length())));
    }

    @And("^I verify alternate tag url references mobile page$")
    public void iVerifyAlternateTagUrlReferencesMobilePage() throws Throwable {
        String alternateURL = URLDecoder.decode(Elements.findElement("header.alternate_tag").getAttribute("href"),"UTF-8");
        Assert.assertTrue(" alternate tag url doesn't reference mobile page", alternateURL.startsWith("m."));
    }



    @And("^I navigate to Brand Index page of specific FOB category in \"([^\"]*)\" mode$")
    public void iNavigateToBrandIndexPageOfSpecificFOBCategoryInMode(String arg0) throws Throwable {
        pageNavigation.I_navigate_to_page_in_mode("brand index", arg0.toLowerCase());

    }

    @When("^I remove the following cookies from the browser:$")
    public void iRemoveTheFollowingCookiesFromTheBrowser(DataTable cookieTable) throws Throwable {
        List<String> cookieList = cookieTable.asList(String.class);
        for (String cookie : cookieList){
            switch (cookie) {
                case "Cookie Name":
                    break;
                default:
                    Cookies.deleteCookie(cookie);
            }
        }
    }


    @When("^I append \"([^\"]*)\" to the current URL$")
    public void iAppendToTheCurrentURL(String arg0) throws Throwable {
        String currentURL =  WebDriverManager.getCurrentUrl();
        // replace ? with &
        urlBuffer = (currentURL.contains("?"))?  currentURL + "&" + arg0.substring(1,arg0.length()) : currentURL + arg0 ;
        WebDriverManager.getWebDriver().get(urlBuffer);
    }


    @Then("^I verify that monetate top banner is visible to the customer$")
    public void iVerifyThatMonetateTopBannerIsVisibleToTheCustomer() throws Throwable {
        Assert.assertTrue("monetate top banner not visible", Wait.secondsUntilElementPresent("monetate_banners.monate_top_banner",15));
    }

    @Then("^I verify the monetate banner is visible and can be closed$")
    public void iVerifyTheMonetateBannerIsVisibleAndCanBeClosed() throws Throwable {
        Assert.assertTrue("monetate banner not visible", Wait.secondsUntilElementPresent("monetate_banners.monetate_lightbox_content",15));
        Clicks.click("monetage_banners.monaetate.lightbox");
    }

    @Then("^I verify monetate banner can be closed by clicking outside of the banner$")
    public void iVerifyMonetateBannerCanBeClosedByClickingOutsideOfTheBanner() throws Throwable {
        Assert.assertTrue("monetate overlay not visible", Wait.secondsUntilElementPresent("monetate_banners.monetate_lightbox",15));
        Clicks.click("monetage_banners.monaetate.lightbox_container");

    }

    @And("^I verify content is displayed properly$")
    public void iVerifyContentIsDisplayedProperly() throws Throwable {
        // verify global container displayed
        WebElement globalContainer = Elements.findElement("home.global_container");
        Assert.assertTrue("global container not displayed", globalContainer.isDisplayed());

        // verify image links displayed
        List<String> imageSrcList  = globalContainer.findElements(By.tagName("img")).stream()
                .map(e -> e.getAttribute("src"))
                .collect(Collectors.toList());

        for (String image_url : imageSrcList) {
            Assert.assertTrue("Seasonal Image link is not reachable",
                    RESTOperations.doGET(image_url, null).getStatus() == 200);
        }
    }


    @And("^I should see Registry Home page$")
    public void iShouldSeeRegistryHomePage() throws Throwable {

        //verify url, title, and elements presented
        Assert.assertTrue(WebDriverManager.getWebDriver().getCurrentUrl().contains("wedding-registry"));
        Assert.assertTrue(WebDriverManager.getWebDriver().getTitle().contains("Wedding Registry & Gift Registry at Bloomingdale's"));

        Assert.assertTrue("Create Registry button doesn't exist",Elements.elementPresent("registry_home.goto_create_registry"));
        Assert.assertTrue("sign in button doesn't exist", Elements.elementPresent("registry_home.sign_in_button"));
        Assert.assertTrue("find button doesn't exist", Elements.elementPresent("registry_home.find_button"));
        Assert.assertTrue("email field doesn't exist",Elements.elementPresent("registry_home.sign_in_email"));
        Assert.assertTrue("password field doesn't exist",Elements.elementPresent("registry_home.sign_in_password"));
        Assert.assertTrue("first name search field doesn't exist",Elements.elementPresent("registry_home.search_first_name"));
        Assert.assertTrue("last name search field doesn't exist",Elements.elementPresent("registry_home.search_last_name"));
        Assert.assertTrue("start here button doesn't exist", Elements.elementPresent("registry_home.goto_start_here"));
        Assert.assertTrue("registry logo doesn't exist", Elements.elementPresent("registry_home.registry_logo"));

    }

    @When("^I click on INFO and EXCLUSIONS link on top of search field$")
    public void iClickOnINFOAndEXCLUSIONSLinkOnTopOfSearchField() throws Throwable {

        Wait.forPageReady();
        Wait.untilElementPresent("header.nav_banner");
        Elements.findElement("header.nav_banner").click();
    }
}
