package com.macys.sdt.projects.Architecture.Experimentation.steps.website.mcom;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.framework.utils.rest.services.UserProfileService;
import com.macys.sdt.projects.Architecture.Experimentation.actions.website.mcom.pages.HomePage;
import com.macys.sdt.projects.Architecture.Experimentation.actions.website.mcom.pages.SignInPage;
import com.macys.sdt.shared.actions.website.mcom.pages.home.Home;
import com.macys.sdt.shared.actions.website.mcom.pages.my_account.CreateProfile;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import java.util.List;

/**
 * Created by Admin on 5/10/2017.
 */
public class HomepageSteps extends StepUtils {
    Home homePage = new Home();
    static String product_Name =  null;
    static String product_Image = null;
    static String product_Final_Cost = null;
    static String promocode_Name = null;
    static int discountPercentageValue=-1;
    static float offerPrice=-1.0f;
    static float salePrice=-1.0f;

    @Then("^I verify the Fob's displayed:$")
    public void i_verify_the_Fob_s_displayed(List<String> fob_Links) throws Throwable {

        for (String fobElement: fob_Links) {
           Assert.assertTrue(HomePage.verifyFobListElement(fobElement));
        }
    }

    @Then("^I should navigate to \"([^\"]*)\" sub category page$")
    public void i_should_navigate_to_sub_category_page(String arg1) throws Throwable {
        Assert.assertTrue(HomePage.verifySubCategoryPage(arg1));
    }


    @When("^I click on SignIn link on home page$")
    public void i_click_on_SignIn_link_on_home_page() throws Throwable {
        HomePage.clickSignInLink();
    }

    @Then("^I click on create button on sign in page$")
    public void i_click_on_create_button_on_sign_in_page() throws Throwable {
        SignInPage.clickOnCreateProfileButton();
    }


    @Then("^I click on Create button after filling all the required details$")
    public void I_click_on_Create_button_after_filling_all_the_required_details() throws Throwable {
        TestUsers.clearCustomer();
        CreateProfile.createProfile(TestUsers.getCustomer(null));
        if (!prodEnv() && !onPage("my_account")) {
            Assert.fail("New Profile is not created");
        }
        Wait.forPageReady();
        CreateProfile.closeSecurityAlertPopUp();
        TestUsers.currentEmail = TestUsers.getCustomerInformation().getUser().getProfileAddress().getEmail();
        TestUsers.currentPassword = TestUsers.getCustomerInformation().getUser().getLoginCredentials().getPassword();
        if (Elements.elementPresent("my_account.one_time_add_card_overlay")) {
            CreateProfile.closeSecurityAlertPopUp();
            Clicks.click("my_account.add_card_overlay_no_thanks_button");
        }
        CreateProfile.closeSecurityAlertPopUp();
        Utils.threadSleep(9000, null);
    }

    @When("^I enter valid email-id and password$")
    public void i_enter_valid_email_id_and_password() throws Throwable {
        UserProfileService.createRandomUserProfile();
        SignInPage.enterEmailAndPassword();
    }

    @When("^I enter valid email-id and password after clicking on SignIn link on home page$")
    public void i_enter_valid_email_id_and_password_after_clicking_on_SignIn_link_on_home_page() throws Throwable {
        UserProfileService.createRandomUserProfile();
        SignInPage.enterEmailAndPassword();
    }

    @When("^I click on SignIn button$")
    public void i_click_on_SignIn_button() throws Throwable {
        SignInPage.clickOnSignInButton();
    }

    @Then("^I should navigate to Home page$")
    public void i_should_navigate_to_Home_page() throws Throwable {
        Assert.assertTrue(HomePage.verifyHomePageAfterLogIn());
    }

    @When("^I enter \"([^\"]*)\" in search text box$")
    public void i_enter_in_search_text_box(String arg1) throws Throwable {
        HomePage.typeTextInSearchBox(arg1);

    }

    @Then("^I verify that \"([^\"]*)\" should appears on browse page$")
    public void i_verify_that_should_appears_on_browse_page(String arg1) throws Throwable {
     String text=   HomePage.verifyQuantityAndSearchText();
     String array[]= text.split("&");
     Assert.assertTrue(Integer.parseInt(array[0])>0 && array[1].equalsIgnoreCase(arg1));
    }

    @Then("^I verify that (\\d+) result for \"([^\"]*)\" should display$")
    public void i_verify_that_result_for_should_display(int arg1, String arg2) throws Throwable {
        String text=   HomePage.verifyQuantityAndSearchText();
        String array[]= text.split("&");
        Assert.assertTrue(Integer.parseInt(array[0])==arg1 && array[1].equalsIgnoreCase(arg2));
    }

