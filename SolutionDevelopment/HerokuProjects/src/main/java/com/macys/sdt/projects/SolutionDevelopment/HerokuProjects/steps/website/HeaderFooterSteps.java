package com.macys.sdt.projects.SolutionDevelopment.HerokuProjects.steps.website;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.Cookies;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.projects.SolutionDevelopment.HerokuProjects.actions.website.HeaderFooterActions;
import com.macys.sdt.shared.actions.website.mcom.pages.my_account.CreateProfile;
import com.macys.sdt.shared.steps.MEW.PageNavigation;
import com.macys.sdt.shared.steps.website.CheckoutSteps;
import com.macys.sdt.shared.steps.website.MyAccountSteps;
import com.macys.sdt.shared.utils.CommonUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.StringEscapeUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HeaderFooterSteps extends StepUtils {


    @Given("^I visit the heroku homepage$")
    public void I_visit_the_heroku_homepage() throws Throwable {
        Navigate.visit("/");
        //If the URL passed as parameter is not heroku, then navigate to the correct url
        if (!WebDriverManager.getCurrentUrl().contains("fashion")) {
            System.out.println("Navigating to Heroku page.");
            String heroku_url = Elements.getText("heroku_homepage.url");
            Navigate.visit(heroku_url);
        }
        Cookies.disableForeseeSurvey();
        Assert.assertTrue("The visited page is not Heroku.", Elements.elementPresent("heroku_homepage.verify_page"));
    }

    @Given("^I visit the heroku homepage as a registered user$")
    public void I_visit_the_heroku_homepage_as_a_registered_user() throws Throwable {
        I_visit_the_heroku_homepage();
        CommonUtils.signInOrCreateAccount();
        if (!CommonUtils.isNewProfileCreated) {
            new CheckoutSteps().iRemoveAllItemsInShoppingBag();
            new MyAccountSteps().iNavigateToMyAccountPage();
        }
        CreateProfile.closeSecurityAlertPopUp();
        I_visit_the_heroku_homepage();
    }

    private static boolean validateCopyright() {
        String actualCopyright = Elements.getText("footer.footer_copyright");
        String expectedCopyright = StringEscapeUtils.unescapeHtml4("&#169;") + " " + Calendar.getInstance().get(Calendar.YEAR) + " Bloomingdale's.";
        return actualCopyright.contains(expectedCopyright);
    }

    @Then("^The copyright looks like expected$")
    public void the_copyright_looks_like_expected() throws Throwable {
        Assert.assertTrue("Bloomingdales copyright is not as expected", validateCopyright());
    }

    @And("^I verify that I am on browse page$")
    public void I_verify_that_I_am_on_browse_page() throws Throwable {
        Assert.assertTrue("ERROR-ENV: Not able to navigate to browse page", onPage("search_result"));
    }

    @When("^I click on the Bloomingdale's logo$")
    public void iClickOnTheBloomingdaleSLogo() throws Throwable {
        Clicks.click("heroku_homepage.bloomies_logo");
        System.out.println("URL: " + WebDriverManager.getCurrentUrl());
    }

    @Then("^I verify that I am on the home page$")
    public void iVerifyThatIAmOnTheHomePage() throws Throwable {
        shouldBeOnPage("home");
    }

    //verifying the flyout menu
    @Then("^I should (see|not see) \"([^\"]*)\" flyout$")
    public void I_should_see_flyout_menu(String option, String menu) throws Throwable {
        if (option.equals("see")) {
            switch (menu) {
                case "My Account":
                    Elements.elementShouldBePresent("heroku_homepage.myaccount_flyout_opened");
                    break;
                case "Brown Bag":
                    Elements.elementShouldBePresent("heroku_homepage.bag_flyout_opened");
                    break;
                default:
                    Elements.elementShouldBePresent("heroku_homepage.fob_flyout_opened");
                    break;
            }
        } else {
            //if element still present, then the verification failed
            String error_message = "ERROR: " + menu + " flyout was not closed.";
            switch (menu) {
                case "My Account":
                    Assert.assertFalse(error_message, Elements.elementPresent("heroku_homepage.myaccount_flyout_opened"));
                    break;
                case "Brown Bag":
                    Assert.assertFalse(error_message, Elements.elementPresent("heroku_homepage.bag_flyout_opened"));
                    break;
                default:
                    Assert.assertFalse(error_message, Elements.elementPresent("heroku_homepage.fob_flyout_opened"));
                    break;
            }
        }
    }


    //I hover over "<categories>"
    @When("^I hover over \"([^\"]*)\"$")
    public void i_hover_over(String cat) throws Throwable {
        switch (cat) {
            case "My Account":
                Clicks.hoverForSelection("header.goto_my_account_link");
                System.out.println("Hover over my Account link");
                break;
            case "Brown Bag":
                Clicks.hoverForSelection("header.quickbag_hover");
                break;
            default:
                Clicks.hoverForSelection(By.linkText(cat.toUpperCase()));
                break;
        }
    }

    //I should see topnav section
    @Then("^I should see topnav section$")
    public void I_should_see_topNav_section(List<String> topnav) throws Throwable {

        for (String value : topnav) {
            switch (value) {
                case "Stores & Events":
                    Assert.assertTrue("Unavailable", Elements.elementPresent("header.goto_stores"));
                    break;
                case "Country change":
                    Assert.assertTrue("Unavailable", Elements.elementPresent("header_and_footer.header_international_container"));
                    break;
                case "My account":
                    Assert.assertTrue("Unavailable", Elements.elementPresent("header.goto_my_account_link"));
                    break;
                case "Wishlist":
                    Assert.assertTrue("Unavailable", Elements.elementPresent("header.goto_wishlist"));
                    break;
                case "Brown bag":
                    Assert.assertTrue("Unavailable", Elements.elementPresent("header.quickbag_hover"));
                    break;
                default:
                    Assert.fail("Invalid Topnav category");
                    break;
            }
            System.out.println("Validated topNav Link " + value);
        }
    }

    @When("^I hover over a random FOB flyout of the \"([^\"]*)\" section$")
    public void i_hover_over_a_random_FOB_flyout_of_the_section(String section) throws Throwable {
        Assert.assertTrue("Unable to hover a random flyout", HeaderFooterActions.HoveOverRandomFlyout(section));
    }

    private String subcategory;

    @When("^I click on a random sucabtegory link$")
    public void i_click_on_a_random_link() throws Throwable {
        subcategory = HeaderFooterActions.clickOnRandomFlyOutLink();
        Assert.assertTrue("Unable to click om a subcategory link", subcategory != null);
    }

    private static boolean validateNavigationToSubCategoryPage(String str) {
        String pageHeader = Elements.findElement("category_sub_splash.page_header").getText();
        String pageTitle = PageNavigation.title();
        if (pageHeader.toUpperCase().contains(str.toUpperCase()) || pageTitle.toUpperCase().contains(str.toUpperCase())) {
            return true;
        } else {
            return false;
        }
    }

    @Then("^I verify it navigated to the correct page$")
    public void i_verify_it_navigated_to_the_correct_page$() throws Throwable {
        Assert.assertTrue("Did not navigate to the right page", validateNavigationToSubCategoryPage(subcategory));
    }

    private List<WebElement> getAllSubcategoriesInFlyout() {
        List<WebElement> temSubcategories = Elements.findElements("heroku_homepage.subcategory");
        List<WebElement> subcategories = new ArrayList<>();
        for (WebElement element : temSubcategories) {
            if (element.isDisplayed()) {
                subcategories.add(element);
            }
        }
        return subcategories;
    }

    @Then("^I verify the flyout has more than one subcategory$")
    public void theFlyoutHasMoreThanOneSubcategory() throws Throwable {
        Assert.assertTrue("No flyout is visible", Elements.elementPresent("heroku_homepage.fob_flyout_opened"));
        Assert.assertTrue("The opened flyout doesn't have more than one subcategory", getAllSubcategoriesInFlyout().size() > 1);
    }

    private boolean validateSubcategoriesHaveUrl() {
        List<WebElement> subcategories = getAllSubcategoriesInFlyout();
        boolean result = true;
        for (WebElement element : subcategories) {
            String href = element.getAttribute("href");
            if (href == null || href == "" || href == " ") {
                result = false;
            }
        }
        return result;
    }

    @And("^I verify that all of the subcategories in the flyout have a correspondent url$")
    public void allOfTheSubcategoriesInTheFlyoutHaveACorrespondentUrl() throws Throwable {
        Assert.assertTrue("There are subcategories that don't have a correspondent url", validateSubcategoriesHaveUrl());
    }

    @Then("^I verify the footer icon is present and has the correct url$")
    public void iVerifyTheFooterIconIsPresentAndHasTheCorrectUrl(List<String> socialMedias) throws Throwable {
        String href;
        for (String socialMedia : socialMedias) {
            switch (socialMedia) {
                case "Facebook":
                    Assert.assertTrue("Facebook icon is not present", HeaderFooterActions.Facebook());
                    href = Elements.getElementAttribute("footer.facebook_icon", "href");
                    Assert.assertTrue("Facebook url is incorrect: " + href, href.toLowerCase().contains("facebook.com/bloomingdales"));
                    break;
                case "Twitter":
                    Assert.assertTrue("Twitter icon is not present", HeaderFooterActions.Twitter());
                    href = Elements.getElementAttribute("footer.twitter_icon", "href");
                    Assert.assertTrue("Twitter url is incorrect: " + href, href.toLowerCase().contains("twitter.com/#!/bloomingdales"));
                    break;
                case "Pinterest":
                    Assert.assertTrue("Pinterest icon is not present", HeaderFooterActions.Pinterest());
                    href = Elements.getElementAttribute("footer.pinterest_icon", "href");
                    Assert.assertTrue("Pinterest url is incorrect: " + href, href.toLowerCase().contains("pinterest.com/bloomingdales"));
                    break;
                case "Instagram":
                    Assert.assertTrue("Instagram icon is not present", HeaderFooterActions.Instagram());
                    href = Elements.getElementAttribute("footer.instagram_icon", "href");
                    Assert.assertTrue("Instagram url is incorrect: " + href, href.toLowerCase().contains("instagram.com/bloomingdales"));
                    break;
                case "Mobile":
                    Assert.assertTrue("Mobile icon is not present", HeaderFooterActions.Mobile());
                    href = Elements.getElementAttribute("footer.mobile_icon", "href");
                    Assert.assertTrue("Mobile url is incorrect: " + href, href.toLowerCase().contains("bloomingdales.com/media/about/mobile.jsp"));
                    break;
                default:
                    Assert.fail("Invalid Social Media");
                    break;
            }
        }
    }
}
