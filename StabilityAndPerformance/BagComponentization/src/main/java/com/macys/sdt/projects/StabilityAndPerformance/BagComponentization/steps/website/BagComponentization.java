package com.macys.sdt.projects.StabilityAndPerformance.BagComponentization.steps.website;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.model.Product;
import com.macys.sdt.framework.model.user.UserProfile;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.projects.StabilityAndPerformance.BagComponentization.utils.website.GenericUtils;
import com.macys.sdt.projects.StabilityAndPerformance.BagComponentization.utils.website.QuickbagProduct;
import com.macys.sdt.shared.actions.website.mcom.pages.home.Home;
import com.macys.sdt.shared.steps.website.MyAccountSteps;
import com.macys.sdt.shared.steps.website.PageNavigation;
import com.macys.sdt.shared.steps.website.Registry;
import com.macys.sdt.shared.utils.CommonUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.macys.sdt.shared.actions.website.mcom.pages.shop_and_browse.ShoppingBag.*;

public class BagComponentization extends StepUtils {
    ListMultimap<String, String> product_details = ArrayListMultimap.create();
    Map<String, String> prodDetailsInPDP = new HashMap<String, String>();
    PageNavigation page = new PageNavigation();
    String domain_url;

    /**
     * @param pageType
     */
    @Then("^I should see \"([^\"]*)\" pages$")
    public void I_should_see(String pageType) {
        switch (pageType) {
            case "slp":
                Assert.assertTrue("Not in SLP page", Elements.elementPresent("search_result.product_count_breadcrumb"));
                break;
            case "PDP":
                Assert.assertTrue("Not in PDP page", Elements.elementPresent("product_display.add_to_bag_button"));
                break;
        }
    }

    /**
     * Select random product thumbnail in browse(or SLP) page
     */
    @When("^I select a random product in slp page$")
    public void I_select_a_random_product() {
        GenericUtils.clickRandomProductThumbnail("product_thumbnails.thumbnail_container");
    }

    /**
     * Add the product to bag. Prior adding it, collect product title/size/color
     * in the map
     */
    @When("^I add the product to bag$")
    public void I_add_the_product_to_bag() {
        /*
        if (Elements.elementPresent("product_display.product_title_pdp")) {
            prodDetailsInPDP.put("productTitleInPDP",
                    GenericUtils.selectedProductTitleElementInPDP("product_display.product_title_pdp").getText());
            Clicks.click(GenericUtils.selectedProductTitleElementInPDP("product_display.product_title_pdp"));
        }
        if (Elements.elementPresent("product_display.total_color_swatches")) {
            Elements.getRandomElement("product_display.total_color_swatches").click();
            prodDetailsInPDP.put("selectedColorInPDP",
                    Elements.getElementAttribute("product_display.default_color_selected", "data-title"));
        }
        if (Elements.elementPresent("product_display.size_section")) {
            if (Elements.elementPresent("product_display.select_size_dropdown")) {
                GenericUtils.selectRandomSizeInPDPPage("product_display.select_size_dropdown");
                prodDetailsInPDP.put("selectedSizeInPDP",
                        GenericUtils.selectedSizeInPDPPage("product_display.select_size_dropdown"));
            } else {
                Elements.getRandomElement("product_display.total_size_swatches").click();
                prodDetailsInPDP.put("selectedSizeInPDP",
                        Elements.getElementAttribute("product_display.default_size_selected", "data-title"));
            }
        }
        try {
            GenericUtils.clickAddToBagButtonInPDPPage();
        } catch (Exception e) {
            System.out.println("Unable to click on 'Add To Bag' button");
            e.printStackTrace();
        }
        */
        if (Elements.elementPresent("product_display.product_title_pdp")) {
            prodDetailsInPDP.put("productTitleInPDP",
                    GenericUtils.selectedProductTitleElementInPDP("product_display.product_title_pdp").getText());
            Clicks.click(GenericUtils.selectedProductTitleElementInPDP("product_display.product_title_pdp"));
        }
        String mode = product_details.get("mode").get(0);
        String color = GenericUtils.selectRandomColor(mode);
        String size = GenericUtils.selectRandomSize(mode);
        prodDetailsInPDP.put("selectedColorInPDP",color);
        prodDetailsInPDP.put("selectedSizeInPDP", size);
        product_details.put("product_size", size);
        product_details.put("product_color", color);
        try {
            GenericUtils.clickAddToBagButtonInPDPPage();
        } catch (Exception e) {
            System.out.println("Unable to click on 'Add To Bag' button");
            e.printStackTrace();
        }
    }