    //This method selects the random product based on the parameter given.(It accepts "ProductFinalCost/ProductPromoCode" parameters).
    @When("^I select the random product with \"([^\"]*)\"$")
    public void i_select_the_random_product_with(String arg1) throws Throwable {
        Wait.untilElementPresent("product_display.loader_completed");
        try{
            String searchText=arg1.toLowerCase();
            if(searchText.contains("name")){
                getProductData("search_result.product_thumbnail_link_name", searchText);
            }
            if(searchText.contains("image")){
                getProductData("search_result.product_thumbnail_link_image", searchText);
            }
            if(searchText.contains("finalcost")) {
                getProductData("search_result.product_final_Cost", searchText);
            }
            if(searchText.contains("code")){
                getProductData("homePage.Promo_Code_Products", searchText);
            }
        }catch(Exception e){
            Assert.fail("failed while selecting the products on the basis on given keyword.");
        }
    }

    public static void getProductData(String selector, String dataToSearch) {
        WebElement selectedElement = Elements.getRandomElement(selector);
        if (dataToSearch.contains("name")) {
            try {
                product_Name = selectedElement.getText();
                Clicks.click(selectedElement);
            }
            catch(Exception e){
                product_Name = selectedElement.getText();
                Clicks.click(selectedElement);
            }
        } else if (dataToSearch.contains("image")) {
            try{
                product_Image = selectedElement.getAttribute("src");
                Clicks.click(selectedElement);}
            catch(Exception e){
                product_Image = selectedElement.getAttribute("src");
                Clicks.click(selectedElement);
            }
        }
        else if(dataToSearch.contains("finalcost"))
        {
            try{
                product_Final_Cost = selectedElement.getText();
                Clicks.click( Elements.paramElement("search_result.product_final_cost_with_price", product_Final_Cost ));
            }catch(Exception e) {
                product_Final_Cost = selectedElement.getText();
                Clicks.click( Elements.paramElement("search_result.product_final_cost_with_price", product_Final_Cost ));
            }
        }
        else if(dataToSearch.contains("code"))
        {
            String promocode_WithOffer=null;
            String promocode_Sale = null;
            try{
                WebElement selectedElement1 = Elements.getRandomElement("homePage.Promo_Code_Products");
                promocode_Name = selectedElement1.getText();
                WebElement selectedElement2 = Elements.getRandomElement(Elements.paramElement("homePage.Promo_Code_WithOffer", promocode_Name ));
                promocode_WithOffer = selectedElement2.getText();
                WebElement selectedElement3 = Elements.getRandomElement(Elements.paramElement("homePage.Promo_Code_Sale", promocode_WithOffer, promocode_Name ));
                promocode_Sale = selectedElement3.getText();
                calculatePromoCodeValues(promocode_WithOffer, promocode_Sale);
                Clicks.click( Elements.paramElement("homePage.Promo_Code_Selected_Product", promocode_WithOffer, promocode_Name, promocode_Sale ));
            }catch(Exception e){
                Wait.untilElementPresent("product_display.loader_completed");
                WebElement selectedElement1 = Elements.getRandomElement("home.Promo_Code_Products");
                promocode_Name = selectedElement1.getText();
                WebElement selectedElement2 = Elements.getRandomElement(Elements.paramElement("homePage.Promo_Code_WithOffer", promocode_Name ));
                promocode_WithOffer = selectedElement2.getText();
                WebElement selectedElement3 = Elements.getRandomElement(Elements.paramElement("homePage.Promo_Code_Sale", promocode_WithOffer, promocode_Name ));
                promocode_Sale = selectedElement3.getText();
                calculatePromoCodeValues(promocode_WithOffer, promocode_Sale);
                Clicks.click( Elements.paramElement("homePage.Promo_Code_Selected_Product", promocode_WithOffer, promocode_Name, promocode_Sale ));
            }
        }
    }

    public static void calculatePromoCodeValues(String promocode_WithOffer, String promocode_Sale){
        try{
            String discountPercentage[] = promocode_Name.split(" ");
            discountPercentageValue =Integer.parseInt(discountPercentage[1].replace("%",""));
            String promocode_WithOfferArray[] = promocode_WithOffer.split("\\$");
            offerPrice= Float.parseFloat(promocode_WithOfferArray[1]);
            String promocode_SaleArray[] = promocode_Sale.split("\\$");
            salePrice= Float.parseFloat(promocode_SaleArray[1]);
            float calculatedOfferPrice = salePrice-((salePrice*discountPercentageValue)/100);
            Assert.assertTrue((calculatedOfferPrice>=offerPrice-.10 &&  calculatedOfferPrice<=offerPrice+.10));
        }catch(Exception e){
            Assert.fail("Could not match the offer price value on Browse page.");
        }
    }

    @When("^I should be quick view page with add to bag and add to list button$")
    public void i_should_be_quick_view_page_with_add_to_bag_and_add_to_list_button() throws Throwable {
        Wait.untilElementPresent("homePage.quick_view_add_to_bag");
        Assert.assertTrue(Elements.anyPresent("homePage.quick_view_add_to_bag"));
        Assert.assertTrue(Elements.anyPresent("homePage.quick_view_add_to_list"));
    }

}
