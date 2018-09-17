package com.macys.sdt.projects.PurchaseAndDelivery.Common.steps.website;

import com.macys.sdt.framework.exceptions.DataException;
import com.macys.sdt.framework.exceptions.ProductionException;
import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.model.addresses.CurrentAddress;
import com.macys.sdt.framework.model.registry.Registry;
import com.macys.sdt.framework.model.user.UserProfile;
import com.macys.sdt.framework.utils.*;
import com.macys.sdt.framework.utils.rest.services.*;
import com.macys.sdt.framework.utils.rest.utils.RESTOperations;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.navigation.Navigate;
import com.macys.sdt.framework.model.Product;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.shop_and_browse.ShoppingBag;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.utils.cookies.CookieManager;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.utils.cookies.CookieName;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.utils.cookies.MacysCookieMap;
import com.macys.sdt.shared.utils.CommonUtils;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import static com.macys.sdt.framework.utils.StepUtils.*;
import static com.macys.sdt.framework.utils.Utils.getResourceFile;

public class ServiceSteps {

    private static JSONObject json = null;
    private static Product product = null;

    private static final Logger logger = LoggerFactory.getLogger(ServiceSteps.class);

    @Given("^I visit the website as a (guest|registered|gvr|bvr) user using rest services$")
    public static void iVisitTheWebsiteAsAGuestUserUsingRestServices(String userType) throws Throwable {
        Navigate.visit("sign_in");
        pausePageHangWatchDog();
        TestUsers.clearCustomer();
        HashMap<String, String> options = new HashMap<>();
        options.put("checkout_eligible", "true");
        options.put("paypal_eligible", "true");
        UserProfile profile = TestUsers.getCustomer(options);
        if (!userType.equalsIgnoreCase("guest")) {
            UserProfileService.createUserProfile(profile, true);
            if (!userType.equalsIgnoreCase("gvr")) {
                CreditCardService.addCreditCardToWallet(CreditCards.getValidCard("Visa"), true);
            }
        }
        if (userType.equalsIgnoreCase("registered")) {
            CommonUtils.signInToCreatedProfile();
        }
        if (userType.equalsIgnoreCase("gvr") || userType.equalsIgnoreCase("bvr")) {
            CurrentAddress address = new CurrentAddress();
            TestUsers.getRandomValidAddress(options, address);
            String secureToken = TestUsers.getCustomer(null).getUser().getTokenCredentials().getToken();
            int retryCount = 0;
            while (retryCount < 7) {
                try {
                    if (Elements.findElements(By.className("container-close"), WebElement::isDisplayed).size() > 0) {
                        Clicks.click(Elements.findElements(By.className("container-close"), WebElement::isDisplayed).get(0));
                    }
                    Registry registry = new Registry();
                    registry.addRandomData();
                    registry.getContactInfo().setCurrentAddress(address);
                    registry.setCurrentAddress(address);
                    registry = RegistryService.createRegistry(registry, secureToken);
                    if (registry.getId() != null) {
                        break;
                    }
                } catch (AssertionError e) {
                    System.out.println("Retrying again...");
                    retryCount++;
                }
            }
            Assert.assertNotNull("Failed to create registry", TestUsers.getCustomer(null).getRegistry().getId());
            if (userType.equalsIgnoreCase("bvr"))
                CommonUtils.signInToCreatedProfile();
        }
        resumePageHangWatchDog();
        logger.info("Visited website as " + userType + " user using rest services");
    }

    @When("^I add an? (?:\"(.*?)\" )?product to my bag using rest service(?: that is not(?: an?)? \"(.*?)\")?$")
    public static void iAddAProductToMyBagUsingRestService(String productTrue, String productFalse) throws ProductionException {
        HashMap<String, Object> opts = new HashMap<>();
        opts.putAll(CommonUtils.extractOptions(productTrue, productFalse));
        product = TestUsers.getRandomProduct(opts);
        Assert.assertNotNull("ERROR - DATA:  No " + productTrue + " product" + (productFalse != null ? " that is not " + productFalse : "") + " was found in products list.", product);
        CookieManager cookieManager = new CookieManager();
        String userId = cookieManager.getCookieNamed(CookieName.ONLINE_UID).getValue();
        AddToBagService.addToBag(product, userId);
        if (product.bopsAvailable) {
            cookieManager.macysCookieMap(CookieName.MISCGCS).
                    set(MacysCookieMap.Key.BOPS_PICKUP_STORE, String.valueOf(product.storeLocationNum));
        }
        Navigate.to(ShoppingBag.class);
    }