    /**
     *
     */
    @And("^I click on Macys logo$")
    public void I_click_on_Macys_logo() {
        try {
            Clicks.click("header.logo");
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            System.out.println("Unable to click on Macy's home page logo");
        }
    }

    /**
     * Compare product title/size/color against with the map created in PDP page
     */
    @Then("^I should see the added product item details in quickbag$")
    public void I_should_see_the_added_product_item_details_in_quickbag() {
        HashMap<String, String> prodDetailsInQuickBag = new HashMap<>();
        WebElement hoverQB = null;
        try {
            hoverQB = Elements.findElement("quick_bag.quickbag_header");
        } catch (Exception e) {
            System.out.println("Unable to hover on quick bag header");
            e.printStackTrace();
        }
        Clicks.hoverForSelection(hoverQB);
        if (Elements.elementPresent("quick_bag.quickbag_header")) {
            List<WebElement> webEl = null;
            try {
                webEl = Elements.findElements("quick_bag.quickbag_item_names");
            } catch (Exception e) {
                Assert.fail("Unable to take quick bag item title or not visible in quick bag header");
                e.printStackTrace();
            }
            if (webEl != null) {
                for (WebElement items : webEl) {
                    String qbTitle = items.findElement(By.tagName("a")).getText();
                    if (qbTitle.contains(prodDetailsInPDP.get("productTitleInPDP"))) {
                        prodDetailsInQuickBag.put("productTitleInQuickBag", qbTitle);
                        List<WebElement> colorSize = null;
                        colorSize = items.findElements(By.className("sizeColorType"));
                        if (colorSize != null) {
                            for (WebElement colorSizeWebEl : colorSize) {
                                String colorSizeString = colorSizeWebEl.getText().trim().replaceAll(",", "");
                                System.out.println(colorSizeString);
                                if (prodDetailsInPDP.get("selectedColorInPDP").matches(colorSizeString)) {
                                    prodDetailsInQuickBag.put("selectedColorInQuickBag", colorSizeString);
                                    Assert.assertTrue("Mismatch in color. Please verify manually",
                                            prodDetailsInQuickBag.get("selectedColorInQuickBag")
                                                    .equalsIgnoreCase(prodDetailsInPDP.get("selectedColorInPDP")));
                                }
                                if (prodDetailsInPDP.get("selectedSizeInPDP").matches(colorSizeString)) {
                                    prodDetailsInQuickBag.put("selectedSizeInQuickBag", colorSizeString);
                                    Assert.assertTrue("Mismatch in size. Please verify manually",
                                            prodDetailsInQuickBag.get("selectedSizeInQuickBag")
                                                    .equalsIgnoreCase(prodDetailsInPDP.get("selectedSizeInPDP")));
                                    System.out.println(prodDetailsInQuickBag.get("productTitleInQuickBag") + " " + prodDetailsInQuickBag.get("selectedColorInQuickBag") + " " + prodDetailsInQuickBag.get("selectedSizeInQuickBag"));
                                    System.out.println(prodDetailsInPDP.get("productTitleInPDP") + " " + prodDetailsInPDP.get("selectedColorInPDP") + " " + prodDetailsInPDP.get("selectedSizeInPDP"));
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Given("^I visit the web site as a \"([^\"]*)\" user in \"([^\"]*)\" mode$")
    public void i_visit_the_web_site_as_a_user_in_mode(String user_type, String mode) throws Throwable {
        com.macys.sdt.shared.steps.website.Registry registryPage = new com.macys.sdt.shared.steps.website.Registry();
        switch (user_type) {
            case "guest":
                switch (mode) {
                    case "site":
                        page.I_visit_the_web_site_as_a_guest_user();
                        break;
                    case "iship":
                        page.I_visit_web_site_as_a_guest_user_in_mode(mode);
                        break;
                    case "registry":
                        page.I_visit_web_site_as_a_guest_user_in_mode(mode);
                        break;
                }
                break;
            case "registered":
                switch (mode) {
                    case "site":
                        page.I_visit_the_web_site_as_a_registered_user();
                        break;
                    case "registry":
                        registryPage.I_visit_the_web_site_as_a_registry_user();
                        break;
                }
        }
        product_details.put("mode",mode);
    }

    @When("^I navigate to PDP page of an \"([^\"]*)\" product$")
    public void i_navigate_to_PDP_page_of_an_product(String product_type) throws Throwable {
        switch (product_type) {
            case "normal":
            case "BOPS":
            case "REGISTRY":
                Home.selectRandomSubCategory();
                CommonUtils.selectRandomProduct(false, false);
                break;
            case "PWP":
                page.I_navigate_to_category_page("Jewelry");
                Clicks.click("left_navigation_category.subcategory");
                CommonUtils.selectRandomProduct(false, false);
                break;
            case "GWP":
                page.I_navigate_to_category_page("BEAUTY");
                Clicks.click("left_navigation_category.subcategory");
                CommonUtils.selectRandomProduct(false, false);
                if(CommonUtils.isMasterProduct()){
                    int mem_prod_count = Elements.findElements(By.className("memberUrlUpdate")).size();
                    int mem_prod_number = (int) (Math.random() * mem_prod_count);
                    Clicks.click(Elements.findElements(By.className("memberUrlUpdate")).get(mem_prod_number).findElement(By.tagName("img")));
                    Clicks.click("quick_view.goto_product_page");
                }
                break;
            case "VGC":
                if(macys() && Elements.elementPresent("header.gift_dropdown"))
                    Clicks.hoverForSelection("header.gift_dropdown");
                Wait.untilElementPresent("header.goto_gift_cards");
                Clicks.click("home.goto_gift_cards");
                int vgc_count = Elements.findElements(By.className("carouselListPoolThumbnails")).size();
                int prod_number = (int) (Math.random() * vgc_count);
                if (prod_number>4 && prod_number <9){
                    Elements.findElement("recently_viewed_items.scroll_right").click();
                    Clicks.click(Elements.findElements(By.className("carouselListPoolThumbnails")).get(prod_number));
                }else if(prod_number >8){
                    for(int i=0;i<2;i++){
                        Clicks.click("recently_viewed_items.scroll_right");
                    }
                    Clicks.click(Elements.findElements(By.className("carouselListPoolThumbnails")).get(prod_number));
                }else{
                    Clicks.click(Elements.findElements(By.className("carouselListPoolThumbnails")).get(prod_number));
                }
                break;
            case "EGC":
                if(macys() && Elements.elementPresent("header.gift_dropdown"))
                    Clicks.hoverForSelection("header.gift_dropdown");
                Wait.untilElementPresent("header.goto_gift_cards");
                Clicks.click("home.goto_gift_cards");
                Clicks.click(Elements.findElements(By.className("nav_cat_sub_3")).get(2).findElement(By.tagName("a")));
                WebElement element = Elements.findElements(By.tagName("iframe")).get(1);
                WebDriverManager.getWebDriver().switchTo().frame(element);
                Elements.findElement(By.xpath("//a[contains(text(),'DreamWorks Trolls Poppy')]")).click();
        }
    }

    @Then("^I verify the product details of an \"([^\"]*)\" product in PDP page$")
    public void i_verify_the_product_details_of_an_product_in_PDP_page(String product_type) throws Throwable {
        if (product_type.equalsIgnoreCase("VGC")) {
            String amount = String.valueOf(10 + (int) (Math.random() * 1000));
            String email = amount.concat("@gmail.com");
            TextBoxes.typeTextbox("product_display.vgc_amount", amount);
            TextBoxes.typeTextbox("product_display.vgc_email", email);
            product_details.put("product_price", amount);
            product_details.put("product_email", email);
        }else if(product_type.equalsIgnoreCase("EGC")){
            WebElement element = Elements.findElements(By.tagName("iframe")).get(1);
            WebDriverManager.getWebDriver().switchTo().frame(element);
            String amount = String.valueOf(10 + (int) (Math.random() * 1000));
            Elements.findElement("product_display_page.gift_card_amount").sendKeys(amount);
        }else {
            page.I_should_be_redirected_to_PDP_page();
            product_details.put("product_name", Elements.findElement(By.className("productTitle")).getText().replaceAll("\n"," "));
            String product_price;
            Assert.assertTrue(Elements.findElement("product_display.add_to_bag_button").isEnabled());
            Assert.assertTrue(Elements.findElement("product_display.product_id_div").isDisplayed());
            if (product_details.get("mode").contains("iship") || product_details.get("mode").contains("registry")) {
                Assert.assertTrue(Elements.findElement("product_display.product_title").isDisplayed());
                product_price = Elements.findElement("product_display.price").getText();
                if ((product_price.contains("Sale")) || product_price.contains("Now")) {
                    product_details.put("product_price", Elements.findElement("product_display.price_box").findElement(By.className("priceSale")).getText().replaceAll("[a-zA-Z ]", ""));
                } else {
                    product_details.put("product_price", Elements.findElement("product_display.price_box").getText().replaceAll("[a-zA-Z ]", ""));
                }
            } else {
                Assert.assertTrue(Elements.findElement("product_display.product_title_pdp").isDisplayed());
                product_price = Elements.findElement("product_display_page.pdp_price").getText();
                if (product_price.contains("Sale")) {
                    Elements.findElement("product_display_page.pdp_sale_price").getText();
                    product_details.put("product_price", Elements.findElement("product_display_page.pdp_sale_price").getText().split("Sale ")[1]);
                } else {
                    product_details.put("product_price", product_price);
                }
            }
            String product_id = Elements.findElement("product_display.product_id_div").getText().replaceAll("[a-zA-Z: ]", "");
            product_details.put("productId", product_id);
        }
        product_details.put("prod_type", product_type);
        product_details.put("quantity", "1");
    }

    @Then("^I verify the basic attributes of add to bag page or overlay for \"([^\"]*)\" product$")
    public void i_verify_the_basic_attributes_of_add_to_bag_page_or_overlay_for_product(String product_type) throws Throwable {
        if (onPage("add_to_bag")){
            if(product_type.equalsIgnoreCase("VGC")){
                Elements.findElement("add_to_bag.product_name").getText().isEmpty();
                product_details.get("product_price").contains(Elements.findElement("add_to_bag.line_item_price").getText().replaceAll("$",""));
                product_details.get("quantity").contains(Elements.findElement("add_to_bag.item_quantity"));
                product_details.get("product_price").contains(Elements.findElement("add_to_bag.sub_total_price").getText().replaceAll("$",""));
            }else{
                product_details.get("product_name").contains(Elements.findElement("add_to_bag.product_name").getText());
                if(Elements.findElement("add_to_bag.line_item_price").getText().contains("Sale")) {
                    product_details.get("product_price").contains(Elements.findElement("add_to_bag.line_item_price").findElement(By.className("bagLowestPrice")).getText().replace("Sale ", ""));
                }else{
                    product_details.get("product_price").contains(Elements.findElement("add_to_bag.line_item_price").getText());
                }
                Assert.assertTrue(Elements.findElement("add_to_bag.sub_total_price").isDisplayed());
            }
            Elements.findElement(By.className("productImage")).isDisplayed();
            Assert.assertTrue(Elements.findElement("add_to_bag.checkout").isEnabled());
            List<WebElement> upc_elements = Elements.findElements("add_to_bag.bag_product_color_size");
            java.util.Iterator<WebElement> upcs = upc_elements.iterator();
            while (upcs.hasNext()) {
                WebElement element = upcs.next();
                Assert.assertTrue(element.isDisplayed());
            }
        }else{
            product_details.get("product_name").contains(Elements.findElement("add_to_bag_dialog.item_description").getText());
            product_details.get("product_price").contains(Elements.findElement("add_to_bag_dialog.add_to_bag_price_dialog_atb").getText().replaceAll("[a-zA-Z: ]",""));
            product_details.get("product_color").contains(Elements.findElement("add_to_bag_dialog.atb_iteminfo_member").getText().split("\n")[1].split(", ")[0].split("Color: ")[1]);
            if(Elements.findElement("add_to_bag_dialog.atb_iteminfo_member").getText().split("\n")[1].contains("Size"))
                product_details.get("product_size").contains(Elements.findElement("add_to_bag_dialog.atb_iteminfo_member").getText().split("\n")[1].split(", ")[1].split("Size: ")[1]);
            product_details.get("product_price").contains(Elements.findElement("add_to_bag_dialog.sub_total_value").getText().replaceAll("[a-zA-Z: ]",""));
            Elements.findElement("add_to_bag_dialog.product_image").isDisplayed();
            Elements.findElement("add_to_bag_dialog.add_to_bag_checkout").isEnabled();
        }
    }

    @When("^I continue checkout to shopping bag page$")
    public void i_continue_checkout_to_shopping_bag_page() throws Throwable {
        if (onPage("add_to_bag")) {
            Clicks.click("add_to_bag.checkout");
        } else {
            Clicks.click("add_to_bag_dialog.add_to_bag_checkout");
        }
    }


    @Then("^I verify the basic attributes of shopping bag page for \"([^\"]*)\" product")
    public void i_verify_the_basic_attributes_of_shopping_bag_page_for_product(String product_type) throws Throwable {
        onPage("shopping_bag");
        List<Product> bagItems = getAllProductDetails();
        if (product_details.get("action").contains("remove")) {
            try {
                Assert.assertTrue(bagItems.size() == 0);
            } catch (Exception e) {
                System.out.println("Product not removed from bag");
                e.printStackTrace();
            }
            Home.selectRandomSubCategory();
            Clicks.hoverForSelection(By.id("itemInfo"));
            Elements.findElement("header.empty_quickbag").getText().equalsIgnoreCase("0 items in your bag. Shop great deals now!");
        } else {
            Elements.findElement(By.id("bagMerchandiseTotal")).isDisplayed();
            Assert.assertTrue(Elements.findElement("shopping_bag.continue_checkout_image").isEnabled());
            Home.selectRandomSubCategory();
            Clicks.hoverForSelection(By.id("itemInfo"));
            List<QuickbagProduct> quickbagProducts = GenericUtils.getAllQuickbagProductDetails();
            System.out.println(quickbagProducts);
            for (int i = 0; i < bagItems.size(); i++) {
                bagItems.get(i).name.toLowerCase().contains(quickbagProducts.get(i).name.toLowerCase());
                quickbagProducts.get(i).color_size.toLowerCase().contains(bagItems.get(i).name.toLowerCase());
                if (quickbagProducts.get(i).quantity == bagItems.get(i).quantity)
                    System.out.println("quantity of the product is same in quickbag and shopping bag");
                else
                    Assert.fail("quantity of the product is not same in quickbag and shopping bag");
                if (quickbagProducts.get(i).retail_price == bagItems.get(i).individualPrice)
                    System.out.println("Retail price is same in quickbag and shopping bag");
                else
                    Assert.fail("product price is not same between quickbag and shopping bag");
            }
        }
    }

    @When("^I update the \"([^\"]*)\" product related options in shopping bag page$")
    public void i_update_the_product_related_options_in_shopping_bag_page(String product_type) throws Throwable {
        List<Product> bagItems = getAllProductDetails();
        product_details.put("action","update");
        if (!(product_details).get("prod_type").contains("VGC")){
            for (int i = 0; i < bagItems.size(); i++) {
                if(product_details.get("product_name").get(i).contains(bagItems.get(i).name)){
                    int max_quantities = Elements.findElements("shopping_bag.item_qty_dropdown").get(i).findElements(By.tagName("option")).size();
                    try {
                        updateQuantity(bagItems.get(i),String.valueOf((int) (Math.random() * max_quantities)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @When("^I remove the \"([^\"]*)\" product in shopping bag page$")
    public void i_remove_the_product_in_shopping_bag_page(String product_type) throws Throwable {
        List<Product> bagItems = getAllProductDetails();
        product_details.put("action","remove");
        for(int i=0;i<product_details.get("productId").size();i++){
            if(Elements.findElements("shopping_bag.line_items").get(i).getText().contains(product_details.get("productId").get(i))){
                try {
                    removeItem(i);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    @When("^I create a new profile with given data$")
    public void i_create_a_new_profile_with_given_data() throws Throwable {
        Registry RegistryPage = new Registry();
        if (product_details.get("mode").contains("site")) {
            page.I_visit_the_web_site_as_a_registered_user();
        }else{
            RegistryPage.I_visit_the_web_site_as_a_registry_user();
        }
    }

    @Then("^I close and reopen browser$")
    public void i_close_and_reopen_the_browser() throws Throwable {
        WebDriverManager.resetDriver(true);
        WebDriverManager.getWebDriver();
    }

    @When("^I sign in during checkout for merge bag$")
    public void i_sign_in_during_checkout_for_merge_bag() throws Throwable {
        onPage("shopping_bag");
        Clicks.click("shopping_bag.continue_checkout_image");
        UserProfile customer = TestUsers.getCustomer(null);
        pausePageHangWatchDog();
        TextBoxes.typeTextbox("checkout_sign_in.email", customer.getUser().getProfileAddress().getEmail());
        TextBoxes.typeTextbox("checkout_sign_in.password", customer.getUser().getLoginCredentials().getPassword());
        Clicks.click("checkout_sign_in.checkout_button");
    }

    @When("^I add the product to a registry and continue shopping$")
    public void i_add_the_product_to_a_registry_and_continue_shopping() throws Throwable{
        Clicks.click(By.className("addToRegistryButton"));
        Clicks.click(By.className("continueShopping"));
    }

    @When("^I navigate to find registry page$")
    public void i_navigate_to_find_registry_page() throws Throwable{
        Clicks.click("header.goto_wedding_registry");
        Elements.findElement("registry_home.goto_find_registry").click();
    }

    @Then("^I click view registry in GVR page$")
    public void i_click_view_registry_in_GVR_page() throws Throwable{
        Clicks.click("find_registry.find_registry_results");
    }

    @When("^I add a registry product to the shopping bag$")
    public void i_add_a_registry_product_to_the_shopping_bag() throws Throwable{
        Clicks.click(By.className("atb"));
        Clicks.click(By.className("checkout"));
    }

    @Given("^I visit the web site as a guest user in desktop or mobile$")
    public void i_visit_the_web_site_as_a_guest_user_in_desktop_or_mobile() throws Throwable {
        String url = WebDriverManager.getCurrentUrl();
        Navigate.visit(RunConfig.url);
        if(url.matches(".*?m(2qa1)?\\.(qa[0-9][0-9]?code)?(macys|mcom|bcom|bloomingdales).*?")){
            closeMewTutorial();
        }else{
            MyAccountSteps.iClearAllTheCookies();
            closeBcomPopup();
        }
    }

    @When("^I_verify_the_macys_logo$")
    public void i_verify_the_macys_logo() throws Throwable{
        String url = WebDriverManager.getCurrentUrl();
        if(url.matches(".*?m(2qa1)?\\.(qa[0-9][0-9]?code)?(macys|mcom|bcom|bloomingdales).*?"))
            Elements.findElement(By.id("mb-j-header-image")).isEnabled();
        else
            Elements.findElement(By.id("macysHomePageLogo")).isEnabled();
    }
}