    @Given("^I visit the website as a iship user from \"([^\"]*)\" with currency \"([^\"]*)\" using rest services$")
    public void iVisitTheWebsiteAsAIshipUserFromWithCurrencyUsingRestServices(String country, String currency) {
        Navigate.visit("sign_in");
        Cookies.editCookie("shippingCountry", Cookies.getCookieValue("shippingCountry"), country);
        Cookies.addCookie("currency", currency);
    }

    @When("^I check for bops item availability")
    public void iCheckForBopsItemAvailability() {
        if (Wait.secondsUntilElementPresent("responsive_checkout.error_container", 10) && Elements.elementPresent("responsive_checkout.item_level_error")) {
            String errorMessage = Elements.getText("responsive_checkout.error_container");
            setAvailability("bops", errorMessage);
            Assert.fail("ERROR - DATA: Unable to checkout your added product due to Product Unavailability Issue. " + errorMessage);
        }
    }

    public void setAvailability(String item, String errorMessage) {
        if (json != null) {
            JSONArray products = json.getJSONArray(macys() ? "macys" : "bloomingdales");
            for (int i = 0; i < products.length(); i++) {
                JSONObject productJson = products.getJSONObject(i);
                if (productJson.get("id").toString().equals(Integer.toString(product.id)) && (errorMessage == null || errorMessage.contains(productJson.getString("name")))) {
                    String attrName = item.equals("sdd") ? "sdd_ineligible" : "mst_unavailable";
                    logger.info("Setting " + attrName + " attribute to true for the product: " + productJson.get("id"));
                    json.getJSONArray(macys() ? "macys" : "bloomingdales").getJSONObject(i).put(attrName, true);
                    break;
                }
            }
        }
    }

    @When("^I add an (available_sdd|available_bops|available_bops and registrable) product to bag$")
    public void iAddAnProductThatIsNot(String productTrue) throws ProductionException, IOException {
        String userId = "";
        Product selectedProduct = null;
        String attrName = productTrue.equals("available_sdd") ? "sdd_ineligible" : "mst_unavailable";
        if (json == null) {
            File bopsData = getResourceFile("bops_products.json");
            json = new JSONObject(Utils.readTextFile(bopsData));
        }
        JSONArray products = json.getJSONArray(macys() ? "macys" : "bloomingdales");
        for (int i = 0; i < products.length(); i++) {
            try {
                JSONObject productJson = products.getJSONObject(i);
                String productId = productJson.get("id").toString();
                if (productTrue.equals("available_sdd") ? !productJson.getBoolean("sdd_ineligible") : !productJson.getBoolean("mst_unavailable")) {
                    boolean found = ProductService.checkoutAvailability(productId);
                    if (found) {
                        selectedProduct = new Product(productJson);
                        if (!productTrue.equals("available_sdd")) {
                            selectedProduct.bopsAvailable = true;
                            if (productTrue.equals("available_bops and registrable")) {
                                selectedProduct.registryItem = true;
                            }
                        }
                        logger.info("found product id : " + productId);
                        CookieManager cookieManager = new CookieManager();
                        userId = cookieManager.getCookieNamed(CookieName.ONLINE_UID).getValue();
                        AddToBagService.addToBag(selectedProduct, userId);
                        if (!productTrue.equals("available_sdd")) {
                            Cookies.deleteCookie("MISCGCs");
                            Cookies.addCookie("MISCGCs", "BOPSPICKUPSTORE1_92_"+String.valueOf(selectedProduct.storeLocationNum));
                        }
                        Navigate.to(ShoppingBag.class);
                        break;
                    } else {
                        json.getJSONArray(macys() ? "macys" : "bloomingdales").getJSONObject(i).put(attrName, true);
                    }
                }
            } catch (DataException e) {
                logger.info("WARN: Selected product is not eligible for pick up.");
                selectedProduct = null;
                json.getJSONArray(macys() ? "macys" : "bloomingdales").getJSONObject(i).put(attrName, true);
                Response response = RESTOperations.doDELETE("http://" + EnvironmentDetails.otherApp("MSPOrder").ipAddress + ":8080/api/order/v1/bags/items/3?userId=" + userId, null);
                if (response.getStatus() != 200) {
                    logger.info("WARN: Failed to remove item from shopping bag page");
                }
            }
        }
        if (selectedProduct == null) {
            Assert.fail("No available product found");
        }
        product = selectedProduct;
    }

}